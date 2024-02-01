package practice.lv2;

import java.util.*;

public class GetMineral {
/*
 * 마인은 곡괭이로 광산에서 광석을 캐려고 합니다. 마인은 다이아몬드 곡괭이, 철 곡괭이, 돌 곡괭이를 각각 0개에서 5개까지 가지고 있으며,
 * 곡괭이로 광물을 캘 때는 피로도가 소모됩니다. 각 곡괭이로 광물을 캘 때의 피로도는 아래 표와 같습니다.
 * 예를 들어, 철 곡괭이는 다이아몬드를 캘 때 피로도 5가 소모되며, 철과 돌을 캘때는 피로도가 1씩 소모됩니다.
 * 각 곡괭이는 종류에 상관없이 광물 5개를 캔 후에는 더 이상 사용할 수 없습니다.
 * 마인은 다음과 같은 규칙을 지키면서 최소한의 피로도로 광물을 캐려고 합니다.
 *
사용할 수 있는 곡괭이중 아무거나 하나를 선택해 광물을 캡니다.
한 번 사용하기 시작한 곡괭이는 사용할 수 없을 때까지 사용합니다.
광물은 주어진 순서대로만 캘 수 있습니다.
광산에 있는 모든 광물을 캐거나, 더 사용할 곡괭이가 없을 때까지 광물을 캡니다.
즉, 곡괭이를 하나 선택해서 광물 5개를 연속으로 캐고, 다음 곡괭이를 선택해서 광물 5개를 연속으로 캐는 과정을 반복하며,
더 사용할 곡괭이가 없거나 광산에 있는 모든 광물을 캘 때까지 과정을 반복하면 됩니다.

마인이 갖고 있는 곡괭이의 개수를 나타내는 정수 배열 picks와 광물들의 순서를 나타내는 문자열 배열 minerals가 매개변수로 주어질 때,
마인이 작업을 끝내기까지 필요한 최소한의 피로도를 return 하는 solution 함수를 완성해주세요.
 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] picks = {1,3,2};
//		String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
		// picks =  [dia, iron, stone];
		/* 다이아몬드 곡괭이 1개 -> 다이아(1) + 다이아(1) + 다이아(1) + 철(1) + 철(1) : 피로도 5
		 * 철 곡괭이 1개 -> 다이아 (5) + 철(1) + 돌(1) : 피로도 7.
		 * */

		// [2,0,3]
		// ["diamond", "diamond", "diamond", "iron", "iron", "stone", "stone", "stone", "stone", "stone", "diamond", "diamond"]
		// 다이아 곡괭이 -> 다 + 다 + 다 + 철 + 철 = 피로도 5
		// 다이아 곡괭이 -> 돌 + 돌 + 돌 + 돌 + 다이아 = 피로도 5
		// 다이아 곡괭이 -> 다 + 다 = 50 => 2

		// 다이아 3개 철 2개 / 돌5개 / 다이아2개

		// 다이아 곡괭이 -> 다 + 다 + 다 + 철 + 철 = 피로도 5
		// 돌 곡괭이 -> 돌 + 돌 + 돌 + 돌 + 다이아 = 피로도 29
		// 다이아 곡괭이 -> 다 + 다 = 피로도 2

		//[1,0,3]
		// 다이아 곡괭이 -> 돌 + 돌 + 돌 + 돌 + 돌 = 피로도 5
		// 돌 곡괭이 -> 다 + 다 + 다 + 다 + 다 = 피로도 120
//		int[] picks = {2,0,3};
//		String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "stone", "stone", "stone", "stone", "stone", "diamond", "diamond"};

		int[] picks = {0, 0, 1};
		String[] minerals = {"stone", "stone", "stone", "stone", "stone", "diamond"};
		solution(picks, minerals);
	}

    public static int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int maxSize = Math.min((picks[0] + picks[1] + picks[2])*5, (int)Math.ceil(minerals.length / 5.0));
        int[][] arr = new int[maxSize][3];

        int idx = -1; int cnt = 0;
        for(String item : minerals) {
        	if(cnt % 5 == 0) idx++;
        	if((picks[0] + picks[1] + picks[2])*5 <= cnt) break;
        	cnt++;
        	if(item.equals("diamond")) arr[idx][0] += 1;
        	else if(item.equals("iron")) arr[idx][1] += 1;
        	else arr[idx][2] += 1;
        }

        for(int i = 0; i < maxSize; i++) {
        	for(int j = 0; j < 3; j++) {
        		System.out.print(arr[i][j] + "\t");
        	}
        	System.out.println();
        }
        System.out.println("----");

        // 다이아몬드로 내림차순.
        Arrays.sort(arr, (x1,x2) ->{
	        	if(x1[0] == x2[0]) {
	        		return x2[1] - x1[1];
	        	}
	        	return x2[0] - x1[0];
	        } // 내림차순
        );

        int sum = 0;
        for(int i = 0; i < idx+1; i++) {
        	int pick = 0;
        	for(int j = 0; j < 3; j++) {
        		System.out.print(arr[i][j] +"\t");
        		if(picks[0] != 0) { // 다이아몬드 곡괭이
        			sum+=(1*arr[i][j]);
        			pick = 0;
        		}else if(picks[1] != 0) { // 철곡괭이
        			if(j == 0) sum+=(5*arr[i][j]); // 다이아
        			else sum+=(1*arr[i][j]);
        			pick = 1;
        		}else if(picks[2] != 0){ // 스톤곡괭이
        			if(j == 0) sum+=(25*arr[i][j]);
        			else if(j==1) sum+=(5*arr[i][j]);
        			else sum+=(1*arr[i][j]);
        			pick = 2;
        		}else {
        			break;
        		}
        	}
        	picks[pick] -= 1;
        	System.out.println("곡괭이 = " + pick + " sum =" + sum);
        }
        System.out.println(sum);
        answer = sum;
        return answer;
    }
}
