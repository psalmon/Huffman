/*
 * Decode the encoded string by traversing a binary tree according to the encodings char by char value.
 * 
 */
public class Decoder {
	private String encodedText;
	private Node root;
	private StringBuilder decoded = new StringBuilder();
	
	public Decoder(String encodedText, Node root){
		this.encodedText = encodedText;
		this.root = root;
	}
	
	public String decode(){
		char[] encodedArray = encodedText.toCharArray();
		Node n = root;
		
		for(char c : encodedArray){
			
			if (c == '0'){//0 means we should explore left
				n = n.getLeft();
				if (n.isLeaf()) {decoded.append(n.getLetter()); n = root;}//if a leaf is found, append it and start our new char decoding back at the root
				
			}else if(c == '1'){//1 means we should explore right
				n = n.getRight();
				if (n.isLeaf()) {decoded.append(n.getLetter()); n = root;}
			}
		}
		
		return decoded.toString();
	}

}
