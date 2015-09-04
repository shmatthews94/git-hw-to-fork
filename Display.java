import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class Display extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    JPanel griddisplay = new JPanel();
    JPanel background = new JPanel();
    public int size;
    public int boxsize;
    public int[][] displaygrid;
    public JTextField cells[][];
    public JPanel boxes[][];
    
    public Display(int z) {
    		size = z;
    		boxsize = (int)Math.sqrt(z);
    		JPanel griddisplay = new JPanel();
    		JPanel boxes[][]= new JPanel[boxsize][boxsize];
    		cells = new JTextField[size][size];
    		displaygrid = new int[size][size];
            for(int x = 0; x < size; x++){
                for(int y = 0; y < size; y++){
                    cells[x][y]=new JTextField("0", 1);
                }
            }
            for(int w=0; w<boxsize; w++){
                for(int y=0; y<boxsize; y++){
                    boxes[w][y]=new JPanel(new GridLayout(boxsize,boxsize));
                }
            }
            griddisplay.setLayout(new GridLayout(boxsize,boxsize,5,5));
            for(int a = 0; a < boxsize; a++){
                for(int b = 0; b < boxsize; b++){    
                    for(int x = 0; x < boxsize; x++){
                        for(int y = 0; y < boxsize; y++){
                            boxes[a][b].add(cells[x+a*boxsize][y+b*boxsize]);
                        }
                    }
                griddisplay.add(boxes[a][b]);
                }
            }
            add(griddisplay);
    }
    
    public void getSolved(int[][] grid) {
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
    			displaygrid[i][j] = grid[i][j];
    		}
    	}
    }
    
    public int[][] getGrid() {
    	String field;
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
    			JTextField a = cells[i][j];
    			field = a.getText();
    			int x = Integer.parseInt(field);
    			displaygrid[i][j] = x;
    		}
    	}
    	return displaygrid;
    }
    
    public void changeDisplay(int[][] grid) {
    	for(int i = 0; i < grid.length; i++) {
    		for(int j = 0; j < grid.length; j++) {
    			String field = "" + grid[i][j];
    			cells[i][j].setText(field);
    		}
    	}
    }
}
