package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	//private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		int randx = new Random().nextInt(maze.cells.length);
		int randy = new Random().nextInt(maze.cells.length);
		
		System.out.println("rx" +randx);
		System.out.println("ry" +randy);
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cells[randx][randy]);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> ccUN = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if (ccUN.size() != 0) {
			//C1. select one at random
			Cell pushc = ccUN.get(new Random().nextInt(ccUN.size()));
			//C2. push it to the stack
			uncheckedCells.push(pushc);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, pushc);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = pushc;
			pushc.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		if (ccUN.size() == 0) {
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
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			} else if (c1.getY()+1 == c2.getY()) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		}													//chkd
		
		else if (c1.getY() == c2.getY()) {
			if (c1.getX() == c2.getX()+1) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			} else if (c1.getX()+1 == c2.getX()) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
				
		ArrayList<Cell> getUnvisitedNeighbors = new ArrayList<Cell>();
		
		System.out.println(c.getX() + "" + c.getY() + "");
		
//		if (c.getX()-1>=0 && c.getY()-1>=0) {
//			if (maze.cells[c.getX()-1][c.getY()-1].hasBeenVisited() == false) {
//				getUnvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()-1]);
//			}
//		}
		
		if (c.getX()>0) {
			if (maze.cells[c.getX()-1][c.getY()].hasBeenVisited() == false) {
				getUnvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()]);
			}
		}
		
		if (c.getY()>0) {
			if (maze.cells[c.getX()][c.getY()-1].hasBeenVisited() == false) {
				getUnvisitedNeighbors.add(maze.cells[c.getX()][c.getY()-1]);
			}
		}
		
//		if (c.getX()+1<=width && c.getY()+1<=height) {
//			if (maze.cells[c.getX()+1][c.getY()+1].hasBeenVisited() == false) {
//				getUnvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()+1]);
//			}
//		}
		
		if (c.getX()<width-1) {
			if (maze.cells[c.getX()+1][c.getY()].hasBeenVisited() == false) {
				getUnvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()]);
			}
		}
		
		if (c.getY()<height-1) {
			if (maze.cells[c.getX()][c.getY()+1].hasBeenVisited() == false) {
				getUnvisitedNeighbors.add(maze.cells[c.getX()][c.getY()+1]);
			}
		}
		
//		if (c.getX()-1<=width && c.getY()+1<=height) {
//			if (maze.cells[c.getX()-1][c.getY()+1].hasBeenVisited() == false) {
//				getUnvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()+1]);
//			}
//		}
//		
//		if (c.getX()+1<=width && c.getY()-1<=height) {
//			if (maze.cells[c.getX()+1][c.getY()-1].hasBeenVisited() == false) {
//				getUnvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()-1]);
//			}
//		}
		
		return getUnvisitedNeighbors;
	}
}
