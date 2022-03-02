# USE CASE: 12 produce a report on the top N populated cities in a country where N is provided by the user so that I can further my understanding of the database.


## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Database manager* I want *to produce a report on the top N populated cities in a country* so that *I can provide a report for the team.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the role.  Database contains current world city data.

### Success End Condition

A report is available for team.

### Failed End Condition

No report is produced.

### Primary Actor

Database manager

### Trigger

A request for city information is sent to the project team.

## MAIN SUCCESS SCENARIO

1. Someone requests population information for the top N populated cities in a country.
2. Database manager captures the population data for every city in a country.
3. Database manager sorts the cities in a country by population.
5. Database manager limits it by N.
6. Database manager provides report.

## EXTENSIONS

None.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0