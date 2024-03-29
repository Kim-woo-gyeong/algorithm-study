package practice.lv2;

import java.util.*;
public class CantorSet {
/*
 * 수학에서 칸토어 집합은 0과 1 사이의 실수로 이루어진 집합으로, [0, 1]부터 시작하여 각 구간을 3등분하여 가운데 구간을
 * 반복적으로 제외하는 방식으로 만들어집니다.남아는 칸토어 집합을 조금 변형하여 유사 칸토어 비트열을 만들었습니다.
 * 유사 칸토어 비트열은 다음과 같이 정의됩니다. 0 번째 유사 칸토어 비트열은 "1" 입니다.
 * n(1 ≤ n) 번째 유사 칸토어 비트열은 n - 1 번째 유사 칸토어 비트열에서의 1을 11011로 치환하고
 * 0을 00000로 치환하여 만듭니다. 남아는 n 번째 유사 칸토어 비트열에서 특정 구간 내의 1의 개수가 몇 개인지
 * 궁금해졌습니다. n과 1의 개수가 몇 개인지 알고 싶은 구간을 나타내는 l, r이 주어졌을 때 그 구간 내의
 * 1의 개수를 return 하도록 solution 함수를 완성해주세요.
 *
 * 2 번째 유사 칸토어 비트열은 "11011 11011 00000 11011 11011" 입니다.
 * 음영 표시된 부분은 폐구간 [4, 17] 이며 구간 내의 1은 8개 있습니다.
 * */
	public static void main(String[] args) {
		int n = 4;
		int l = 27;
		int r = 68;
		// 0


		solution(n, l, r);
	}

	public static int solution(int n, long l, long r) {
        int answer = 0;
        // 1. 한줄 사이즈 5^(n-1)
        // 2. 한줄 1의 갯수 4^(n-1)
        //    4*4*4 / 4*4*4 / 0 / 4*4*4
        // 3. 한개 그룹당 5^(n-2)
        double[] count = new double[5];
        long[] D = new long[2];
        long sum = 0;


        long number = l-1;
        int row = 1;
        for(int i = n; i > 0; i--) {
        	double a = Math.pow(5, i-1);

        	double b = Math.pow(4, i-1);
        	count = new double[] {b,b,0,b,b};

        	int x = (int) (number / a);
        	for(int j = 0; j < x; j++) {
        		sum += count[j];
        	}
        	number = number % (long) a;

        	if(x == 2) break;
        }
        D[0] = sum;
        System.out.println(sum);
        number = r;
        row = 1;
        sum = 0;
        for(int i = n; i > 0; i--) {
        	double a = Math.pow(5, i-1);

        	double b = Math.pow(4, i-1);
        	count = new double[] {b,b,0,b,b};

        	int x = (int) (number / a);
        	for(int j = 0; j < x; j++) {
        		sum += count[j];
        	}
        	number = number % (long) a;
        	if(x == 2) break;
        }
        D[1] = sum;
        System.out.println(sum);
        System.out.println(D[1] - D[0]);

        return answer;
    }

	public static void println(List<Integer> list) {
		int idx = 1;
		for(int item : list) {
			System.out.print(item + " ");
			if(idx % 25 == 0) {
				System.out.println();
			}
			idx++;
		}
	}
}
