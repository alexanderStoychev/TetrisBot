# PentriX

<img src="/imgs/screenshot.png" alt="Thumbnail" width="45%"/>

This game was made for the Project 1 - Part 2, from the Bachelor in Data Science and Knowledge Engineering and Maastricht University.  
The code is written by Lucas Giovanni Uberti-Bona Marin, Silvia Fallone, Antonio Rodriguez, Sarah Waseem, Pierre Bongrand, and me.

In short, this is like a normal Tetris game, but the pieces are not made of 4 blocks but 5, called pentominoes.

![Pentominoes](https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/All_18_Pentominoes.svg/1200px-All_18_Pentominoes.svg.png)


Moreover, it has an AI included (inspired in [this site](https://codemyroad.wordpress.com/2013/04/14/tetris-ai-the-near-perfect-player/)), which uses heuristics to make decisions during the game.

It can be played using the following controls:  
 - <kbd>RIGHT ARROW</kbd>: move 1 unit to the right  
 - <kbd>LEFT ARROW</kbd>: move 1 unit to the left  
 - <kbd>DOWN ARROW</kbd>: move 1 unit down  
 - <kbd>UP ARROW</kbd>: rotate (clockwise)
 - <kbd>SPACE</kbd>: drop
 - <kbd>Q</kbd>: quit
 - <kbd>B</kbd>: toggle bot (active / inactive)

General settings such as the weights for the Bot's heuristics, the size of the window, the number of rows and columns of the board, etc. can be modified in `PInfo.java`.
