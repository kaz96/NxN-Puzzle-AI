package solver;


public class AStarPuzzleComparator extends PuzzleComparator
{
	

	@Override
	public int compare(PuzzleState state1, PuzzleState state2) 
	{
		
		Integer Value1 = state1.getEvaluationFunction()+ state1.Cost;
		Integer Value2 = state2.getEvaluationFunction()+ state2.Cost;

	
		return Value1 +  - Value2;
	}
	
	
}