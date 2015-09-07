import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class p1185___×´ˆRdp___MLE×î¿ì {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, condition[], s[][], type[], c[][], mark[][],
			replace[][][], repNum;

	public static void main(String[] args) throws IOException {
		n = nextInt();
		m = nextInt();
		String str;
		if (n > m) {
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
		} else {
			n = n ^ m;
			m = n ^ m;
			n = n ^ m;
			condition = new int[n + 1];
			for (int j = 1; j <= m; j++) {
				str = next();
				for (int i = 1; i <= n; i++) {
					if (str.charAt(i - 1) == 'P')
						condition[i] = condition[i] << 1 | 1;
					else
						condition[i] = condition[i] << 1;
				}
			}
		}
		// condition represents the position that can be put

		s = new int[n + 3][1 << m | 1];
		type = new int[n + 3];
		preWork(0, 0);
		c = new int[n + 1][type[0] + 1];
		int[] record = new int[1 << m + 1];
		int tmp;
		for (int i = 1; i <= n; i++) {
			Arrays.fill(record, -1);
			for (int j = 1; j <= type[0]; j++) {
				if (record[s[0][j] & condition[i]] == -1) {
					record[s[0][j] & condition[i]] = 0;
					s[i][++type[i]] = s[0][j] & condition[i];
					tmp = s[i][type[i]];
					while (tmp > 0) {
						c[i][type[i]]++;
						tmp -= (tmp & -tmp);
					}
				}
			}
		}
		type[n + 1] = 1;// very important!!!

		replace = new int[n + 1][type[0] + 1][type[0] + 1];
		preWork();
		System.out.println();

		mark = new int[n + 1][repNum + 1];
		for (int i = 0; i <= n; i++)
			Arrays.fill(mark[i], -1);
		out.println(dfs(n, 1, 1));
		out.flush();
		out.close();
	}

	static void preWork(int line, int state) {
		if (line == m) {
			s[0][++type[0]] = state;
		} else {
			preWork(line + 1, state << 1);
			if ((state & 1) == 0 && (state >> 1 & 1) == 0)
				preWork(line + 1, (state << 1) ^ 1);
		}
	}

	static void preWork() {
		for (int line = 0; line <= n; line++) {
			for (int s1 = 1; s1 <= type[line + 1]; s1++) {
				for (int s2 = 1; s2 <= type[line + 2]; s2++) {
					if ((s[line + 1][s1] & s[line + 2][s2]) == 0) {
						replace[line][s1][s2] = ++repNum;
					}
				}
			}
		}
	}

	static int dfs(int line, int curCondition, int preCondition) {
		if (line >= 1) {
			int ans = 0;
			for (int i = 1; i <= type[line]; i++) {
				if ((s[line][i] & s[line + 1][curCondition]) == 0
						&& (s[line][i] & s[line + 2][preCondition]) == 0) {
					if (mark[line - 1][replace[line - 1][i][curCondition]] == -1) {
						mark[line - 1][replace[line - 1][i][curCondition]] = dfs(
								line - 1, i, curCondition);
					}
					ans = Math.max(ans,
							mark[line - 1][replace[line - 1][i][curCondition]]
									+ c[line][i]);
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
