import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
public class Sudoku implements ActionListener {
	static int size;
	static int boxsize;
	public int[][] grid;
	public static Display solved;
	static JButton Solve;
	static JLabel Solveable;
	private static Scanner scan;
	
	public static boolean checkvalidity(int grid[][]) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(!isValid(grid, i, j, grid[i][j]) && grid[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isValid(int grid[][], int row, int col, int value) {
		return (!inRow(grid, row, value) && !inCol(grid, col, value) && !inBox(grid, row, col, value));
	}
	
	public static boolean inRow(int grid[][], int row, int value) {
		for(int i = 0; i < size; i++) {
			if(grid[row][i] == value) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean inCol(int grid[][], int col, int value) {
		for(int i = 0; i < size; i++) {
			if(grid[i][col] == value) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean inBox(int grid[][], int row, int col, int value) {
		row = row - row%boxsize;
		col = col - col%boxsize;
		for(int i = 0; i < boxsize; i++) {
			for(int j = 0; j < boxsize; j++) {
				if(grid[row+i][col+j] == value) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean PuzzleSolved(int grid[][]) {
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				if (grid[r][c] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean SolvePuzzle(int grid[][]) {
		int row = 0;
		int col = 0;
		loop:
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				if (grid[r][c] == 0) {
					row = r;
					col = c;
					break loop;
				}
			}
		}
		for(int value = 1; value <= size; value++) {
			if(isValid(grid, row, col, value)) {
				grid[row][col] = value;
				SolvePuzzle(grid);
				if(PuzzleSolved(grid)) {
					return true;
				}
				else {
					grid[row][col] = 0;
				}
			}
		}
		return false;
	}
	
	public static void printGrid(int grid[][]) {
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		grid = new int[size][size];
		grid = solved.getGrid();
		SolvePuzzle(grid);
		if(PuzzleSolved(grid)) {
			solved.getSolved(grid);
			printGrid(grid);
			solved.changeDisplay(grid);
		}
		else {
			Solveable.setText("UNSOLVEABLE");
		}
	}
	
	public static void main(String [] args) {
		scan = new Scanner(System.in);
		System.out.println("Welcome to Shane's Sudoku Solver!");
		System.out.println("Enter the Sudoku size you wish to create (4, 9, 16); ");
		size = scan.nextInt();
		boxsize = (int)Math.sqrt(size);
		solved = new Display(size);
		Solve = new JButton("Solve");
		Solveable = new JLabel("Shane's Sudoku Solver!");
		Sudoku sk = new Sudoku();
		Solve.addActionListener(sk);
		solved.add(Solve, BorderLayout.NORTH);
		solved.add(Solveable, BorderLayout.SOUTH);
		solved.setLocation(10, 10);
		solved.setSize(400, 400);
		solved.setVisible(true);
		}
	}
