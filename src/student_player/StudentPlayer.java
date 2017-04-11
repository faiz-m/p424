package student_player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import bohnenspiel.BohnenspielBoardState;
import bohnenspiel.BohnenspielMove;
import bohnenspiel.BohnenspielPlayer;
import bohnenspiel.BohnenspielMove.MoveType;
import student_player.mytools.MyTools;

/** A Hus player submitted by a student. */
public class StudentPlayer extends BohnenspielPlayer {

    /** You must modify this constructor to return your student number.
     * This is important, because this is what the code that runs the
     * competition uses to associate you with your agent.
     * The constructor should do nothing else. */
    public StudentPlayer() { super("260564849"); }

    /** This is the primary method that you need to implement.
     * The ``board_state`` object contains the current state of the game,
     * which your agent can use to make decisions. See the class
bohnenspiel.RandomPlayer
     * for another example agent. */
    public BohnenspielMove chooseMove(BohnenspielBoardState board_state)
    {
    	Random rand = new Random();
    	//get available moves
    	ArrayList<BohnenspielMove> moves = board_state.getLegalMoves();
    	//an array to store the results of minimax/monte carlo
    	int[] max = new int[moves.size()];
    	Arrays.fill(max, 0);
    	//loop to run minimax on the available moves
    	for(int i=0; i<moves.size(); i++){
    		BohnenspielBoardState cloned_board_state = (BohnenspielBoardState) board_state.clone();
	        cloned_board_state.move(moves.get(i));
    		max[i] = MyTools.minimax(cloned_board_state, board_state.getTurnPlayer(), 6, Integer.MIN_VALUE, Integer.MAX_VALUE);
    	}
    	//loop to run monte carlo on the available moves. not used
//    	for(int i=0; i<moves.size(); i++){
//    		for(int j=0; j<10;j++){
//    			BohnenspielBoardState cloned_board_state = (BohnenspielBoardState) board_state.clone();
//        		cloned_board_state.move(moves.get(i));
//    			max[i] += MyTools.simulate(cloned_board_state, board_state.getTurnPlayer());
//    		}
//    	}
//		printing for debugging
//    	System.out.print(moves.size());
//    	for(int i=0; i<moves.size(); i++){
//    		System.out.print(max[i]);
//    	}
//    	System.out.println("");
    	
    	
    	int maxv = Integer.MIN_VALUE;
    	ArrayList<Integer> maxva = new ArrayList<Integer>();
    	//get the highest value returned by search
    	for(int i=0; i<max.length; i++){
    		if (max[i]>maxv) maxv = max[i];
    	}
    	//get the corresponding indexes for the moves leading to the highest scores
    	for(int i=max.length-1; i>=0; i--){
    		if (max[i]==maxv) maxva.add(i);
    	}
    	//if there is a tie in best moves, choose by random
    	int maxvi = maxva.get(rand.nextInt(maxva.size()));
//    	System.out.println("maxv = " + maxv + "and maxvi= " + maxvi);
    	//return the move
    	return moves.get(maxvi);
    }
}