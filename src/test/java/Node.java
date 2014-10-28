/*
 * Implementation of nodes for a Binary Tree.
 *
 */
public class Node implements Comparable<Node>{ //needs to implement Comparable for the sake of priority queue.
	
	//None of the fields should change once initialized.
	//Use getters to retrieve
	private Node left;//children left uninitialized in leafs.
	private Node right;
	private int frequency;//Sometimes combined from children.
	private char letter;//letter left uninitialized in nodes.
	
	//constructor used for creating a leaf node
	public Node(int frequency, char letter){//constructor used when generating a leaf.
		this.frequency = frequency;
		this.letter = letter;
	}
	
	
	//overloaded constructor for creating a parent node
	public Node(Node left, Node right){
		this.frequency = left.frequency + right.frequency;//combine leaf frequency for their parent node
		this.left = left;
		this.right = right;
	}
	
	public boolean isLeaf(){//called upon to determine if traversal should stop.
		if (left == null && right == null){
			return true;
		}
		return false;
	}
	
	public int compareTo(Node other){//used for priority queue
		return (this.frequency - other.getFrequency());//we want this sorted HIGHEST to lowest.
		//so this returns a positive int if this > other in terms of priority.
	}
	
	//getters
	public Node getLeft(){
		return left;
	}
	
	public Node getRight(){
		return right;
	}
	
	public char getLetter(){
		return letter;
	}
	
	public int getFrequency(){
		return frequency;
	}

}
