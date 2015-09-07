import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p1273___×î´óÁ÷___Dinic {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, cap[][], flow[][], level[];
	static LinkedList<Integer> q = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		int x, y, val, ans, tmp;
		while (in.nextToken() != in.TT_EOF) {
			m = (int) in.nval;
			n = nextInt();
			cap = new int[n + 1][n + 1];
			flow = new int[n + 1][n + 1];
			for (int i = 1; i <= m; i++) {
				x = nextInt();
				y = nextInt();
				val = nextInt();
				cap[x][y] += val;
			}
			level = new int[n + 1];
			ans = 0;
			tmp = 0;
			while (bfs(1, n)) {
				ans += tmp = dfs(1, Integer.MAX_VALUE);
			}
			out.println(ans);
		}

		out.flush();
		out.close();
	}

	static boolean bfs(int s, int t) {
		Arrays.fill(level, -1);
		q.clear();
		q.push(s);
		level[s] = 0;
		while (!q.isEmpty()) {
			int x = q.poll();
			for (int i = 1; i <= n; i++)
				if (level[i] == -1 && cap[x][i] > 0) {
					q.push(i);
					level[i] = level[x] + 1;
				}
		}
		return (level[t] != -1);
	}

	static int dfs(int v, int tmpFlow) {
		if (v == n)
			return tmpFlow;

		int dt = tmpFlow;
		for (int i = 1; i <= n; ++i) {
			if (cap[v][i] > 0 && level[v] + 1 == level[i]) {
				int flow = dfs(i, Math.min(dt, cap[v][i]));
				cap[v][i] -= flow;
				cap[i][v] += flow;
				dt -= flow;
			}
		}

		return tmpFlow - dt;

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
