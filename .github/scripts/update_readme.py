from pathlib import Path
from urllib.parse import quote
import os
import re
import subprocess

ROOT = Path(__file__).resolve().parents[2]
README = ROOT / "README.md"

START = "<!-- AUTO-README:START -->"
END = "<!-- AUTO-README:END -->"

IGNORED_DIRS = {
    ".git",
    ".github",
    ".idea",
    "node_modules",
    "target",
    "build",
    "out",
    "dist",
    "__pycache__",
}


def git_output(*args):
    return subprocess.check_output(
        ["git", *args],
        cwd=ROOT,
        text=True,
        stderr=subprocess.DEVNULL,
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
    name = re.sub(r"^\\d+-", "", folder_name)
    return name.replace("-", " ").replace("_", " ").title()


def get_problem_folders():
    folders = [
        folder
        for folder in ROOT.iterdir()
        if folder.is_dir()
        and not folder.name.startswith(".")
        and folder.name not in IGNORED_DIRS
        and contains_java(folder)
    ]
    return sorted(folders, key=lambda folder: folder.name.lower())


def get_latest_problem_commit():
    return git_output("log", "-1", "--format=%H", "--", "*.java")


def generate_section():
    folders = get_problem_folders()
    repository = os.environ.get(
        "GITHUB_REPOSITORY",
        "Chakaliprakash/Dsa_Java",
    )

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
    ).replace("|", "\\|").replace("\\n", " ")
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
        lines.append(
            f"- **Problem:** [{problem_name}](./{folder_url})"
        )

    lines.extend([
        f"- **Message:** {commit_subject}",
        f"- **Date:** {commit_date}",
    ])

    return "\\n".join(lines)


def main():
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

    generated = "\\n\\n" + generate_section() + "\\n\\n"
    updated = content[:start_index] + generated + content[end_index:]

    if updated != content:
        README.write_text(updated, encoding="utf-8")
        print("README updated successfully.")
    else:
        print("README is already up to date.")


if __name__ == "__main__":
    main()
