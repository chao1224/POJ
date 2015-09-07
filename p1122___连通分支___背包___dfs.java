import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Map.Entry;

public class p1122___连通分支___背包___dfs {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int graph[][], color[], num[][], p[][][], cnt, n, path[][];
	static boolean dp[][];

	public static void main(String[] args) throws IOException {
		n = nextInt();
		graph = new int[n + 1][n + 1];
		color = new int[n + 1];
		num = new int[n + 1][2];
		Arrays.fill(color, -1);
		p = new int[n + 1][2][n + 1];

		int tmp;
		for (int i = 1; i <= n; i++) {
			while (true) {
				tmp = nextInt();
				if (tmp == 0)
					break;
				graph[i][tmp] = 1;
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				if (graph[i][j] != 0 && graph[j][i] != 0)
					graph[i][j] = graph[j][i] = 0;
				else
					graph[i][j] = graph[j][i] = 1;
			}
		}

		cnt = 0;
		for (int i = 1; i <= n; i++) {
			if (color[i] == -1) {
				color[i] = 0;
				if (!dfs(i)) {
					out.println("No solution");
					out.flush();
					out.close();
					return;
				}
				cnt++;
			}
		}
		compute();

		out.flush();
		out.close();
	}

	static boolean dfs(int u) {
		int col = color[u];
		int index = num[cnt][col];
		num[cnt][col]++;
		p[cnt][col][index] = u;
		for (int i = 1; i <= n; i++) {
			if (graph[u][i] != 0) {
				if (color[i] == -1) {
					color[i] = 1 - col;
					if (!dfs(i))
						return false;
				} else if (color[u] == color[i])
					return false;
			}
		}
		return true;
	}

	static void compute() {
		int i, j = 0, k, tmp = 0, res, col;
		dp = new boolean[n + 1][n + 1];
		Arrays.fill(dp[0], false);
		Arrays.fill(dp[1], false);
		dp[0][num[0][0]] = true;
		dp[0][num[0][1]] = true;

		path = new int[n + 1][n + 1];
		path[0][num[0][0]] = 0;
		path[0][num[0][1]] = 1;

		for (i = 1; i < cnt; i++) {
			for (j = n / 2; j >= 0; j--) {
				dp[i & 1][j] = false;
				tmp = j - num[i][0];
				if (tmp >= 0 && dp[(i - 1) & 1][tmp]) {
					dp[i & 1][j] = true;
					path[i][j] = 0;
					if (i == cnt - 1)
						break;
					continue;
				}
				tmp = j - num[i][1];
				if (tmp >= 0 && dp[(i - 1) & 1][tmp]) {
					dp[i & 1][j] = true;
					path[i][j] = 1;
					if (i == cnt - 1)
						break;
				}
			}
		}

		tmp = j;
		res = 0;
		for (i = cnt - 1; i >= 0; i--) {
			if (path[i][j] == 0) {
				res += num[i][0];
				j -= num[i][0];
			} else {
				res += num[i][1];
				j -= num[i][1];
			}
		}
		out.print(res);
		j = tmp;
		for (i = cnt - 1; i >= 0; i--) {
			col = 1;
			if (path[i][j] == 0) {
				col = 0;
				j -= num[i][0];
			} else
				j -= num[i][1];
			for (k = 0; k < num[i][col]; k++)
				out.print(" " + p[i][col][k]);
		}
		out.println();
		out.print(n - res);
		j = tmp;
		for (i = cnt - 1; i >= 0; i--) {
			col = 0;
			if (path[i][j] == 0) {
				col = 1;
				j -= num[i][0];
			} else
				j -= num[i][1];
			for (k = 0; k < num[i][col]; k++)
				out.print(" " + p[i][col][k]);
		}
		out.println();

	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static short nextShort() throws IOException {
		in.nextToken();
		return (short) in.nval;
	}

}
