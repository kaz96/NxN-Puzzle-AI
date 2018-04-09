package solver;
import java.util.*;

public class BFSStrategy extends SearchMethod {

	public BFSStrategy()
	{
		code = "BFS";
		longName = "Breadth-First Search";
		Frontier = new LinkedList<PuzzleState>();
		Searched = new LinkedList<PuzzleState>();


	}
	
	protected PuzzleState popFrontier()
	{
		//remove an item from the fringe to be searched
		PuzzleState thisState = Frontier.removeLast();
		//Add it to the list of searched states, so that it isn't searched again
		
		return thisState;
	}
	
	@Override
	public direction[] Solve(nPuzzle puzzle) {
		//This method uses the fringe as a queue.
		//Therefore, nodes are searched in order of cost, with the lowest cost
		// unexplored node searched next.
		//-----------------------------------------
	
		//put the start state in the Fringe to get explored.
		if (Frontier.size() == 0 )
		{
			addToFrontier(puzzle.StartState);

		}
		
		
		
		ArrayList<PuzzleState> newStates = new ArrayList<PuzzleState>();
				
		while(Frontier.size() > 0)
		{
			Boolean checkedNodesValidation = false;
			PuzzleState currentNode = Frontier.getLast();
			
			
			//get the next item off the fringe
			//PuzzleState thisState = popFrontier();
			
//			for (int i=0; i<this.Searched.size(); i++)
//			{
//				if (thisState.equals(this.Searched.get(i)) && thisState.Parent != null)
//				{
//					thisState.explored = false;
//					//thisState.Puzzle = thisState.Parent.Puzzle;
//					
//				}
//				
//			}
			
			if (currentNode.explored != true)
			{
			
				Searched.add(currentNode);
						
			     System.out.println(Arrays.deepToString(currentNode.Puzzle) + ": " + Integer.toString(currentNode.Cost) );
	
				
				//is it the goal item?
				if(currentNode.equals(puzzle.GoalState))
				{
					//We have found a solution! return it!
					return currentNode.GetPathToState();
				}
				else
				{
					//This isn't the goal, just explore the node
					
					
					newStates = currentNode.explore();
				}
					
					//for(int i = 0; i < newStates.size(); i++)
					//{
						//add this state to the fringe, addToFringe() will take care of duplicates
						addToFrontier(newStates.get(0));
						//break;
					//}
			}
			else
			{			      	
			    
			     if (currentNode.Down == true && checkedNodesValidation == false)
			    	 {

			    	 	try 
			    	 	{
		    	 			// Creates child node for the current one
						currentNode.Children.add(currentNode.move(direction.Down));
						// Adds the new child node to the frontier
						addToFrontier(currentNode.Children.get(currentNode.Children.size()-1));
						// Validation turns true so it doesn't check all of the available movement options
						checkedNodesValidation = true;
					} 
			    	 	catch (CantMoveThatWayException e) 
			    	 	{
			    	 		e.printStackTrace();
					}	
			    	 }
			     if (currentNode.Up == true && checkedNodesValidation == false)
			     {
			    	 	try 
			    	 	{
						currentNode.Children.add(currentNode.move(direction.Up));
						addToFrontier(currentNode.Children.get(currentNode.Children.size()-1));
						checkedNodesValidation = true;
					} 
			    	 	catch (CantMoveThatWayException e) 
			    	 	{
						e.printStackTrace();
					}	
		    	 	}
			     if (currentNode.Left == true && checkedNodesValidation == false)
		    	 	{
			    	 	try 
			    	 	{
						currentNode.Children.add(currentNode.move(direction.Left));
						addToFrontier(currentNode.Children.get(currentNode.Children.size()-1));
						checkedNodesValidation = true;
					} 
			    	 	catch (CantMoveThatWayException e) 
			    	 	{
						e.printStackTrace();
					}	
		    	 	}
			     if (currentNode.Right == true && checkedNodesValidation == false)
		    	 	{
			    	 	try 
			    	 	{
						currentNode.Children.add(currentNode.move(direction.Right));
						addToFrontier(currentNode.Children.get(currentNode.Children.size()-1));
						checkedNodesValidation = true;
					} 
			    	 	catch (CantMoveThatWayException e) 
			    	 	{
						e.printStackTrace();
					}	
		    	 	}
			     if (checkedNodesValidation == false && currentNode.Down == false && currentNode.Up == false && currentNode.Left == false && currentNode.Right == false)
			     popFrontier();
			     
			}
		}
		
		//No solution found and we've run out of nodes to search
		//return null.
		return null;
	}
	
	public boolean addToFrontier(PuzzleState aState)
	{
		//if (aState.Cost > 100)
		//{
		  //  System.out.println("evefe");

		//}
		
		 if (aState.PathFromParent == direction.Left) 
		 {
			 aState.Parent.Left = false;
		 }
		 
		 if (aState.PathFromParent == direction.Right)
		 {
			 aState.Parent.Right = false;
		 }
		 
		 if (aState.PathFromParent == direction.Up)
		 {
			 aState.Parent.Up = false;
		 }
		 if (aState.PathFromParent == direction.Down)
		 {
			 aState.Parent.Down = false;
		 }
		  

		//if this state has been found before,

	if(Searched.contains(aState) || Frontier.contains(aState))
		{
		//popFrontier();

		return false;
			//discard it
//			for (int i=0; i<Searched.size();i++)
//			{
//				// If this puzzle has been already searched in the searched array
//				if (Arrays.deepToString(Searched.get(i).Puzzle).equals(Arrays.deepToString(aState.Puzzle)))
//				{
//					
//					if (Searched.get(i).Down && Searched.get(i).Up && Searched.get(i).Left && Searched.get(i).Right == false)
//					{
//						return false;
//					}
//						  Searched.get(i).Down = aState.Down;
//				    		  Searched.get(i).Up = aState.Up;
//				    		  Searched.get(i).Left = aState.Left;
//				    		  Searched.get(i).Right = aState.Right;
//				    		  // Takes current states direction and makes parent's already searched direction false
//				    		  
//				    		 if (aState.PathFromParent == direction.Left) 
//				    		 {
//				    			 Searched.get(i).Left = false;
//				    		 }
//				    		 
//				    		 if (aState.PathFromParent == direction.Right)
//				    		 {
//				    			 Searched.get(i).Right = false;
//				    		 }
//				    		 
//				    		 if (aState.PathFromParent == direction.Up)
//				    		 {
//				    			 Searched.get(i).Up = false;
//				    		 }
//				    		 if (aState.PathFromParent == direction.Down)
//				    		 {
//				    			 Searched.get(i).Down = false;
//				    		 }
////				    		  goes through entire searched array from the most recent state
//				    		 for (int j = this.Searched.size()-1;j >0; j--)
//				    		 {
//					    		//DOWN
//					    			 // While going back the searched list if there is an array with a true direction then it uses that.
//					    		 try 
//					    		 {
//					    			 if (this.Searched.get(j).Down == true)
//						    		 {
//					    				 this.Searched.get(j).Children.add(this.Searched.get(j).move(direction.Down));
//					    				 
//						    			 // create a children node for the parent of the nodes that hasnt been checked
//					    				 //Adds the searched children into the frontier
//					    				 Frontier.addLast(this.Searched.get(j).Children.get(this.Searched.get(j).Children.size()-1));
//						    			//Frontier.addLast(aState.Parent.Children.get(aState.Parent.Children.size()-1)); 
//					    				 
//					    				this.Searched.get(j).Down = false;
//						    			return true;
//						    			
//	
//						    		 }								
//					    		 } 
//					    		 catch (CantMoveThatWayException e) 
//					    		 {
//					    			 e.printStackTrace();
//							 }
//					    		 //UP
//					    		 try 
//					    		 {
//					    			 if (this.Searched.get(j).Up == true)
//						    		 {
//					    				 this.Searched.get(j).Children.add(this.Searched.get(j).move(direction.Up));
//
//					    				 Frontier.addLast(this.Searched.get(j).Children.get(this.Searched.get(j).Children.size()-1));
//
//						    				this.Searched.get(j).Up = false;
//							    			
//							    		return true;
//	
//						    		 }								
//					    		 } 
//					    		 catch (CantMoveThatWayException e) 
//					    		 {
//					    			 e.printStackTrace();
//							 }
//					    		 //LEFT
//					    		 try 
//					    		 {
//					    			 if (this.Searched.get(j).Left)
//						    		 {
//					    				 this.Searched.get(j).Children.add(this.Searched.get(j).move(direction.Left));
//
//					    				 Frontier.addLast(this.Searched.get(j).Children.get(this.Searched.get(j).Children.size()-1));
//
//						    				this.Searched.get(j).Left = false;
//							    			return true;					    		 
//							    	 }								
//					    		 } 
//					    		 catch (CantMoveThatWayException e) 
//					    		 {
//					    			 e.printStackTrace();
//							 }
//					    		 //RIGHT
//					    		 try 
//					    		 {
//					    			 if (this.Searched.get(j).Right)
//						    		 {
//					    				 this.Searched.get(j).Children.add(this.Searched.get(j).move(direction.Right));
//
//					    				 Frontier.addLast(this.Searched.get(j).Children.get(this.Searched.get(j).Children.size()-1));
//
//						    				this.Searched.get(j).Right = false;
//							    			return true;					    		 
//							    	 }								
//					    		 } 
//					    		 catch (CantMoveThatWayException e) 
//					    		 {
//					    			 e.printStackTrace();
//							 }	
//				    		}
//			   }
//			}		
//			return false;
			
		}
		else
		{
			//else put this item on the end of the queue;
			Frontier.addLast(aState);
			return true;
		}
	}

}
