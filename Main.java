
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[][] test = {{0,0,0,0,0}, {0,0,0,0,0}, {0,0,1,0,2}, {0,1,2,1,2}};
		int[][] real = {{0,0,0,0,0}, {0,2,0,0,0}, {0,1,0,0,0}, {1,1,2,0,0}};
//		int[][] fake = {{0,0,0,0,2}, {1,2,1,2,1}, {1,2,1,2,1}, {2,1, 2,1,2}};
//		int[][] almostEmpty = {{0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}, {0,0,1,0,0}};
		State state = new State(real, 2);
		System.out.println("starting state");
		state.printBoard();
		
		
		Node node = new Node(state);
		Node nextTurn = node.minimaxDecision(node);
		
		state.printBoard();
		nextTurn.getState().printBoard();
		System.out.println("done");
		
		
	}

}
