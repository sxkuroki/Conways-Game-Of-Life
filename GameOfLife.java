import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
public class GameOfLife extends JPanel{
	static int generationNum = 0;
	static Timer timer;
	//note that x is horizontal and y is vertical
	static int boardSizeX = 15, boardSizeY = 15;
	static int[][] board = 
		{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,0,1,0,1,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,1,0,1,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		/*{ {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,1,1,1,1,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
			//new int[boardSizeY][boardSizeX];*/
	static int xInc, yInc;
	static int[][] oldBoard = new int[boardSizeY][boardSizeX];
	public static void main(String[] args) {
		//randomBoard();
		graphicsSystem();
	}
	
	
	public static void deadBoard() {
		for(int i = 0; i<boardSizeY; i++) {
			for(int v = 0; v<boardSizeX; v++) {
				board[i][v] = 0;
				oldBoard[i][v] = 0;
			}
			
		}
	}
	public static void randomBoard() {
		deadBoard();
		for(int i = 0; i<boardSizeX; i ++) {
			for(int v = 0; v<boardSizeY; v++) {
				if((int)(Math.random()*3)==1) {
					board[i][v] = 1;
				}
			}
		}
	}
	public static void printBoard() {
		System.out.println("Generation: " + generationNum);
		for(int i = 0; i<boardSizeY; i++) {
			for(int v = 0; v<boardSizeX; v++) {
				if(v == boardSizeX-1) {
					if(board[i][v] == 0) {
						System.out.println(".");
					}
					if(board[i][v] == 1) {
						System.out.println("*");
					}
				}else {
					if(board[i][v] == 0) {
						System.out.print(".");
					}
					if(board[i][v] == 1) {
						System.out.print("*");
					}
				}
			}
			
		}
		generationNum++;
	}
	public static void rules() {
		int numAlive;
		for(int i = 0; i <boardSizeY; i++) {
			for(int v = 0; v<boardSizeX; v++) {
				oldBoard[i][v] = board[i][v];
			}
		}
		for(int i = 0; i<boardSizeY; i++) {
			for(int v = 0; v<boardSizeX; v++) {
				numAlive = numAlive(i,v);

				if(oldBoard[i][v] == 0) {
					if(numAlive == 3) {
						board[i][v]= 1;
					}
				}else {
					if(numAlive < 2) {
						board[i][v] = 0;
					}else if(numAlive>=4) {
						board[i][v] = 0;
					}
				}
			}
		}
	}

	public static int numAlive(int y, int x) {
		int num = 0;
		int xLowerBound = -1, xUpperBound = 1, yLowerBound = -1, yUpperBound= 1;
		
		if(x == 0 || x == boardSizeX-1|| y == 0||y == boardSizeY-1) {
			if(x==0) {
				xLowerBound = 0;
			}
			if(x == boardSizeX-1) {
				xUpperBound = 0;
		
			}
			if(y == 0) {
				yLowerBound = 0;
			}
			if(y == boardSizeY-1) {
				yUpperBound = 0;
			}
		}
		for(int i = yLowerBound; i<=yUpperBound; i++) {
			for(int v = xLowerBound; v<=xUpperBound; v++) {
					if(oldBoard[y+i][x+v] == 1) {
						if(!(i==0 && v== 0)) {
							num++;
						}
					}
			}
			
		}
		return num;
	}

	static void graphicsSystem() {
		  	GameOfLife d = new GameOfLife();
			JFrame f = new JFrame("Display Graphics Drawings");
			f.add(d);
			f.setSize(1000,1000);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setLocationRelativeTo(null);
			f.setVisible(true);
			xInc = f.getWidth()/boardSizeX ;
			yInc = f.getHeight()/boardSizeY;
			timer = new Timer(300,new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rules();
					f.repaint();
				}
			});
			timer.start();
	      
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		for(int i = 0; i<boardSizeX; i++) {
			for(int v = 0; v<boardSizeY; v++) {
				if(board[i][v] == 1) {
					g.fillRect(v*xInc, (i*yInc), xInc, yInc);
				}
				
			}
		}
	}
}
