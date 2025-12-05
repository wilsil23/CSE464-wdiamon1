Project Part 3 — Refactoring + Design Patterns
Author: William Diamond
Course: CSE 464

Overview

This project refactors the original graph processing system to improve modularity, readability, testability, and maintainability.
Five required refactorings were completed, and two major design patterns were implemented:

Below are the five refactors, their justification, what changes were made, and their GitHub commit links.

Refactor #1 — Replace raw String[] edges with Edge class

Explanation: 
The original graph stored edges as String[], which was error-prone, unclear, and made the code hard to maintain. Creating a dedicated Edge class improves readability and ensures each edge has well-defined structure.

Changes Made:
Added Edge class with src and dst fields.
Replaced all List<String[]> usages with List<Edge>.
Updated add/remove logic to use Edge objects.

GitHub Commit:
https://github.com/wilsil23/CSE464-wdiamon1/commit/a441de403c57e2d523dc2770182f999f7c53d42f

Refactor #2 — Add defensive copies for getters

Explanation:
getNodes() and getEdges() exposed internal collections directly, allowing external code to mutate graph structure unintentionally. This violated encapsulation.

Changes Made:
Updated getters to return defensive copies:
public Set<String> getNodes() { return new HashSet<>(nodes); }
public List<Edge> getEdges() { return new ArrayList<>(edges); }
Removed all direct reliance on internal collections outside the class.

GitHub Commit:
https://github.com/wilsil23/CSE464-wdiamon1/commit/22991da74402d2b786aeca1bb0b51225aa689c5b

Refactor #3 — Extract parsing into GraphParser

Explanation:
The Graph class was responsible not only for maintaining nodes and edges, but also for parsing .dot files. Parsing is a separate responsibility and should be external.

Changes Made:
Added new class GraphParser.
Moved parsing logic from Graph to GraphParser.parseGraph(InputStream).
Reduced size and responsibility of Graph.

GitHub Commit:
https://github.com/wilsil23/CSE464-wdiamon1/commit/fd9e63773a2da035b6b3400a1ee69e7fb0f0bc32

Refactor #4 — Extract BFS/DFS into BFSSearch and DFSSearch classes

Explanation:
The original Graph class implemented BFS and DFS internally, violating both SRP and Open/Closed principles. They needed to be separated into reusable, testable components.

Changes Made:
Created BFSSearch and DFSSearch classes.
Removed BFS/DFS logic from Graph.
Updated tests to call search classes directly.

GitHub Commit:
https://github.com/wilsil23/CSE464-wdiamon1/commit/13ce82521095b3a54761586937b99f62487fd9b8

Refactor #5 — Add Template Method + Strategy Pattern

Explanation:
BFS and DFS share common steps (validation, starting node processing) but differ in how they explore neighbors. Template Method extracts common logic; Strategy Pattern allows choosing algorithms dynamically.

Changes Made:
Added abstract class GraphSearch with search() template method.
BFSSearch and DFSSearch now override doSearch() with algorithm-specific behavior.
Added optional RandomWalkSearch strategy.
Updated Graph.GraphSearch(src, dst, algo) to select the appropriate strategy based on the enum.

GitHub Commit:
https://github.com/wilsil23/CSE464-wdiamon1/commit/bcf361dd88bd8a3831fef2ba7ab5418b55015423
