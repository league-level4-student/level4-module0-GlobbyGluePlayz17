package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		int randx = randGen.nextInt(maze.cells.length);
		int randy = randGen.nextInt(maze.cells.length);
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cells[randx][randy]);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.hasBeenVisited();
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();
		//C. if has unvisited neighbors,
		if (unvisitedNeighbors.size() != 0) {
			//C1. select one at random
			int rx = randGen.nextInt(maze.cells.length);
			int ry = randGen.nextInt(maze.cells.length);
			//C2. push it to the stack
			uncheckedCells.push(maze.cells[rx][ry]);
			//C3. remove the wall between the two cells
			removeWalls(maze.cells[rx][ry], currentCell);
			//C4. make the new cell the current cell and mark it as visited
	/***/			
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		if (unvisitedNeighbors.size() == 0) {
			//D1. if the stack is not empty
			if (!uncheckedCells.isEmpty()) {
				// D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();
				// D1b. make that the current cell
				//-----
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() == c2.getX()) {
			if (c1.getY() == c2.getY()+1) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			} else if (c1.getY()+1 == c2.getY()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
		}
		
		if (c1.getY() == c2.getY()) {
			if (c1.getX() == c2.getX()+1) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			} else if (c1.getX()+1 == c2.getX()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
				
		if (x<cells.length-1 && y<cells[x].length-1) {
			if (cells[x+1][y+1].isAlive) {
				livingcellpopulation+=1;
			}
		}
		
		if (x<cells.length-1 && y>0) {
			if (cells[x+1][y-1].isAlive) {
				livingcellpopulation+=1;
			}
		}
		
		if (x>0 && y<cells[x].length-1) {
			if (cells[x-1][y+1].isAlive) {
				livingcellpopulation+=1;
			}
		}
		
		if (y>0) {
			if (cells[x][y-1].isAlive) {
				livingcellpopulation+=1;
			}
		}
		
		if (x<cells.length-1) {
			if (cells[x+1][y].isAlive) {
				livingcellpopulation+=1;
			}
		}
		
		if (y<cells[x].length-1) {
			if (cells[x][y+1].isAlive) {
				livingcellpopulation+=1;
			}
		}
		
		if (x>0) {
			if (cells[x-1][y].isAlive) {
				livingcellpopulation+=1;
			}
		}
		return null;
	}
}
