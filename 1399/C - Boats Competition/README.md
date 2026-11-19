<h2><a href="https://codeforces.com/contest/1399/problem/C" target="_blank" rel="noopener noreferrer">1399C — Boats Competition</a></h2>

| | |
|---|---|
| **Difficulty** | 1200 |
| **Language** | Java 21 |
| **Verdict** | ✅ Accepted |
| **Problem Link** | [Codeforces 1399C](https://codeforces.com/contest/1399/problem/C) |

## Topics
`brute force` `greedy` `two pointers`

---

## Problem Statement

<div class="header"><div class="title">C. Boats Competition</div><div class="time-limit"><div class="property-title">time limit per test</div>2 seconds</div><div class="memory-limit"><div class="property-title">memory limit per test</div>256 megabytes</div><div class="input-file input-standard"><div class="property-title">input</div>standard input</div><div class="output-file output-standard"><div class="property-title">output</div>standard output</div></div><div><p>There are $$$n$$$ people who want to participate in a boat competition. The weight of the $$$i$$$-th participant is $$$w_i$$$. Only teams consisting of <span class="tex-font-style-bf">two</span> people can participate in this competition. As an organizer, you think that it's fair to allow only teams with <span class="tex-font-style-bf">the same total weight</span>.</p><p>So, if there are $$$k$$$ teams $$$(a_1, b_1)$$$, $$$(a_2, b_2)$$$, $$$\dots$$$, $$$(a_k, b_k)$$$, where $$$a_i$$$ is the weight of the first participant of the $$$i$$$-th team and $$$b_i$$$ is the weight of the second participant of the $$$i$$$-th team, then the condition $$$a_1 + b_1 = a_2 + b_2 = \dots = a_k + b_k = s$$$, where $$$s$$$ is the total weight of <span class="tex-font-style-bf">each</span> team, should be satisfied.</p><p>Your task is to choose such $$$s$$$ that the number of teams people can create is the <span class="tex-font-style-bf">maximum</span> possible. Note that each participant can be in <span class="tex-font-style-bf">no more than one</span> team.</p><p>You have to answer $$$t$$$ independent test cases.</p></div><div class="input-specification"><div class="section-title">Input</div><p>The first line of the input contains one integer $$$t$$$ ($$$1 \le t \le 1000$$$) — the number of test cases. Then $$$t$$$ test cases follow.</p><p>The first line of the test case contains one integer $$$n$$$ ($$$1 \le n \le 50$$$) — the number of participants. The second line of the test case contains $$$n$$$ integers $$$w_1, w_2, \dots, w_n$$$ ($$$1 \le w_i \le n$$$), where $$$w_i$$$ is the weight of the $$$i$$$-th participant.</p></div><div class="output-specification"><div class="section-title">Output</div><p>For each test case, print one integer $$$k$$$: the <span class="tex-font-style-bf">maximum</span> number of teams people can compose with the total weight $$$s$$$, if you choose $$$s$$$ optimally.</p></div><div class="sample-tests"><div class="section-title">Example</div><div class="sample-test"><div class="input"><div class="title">Input<div title="Copy" data-clipboard-target="#id0011472140946132292" id="id005984682237321711" class="input-output-copier">Copy</div></div><pre id="id0011472140946132292">5
5
1 2 3 4 5
8
6 6 6 6 6 6 8 8
8
1 2 2 1 2 1 1 2
3
1 3 3
6
1 1 3 4 2 2
</pre></div><div class="output"><div class="title">Output<div title="Copy" data-clipboard-target="#id008700551894959275" id="id006941930264954196" class="input-output-copier">Copy</div></div><pre id="id008700551894959275">2
3
4
1
2
</pre></div></div></div><div class="note"><div class="section-title">Note</div><p>In the first test case of the example, we can reach the optimal answer for $$$s=6$$$. Then the first boat is used by participants $$$1$$$ and $$$5$$$ and the second boat is used by participants $$$2$$$ and $$$4$$$ (indices are the same as weights).</p><p>In the second test case of the example, we can reach the optimal answer for $$$s=12$$$. Then first $$$6$$$ participants can form $$$3$$$ pairs.</p><p>In the third test case of the example, we can reach the optimal answer for $$$s=3$$$. The answer is $$$4$$$ because we have $$$4$$$ participants with weight $$$1$$$ and $$$4$$$ participants with weight $$$2$$$.</p><p>In the fourth test case of the example, we can reach the optimal answer for $$$s=4$$$ or $$$s=6$$$.</p><p>In the fifth test case of the example, we can reach the optimal answer for $$$s=3$$$. Note that participant with weight $$$3$$$ can't use the boat because there is no suitable pair for him in the list.</p></div>