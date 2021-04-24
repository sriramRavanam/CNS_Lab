package lab;


import java.util.*;
public class RailFenceCipher {
	public static void main(String args[]) {
		
		CipherRailFence cipher = new CipherRailFence(3);
		//cipher.encipher();
		//System.out.println(cipher.cipherText);
		
		cipher.decipher();
		System.out.println("PlainText after Decipher");
		System.out.println(cipher.plainText);
	}
}

class CipherRailFence {
	int stride;
	String plainText;
	String cipherText;
	public CipherRailFence(int fenceStride) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the ciphertext");
		this.cipherText = sc.nextLine();
		
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
	
	public void decipher() {
		String res = "";
		int i,j=0,c;
		char temp;
		int counter[] = new int[this.stride];
		
		int div[] = getDivisions();
		String subDiv[] = new String[this.stride];
		int sum = 0;
		
		for(i = 0;i<this.stride;i++) {
			subDiv[i] = this.cipherText.substring(sum, sum+div[i]);
			sum+=div[i];
			System.out.println(subDiv[i]);
			//initialization
			counter[i] = 0;
		}
		j=0;
		c=-1;
		for(i=0;i<this.cipherText.length();i++) {
			res+= subDiv[j].charAt(counter[j]);
			counter[j]+=1;
			
			if(j==0) {
				c = 1;
			} else if(j == this.stride-1) {
				c = -1;
			}
			
			j = j + c;
			
		}
		
		
		
		this.plainText = res;
	}
	
	private int[] getDivisions() {
		
		//here the first and last elements occur once in the encryption, the remaining elements occur twice 
		//hence for end elements we divide by 2*(this.stride - 1).
		int i;		
		int div[] = new int[this.stride];
		int jumpstart = (int)((this.cipherText.length()-1)/(2*this.stride - 2));
		int jumpend = (int)((this.cipherText.length()-1 - (this.stride-1))/(2*this.stride-2));
		jumpstart +=1;
		jumpend +=1;
			
		for(i = 1;i<this.stride-1;i++) {
			div[i] = (int)((this.cipherText.length()-1 - i)/(this.stride-1));
			div[i] += 1;
		} 
		div[0] = jumpstart;
		div[this.stride-1] = jumpend;
		
		
		return div;
	}
}


