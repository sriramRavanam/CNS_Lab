//so far, identified one error - taking characters together


class playFair {
	public static void main(String args[]) {
		cipher c = new cipher();
		System.out.println(c.encipher("hello", "cse"));
	}
}

class cipher{
	
	private char[][] makeMatrix(String key){
		char[][] mat = new char[5][5];
		
		char[] a = new char[26];
		int[] b = new int[26];
		
		//making array of letters and ifInserted
		for(int i=0;i<26;i++) {
			a[i]=(char)(97+i);
			b[i]= 0;
			
		}
		
		//making matrix
		int x=0,y=0;
		for(int i=0;i<key.length();i++) {
			int temp = (int)(key.charAt(i));
			if((char)temp == 'j')
				temp--;
			temp-=97;
			if(b[temp]==0) {
				mat[x][y]=(char)(temp+97);
				b[temp]=1;
				x=(x+1)%5;
				if(x==0)
						y++;
			}
		}
		
		for(int i =0;i<26;i++) {
			int temp = (int)'j';
			if(b[(temp-97)]==0)
					b[(temp-97)]=1;
			if(b[i]==0) {
				mat[x][y]=(char)(i+97);
				x=(x+1)%5;
				if(x==0)
						y++;
			}
		}
		
		
		return mat;
	}
	
	public String encipher(String pt,String key) {
		
		String res = "";
		
		pt = pt.toLowerCase();
		key = key.toLowerCase();
		char[][] mat = new char[5][5];
		mat = makeMatrix(key);
		
		for(int i=0;i<pt.length();i+=2) {
			//TODO: if letters in plaintext is not a multiple of two, then add letter at end
			
			char c1 = pt.charAt(i);
			char c2;
			if(i == pt.length()-1) {
				c2 = 'x'; 
			}
			else {
				c2 = pt.charAt(i+1);
			}
			if(c1 == c2) {
				i--;
				c2='x';
			}
			int x1=0,x2=0,y1=0,y2=0;
			
			for(int j=0;j<5;j++)
				for(int k=0;k<5;k++) {
					if(mat[j][k] == c1) {
						x1 = k;
						y1 = j;
					}
					if(mat[j][k] == c2) {
						x2 = k;
						y2 = j;
					}
					
				}
			
			if(x1 == x2) {
				res+=(char)mat[(y1+1)%5][x1];
				res+=(char)mat[(y2+1)%5][x2];
			}
			else if(y1 == y2) {
				res+= (char)mat[y1][(x1+1)%5];
				res+= (char)mat[y2][(x2+1)%5];
			}
			else {
				res+= (char)mat[y1][x2];
				res+= (char)mat[y2][x1];
			}
		}
		
		return res;
		
	}
}
