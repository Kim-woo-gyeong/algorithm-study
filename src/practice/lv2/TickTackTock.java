package practice.lv2;

import java.util.*;

public class TickTackTock {

	public static void main(String[] args) {
		// 1
//		String[] board = {"O.X", ".O.", "..X"};

		// 0
//		String[] board = {"OOO", "...", "XXX"};

//		String[] board = {"...", ".X.", "..."};

//		String[] board = {"O.X", "OOX", "O.X"};

//		String[] board = {"...", ".O.", "..."};

//		String[] board = {"OXX", "OOX", "OXO"};

//		String[] board = {"...", "...","..."};

		String[] board = {"OXO","XOX","OXO"};

		solution(board);
	}

    public static int solution(String[] board) {
    	/*
    	 * 정상적인 틱택톡 게임 조건.
    	 * 1. O와 X 갯수가 같거나 O가 1개 더 많은 경우
    	 * 2. O와 X 갯수가 같을때 무승부 이거나  후공인 X가 이겨야 함.
    	 * 3. O 갯수가 1개 더 많을 때, 무승부 이거나  O가 이겨야 함.
    	 * */
        int answer = 1;

        int width = 3; int height = 3;
        int[] count = new int[2];
        for(int i = 0; i < height; i++) {
        	for(int j = 0; j < width; j++) {
        		if(board[i].charAt(j) == 'O') {
        			count[0]++;
        		} else if(board[i].charAt(j) == 'X') {
        			count[1]++;
        		}
        	}
        }

        // O가 한개 더 많은 경우에는 무승부 이거나 O가 이겨야 함.
        if(count[0] - count[1] == 1) {
        	boolean chk = chkBoard(board, "X");
        	if(chk) {
        		answer = 0;
        	}
        }

        if(count[0] == count[1]) {
        	boolean chk = chkBoard(board, "O");
        	if(chk) {
        		answer = 0;
        	}
        }

        if(count[0] < count[1] || count[0] - count[1] > 1) {
        	answer = 0;
        }

        System.out.println(" 정답 : " + answer);
        return answer;
    }

    public static boolean chkBoard(String[] board, String turn) {
    	int width = 3;
        int height = 3;

        int cnt = 0;
    	int nx = 0; int ny = 0;
    	// 대각선으로 빙고인지.
    	if((turn.equals(String.valueOf(board[0].charAt(0)))
    		&& turn.equals(String.valueOf(board[1].charAt(1)))
    		&& turn.equals(String.valueOf(board[2].charAt(2)))
    		) || (turn.equals(String.valueOf(board[0].charAt(2)))
    	    		&& turn.equals(String.valueOf(board[1].charAt(1)))
    	    		&& turn.equals(String.valueOf(board[2].charAt(0)))
    	    		)) {
    		return true;
    	}

    	// 세로로 완성됐는지.
    	for(int j = 0; j < width; j++) {
    		cnt = 0;
    		for(int i = 0; i < height; i++) {
    			if(turn.equals(String.valueOf(board[i].charAt(j)))) {
    				cnt++;
    			}
    		}
    		if(cnt == height) return true;
    	}


    	// 가로로 완성됐는지.
    	for(int i = 0; i < height; i++) {
    		cnt = 0;
    		for(int j = 0; j < width; j++) {
    			if(turn.equals(String.valueOf(board[i].charAt(j)))) {
    				cnt++;
    			}
    		}
    		if(cnt == width) return true;
    	}


    	return false;

    }

	class Solution {
		public int solution(String[] board) {
			int answer = 1;
			int[] count = getCount(board);
			boolean o = false;
			boolean x = false;
			for (int i = 0; i < board.length; i++) {
				String s = board[i];
				if (s.equals("OOO")) {
					o = true;
				} else if (s.equals("XXX")) {
					x = true;
				}
				if (board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)) {
					if (board[0].charAt(i) == 'O') {
						o = true;
					} else if (board[0].charAt(i) == 'X') {
						x = true;
					}
				}
			}
			if (board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)
					|| board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)) {
				if (board[1].charAt(1) == 'O') {
					o = true;
				} else if (board[1].charAt(1) == 'X') {
					x = true;
				}
			}
			if (count[0] == count[1]) {
				if ((!o && !x) || (x && !o)) {
					return 1;
				}
			} else if (count[0] == count[1] + 1) {
				if ((!o && !x) || (o && !x)) {
					return 1;
				}
			}
			return 0;
		}

		private int[] getCount(String[] board) {
			int[] count = new int[2];
			for (String s : board) {
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == 'O') {
						count[0]++;
					} else if (s.charAt(i) == 'X') {
						count[1]++;
					}
				}
			}
			return count;
		}
	}

}
