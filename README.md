# AcademicTranscripts (Core Execution & API Specification)

## Overview

`AcademicTranscripts` is a command-line driven Java system for **incremental construction and validation of a student's academic record**. The program processes a **sequential command stream** and maintains a **mutable transcript state**, updating and printing results after each valid operation.

The system enforces **strict input validation, bounded state constraints**, and **deterministic GPA computation**.

---

## Execution Model

### Entry Point

```java
public static void main(String[] args)
```

The program interprets `args` as a **linear instruction stream**, where commands are executed in-order under a **stateful transcript model**.

---

## Command Grammar

### 1. Declare Major

```
declareMajor <MAJ>
```

#### Preconditions

* `args.length ≥ 2`
* `<MAJ>` must satisfy department validity constraints

#### Failure Modes

* Missing argument:

  ```
  Invalid declareMajor command. Major not provided.
  ```
* Invalid department:

  ```
  Invalid major: <MAJ>
  ```

#### Semantics

* Initializes transcript state with declared major
* Establishes **major-specific aggregation domain**

---

### 2. Add Grade

```
addGrade <DEPT> <GRADE> <CREDITS>
```

#### Arity Constraint

* Requires **exactly 3 arguments**

#### Failure Modes

* Missing parameters:

  ```
  Invalid addGrade command. Department, grade, and/or credits not provided.
  ```

---

## Validation Layer

### Department Validation

A department is valid iff:

[
|department| = 3 \land department \neq \emptyset
]

---

### Grade Validation

Valid grades form a finite ordered set:

[
{A, A-, B+, B, B-, C+, C, C-, D+, D, D-, F}
]

Each grade maps to a **grade-point value** via a deterministic function:

```java
getGradePoints(String grade)
```

---

### Credit Constraints

[
credits \in {1,2,3,4}
]

Additionally:

[
\text{Current Semester Credits} \leq 18
]

#### Violation

```
Cannot add grade. Maximum credits allowed per semester is 18.
```

---

## State Model

The system maintains the following **state variables**:

| Variable           | Description                             |
| ------------------ | --------------------------------------- |
| `total_credits`    | Cumulative credits across all semesters |
| `semester_credits` | Credits in current semester             |
| `major_credits`    | Credits within declared major           |
| `GPA`              | Global GPA                              |
| `semester_GPA`     | GPA for current semester                |
| `major_GPA`        | GPA restricted to major                 |
| `major`            | Declared department                     |

---

## Operational Semantics

### addGrade(...)

#### Preconditions

* Major must be declared
* Input must pass `isAddGradeValid(...)`

#### Validation Pipeline

1. Validate department
2. Validate grade
3. Validate credits
4. Check semester credit bound

#### Effects

* Updates:

  * `total_credits`
  * `semester_credits`
  * `GPA`
  * `semester_GPA`
* Conditional:

  * If `department == major`:

    * Update `major_credits`
    * Update `major_GPA`

#### Output

```
Grade added: <DEPT> <GRADE> <CREDITS>
```

---

### startNewSemester(...)

#### Semantics

* Resets:

  * `semester_credits ← 0`
  * `semester_GPA ← 0`
* Preserves:

  * Global GPA
  * Total credits
  * Major statistics

---

## GPA Computation

### General Formula

[
GPA = \frac{\sum (grade_points_i \cdot credits_i)}{\sum credits_i}
]

Implemented via:

```java
getGpa(double gradePoints, int credits)
```

### Specialized Variants

* `getCurrentSemesterGPA(...)`
* `getMajorGPA(...)`

All follow **weighted average semantics**.

---

## Transcript Output

### Format

```
==========
Total Credits: <int>
Overall GPA: <double>
Current Semester Credits: <int>
Current Semester GPA: <double>
Major: <string>
Major Credits: <int>
Major GPA: <double>
==========
```
