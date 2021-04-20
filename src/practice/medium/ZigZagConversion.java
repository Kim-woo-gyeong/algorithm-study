package practice.medium;

public class ZigZagConversion {
	public static void main(String[] args) {
        String result = convert("PAYPALISHIRING", 3);
        
        System.out.println(result);
    }

    public static String convert(String s, int numRows) {
        if(numRows == 1 ) {
            return s;
        } else {
         int array [][] = new int[numRows][s.length()];

         int idx1 = 0;
         int idx2 = 0;
         int idx3 = 0;
         int num = 0;
         
         StringBuffer new_s = new StringBuffer();
         new_s.append(s.charAt(0)); 
         for(int i = 0; i < s.length(); i+=(numRows-1)) {
			 if(idx2%2 == 0) {
				 for(int j = 0; j < numRows; j++) {
					 array[idx1][idx3] = num;	
					 idx1++;
					 num++;
				 }
				 idx1--;
			 } else {
				 for(int j = numRows-1; j > 0; j--) {
					 idx1--;
					 idx3++;
					 array[idx1][idx3] = num;
					 num++;
				 }
				 num--;
			 }			 
			 idx2++;
		 }
		 for( int i = 0 ; i < numRows; i++) {
			 for(int j = 0; j < s.length(); j++) {
				 if(!(i==0 && j==0) && array[i][j]!=0) {
					 if(array[i][j] >= s.length()) {
						 break;
					 }
					 new_s.append(s.charAt(array[i][j]));
				 }
			 }
		 }
            return new_s.toString();
        }     
    }    
}
