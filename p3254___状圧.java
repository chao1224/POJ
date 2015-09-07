import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p3254___×´ˆR {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, condition[], modder = 100000000, s[][], type[], mark[][];

	public static void main(String[] args) throws IOException {
		n = nextInt();
		m = nextInt();
		int tmp;
		if (n < m) {
			condition = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					tmp = nextInt();
					condition[i] = condition[i] << 1 | tmp;
				}
			}
		} else {
			n = n ^ m;
			m = n ^ m;
			n = n ^ m;
			condition = new int[n + 1];
			for (int i = 1; i <= m; i++) {
				for (int j = 1; j <= n; j++) {
					tmp = nextInt();
					condition[j] = condition[j] << 1 | tmp;
				}
			}
		}

		s = new int[n + 2][1 << m | 1];
		type = new int[n + 1];
		preWork(0, 0);
		int[] record = new int[1 << m + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(record, -1);
			for (int j = 1; j <= type[0]; j++) {
				if (record[s[0][j] & condition[i]] == -1) {
					record[s[0][j] & condition[i]] = 0;
					s[i][++type[i]] = s[0][j] & condition[i];
				}
			}
		}

		mark = new int[n + 1][type[0] + 1];
		out.println(dfs(n, 0));
		out.flush();
		out.close();
	}

	static void preWork(int line, int state) {
		if (line == m) {
			s[0][++type[0]] = state;
		} else {
			preWork(line + 1, state << 1);
			if ((state & 1) == 0)
				preWork(line + 1, (state << 1) ^ 1);
		}
	}

	static int dfs(int line, int preCondition) {
		if (mark[line][preCondition] != 0)
			return mark[line][preCondition];
		if (line >= 1) {
			int ans = 0;
			for (int i = 1; i <= type[line]; i++) {
				if ((s[line][i] & s[line + 1][preCondition]) == 0) {
					ans += dfs(line - 1, i) % modder;
					ans = (ans % modder + modder) % modder;
				}
			}
			mark[line][preCondition] = ans;
			return ans;
		} else {
			return 1;
		}
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
