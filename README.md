# ProgrammingExercise
to run, please use command: mvn exec:java -Dexec.mainClass="task.App"

screen from app are on wiki - https://github.com/TheBestSoftInTheWorld/ProgrammingExercise/wiki

Task:
The size of the table as two integers [width, height]
The objects starting position as two integers [x, y]

If the simulation succeeded: The objects final position as two integers [x, y].
If the simulation failed (the object falls off the table): [-1, -1] should be returned

Commands
The object always has a direction (north, east, south or west). A simulation always starts
with direction north. North means that if the object sits on [2, 4] and moves forward one
step, the object will now stand on [2, 3].
The commands are:
0 = quit simulation and print results to stdout
1 = move forward one step
2 = move backwards one step
3 = rotate clockwise 90 degrees (eg north to east)
4 = rotate counterclockwise 90 degrees (eg west to south)

Example
If the program gets 4,4,2,2 as input, the table is initiated to size 4 x 4 with the object in
position [2, 2] with direction north. Then, commands 1,4,1,3,2,3,2,4,1,0 are read
from stdin and executed. The final output would then be the end position of the object,
in this case [0, 1].
