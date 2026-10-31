from pathlib import Path
from urllib.parse import quote
import os
import re
import subprocess
from datetime import datetime, timedelta
from zoneinfo import ZoneInfo
from html import escape

ROOT = Path(__file__).resolve().parents[2]
README = ROOT / "README.md"

START = "<!-- AUTO-README:START -->"
END = "<!-- AUTO-README:END -->"

IGNORED_DIRS = {
    ".git", ".github", ".idea", "node_modules", "target",
    "build", "out", "dist", "__pycache__",
}


def git_output(*args):
    return subprocess.check_output(
        ["git", *args], cwd=ROOT, text=True, stderr=subprocess.DEVNULL
    ).strip()


def contains_java(folder):
    for path in folder.rglob("*.java"):
        if any(
            part in IGNORED_DIRS or part.startswith(".")
            for part in path.relative_to(folder).parts[:-1]
        ):
            continue
        return True
    return False


def display_name(folder_name):
    # Remove a leading LeetCode/problem number, e.g. "121-best-time" -> "best time".
    name = re.sub(r"^\d+-", "", folder_name)
    return name.replace("-", " ").replace("_", " ").title()


def get_problem_folders():
    folders = [
        folder for folder in ROOT.iterdir()
        if folder.is_dir()
        and not folder.name.startswith(".")
        and folder.name not in IGNORED_DIRS
        and contains_java(folder)
    ]
    return sorted(folders, key=lambda folder: folder.name.lower())


def get_latest_problem_commit():
    return git_output("log", "-1", "--format=%H", "--", "*.java")



def get_repo_active_dates():
    """Return commit-active dates for this repository, using India time."""
    output = git_output("log", "--format=%cI")
    india = ZoneInfo("Asia/Kolkata")
    dates = set()

    for timestamp in output.splitlines():
        if timestamp.strip():
            commit_time = datetime.fromisoformat(timestamp.strip())
            dates.add(commit_time.astimezone(india).date())

    return dates


def calculate_streaks(active_dates):
    if not active_dates:
        return 0, 0, None

    today = datetime.now(ZoneInfo("Asia/Kolkata")).date()
    anchor = today if today in active_dates else today - timedelta(days=1)

    current = 0
    day = anchor
    while day in active_dates:
        current += 1
        day -= timedelta(days=1)

    longest = 0
    run = 0
    previous = None
    for day in sorted(active_dates):
        run = run + 1 if previous and day == previous + timedelta(days=1) else 1
        longest = max(longest, run)
        previous = day

    return current, longest, max(active_dates)


def generate_repo_streak_svg():
    active_dates = get_repo_active_dates()
    current, longest, latest = calculate_streaks(active_dates)
    total_days = len(active_dates)
    latest_text = latest.strftime("%d %b %Y") if latest else "No commits yet"

    # Tokyo Night-inspired layout, styled like the familiar GitHub streak card.
    svg = f"""<svg xmlns="http://www.w3.org/2000/svg" width="900" height="300" viewBox="0 0 900 300" role="img" aria-labelledby="title desc">
<title id="title">Dsa_Java GitHub streak statistics</title>
<desc id="desc">Repository-only statistics: {total_days} active commit days, a current streak of {current} days, and a longest streak of {longest} days. Latest activity: {escape(latest_text)}.</desc>
<defs>
  <linearGradient id="bg" x1="0" y1="0" x2="1" y2="1">
    <stop offset="0%" stop-color="#1a1b27"/>
    <stop offset="100%" stop-color="#24283b"/>
  </linearGradient>
  <linearGradient id="accent" x1="0" y1="0" x2="1" y2="0">
    <stop offset="0%" stop-color="#7aa2f7"/>
    <stop offset="100%" stop-color="#bb9af7"/>
  </linearGradient>
  <filter id="glow" x="-50%" y="-50%" width="200%" height="200%">
    <feGaussianBlur stdDeviation="5" result="blur"/>
    <feMerge><feMergeNode in="blur"/><feMergeNode in="SourceGraphic"/></feMerge>
  </filter>
</defs>
<rect x="1" y="1" width="898" height="298" rx="18" fill="url(#bg)" stroke="#414868" stroke-width="2"/>
<rect x="28" y="24" width="5" height="34" rx="2.5" fill="url(#accent)"/>
<text x="49" y="43" fill="#c0caf5" font-family="Segoe UI,Arial,sans-serif" font-size="21" font-weight="700" letter-spacing="1">GITHUB STREAK STATS</text>
<text x="49" y="65" fill="#787c99" font-family="Segoe UI,Arial,sans-serif" font-size="12">DSA_JAVA · REPOSITORY ACTIVITY ONLY</text>
<rect x="704" y="30" width="164" height="28" rx="14" fill="#292e42" stroke="#414868"/>
<circle cx="720" cy="44" r="4" fill="#9ece6a"/>
<text x="732" y="48" fill="#a9b1d6" font-family="Segoe UI,Arial,sans-serif" font-size="11" font-weight="600">REPO TRACKING</text>
<line x1="28" y1="86" x2="872" y2="86" stroke="#414868" stroke-width="1"/>
<g text-anchor="middle" font-family="Segoe UI,Arial,sans-serif">
  <rect x="28" y="104" width="264" height="142" rx="12" fill="#1f2335" stroke="#414868"/>
  <rect x="318" y="104" width="264" height="142" rx="12" fill="#1f2335" stroke="#414868"/>
  <rect x="608" y="104" width="264" height="142" rx="12" fill="#1f2335" stroke="#414868"/>
  <g transform="translate(150 128)" filter="url(#glow)">
    <path d="M0 -15 C-13 -2 -17 7 -10 17 C-5 24 7 24 13 16 C20 6 9 -5 5 -14 C4 -6 1 -2 -2 1 C-4 -5 -2 -10 0 -15Z" fill="#e0af68"/>
  </g>
  <text x="160" y="183" fill="#7aa2f7" font-size="39" font-weight="700">{total_days}</text>
  <text x="160" y="211" fill="#c0caf5" font-size="15" font-weight="600">TOTAL ACTIVE DAYS</text>
  <text x="160" y="230" fill="#787c99" font-size="11">Days with at least one commit</text>
  <g transform="translate(450 128)">
    <path d="M0 -15 C-13 -2 -17 7 -10 17 C-5 24 7 24 13 16 C20 6 9 -5 5 -14 C4 -6 1 -2 -2 1 C-4 -5 -2 -10 0 -15Z" fill="#f7768e"/>
  </g>
  <text x="450" y="183" fill="#f7768e" font-size="39" font-weight="700">{current}</text>
  <text x="450" y="211" fill="#c0caf5" font-size="15" font-weight="600">CURRENT STREAK</text>
  <text x="450" y="230" fill="#787c99" font-size="11">Consecutive active days</text>
  <g transform="translate(740 128)">
    <path d="M-13 15 L0 -15 L13 15 Z" fill="none" stroke="#bb9af7" stroke-width="3" stroke-linejoin="round"/>
    <path d="M0 -7 L0 5" stroke="#bb9af7" stroke-width="3" stroke-linecap="round"/>
    <circle cx="0" cy="10" r="1.8" fill="#bb9af7"/>
  </g>
  <text x="740" y="183" fill="#bb9af7" font-size="39" font-weight="700">{longest}</text>
  <text x="740" y="211" fill="#c0caf5" font-size="15" font-weight="600">LONGEST STREAK</text>
  <text x="740" y="230" fill="#787c99" font-size="11">Best consecutive-day run</text>
</g>
<line x1="28" y1="263" x2="872" y2="263" stroke="#414868" stroke-width="1"/>
<text x="32" y="283" fill="#9ece6a" font-family="Segoe UI,Arial,sans-serif" font-size="12">●</text>
<text x="48" y="283" fill="#a9b1d6" font-family="Segoe UI,Arial,sans-serif" font-size="12">Latest repository commit: {escape(latest_text)}</text>
<text x="868" y="283" text-anchor="end" fill="#787c99" font-family="Segoe UI,Arial,sans-serif" font-size="11">Calculated in IST · Dsa_Java only</text>
</svg>
"""
    (ROOT / "repo-streak-v3.svg").write_text(svg, encoding="utf-8")
    print("Repository-specific streak card generated.")

def generate_section():
    folders = get_problem_folders()
    repository = os.environ.get("GITHUB_REPOSITORY", "Chakaliprakash/Dsa_Java")

    lines = [
        "## 📚 Automatically Updated Problems",
        "",
        f"**Total problem folders:** {len(folders)}",
        "",
        "<!-- Problem links are generated from folders containing Java files. -->",
        "",
    ]

    if folders:
        for folder in folders:
            name = display_name(folder.name)
            url = quote(folder.name, safe="-_.")
            lines.append(f"- [{name}](./{url})")
    else:
        lines.append("_No Java problem folders found yet._")

    problem_commit = get_latest_problem_commit()
    changed_java_files = git_output(
        "diff-tree", "--no-commit-id", "--name-only", "-r",
        problem_commit, "--", "*.java"
    ).splitlines()

    problem_folder = None
    for file_name in changed_java_files:
        parts = Path(file_name).parts
        if len(parts) > 1 and (ROOT / parts[0]).is_dir():
            problem_folder = parts[0]
            break

    short_hash = git_output("rev-parse", "--short", problem_commit)
    commit_subject = git_output(
        "show", "-s", "--format=%s", problem_commit
    ).replace("|", "\\|").replace(chr(10), " ")
    commit_date = git_output("show", "-s", "--format=%cI", problem_commit)

    lines.extend([
        "",
        "## 🔄 Latest Commit",
        "",
        f"- **Commit:** [`{short_hash}`](https://github.com/{repository}/commit/{problem_commit})",
    ])

    if problem_folder:
        problem_name = display_name(problem_folder)
        folder_url = quote(problem_folder, safe="-_.")
        lines.append(f"- **Problem:** [{problem_name}](./{folder_url})")

    lines.extend([
        f"- **Message:** {commit_subject}",
        f"- **Date:** {commit_date}",
    ])

    return chr(10).join(lines)


def main():
    generate_repo_streak_svg()

    if not README.exists():
        raise FileNotFoundError("README.md was not found.")

    content = README.read_text(encoding="utf-8")

    if content.count(START) != 1 or content.count(END) != 1:
        raise ValueError(
            "Add exactly one AUTO-README START marker and one END marker "
            "to README.md before enabling this workflow."
        )

    start_index = content.index(START) + len(START)
    end_index = content.index(END)

    if start_index > end_index:
        raise ValueError("README markers are in the wrong order.")

    generated = chr(10) * 2 + generate_section() + chr(10) * 2
    updated = content[:start_index] + generated + content[end_index:]

    if updated != content:
        README.write_text(updated, encoding="utf-8")
        print("README updated successfully.")
    else:
        print("README is already up to date.")


if __name__ == "__main__":
    main()
