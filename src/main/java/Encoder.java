/*
 * Compress / Encode an input string according to the Huffman Algorithm.
 * Creates a binary tree sorted by frequency of occurrence (Thereby, less time on most commonly appearing character)
 * 
 */

import java.util.PriorityQueue;


public class Encoder {
	private Node root = null;
	private final static int MAX_ASCII_VALUE = 256; //add 1 to your max value (n-1 max index). 256 as default (255).
	private String lookupTable[] = new String[MAX_ASCII_VALUE];//hard coded to cap at ASCII 255. Java supports a wider range of characters. In theory, this can be set much higher...
	private StringBuilder encoded = new StringBuilder();//use getter.
	private String input;
	
	public Encoder(String input){
		this.input = input;		
	}
	
	public void encode(){
		
		int[] frequency = new int[MAX_ASCII_VALUE];
		//frequency array, where index converts to its ASCII value when casted
		//(hence the max size of 256 to account for standard ASCII range)
		
		//fill the frequency array for how often each ASCII char has occurred.
		for(int i = 0; i < input.length(); i++){
			frequency[input.charAt(i)]++;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();//a priorities queue of tree/subtrees
		
		//create new leafs for each ASCII char used in the string.
		for(int i = 0; i < frequency.length; i++){
			if(frequency[i] > 0){
				pq.add(new Node(frequency[i], (char)i));
			}
		}
		
		while (pq.size() > 1){
			//combine leafs to a node when we can
			Node l = pq.remove();
			Node r = pq.remove();
			//insert back into the PQ as a combined node
			pq.add(new Node(l, r));
		}

		root = pq.poll();//root node for final form of tree.
		
		
		/* 
		 * Everything below here can be commented out if you do not wish to see the printed table or encoded string
		 */
		
		writeLookup(root, "");
		for (char c : input.toCharArray()){
			encoded.append(lookupTable[c]);
		}
		
		
	}
	
	private void writeLookup(Node curr, String coded){//only used internally
		//we're at a leaf?
		if ((curr.isLeaf())){//recursive tree traversal
			lookupTable[curr.getLetter()] = coded;//using letter to int as index, give code.
		}
		//we're at a node.
		else{
			writeLookup(curr.getLeft(), coded + '0');
			writeLookup(curr.getRight(), coded + '1');
		}
	}
	
	public void printEncoded(){

		for(int i = 0; i < MAX_ASCII_VALUE; i++){
			try{if(!(lookupTable[i].isEmpty()))System.out.println((char)i + " : " + lookupTable[i]);
			}catch(NullPointerException e){continue;}//dont print the values that are 0
		}
		
		System.out.println("Encoded to: " + encoded.toString());
	}
	
	//getters
	public String getEncoded(){
		return encoded.toString();
	}
	
	public Node getRoot(){
		return root;
	}
	
}
