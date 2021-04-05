import java.util.*;

public class HillCipher {
	public static void main(String args[]) {
		Cipher c = new Cipher();
		if(c.choice == 1) {
			System.out.println("Encrypt");
			c.encipher();
			System.out.print("cipher text = ");
			System.out.println(c.cipherText);
		}else if(c.choice == 2) {
			System.out.println("Decrypt");
			//c.decipher();
			System.out.print("plain text = ");
			System.out.println(c.plainText);
		}
		System.out.println(c.cipherText);
		
	}
}

class Cipher {
	String plainText;
	String cipherText;
	String key;
	int n;
	int choice;
	Cipher(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose option");
		System.out.println("1.Encrypt.");
		System.out.println("2.Decrypt.");
		this.choice = sc.nextInt();
		
		if(choice == 1) {
			System.out.println("Enter plaintext");
			this.plainText = sc.nextLine().toUpperCase();
			
			System.out.println("Enter the key");
			this.key = sc.nextLine().toUpperCase();
		}else if(choice == 2) {
			System.out.println("Enter ciphertext");
			this.cipherText = sc.nextLine().toUpperCase();
			
			System.out.println("Enter the key");
			this.key = sc.nextLine().toUpperCase();
		}
		else {
			System.out.println("Invalid Choice");
		}
		
		this.n = (int)Math.sqrt(key.length());
	}
	
	public void encipher() {
		String res = "";
		int mat[][] = makeMat();
		int temp[] = new int[n];
		int tempRes[] = new int[n];
		for(int i=0;i<plainText.length();i+=n) {
			 for(int j=0;j<n;j++) {
				 temp[j] = (int)plainText.charAt(i+j) -65;
			 }
			 
			 tempRes = matMul(temp,mat);
			 
			 for(int j=0;j<n;j++) {
				 tempRes[j] = tempRes[j]%26;
				 res+=(char)(tempRes[j]+65);
			 }
			 
		}
		
		this.cipherText = res;
	}
	
	public void decipher() {
		String res = "";
		int mat[][] = makeMat();
		int temp[] = new int[n];
		int tempRes[] = new int[n];
		int matInv[][] = new int[n][n];
		matInv = invMat(mat);
		
		
	}
	

	
	
	public int[][] makeMat() {
		int i=0,j=0;
		int mat[][] = new int[n][n];
		
		for(i=0;i<n;i++) {
			for(j=0;j<n;j++) {
				mat[i][j] = ((int)this.key.charAt(i*n+j) - 65);
			}
		}
		
		return mat;
	}
	
	public int[] matMul(int a[],int b[][]) {
		int temp[] = new int[n];
		
		for(int i=0;i<n;i++) {
			temp[i] = 0;
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				temp[i] += b[i][j]*a[j];
			}
		}
		return temp;
	}
}
