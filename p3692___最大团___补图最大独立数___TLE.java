import java.awt.*;
import java.io.*;
import java.util.*;

public class p3692___最大团___补图最大独立数___TLE {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int g, b, n, m;
	static int map[][];

	public static void main(String[] args) throws IOException {
		int u, v;
		int test = 0;
		while (true) {
			g = nextInt();
			b = nextInt();
			m = nextInt();
			if (g == 0 && b == 0 && m == 0)
				break;
			n = g + b;
			map = new int[n + 1][n + 1];
			choice = new int[n + 1];
			opt = new int[n + 1];
			most = 0;
			for (int i = 1; i <= m; i++) {
				u = nextInt();
				v = nextInt() + g;
				map[u][v] = map[v][u] = 1;
			}
			for (int i = 1; i <= g; i++)
				for (int j = i; j <= g; j++)
					map[i][j] = map[j][i] = 1;
			for (int i = g + 1; i <= n; i++)
				for (int j = i; j <= n; j++)
					map[i][j] = map[j][i] = 1;

			dfs(1, 0);
			++test;
			out.println("Case " + test + ": " + most);
		}

		out.flush();
		out.close();
	}

	static int choice[], opt[];
	static int most = 0;

	static void dfs(int dep, int count) {
		if (dep > n) {
			if (most < count) {
				for (int i = 1; i <= n; i++)
					opt[i] = choice[i];
				most = count;
			}
		} else {
			boolean flag = true;

			for (int i = 1; i <= n; i++) {
				if (choice[i] == 1 && map[dep][i] == 0) {
					flag = false;
					break;
				}
			}

			if (flag) {
				choice[dep] = 1;
				dfs(dep + 1, count + 1);
				choice[dep] = 0;
			}

			if (count + n - dep > most)
				dfs(dep + 1, count);

		}
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static char nextChar() throws IOException {
		in.nextToken();
		return in.sval.charAt(0);
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}