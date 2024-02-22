package practice.lv2;

import java.util.*;

public class RentAhotel {
/*
 * 호텔을 운영 중인 코니는 최소한의 객실만을 사용하여 예약 손님들을 받으려고 합니다.
 * 한 번 사용한 객실은 퇴실 시간을 기준으로 10분간 청소를 하고 다음 손님들이 사용할 수 있습니다.
 * 예약 시각이 문자열 형태로 담긴 2차원 배열 book_time이 매개변수로 주어질 때, 코니에게 필요한
 * 최소 객실의 수를 return 하는 solution 함수를 완성해주세요.
 * */
	public static void main(String[] args) {
		// 3
//		String[][] book_time = {{"15:00", "17:00"}
//							   ,{"16:40", "18:20"}
//							   ,{"14:20", "15:20"}
//							   ,{"14:10", "19:20"}
//							   ,{"18:20", "21:20"}};

		String[][] book_time = {{"14:10", "23:55"}
							   ,{"14:20", "15:50"}
							   ,{"15:00", "15:20"}
							   ,{"16:00", "18:00"}
							   ,{"18:10", "23:58"}
							   ,{"23:55", "23:58"}};

		// 3
//		String[][] book_time = {
//				{"10:20", "12:30"},{"10:20", "12:30"},{"10:20", "12:30"}
//		};

//		String[][] book_time = {
//				{"09:10", "10:10"},{"10:20", "12:20"}
//		};
		solution(book_time);

	}

	public static int solution(String[][] book_time) {
        int answer = 0;

        Arrays.sort(book_time,(o1, o2) -> {
        	String[] temp = o1[0].split(":");
        	String str = "";
        	str += temp[0]; str += temp[1];

        	int time1 = Integer.parseInt(str);

        	temp = o2[0].split(":");
        	str = "";
        	str += temp[0]; str += temp[1];

        	int time2 = Integer.parseInt(str);

        	return time1 - time2;
        });

        print(book_time);

        int count = 1;
        int row = book_time.length;

        int[] rooms = new int[row];
        rooms[0] = 0;
        for(int i = 1; i < row; i++) {
        	boolean chk = false;
    		String[] strTime = book_time[i][0].split(":");
    		int nextStart = Integer.parseInt(strTime[0] + strTime[1]);
    		System.out.println("시작 시간 = " + nextStart);

        	for(int room = 0; room < count; room++) {
        		strTime = book_time[rooms[room]][1].split(":");
        		String endMm = String.format("%02d", (Integer.parseInt(strTime[1]) + 10) % 60);
        		String endHh =  String.format("%02d", (Integer.parseInt(strTime[0])
        											+ (Integer.parseInt(strTime[1]) + 10) / 60));
        		System.out.println("끝나는 시간 = " + endHh + "" + endMm);
        		int endTime = Integer.parseInt(endHh + endMm);

        		if(nextStart >= endTime) {
        			chk = true;
        			System.out.println("가능한 방이 있음.");

        			rooms[room] = i;
        			break;
        		} else {
        			rooms[count] = i;
        		}
        	}
        	if(!chk) count++;
        	System.out.println();
        }
    	System.out.println("정답 = " + count);
        return answer;
    }

	public static void print(String[][] book_time) {
		int row = book_time.length;

		for(int i = 0; i < row; i++) {
			for(int j = 0; j < 2; j++) {
				System.out.print(book_time[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
