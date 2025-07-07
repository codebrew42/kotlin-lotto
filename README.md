# kotlin-lotto

This is the submission for "step 1 - lotto" for the "Hero Tech Course 2025" by [Jin](https://github.com/codebrew42) and [Deniz](https://github.com/deniz-oezdemir).

This project is a console-based Kotlin application that simulates a lotto game. After the user inputs a purchase amount, winning numbers, and a bonus number, the program generates tickets, validates inputs, calculates results, and displays winning statistics.

## features

The features were implemented in the following order:

- [x] get input from user: purchase amount from user
  - [x] units of 1000
  - [x] greater than or equal to 1000
- [x] display purchase amount `N`
- [x] display N-Sets of randomly generated numbers
  - [x] size of ticket is 6
  - [x] each radom number is in range [1, 45]
  - [x] no duplicates
- [x] get input from user: last week's winning numbers (6 digits)
  - [x] no duplicates
  - [x] in the range of [1, 45]
- [x] get input from user: bonus number (1 digit)
  - [x] different from the winning numbers
  - [x] in the range of [1, 45]
- [x] find and display matches
- [x] calculate winning statistics
- [x] display winning statistics

## learnings
- use [model-view-controller pattern](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
- apply test driven development as (1) writing tests, (2) write code that passes tests, (3) refactor code,
and repeat (1) to (3) until program fulfills all requirements