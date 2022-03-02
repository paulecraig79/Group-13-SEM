# USE CASE: 18 produce a report on the top N populated capital cities in a continent.

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *database manager* I want *to produce a report on the top N populated capital cities in a continent *I can support the population reports for the organisation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the role.  Database contains current world population data.

### Success End Condition

A report is available for project manager and team.

### Failed End Condition

No report is produced.

### Primary Actor

Project manager

### Trigger

A request for population information is sent to the project team.

## MAIN SUCCESS SCENARIO

1. Someone request population information for the top number of capital cities in a continent
2. Project manager captures the population data for every capital city in a continent to get population information.
3. Project manager sorts the population for each capital city biggest to smallest.
4. Project manager limits it by N.
5. Project manager provides population report.

## EXTENSIONS

None.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
