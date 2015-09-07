import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class p1185___改进版 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, condition[], s[], type, c[], mark[][], replace[][],
			repNum;

	public static void main(String[] args) throws IOException {
		n = nextInt();
		m = nextInt();
		String str;
		// if (n > m) {
		condition = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			str = next();
			for (int j = 0; j < m; j++) {
				if (str.charAt(j) == 'P')
					condition[i] = condition[i] << 1 | 1;
				else
					condition[i] = condition[i] << 1;
			}
		}
		// } else {
		// n = n ^ m;
		// m = n ^ m;
		// n = n ^ m;
		// condition = new int[n + 1];
		// for (int j = 1; j <= m; j++) {
		// str = next();
		// for (int i = 1; i <= n; i++) {
		// if (str.charAt(i - 1) == 'P')
		// condition[i] = condition[i] << 1 | 1;
		// else
		// condition[i] = condition[i] << 1;
		// }
		// }
		// }

		s = new int[1 << m | 1];
		preWork(0, 0);
		c = new int[1 << m | 1];
		for (int i = 1; i <= type; ++i) {
			long tmp = s[i];
			while (tmp != 0) {
				c[i]++;
				tmp -= (tmp & -tmp);
			}
		}

		replace = new int[type + 1][type + 1];
		preWork();

		mark = new int[n + 1][repNum + 1];
		for (int i = 0; i <= n; i++)
			Arrays.fill(mark[i], -1);

		out.println(dfs(n, 1, 1));
		out.flush();
		out.close();
	}

	static void preWork(int line, int state) {
		if (line == m) {
			s[++type] = state;
		} else {
			preWork(line + 1, state << 1);
			if ((state & 1) == 0 && (state >> 1 & 1) == 0)
				preWork(line + 1, (state << 1) ^ 1);
		}
	}

	static void preWork() {
		for (int i = 1; i <= type; i++) {
			for (int j = 1; j <= type; j++) {
				if ((s[i] & s[j]) == 0) {
					replace[i][j] = ++repNum;
				}
			}
		}
	}

	static int temp;

	static int dfs(int line, int curCondition, int preCondition) {
		if (line >= 1) {
			int ans = 0;
			int[] record = new int[1 << m | 1];
			for (int i = 1; i <= type; i++) {
				temp = s[i] & condition[line];
				if (record[temp] == 0) {
					record[temp] = 1;
					// out.println(Integer.toBinaryString(s[i]) + "   "
					// + Integer.toBinaryString(s[i] & condition[line]));
					// 能这么做的重要原因是这两个相等！！！
					// s[i] & condition[line] 与 s[i]
					if ((temp & s[curCondition]) == 0
							&& (temp & s[preCondition]) == 0) {
						if (mark[line - 1][replace[i][curCondition]] == -1)
							mark[line - 1][replace[i][curCondition]] = dfs(
									line - 1, i, curCondition);
						ans = Math
								.max(
										ans,
										mark[line - 1][replace[i][curCondition]]
												+ c[i]);
					}
				}
			}
			return ans;
		} else {
			return 0;
		}
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
