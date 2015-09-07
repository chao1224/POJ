import java.awt.*;
import java.io.*;
import java.util.*;

public class p3692___最大团___补图最大独立数___顶点数减去最小点覆盖数 {
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
			map = new int[g + 1][b + 1];
			for (int i = 1; i <= g; i++)
				Arrays.fill(map[i], 1);
			for (int i = 1; i <= m; i++) {
				u = nextInt();
				v = nextInt();
				map[u][v] = 0;
			}

			++test;
			init(g, b);
			out.println("Case " + test + ": " + (g + b - hungary()));
		}

		out.flush();
		out.close();
	}

	static int mark[], cx[], cy[], nx, ny;

	static void init(int a, int b) {
		nx = a;
		ny = b;
		cx = new int[nx + 1];
		Arrays.fill(cx, -1);
		cy = new int[ny + 1];
		Arrays.fill(cy, -1);
		mark = new int[ny + 1];
	}

	static int hungary() {
		int ans = 0;
		for (int i = 1; i <= nx; i++) {
			if (cx[i] == -1) {
				Arrays.fill(mark, 0);
				ans += path(i);
			}
		}
		return ans;
	}

	static int path(int u) {
		for (int v = 1; v <= ny; v++) {
			if (map[u][v] == 1 && mark[v] == 0) {
				mark[v] = 1;
				if (cy[v] == -1 || path(cy[v]) == 1) {
					cx[u] = v;
					cy[v] = u;
					return 1;
				}
			}
		}
		return 0;
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