import java.util.ArrayList;
class GameModel {
	private boolean[][] board;
	private boolean evenX = true, evenY = true;
	private ArrayList <boolean[][]> list = new ArrayList <boolean[][]>();
	private int boardSizeX,boardSizeY;
	private int round = 0;
	public GameModel(){
		board = new boolean[15][15];
		boardSizeX = 15;
		boardSizeY = 15;
		round = 0;
	}
	
	public void setBoardSizeX(int num) {
		boardSizeX = num;

		board = resetBoardAfterBoardSize(num,0);
		evenX = !evenX;
	}
	public void setBoardSizeY(int num) {
		boardSizeY = num;
		
		board = resetBoardAfterBoardSize(0,num);
		evenY = !evenY;
	}
	public int getBoardSizeX() {
		return boardSizeX;
	}
	public int getBoardSizeY() {
		return boardSizeY;
	}
	public boolean[][] getBoard(){
		return board;
	}
	public int getRound() {
		return round;
	}
	private boolean[][] resetBoardAfterBoardSize(int x, int y) {
		int backwardsY = 0, backwardsX = 0;
		if(evenX && x==1) {
			backwardsX++;
		}
		if(evenY && y==1) {
			backwardsY++;
		}
		boardSizeY+=y;
		boardSizeY+=x;
		boolean[][] newArr = new boolean[boardSizeY][boardSizeX];
		for(int i = backwardsY; i<boardSizeY-backwardsY;i++) {
			for(int j = backwardsX; i<boardSizeX - backwardsX;j++) {
				newArr[i][j] = board[i][j];
			}
		}
		return newArr;
	}
	public void resetBoard(int presetNum) {
		board = new boolean[15][15];
		list.clear();
		round = 0;
		if(presetNum ==0) {
			board = new boolean[15][15];
		}else if(presetNum == 1) {
			//Repeating Bombs
			boolean[][] boardPreset = {{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
					{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
					{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
					{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
					{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
					{false,false,false,false,false,true,false,true,false,true,false,false,false,false,false},
					{false,false,false,false,false,true,false,false,false,true,false,false,false,false,false},
					{false,false,false,false,false,true,false,false,false,true,false,false,false,false,false},
					{false,false,false,false,false,true,false,false,false,true,false,false,false,false,false},
					{false,false,false,false,false,true,false,true,false,true,false,false,false,false,false},
					{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
					{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
					{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
					{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
					{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}};
			for(int i = 0;i<board.length;i++) {
				System.arraycopy(boardPreset[i],0,board[i],0,15);
			}
		}
		else if (presetNum == 2) {
			//SpaceShip
			boolean[][] boardPreset = {{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,true,true,true,true,false,false,false,false},
				{false,false,false,false,false,false,true,false,false,false,true,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,true,false,false,false,false},
				{false,false,false,false,false,false,true,false,false,true,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}};
			for(int i = 0;i<board.length;i++) {
				System.arraycopy(boardPreset[i],0,board[i],0,15);
			}
		}else if(presetNum == 3) {
			for(int i = 0; i<boardSizeY; i ++) {
				for(int v = 0; v<boardSizeX; v++) {
					if((int)(Math.random()*2)==1) {
						board[i][v] = true;
					}
				}
			}
		}
		
		
		
		
	}
	public void changeBoard(int x, int y) {
		System.out.println(x+ " " + y);
		board[y][x] = !board[y][x];
		
	}
	/*public void randomBoard() {
		
		for(int i = 0; i<boardSizeY; i ++) {
			for(int v = 0; v<boardSizeX; v++) {
				if((int)(Math.random()*3)==1) {
					board[i][v] = true;
				}
			}
		}
	}*/
	public void rules() {
		int numAlive;
		round++;
		boolean [][] oldBoard = new boolean [boardSizeY][boardSizeX];
		for(int i = 0; i <boardSizeY; i++) {
			for(int v = 0; v<boardSizeX; v++) {
				oldBoard[i][v] = board[i][v];
			}
		}
		for(int i = 0; i<boardSizeY; i++) {
			for(int v = 0; v<boardSizeX; v++) {
				numAlive = numAlive(i,v,oldBoard);

				if(oldBoard[i][v] == false) {
					if(numAlive == 3) {
						board[i][v]= true;
					}
				}else {
					if(numAlive < 2) {
						board[i][v] = false;
					}else if(numAlive>=4) {
						board[i][v] = false;
					}
				}
			}
		}
		
	}
	private int numAlive(int y, int x,boolean[][] oldBoard) {
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
					if(oldBoard[y+i][x+v] == true) {
						if(!(i==0 && v== 0)) {
							num++;
						}
					}
			}
			
		}
		return num;
	}
	public void nextRound() {
		
		if(round==0 && list.size()==0) {
			boolean[][] board2 = new boolean[boardSizeY][boardSizeX];
			for(int i = 0;i<boardSizeY;i++) {
				for(int j = 0; j<boardSizeX;j++) {
					board2[i][j] = board[i][j];
				}
			}
			list.add(board2);
		}
		rules();
		boolean[][] board3 = new boolean[boardSizeY][boardSizeX];
		for(int i = 0;i<boardSizeY;i++) {
			for(int j = 0; j<boardSizeX;j++) {
				board3[i][j] = board[i][j];
			}
		}
		System.out.println("s: " + list.size());
		if(list.size()==round) {
			list.add(board3);

		}else {
			list.set(round, board3);
			
		}
	}

	public void lastRound() {
		if(round > 0) {
			round--;
			for(int i = 0;i<board.length;i++) {
				for(int j = 0;j<board[i].length;j++) {
					board[i][j] = list.get(round)[i][j];
				}
			}
			//board = list.get(round);
		}
		System.out.println("LASTROUND");
	}
}
