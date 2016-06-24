# Sudoku

<b>Sudoku game.</b> Basic functionality:
- filling cells,
- checking answers with error highlighting,
- providing proper answers,
- 5 difficulty levels,
- 8 additional editable mini-cells for hint notation.

Depending on difficulty level choice, the creation of new game board could take up to 20 sec. Diversed difficulty of puzzles is achieved by diversing of model algorithm, which is responsible for finding cells which in given puzzle could be blank, and number of blank ciles:
- VERY EASY - 28 to 30 blanks - random iteration model,
- EASY - 31 to 44 blanks- random iteration model,
- MODERATE - 45 to 49 blanks- S-like iteration model,
- HARD - 49 to 54 blanks- S-like iteration model,
- VERY HARD - 55 to 61 blanks- linear iteration model,

The creational model is loosely based on <a href="http://zhangroup.aporc.org/images/files/Paper_3485.pdf"> this document.</a> 


<i>Another project which gives me a lot of fun, however board creation algorithms were challenging. Effect is not entirely satisfying, as creation of extremely difficult level board take a lot of time. Also the program flow was criticised on <a href="http://codereview.stackexchange.com/questions/108143/sudoku-game-with-varied-difficulty-level">SO Code Review</a>. Whats more, failure in MVC implementation and lack of test, make it not the best of my projects, but in the end, as a recreational, amateur project I am pleased with result. </i>
