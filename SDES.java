package lab;

import java.util.Scanner;


public class SDES {
	public static void main(String args[]) {
		 SDEScipher cipher = new SDEScipher();
		 System.out.println("Cipher text = "+cipher.encipher());
	}
}

class SDEScipher {
	String cipherText;
	String plainText;
	String key;
	private String key1;
	private String key2;
	public SDEScipher() {
		Scanner sc = new Scanner(System.in);
//		System.out.println("enter the 8 bit plaintext");
//		this.plainText = sc.nextLine();
//		System.out.println("enter the 10 bit key");
//		this.key = sc.nextLine();
		this.plainText = "11110011";
		this.key = "1010000010";
		if(this.key.length()!=10) {
			System.out.println("invalid key");
		} else {
			this.key1 = "";
			this.key2 = "";
			this.keyGeneration();
		}
		
		sc.close();
	}
	
	
	//TODO: error in key generation, getting 12 bit key
	private void keyGeneration() {
		final int p10[] = {3,5,2,7,4,10,1,9,8,6};
		final int p8[] = {6,3,7,4,8,5,10,9};
		
		String keyInit = this.key;
		
		String resp10 = ""; //resulting string after p10 permutation
		
		//p10 permutations
		for(int i=0;i<p10.length;i++) {
			resp10 += keyInit.charAt(p10[i]-1);
		}
		
		
		
		String leftHalf = resp10.substring(0,5);
		String rightHalf = resp10.substring(5,10);
		
		
		
		leftHalf = leftShift(leftHalf);
		rightHalf = leftShift(rightHalf);
		
		
		
		//combine halves again
		resp10 = leftHalf + rightHalf;
		
		
		
		for(int i=0;i<p8.length;i++) {
			this.key1 += resp10.charAt(p8[i]-1);	
		}
		
		
		
		leftHalf = leftShift(leftShift(leftHalf));
		rightHalf = leftShift(leftShift(rightHalf));
		
		resp10 = leftHalf + rightHalf;
		
		for(int i=0;i<p8.length;i++) {
			this.key2 += resp10.charAt(p8[i]-1);	
		}
		
	}
	

	
	public String encipher() {
		
		if(this.plainText.length() == 8) {
			return SDESfragment(this.plainText);
		} else {
			System.out.println("plainText not 8 bits");
			return "error";
		}
		

		
	}
	
	private String leftShift(String s) {
		String res = "";
		for(int i=0;i<s.length();i++) {
			res += s.charAt((i+1)%s.length());
		}
		return res;
	}
	
	private String SDESfragment(String string) {
		final int[] IP = {2,6,3,1,4,8,5,7};
		final int[] IPinv = {4,1,2,5,7,2,8,6};
		final int[] EP = {4,1,2,3,2,3,4,1};
		final int[][] S0 = {{1,0,3,2},{3,2,1,0},{0,2,1,3},{3,1,3,2}};
		final int[][] S1 = {{0,1,2,3},{2,0,1,3},{3,0,1,0},{2,1,0,3}};
		final int[] P4 = {2,4,3,1};
		
		String ip = "";
		String ep = "";
		String resP4 = "";
		String res = "";
		
		for(int i = 0;i<IP.length;i++) {
			ip+= string.charAt(IP[i]-1);
		}
		
		String left = ip.substring(0,(ip.length()/2));
		String right = ip.substring((ip.length()/2),ip.length());
		
		for(int i = 0;i<EP.length;i++) {
			ep += right.charAt(EP[i]-1); 
		}
		
		//perform bitwise XOR with key 1 and ep
		
		ep = XOR(ep,this.key1);
		
		String epleft = ep.substring(0,(ep.length()/2));
		String epright = ep.substring((ep.length()/2),ep.length());
		
		epleft = operationS(epleft,S0);
		epright= operationS(epright,S1);
		
		ep = epleft + epright;
		
		for(int i =0;i<P4.length;i++) {
			resP4 += ep.charAt(P4[i]-1);
		}
		
		resP4 = XOR(resP4,left);
		
		left = right;
		right = resP4;
		
		//starting of key2 computation
		
		resP4 = "";
		ep = "";
		for(int i = 0;i<EP.length;i++) {
			ep += right.charAt(EP[i]-1); 
		}
		
		
		
		//perform bitwise XOR with key 2 and ep
		
		ep = XOR(ep,this.key2);
		
		epleft = ep.substring(0,(ep.length()/2));
		epright = ep.substring((ep.length()/2),ep.length());
		
		epleft = operationS(epleft,S0);
		epright= operationS(epright,S1);
		
		ep = epleft + epright;
		
		for(int i =0;i<P4.length;i++) {
			resP4 += ep.charAt(P4[i]-1);
		}
		

		
		resP4 = XOR(resP4,left);
		
		String temp = resP4 + right;
		
		for(int i=0;i<IPinv.length;i++) {
			res += temp.charAt(IPinv[i]-1);
		}
		
		return res;
		
	}
	
	private String XOR(String s1,String s2) throws ArithmeticException {
		if(s1.length() == s2.length()) {
			String res = "";
			int a,b;
			for(int i=0;i<s1.length();i++) {
				a = Character.getNumericValue(s1.charAt(i));
				b = Character.getNumericValue(s2.charAt(i));
				
				res += (a^b);
			
			}
			
			return res;
			
		} else {
			System.out.println("strings not same length in XOR");
			throw new ArithmeticException("different length strings in XOR");
		}
		
	}
	
	private String operationS(String str, int[][] s) {
		char list[] = str.toCharArray();
 		int[] intList = new int[str.length()];
 		for(int i=0;i<str.length();i++) {
 			intList[i] = Character.getNumericValue(list[i]);
 		}
 		
 		int a = 2*intList[0] + intList[3];
 		int b = 2*intList[1] + intList[2];
 		
 		switch(s[a][b]) {
 			case 0:
 				return "00";
 				
 			case 1:
 				return "01";
 				
 			case 2:
 				return "10";
 				
 			case 3:
 				return "11";
 				
 			default:
 				System.out.println("error in operationS");
 				return "";
 		}
	}
	
}
