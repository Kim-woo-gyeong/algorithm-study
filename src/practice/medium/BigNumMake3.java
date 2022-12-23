package practice.medium;

import java.util.Stack;

public class BigNumMake3 {

	public static void main(String[] args) {
		String number = "4177252841";
		int k = 4;
//		String number = "1924";
//		int k = 2;
//		String number = "1231234";
//		int k = 3;

		String[] newArr = number.split("");
		int _length = newArr.length;
		StringBuilder sb = new StringBuilder();

		int next = 0;
		int temp = next;

		while(k < _length) {
			int max = number.charAt(next) - '0';
			temp = next;

			for(int z = next; z < _length; z++) {
				if(k < z) {
					break;
				}

				if(max < number.charAt(z) - '0') {
					max = number.charAt(z) - '0';
					temp = z;
				}
			}

			sb.append(max);
//			System.out.println(sb);
			k++;
			next = temp + 1;
		}


		char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }
        for (int i=0; i<result.length; i++) {
            result[i] = stack.get(i);
        }
//        return new String(result);
	}

}
