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

    svg = f"""<svg xmlns="http://www.w3.org/2000/svg" width="760" height="230" viewBox="0 0 760 230" role="img" aria-labelledby="title desc">
<title id="title">Dsa_Java repository commit streak</title>
<desc id="desc">{total_days} active days, current streak {current} days, longest streak {longest} days. Latest activity: {escape(latest_text)}.</desc>
<rect width="760" height="230" rx="14" fill="#1a1b27"/>
<text x="34" y="38" fill="#a9b1d6" font-family="Segoe UI,Arial,sans-serif" font-size="17" font-weight="600">DSA_JAVA REPOSITORY STREAK</text>
<line x1="253" y1="65" x2="253" y2="175" stroke="#565a73" stroke-width="1"/>
<line x1="507" y1="65" x2="507" y2="175" stroke="#565a73" stroke-width="1"/>
<g text-anchor="middle" font-family="Segoe UI,Arial,sans-serif">
  <text x="126" y="112" fill="#7aa2f7" font-size="43" font-weight="700">{total_days}</text>
  <text x="126" y="145" fill="#c0caf5" font-size="17">Active Days</text>
  <text x="380" y="112" fill="#bb9af7" font-size="43" font-weight="700">{current}</text>
  <text x="380" y="145" fill="#c0caf5" font-size="17">Current Streak</text>
  <text x="634" y="112" fill="#7aa2f7" font-size="43" font-weight="700">{longest}</text>
  <text x="634" y="145" fill="#c0caf5" font-size="17">Longest Streak</text>
</g>
<text x="380" y="195" fill="#73daca" font-family="Segoe UI,Arial,sans-serif" font-size="13" text-anchor="middle">Latest repository activity: {escape(latest_text)} · Calculated in IST</text>
</svg>
"""
    (ROOT / "repo-streak.svg").write_text(svg, encoding="utf-8")
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
