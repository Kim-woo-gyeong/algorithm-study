package practice.medium;

import java.util.ArrayList;
import java.util.List;

public class Dev_2021_02 {
/*
 * 문제 설명
rows x columns 크기인 행렬이 있습니다.
행렬에는 1부터 rows x columns까지의 숫자가 한 줄씩 순서대로 적혀있습니다.
이 행렬에서 직사각형 모양의 범위를 여러 번 선택해, 테두리 부분에 있는 숫자들을 시계방향으로
회전시키려 합니다. 각 회전은 (x1, y1, x2, y2)인 정수 4개로 표현하며, 그 의미는 다음과 같습니다.

x1 행 y1 열부터 x2 행 y2 열까지의 영역에 해당하는 직사각형에서 테두리에 있는 숫자들을 한 칸씩
시계방향으로 회전합니다. 다음은 6 x 6 크기 행렬의 예시입니다.

grid_example.png

이 행렬에 (2, 2, 5, 4) 회전을 적용하면, 아래 그림과 같이 2행 2열부터 5행 4열까지 영역의 테두리가
시계방향으로 회전합니다. 이때, 중앙의 15와 21이 있는 영역은 회전하지 않는 것을 주의하세요.

rotation_example.png

행렬의 세로 길이(행 개수) rows, 가로 길이(열 개수) columns,
그리고 회전들의 목록 queries가 주어질 때, 각 회전들을 배열에 적용한 뒤,
그 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 배열에 담아 return 하도록
solution 함수를 완성해주세요.

제한사항
rows는 2 이상 100 이하인 자연수입니다.
columns는 2 이상 100 이하인 자연수입니다.
처음에 행렬에는 가로 방향으로 숫자가 1부터 하나씩 증가하면서 적혀있습니다.
즉, 아무 회전도 하지 않았을 때, i 행 j 열에 있는 숫자는 ((i-1) x columns + j)입니다.
queries의 행의 개수(회전의 개수)는 1 이상 10,000 이하입니다.
queries의 각 행은 4개의 정수 [x1, y1, x2, y2]입니다.
x1 행 y1 열부터 x2 행 y2 열까지 영역의 테두리를 시계방향으로 회전한다는 뜻입니다.
1 ≤ x1 < x2 ≤ rows, 1 ≤ y1 < y2 ≤ columns입니다.
모든 회전은 순서대로 이루어집니다.
예를 들어, 두 번째 회전에 대한 답은 첫 번째 회전을 실행한 다음, 그 상태에서 두 번째 회전을 실행했을 때
이동한 숫자 중 최솟값을 구하면 됩니다.

입출력 예시
rows	columns	queries	result
6	6	[[2,2,5,4],[3,3,6,6],[5,1,6,3]]	[8, 10, 25]
3	3	[[1,1,2,2],[1,2,2,3],[2,1,3,2],[2,2,3,3]]	[1, 1, 5, 3]
100	97	[[1,1,100,97]]	[1]
입출력 예 설명
입출력 예 #1

회전을 수행하는 과정을 그림으로 표현하면 다음과 같습니다.
example1.png
입출력 예 #2

회전을 수행하는 과정을 그림으로 표현하면 다음과 같습니다.
example2.png
입출력 예 #3

이 예시에서는 행렬의 테두리에 위치한 모든 칸들이 움직입니다. 따라서, 행렬의 테두리에 있는 수 중 가장 작은 숫자인 1이 바로 답이 됩니다.
 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int rows = 6;
//		int columns = 6;
//		int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};

		int rows = 3;
		int columns = 3;
		int[][] queries = {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};

		solutions(rows, columns, queries);
	}

	public static void solutions(int rows, int columns, int[][] queries) {

		int[] answer = new int[queries.length];
		int[][] array = new int[rows+1][columns+1];
		int[][] temp = new int[rows+1][columns+1];
		int count = 1;
		for(int i = 1; i <= rows; i++) {
			for(int j = 1; j <= columns; j++) {
				array[i][j] = count;
				temp[i][j] = count;
				count++;
			}
		}

		//println(array,rows,columns);
		int _length = 0;
		for(int[] item : queries) {

			int startRow = item[0];
			int startCell = item[1];
			int endRow = item[2];
			int endCell = item[3];


			int row = startRow; int cell = startCell+1;
			int min = array[row][cell];
			while(!(row == startRow && cell == startCell)) {
				if(row == startRow && cell < endCell) {
					// ex) array[2][4] = temp[2][3]
					array[row][cell+1] = temp[row][cell];
					cell++; // 4
				} else if(cell == endCell && row < endRow) {
					// array[3][4] = temp[2][4]
					array[row+1][cell] = temp[row][cell];
					row++; // 3 4
				} else if(row == endRow && cell > startCell) {
					// array[5][3] = temp[5][4]
					array[row][cell-1] = temp[row][cell];
					cell--;
				} else if(cell == startCell && row > startRow) {
					// array[5][2] = temp[5][2]
					array[row-1][cell] = temp[row][cell];
					row --;
				}

				if(min > temp[row][cell]) {
					min = temp[row][cell];
				}

			}
			//System.err.print(temp[row][cell] + "\t");
			array[row][cell+1] = temp[row][cell];
			if(min > temp[row][cell]) {
				min = temp[row][cell];
			}
			copy(array,temp,rows);
			//System.out.println();
			//println(array,rows,columns);
			answer[_length] = min;
			_length++;
		}

		for(int i = 0; i < _length; i++) {
			System.out.println(answer[i]);
		}
	}

	public static void copy(int[][] array, int[][] temp, int rows) {
		for(int i = 1; i <= rows; i++) {
			temp[i] = array[i].clone();
		}
	}

	public static void println(int[][] array, int rows, int columns) {
		for(int i = 1; i <= rows; i++) {
			for(int j = 1; j <= columns; j++) {
				System.out.print(array[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
