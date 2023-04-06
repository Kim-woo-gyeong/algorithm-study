package practice.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sun.net.NetworkServer;

public class wordDictionary {
/*
 * 문제 설명
사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 길이 5 이하의 모든 단어가 수록되어 있습니다.
사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.

단어 하나 word가 매개변수로 주어질 때, 이 단어가 사전에서 몇 번째 단어인지 return 하도록 solution 함수를 완성해주세요.

제한사항
word의 길이는 1 이상 5 이하입니다.
word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.
입출력 예
word	result
"AAAAE"	6
"AAAE"	10
"I"	1563
"EIO"	1189
입출력 예 설명
입출력 예 #1

사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA", "AAA", "AAAA", "AAAAA", "AAAAE", ... 와 같습니다.
"AAAAE"는 사전에서 6번째 단어입니다.

입출력 예 #2

"AAAE"는 "A", "AA", "AAA", "AAAA", "AAAAA", "AAAAE", "AAAAI", "AAAAO", "AAAAU"의 다음인
10번째 단어입니다.

입출력 예 #3

"I"는 1563번째 단어입니다.

입출력 예 #4

"EIO"는 1189번째 단어입니다.
 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String word = "AAAAE";	//6
//		String word = "AAAE";	//10
		String word = "I";	// 1563
//		String word = "EIO";	// 1189

		solutions(word);
	}
	private static String[] arr;
	private static Map<String,Double> result = new HashMap<String, Double>();

	// 수학적으로 푼 방법..
	public static void solutions2(String word) {
        int answer = 0, per = 3905;
        for(String s : word.split(""))
        	answer += "AEIOU".indexOf(s) * (per /= 5) + 1;
	}

	public static void solutions(String word) {
		String[] alpa = {"A","E","I","O","U"};

		for(int pick = 1; pick <= 5; pick++) {
			arr = new String[pick];
			dfs(alpa, pick, 0);
		}
		List<Map.Entry<String, Double>> entryList = new LinkedList<>(result.entrySet());
		entryList.sort(Map.Entry.comparingByValue());

		int rank = 1;
		for(Map.Entry<String, Double> entry : entryList){
			if(word.equals(entry.getKey())) {
				System.out.println(entry.getKey() + " 순위 : " + rank);
				break;
			}
			rank++;
		}
	}

	public static void dfs(String[] word, int pick, int depth) {
		if(pick == depth) {
			StringBuilder newWord = new StringBuilder();
			StringBuilder newWordInt = new StringBuilder();
			for(int i = 0; i < pick; i++) {
				newWord.append(arr[i]);
				newWordInt.append("AEIOU".indexOf(arr[i])+1);
			}
			if(result.get(newWord.toString()) == null)
				result.put(newWord.toString(), Double.parseDouble("0."+newWordInt));
			return;
		}

		for(String item : word) {
			for(int i = depth; i < pick; i++) {
				arr[i] = item;
				dfs(word, pick, i+1);
			}
		}

		return;
	}
}
