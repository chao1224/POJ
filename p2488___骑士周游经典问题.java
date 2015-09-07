import java.util.Scanner;

public class p2488___骑士周游经典问题 {
	static int total_num, current_num, p, q, times = 1;
	static String[][] pre;
	static String[] answer;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int turn = scan.nextInt();
		for (int tt = 1; tt <= turn; tt++) {
			System.out.println("Scenario #" + tt + ":");
			q = scan.nextInt();
			p = scan.nextInt();
			boolean[][] board = new boolean[p + 1][q + 1];
			for (int i = 1; i <= p; i++)
				for (int j = 1; j <= q; j++)
					board[i][j] = true;
			pre = new String[p + 1][q + 1];
			times = 1;
			total_num = p * q;
			current_num = 0;
			answer = new String[p * q + 1];

			board[1][1] = false;
			dfs(board, 1, 1, 0);

			if (current_num == total_num) {
				for (int t = 1; t <= p * q; t++)
					System.out.print(answer[t]);
				System.out.println();
			} else {
				System.out.println("impossible");
			}
			if (tt != turn)
				System.out.println();
		}
	}

	static void dfs(boolean[][] board, int i, int j, int current) {
		current++;
		// System.out.println(current);
		// System.out.println(i + " " + j);
		// System.out.println();

		if (current == total_num && times == 1) {
			String temp = i + "" + j;
			for (int tt = p * q; tt >= 1; tt--) {
				i = (int) temp.charAt(0) - 48;
				j = (int) temp.charAt(1) - 48;
				temp = pre[i][j];
				answer[tt] = (char) (i + 64) + "" + j;
			}
			current_num = total_num;
			times = 2;
		}

		if (i > 2) {
			if (j > 1)
				if (board[i - 2][j - 1]) {
					board[i - 2][j - 1] = false;
					pre[i - 2][j - 1] = i + "" + j;
					dfs(board, i - 2, j - 1, current);
					board[i - 2][j - 1] = true;
				}
			if (j + 1 <= q)
				if (board[i - 2][j + 1]) {
					board[i - 2][j + 1] = false;
					pre[i - 2][j + 1] = i + "" + j;
					dfs(board, i - 2, j + 1, current);
					board[i - 2][j + 1] = true;
				}
		}

		if (i > 1) {
			if (j > 2)
				if (board[i - 1][j - 2]) {
					board[i - 1][j - 2] = false;
					pre[i - 1][j - 2] = i + "" + j;
					dfs(board, i - 1, j - 2, current);
					board[i - 1][j - 2] = true;
				}
			if (j + 2 <= q)
				if (board[i - 1][j + 2]) {
					board[i - 1][j + 2] = false;
					pre[i - 1][j + 2] = i + "" + j;
					dfs(board, i - 1, j + 2, current);
					board[i - 1][j + 2] = true;
				}
		}

		if (i + 1 <= p) {
			if (j > 2)
				if (board[i + 1][j - 2]) {
					board[i + 1][j - 2] = false;
					pre[i + 1][j - 2] = i + "" + j;
					dfs(board, i + 1, j - 2, current);
					board[i + 1][j - 2] = true;
				}
			if (j + 2 <= q)
				if (board[i + 1][j + 2]) {
					board[i + 1][j + 2] = false;
					pre[i + 1][j + 2] = i + "" + j;
					dfs(board, i + 1, j + 2, current);
					board[i + 1][j + 2] = true;
				}
		}

		if (i + 2 <= p) {
			if (j > 1)
				if (board[i + 2][j - 1]) {
					board[i + 2][j - 1] = false;
					pre[i + 2][j - 1] = i + "" + j;
					dfs(board, i + 2, j - 1, current);
					board[i + 2][j - 1] = true;
				}
			if (j + 1 <= q)
				if (board[i + 2][j + 1]) {
					board[i + 2][j + 1] = false;
					pre[i + 2][j + 1] = i + "" + j;
					dfs(board, i + 2, j + 1, current);
					board[i + 2][j + 1] = true;
				}
		}

		current--;
	}
}