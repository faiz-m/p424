package student_player.mytools;

import bohnenspiel.BohnenspielBoardState;
import bohnenspiel.BohnenspielMove;

import java.util.ArrayList;
import java.util.Random;

public class MyTools {
	static Random rand = new Random();

    public static double getSomething(){
        return Math.random();
    }
    
    public static int simulate(BohnenspielBoardState board_state, int player){
    	while (board_state.gameOver()==false){
    		BohnenspielMove move = (BohnenspielMove) board_state.getRandomMove();
    		if (board_state.isLegal(move) == false) System.out.println("Illegal move");
    		board_state.move(move);
    	}
    	if (board_state.gameOver() == true) System.out.println("gameover");
    	if (board_state.getWinner()!=player) return 0;
    	else return 1;
    }
    
    public static int eval(BohnenspielBoardState board_state, int player, int depth){
    	if (player == 0){
    		return board_state.getScore(0) - board_state.getScore(1) + 2*(depth - 6) /*+ (board_state.getCredit(player) - 2 )*/;
    	}
    	else{
    		return board_state.getScore(1) - board_state.getScore(0) + 2*(depth - 6) /*+ (board_state.getCredit(player) - 2 )*/;
    	}    	
    }
    
    public static int minimax(BohnenspielBoardState board_state, int player, int depth, int min, int max){
    	if (board_state.gameOver()==true | depth==0) return eval(board_state, player, depth);
		ArrayList<BohnenspielMove> moves = board_state.getLegalMoves();
    	if (board_state.getTurnPlayer() == player){
    		int v = min;
    		for(int i=0; i<moves.size(); i++){
    			//System.out.println("Loop" + i);
    			BohnenspielBoardState cloned_board_state = (BohnenspielBoardState) board_state.clone();
    	        cloned_board_state.move(moves.get(i));
    	        int vs = minimax (cloned_board_state, player, depth-1, v, max);
    	        if (vs > v){ 
    	        	v=vs;
    	        }
    	        if (v>max) return max;
    		}    	
        	return v;
    	}
    	    
    	else{
    		int v = max;
    		for(int i=0; i<moves.size(); i++){
    			//System.out.println("Loop" + i);
    			BohnenspielBoardState cloned_board_state = (BohnenspielBoardState) board_state.clone();
    			cloned_board_state.move(moves.get(i));
    	        int vs = minimax (cloned_board_state, player, depth-1, min, v);
    	        if (vs < v){ 
    	        	v=vs;
    	        }
    	        if (v<min) return min;
    		}
        	return v;
    	}
    }
    
}
