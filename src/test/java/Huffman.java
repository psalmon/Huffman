/*@author: Paul Salmon
 *@date: October 25th, 2014
 *@title: Huffman Encoder and Decoder
 * 
 * Takes a command line string and encodes it using the Huffman algorithm,
 *  and decodes it by traversing the binary tree based on the encoded string.
 * 
 *  Though it can accommodate any Java char, it is hard-coded to cap at ASCII #255).
 *  Supplying characters outside of this range will break it (likely throwing an index out of bounds error).
 *  
 *  
 */
 
 import org.junit.Test;
 import org.junit.Assert;

public class Huffman {

	int testCount = 0;
	
	public static void main(String[] args) {

		String rawText = "You should supply your own string to compress from the command line!";
		try{rawText = args[0];}//try for command line arg. Use default otherwise.
		catch(Exception e){System.out.println("Using default string...");}
		
		System.out.println("String: " + rawText);
		Encoder e1 = new Encoder(rawText);
		e1.encode();//encode the string using the huffman algorithm.
		
		System.out.println("The lookup table:");
		e1.printEncoded();
		
		Decoder d1 = new Decoder(e1.getEncoded(), e1.getRoot());
		System.out.println("Decoded to: " + d1.decode());

	}

	@Test
	public void runTests(){
		//test cases
		
		//tester("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
		tester("test me");//spaces
		tester("test\tme\ntoo");//tabs and new lines
		tester("don't forget about me&me&me");//special characters
	
	}
	
	public void tester(String input){
		Encoder e = new Encoder(input);
		e.encode();
		
		Decoder d = new Decoder(e.getEncoded(), e.getRoot());
		String decoded = d.decode();
		
		Assert.assertEquals(input, decoded);
		
	}

}
