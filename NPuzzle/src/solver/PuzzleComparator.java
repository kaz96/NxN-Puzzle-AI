package solver;

import java.util.Arrays;
import java.util.Comparator;

public class PuzzleComparator implements Comparator<PuzzleState>
{
	
	@Override
	public int compare(PuzzleState state1, PuzzleState state2) 
	{
		   // System.out.println(Arrays.deepToString(state1.Puzzle) + state1.getEvaluationFunction());
		    
		    //System.out.println(Arrays.deepToString(state2.Puzzle) + state1.getEvaluationFunction());
		    
		    int test1 = state1.getEvaluationFunction();
			int test2 = state2.getEvaluationFunction();
		    

		   // System.out.println(test1 +  - test2);

		
		return state1.getEvaluationFunction() +  - state2.getEvaluationFunction();
	}

}



