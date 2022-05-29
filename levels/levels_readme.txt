Creating custom levels is possible (and very easy). Simply put int numbers in a 15x15 grid to a .txt file and put it in the levels folder. The numbers resemble following objects:

0 - nothing
1- unbreakable wall
2 - breakable wall
3 - player starting position (always facing right)
4 - level goal
5 - enemy (always facing right)

The file must be called "[number].txt" (1.txt, 2.txt, etc.). For a level to be played, all levels before it must be accounted for (for example, 4.txt will never be ran if 3.txt doesn't exist).