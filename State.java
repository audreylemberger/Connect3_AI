
public class State {
	private int EMPTY = 0;
	private int MAX= 1;
	private int MIN =2;
	
	
	private int height;
	private int width;
	
	
	private int[][] board;
	private int whoseTurn;
	
	
	public State(){
		
		height = 4;
		width = 5;
		whoseTurn = 1;
		board = new int[height][width];
		for (int i = 0; i< board.length; i++){
			for (int j = 0; j< board[0].length; j++){
				board[i][j] = EMPTY;
			}
		}
		
	}
	//works
	public State(int[][] board, int whoseTurn){
		this();
		this.board = board;
		this.whoseTurn = whoseTurn;
	}
	
	//works
	public State(State tocopy){
		this();
		whoseTurn = tocopy.getWhoseTurn();
		int[][] tempBoard = new int[height][width];
		for (int i = 0; i< tocopy.getBoard().length; i++){
			for(int j = 0; j<tocopy.getBoard()[0].length; j++){
				tempBoard[i][j] = tocopy.getBoard()[i][j];
			}
		}
		board = tempBoard;
		
		
	}
	//works
	public boolean isValid(int column){
		if (column >4 || column<0){
			//System.out.println("not valid 52");
			return false;
		}
		else if(board[0][column] !=EMPTY){
			//System.out.println("not valid 56");
			return false;
		}
		else{
			//System.out.println("valid 60");
			return true;
		}
	}
	
	//works
	public State move(int column){
		int[][] tempBoard = new int[height][width];
		for (int i = 0; i< board.length; i++){
			for (int j = 0; j<board[0].length; j++){
				tempBoard[i][j] = board[i][j];
			}
		}
		if(tempBoard.equals(board)){
			System.out.println("deep copy fail");
		}
		//System.out.println("move 73");
		
		int row = 0;
		if (isValid(column)){
			while (tempBoard[row][column] == EMPTY){
				//System.out.println("move 78");
					row = row + 1;
					if (row == 4){
						//System.out.println("break 81");
						break;
					}
				
			}
			
			tempBoard[row -1][column] = whoseTurn;
			State newState = new State(tempBoard, switchWhoseTurn());
			
			return newState;
		}
		else{
			return null;
		}
		
		
	}

	
	public boolean noWinner(){
		for (int i = 0; i< board.length; i++){
			for (int j = 0; j< board[0].length; j++){
				if (board[i][j] == EMPTY){
					return false;
				}
			}
		}
		return true;
	}
	//works
	public boolean isTerminal(){
		
		for (int i = 0; i< board.length; i++){
			for (int j = 0; j< board[0].length; j++){
				if (board[i][j] == MIN){
					
					if (i +2 < 4 && j+2 <5){
						//terminal 115
						if(board[i+1][j+1] == MIN && board[i+2][j+2] ==MIN){
							//System.out.println("terminal120");
							return true;
						}
						
					}
					
					if (i +2 < 4 && j-2 >= 0){
						//terminal 124
						if(board[i+1][j-1] == MIN && board[i+2][j-2] ==MIN){
							//System.out.println("terminal129");
							return true;
						}
						
					}
					
					if (i +2 < 4){
						//terminal 133
						if(board[i+1][j] == MIN && board[i+2][j] ==MIN){
							//System.out.println("terminal138");
							return true;
						}
					}
				
				
					if (j +2 < 5){
						//terminal 142
						if(board[i][j+1] == MIN && board[i][j+2] ==MIN){
							//System.out.println("terminal147");
							return true;
						}
					}
					
				}
				else if (board[i][j] == MAX){
					if (i +2 < 4 && j+2 <5){
						//diagonally to the right 152
						if(board[i+1][j+1] == MAX && board[i+2][j+2] ==MAX){
							//System.out.println("terminal157");
							return true;
						}
						
					}
					
					if (i +2 < 4 && j-2 >= 0){
						//terminal 161
						if(board[i+1][j-1] == MAX && board[i+2][j-2] ==MAX){
							//System.out.println("terminal166");
							return true;
						}
						
					}
					
					if (i +2 < 4){
						//terminal 170
						if(board[i+1][j] == MAX && board[i+2][j] ==MAX){
							//System.out.println("terminal175");
							return true;
						}
					}
				
				
					if (j +2 < 5){
						//terminal 179
						if(board[i][j+1] == MAX && board[i][j+2] ==MAX){
							//System.out.println("terminal184");
							return true;
						}
					}
				}
			}
		}
		if(noWinner()){
			//System.out.println("noWinner192");
			//printBoard();
			return true;
			
		}
		else{
			//System.out.println("false");
		
			return false;
		}
			
		
		
	}
	
	public Integer utility(){
		if (isTerminal()){
			
		
		for (int i = 0; i< board.length; i++){
			for (int j = 0; j< board[0].length; j++){
				if (board[i][j] == MIN){
					
					if (i +2 < 4 && j+2 <5){
						//diagonally to the right 207
						if(board[i+1][j+1] == MIN && board[i+2][j+2] ==MIN){
							//System.out.println("diagonally right");
							return -1;
						}
						
					}
					
					if (i +2 < 4 && j-2 >= 0){
						//diagonally to the left 216
						if(board[i+1][j-1] == MIN && board[i+2][j-2] ==MIN){
							//System.out.println("diagonally left");
							return -1;
						}
						
					}
					
					if (i +2 < 4){
						//vertical 225
						if(board[i+1][j] == MIN && board[i+2][j] ==MIN){
							//System.out.println("vertical");
							return -1;
						}
					}
				
				
					if (j +2 < 5){
						//horizontal 234
						if(board[i][j+1] == MIN && board[i][j+2] ==MIN){
							//System.out.println("horizontal");
							return -1;
						}
					}
					
				}
				else if (board[i][j] == MAX){
					if (i +2 < 4 && j+2 <5){
						//diagonally to the right 244
						if(board[i+1][j+1] == MAX && board[i+2][j+2] ==MAX){
							//System.out.println("diagonally right");
							return 1;
						}
						
					}
					
					if (i +2 < 4 && j-2 >= 0){
						//diagonally to the left 253
						if(board[i+1][j-1] == MAX && board[i+2][j-2] ==MAX){
							//System.out.println("diagonally left");
							return 1;
						}
						
					}
					
					if (i +2 < 4){
						//vertical 262
						if(board[i+1][j] == MAX && board[i+2][j] ==MAX){
							//System.out.println("vertical");
							return 1;
						}
					}
				
				
					if (j +2 < 5){
						//horizontal 271
						if(board[i][j+1] == MAX && board[i][j+2] ==MAX){
							//System.out.println("horizontal");
							return 1;
						}
					}
				}
				 if (noWinner()){
					//System.out.println("no winner 279");
					return 0;
				}
			
		
			}
		}
			
		}
		else{
			//System.out.println("292terminal");
			return null;
		}
		//System.out.println("295terminal");
		//System.out.println(isTerminal());
		//xxxfprintBoard();
		return null;
		
	}
	
	public void printBoard(){
		for (int i = 0; i < board.length; i++){
			for (int j=0; j< board[0].length; j++){
				System.out.print
				(board[i][j]);
			}
			System.out.println("");
		}
		System.out.println("whoseTurn " + whoseTurn);
	}
	
	public int[][] getBoard(){
		return board;
	}
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	public int getWhoseTurn(){
		return whoseTurn;
	}
	public int switchWhoseTurn(){
		if (whoseTurn == 1){
			return  2;
		}
		else if (whoseTurn ==2){
			return 1;
		}
		return 0;
	}
	
	public String toString(){
		String boardString = "";
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j< board[0].length; j++){
				boardString = boardString + board[i][j];
			}
		}
		boardString = boardString + whoseTurn;
		return boardString;
	}

			
}
