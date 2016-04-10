import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Node {
	Node parent; //a pointer to the parent node of this one
	 //you may also like to keep a cache of all the successors of this node
	State state; //the state represented at this node
	int depth;   //the depth from the root node
	
	static Map<String, Integer> cache = new HashMap<String,Integer>();;
	static Map<String, Long> treeSize = new HashMap<String, Long>();
	
	public Node() {
		//Basic constructor
		this.parent = null;
		this.state = new State();
		this.depth = 0;
		
		
	}
	
	public Node(State state) {
		//Basic constructor for a root node

		//TODO: temporary code
		this();
		this.state = state;
	}
	
	public Node(Node parent, State state) {
		//Basic constructor for an interior node

		//TODO: temporary code
		this();
		this.state = state;
		this.parent = parent;
		this.depth = parent.getDepth()+1;
	}
	
	public String toString() {
		//basically print out the depth of the node, and then print out the state
		String get = "";
		get = "depth: "+depth + "state" + state;
		//TODO: temporary code
		return get;
	}
	
	//works
	public List<Node> expand() {
		//generate a list of all the successor nodes from this node
		//by applying every possible legal move to the state

		//TODO: temporary code
		
		List<Node> successors= new ArrayList<Node>();
		for (int i = 0; i < 5; i++){
			if(state.move(i) == null){
				
			}else{
				Node nextMove = new Node(this,state.move(i));
				successors.add(nextMove);
				//System.out.println("61 " + state.toString());
				//System.out.println("successor: "+ i + " "+nextMove);
			}
			
			
		}
		
		
		return successors;
	}
	
	
	

	
	//hashmap?
	//hashmap<toString, minimaxValue
	public int minimaxValue() {
		
		
		
		if (cache.containsKey(state.toString())){
			//System.out.println("82");
			return cache.get(state.toString());
			
		
		}
		else{
			
		
		if (state.isTerminal()){
			cache.put(state.toString(), state.utility());
			
			
			
			return state.utility();
			
		}
		else{
			if (state.getWhoseTurn() ==1){
				
				List<Node> successors = expand();
			
				int lookingAt = successors.get(0).minimaxValue();
				//System.out.println("lookingAt " +lookingAt);
				for (int i =1; i < successors.size(); i++){
					int temp = successors.get(i).minimaxValue();
					if (temp> lookingAt){
						lookingAt = temp;
						//System.out.println("CHANGED 117");
					}
					//System.out.println("temp " + temp);
					
				}
				//System.out.println("whoseturn 110");
				cache.put(state.toString(), lookingAt);
				return lookingAt;
			}
			else if (state.getWhoseTurn() ==2){
				List<Node> successors = expand();
				
				int lookingAt = successors.get(0).minimaxValue();
				for (int i =1; i < successors.size(); i++){
					int temp = successors.get(i).minimaxValue();
					if (temp< lookingAt){
						//System.out.println("CHANGED 131");
						lookingAt = temp;
					}
					
				}
				//System.out.println("whoseturn 124");
				cache.put(state.toString(), lookingAt);
				return lookingAt;
			}
			
		}
		
		return 0;
		}
	}
	
	public Node minimaxDecision(Node rootNode){
		
		
		List<Node> successors = rootNode.expand();
		
		
		Node bestFit = successors.get(0);

		
		
		for (int i = 1; i < successors.size(); i ++){
			//want to get whoseTurn at rootNode or successors(i)
			//want to print path or children
			if (rootNode.getState().getWhoseTurn() == 1){
				
				if (successors.get(i).minimaxValue() > bestFit.minimaxValue()){
					bestFit = successors.get(i);
					
				}
			}
			else if (rootNode.getState().getWhoseTurn() ==2) {
				
				if (successors.get(i).minimaxValue() < bestFit.minimaxValue()){
					bestFit = successors.get(i);
					
				}
			}
			
		}
		
		System.out.println("0" +successors.get(0).minimaxValue());
		System.out.println("1" +successors.get(1).minimaxValue());
		System.out.println("2" +successors.get(2).minimaxValue());
		System.out.println("3" +successors.get(3).minimaxValue());
		System.out.println("4" +successors.get(4).minimaxValue());
		return bestFit;
	}
	
public Node alphabetaDecision(Node rootNode){
		
	//System.out.println("ALPHABETA VALUE");
		List<Node> successors = rootNode.expand();
		
		
		Node bestFit = successors.get(0);

		
		
		for (int i = 1; i < successors.size(); i ++){
			//want to get whoseTurn at rootNode or successors(i)
			//want to print path or children
			if (rootNode.getState().getWhoseTurn() == 1){
				//System.out.println("NO HERE");
				if (successors.get(i).alphabetaValue(Integer.MIN_VALUE, Integer.MAX_VALUE) > bestFit.alphabetaValue(Integer.MIN_VALUE, Integer.MAX_VALUE)){
					bestFit = successors.get(i);
					
				}
			}
			else if (rootNode.getState().getWhoseTurn() ==2) {
				//System.out.println("HERE");
				if (successors.get(i).alphabetaValue(Integer.MIN_VALUE, Integer.MAX_VALUE) < bestFit.alphabetaValue(Integer.MIN_VALUE, Integer.MAX_VALUE)){
					bestFit = successors.get(i);
				
				}
			}
			
		}
		
		return bestFit;
	}
	
	public int alphabetaValue(int a, int b){
		//System.out.println("ALPHABETA VALUE");
		if (cache.containsKey(state.toString())){
			return cache.get(state.toString());
		}
		else{
		if (state.isTerminal()){
			cache.put(state.toString(), state.utility());
			return state.utility();
		}
		else{
			if (state.getWhoseTurn() ==1){
				
				List<Node> successors = expand();
			
				int lookingAt = successors.get(0).alphabetaValue(a, b);
				for (int i =1; i < successors.size(); i++){
					int temp = successors.get(i).alphabetaValue(a, b);
					if (temp> lookingAt){
						lookingAt = temp;
					}
					
				}
				if(lookingAt >= b){
					cache.put(state.toString(), lookingAt);
					
					return lookingAt;
				}
				a = alphabetaValue(a, lookingAt);
				return lookingAt;
			}
			else if (state.getWhoseTurn() ==2){
				List<Node> successors = expand();
				//v=-oo?
				int lookingAt = successors.get(0).alphabetaValue(a, b);
				for (int i =1; i < successors.size(); i++){
					int temp = successors.get(i).alphabetaValue(a, b);
					if (temp< lookingAt){
						cache.put(state.toString(), lookingAt);
						lookingAt = temp;
					}
					
				}
				if(lookingAt>= a){
					
					return lookingAt;
					
				}
				b = alphabetaValue(b, lookingAt);
				return lookingAt;
			}
			
		}
		return b;
		}
	}
	
	public int getDepth(){
		return depth;
	}
	public State getState(){
		return state;
	}
	
	
	public Long getSize(){
		if (treeSize.containsKey(state.toString())){
			return treeSize.get(state.toString());
		}
		else{
			
		
		if (state.isTerminal()){
			treeSize.put(state.toString(), 1L);
			return (long) 1;
			
		}
		else{
			List<Node> successors = expand();
			
			
			Long temp = (long) 1;
			for (int i =0; i < successors.size(); i++){
				temp = temp + successors.get(i).getSize();
				
				
			}
			treeSize.put(state.toString(), temp);
			return temp;
		}
	}
	
}
}
