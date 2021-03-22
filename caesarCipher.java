
import java.util.*;

class caesar1 {
	public String encryptCaesar(String a) {
		int temp;
		String result = "";
		for(int i =0;i<a.length();i++) {
			char ch = a.charAt(i);
			if(Character.isUpperCase(ch)) {
				ch-=62;
				temp = ch%26;
				ch = (char)temp;
				ch+=65;
				result = result+ch;
			}
			else if(Character.isLowerCase(ch)) {
				ch-=94;
				temp = ch%26;
				ch = (char)temp;
				ch+=97;
				result = result+ch;
			}
			else if(Character.isDigit(ch)) {
				ch-=48;
				ch+=3;
				temp = ch%10;
				ch = (char)temp;
				ch+=48;
				result = result+ch;
			}
		}
		
		return result;
	}
	
	public String decryptCaesar(String a) {
		int temp;
		String result = "";
		for(int i =0;i<a.length();i++) {
			char ch = a.charAt(i);
			if(Character.isUpperCase(ch)) {
				ch-=65;
				temp = (int)ch;
				temp=(temp-3+26)%26;
				ch = (char)temp;
				ch+=65;
				result = result+ch;
			}
			else if(Character.isLowerCase(ch)) {
				ch-=97;
				temp = (int)ch;
				temp = (temp-3+26)%26;
				ch = (char)temp;
				ch+=97;
				result = result+ch;
			}
			else if(Character.isDigit(ch)) {
				ch-=48;
				temp = (int)ch;
				temp-=3;
				temp = (temp+10)%10;
				ch = (char)temp;
				ch+=48;
				result = result+ch;
			}
		}
		
		return result;
	}
	
	
	
}

class caesar {
	public static void main(String args[]) {
		System.out.println("enter the plaintext");
		Scanner sc = new Scanner(System.in);
		String plainText = sc.nextLine();
		caesar1 ob = new caesar1();
		System.out.println("cipher text = "+ob.encryptCaesar(plainText));
		
		String cipherText = ob.encryptCaesar(plainText);
		System.out.println("plaintext after decode = "+ob.decryptCaesar(cipherText));
	}
}



