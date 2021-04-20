package lab;


import java.util.*;
public class RailFenceCipher {
	public static void main(String args[]) {
		CipherRailFence cipher = new CipherRailFence(4);
		cipher.encipher();
		System.out.println(cipher.cipherText);
	}
}

class CipherRailFence {
	int stride;
	String plainText;
	String cipherText;
	public CipherRailFence(int fenceStride) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the plaintext");
		this.plainText = sc.nextLine();
		
		this.stride = fenceStride;
		
		sc.close();
	}
	
	public void encipher() {
		String res = "";
		int i,j=0,c=-1;
		String[] sub = new String[this.stride];
		for(i=0;i<this.stride;i++) {
			sub[i] = "";
		}
		for(i=0;i<this.plainText.length();i++) {
			sub[j] += this.plainText.charAt(i);
			if(i%(this.stride-1) == 0) {
				c = c==1?-1:1;
			}
			j=j+c;
		}
		
		for(i=0;i<this.stride;i++) {
			res += sub[i];
		}
		this.cipherText = res;
	}
}


