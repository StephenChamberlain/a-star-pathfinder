# A* Pathfinder
A* is a popular pathfinding algorithm that can be found in many games, particularly Real Time Strategy titles such as Age of Empires, 
or Command & Conquer. This program is a Java and OpenGL based implementation made for fun. It can be used to test the theory behind the algorithm. 

The algorithm is not explained here - there are many better explanations out there, such as wikipedia <a 
href="https://en.wikipedia.org/wiki/A*_search_algorithm">https://en.wikipedia.org/wiki/A*_search_algorithm</a>

When searching an area for a path, the area itself should be divided in such a way to simplify the task. In this program, the grid
 is a 2D area made up of squares. It doesn't have to be squares - it could use hexagons, triangles or any other shape.

Black squares are considered obstacles - walls of a maze, areas of water, cliffs or any other unpassable terrain. The green
 square signifies the start point. The red square is the finish point. These two points can be set by right clicking on a square
 and selecting start or finish. An obstacle is created when you left click on a square.

<img src="Application\Help\start.png" />
 
When you have created a grid you want to try, click on the 'Calculate path' button.  

<img src="Application\Help\solution.png" />

Squares which have been visited, but not used are shown as yellow. The blue squares show the selected path and 
provide the solution for the current grid. 

To clear the grid of all obstacles, start and finish points, click on the 'Reset' menu item under the 'Tools menu'.

<img src="Application\Help\reset.png" />

You can create new grids, save them and open them again in the program. This makes sharing grids easy. To do 
this, use the items in the 'File' menu.

<img src="Application\Help\menu.png" />
</html>