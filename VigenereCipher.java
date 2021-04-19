import java.util.*;
public class VigenereCipher {

	public static void main(String[] args) {
		Cipher cipher = new Cipher();
		cipher.encipher();
		System.out.println("Cipher text = ");
		System.out.println(cipher.cipherText);
		cipher.decipher();
		System.out.println("Plaintext after deciphering = ");
		System.out.println(cipher.plainText);

	}

}

class Cipher{
	String cipherText;
	String plainText;
	String key;
	public Cipher() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Key");
		this.key = sc.nextLine();
		
		System.out.println("Enter the PlainText");
		this.plainText = sc.nextLine();
		sc.close();
	}
	
	public void encipher() {
		String res = "";
		
		char[][] mat = makeTable();
		
		String temp = "";
		for(int i=0;i<plainText.length();i++) {
			temp += key.charAt(i%key.length());
		}
		
		for(int i = 0;i<plainText.length();i++) {
			int r = temp.charAt(i);
			int c = plainText.charAt(i);
			r = r-65;
			c = c-65;
			res += mat[r][c];
		}
		
		
		this.cipherText = res;
	}
	
	public void decipher() {
		String res = "";
		int i,r,c,j;
		
		char[][] mat = makeTable();
		
		String temp = "";
		
		for(i=0;i<cipherText.length();i++) {
			temp += key.charAt(i%key.length());
		}
		
		
		for(i =0;i<cipherText.length();i++) {
			r = temp.charAt(i);
			c = cipherText.charAt(i);
			r = r-65;
			j=0;
			for(j =0;j<26;j++) {
				if(mat[j][r] == c) {
					break;
				}
			}
			res += (char)(j+65);
		}
		
		this.plainText = res;
		
	}
	
	private char[][] makeTable(){
		char[][] tab = new char[26][26];
		
		for(int i=0;i<26;i++) {
			for(int j=0;j<26;j++) {
				tab[i][j] = (char)(((i+j)%26)+65);
			}
		}
		
		return tab;
	}
}
