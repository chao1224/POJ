import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class p3259___Bellaman_Ford___设置超级源点 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, w, adj[][], a, b, val, limit = 10010, dis[], cnt;
	static p3259___edge[] list;

	public static void main(String[] args) throws IOException {
		// 给各路大神跪了
		// n次的Bellman_Ford明显超时了
		// 解决方法，设置一个超级源点v0，将v0与所有的点相连
		int testcase = nextInt();
		for (int test = 1; test <= testcase; test++) {
			n = nextInt();
			m = nextInt();
			w = nextInt();
			adj = new int[n + 1][n + 1];
			dis = new int[n + 1];

			for (int i = 1; i <= n; i++) {
				Arrays.fill(adj[i], limit);
			}
			for (int i = 1; i <= m; i++) {
				a = nextInt();
				b = nextInt();
				val = nextInt();
				adj[a][b] = adj[b][a] = Math.min(val, adj[a][b]);
			}
			for (int i = 1; i <= w; i++) {
				a = nextInt();
				b = nextInt();
				val = nextInt();
				adj[a][b] = Math.min(adj[a][b], -val);
			}

			list = new p3259___edge[m * 2 + w + 1 + n];
			cnt = 0;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (adj[i][j] < limit) {
						list[++cnt] = new p3259___edge(i, j, adj[i][j]);
					}
				}
			}
			for (int i = 1; i <= n; i++)
				list[++cnt] = new p3259___edge(0, i, 0);

			if (Bellman_Ford())
				out.println("YES");
			else
				out.println("NO");
		}

		out.flush();
		out.close();
	}

	static boolean Bellman_Ford() {
		int v0 = 0;
		for (int i = 1; i <= n; i++) {
			dis[i] = limit;
		}
		dis[v0] = 0;

		for (int j = 1; j <= n; j++) {
			for (int i = 1; i <= cnt; i++) {
				if (dis[list[i].u] < limit
						&& dis[list[i].v] > dis[list[i].u] + list[i].val)
					dis[list[i].v] = dis[list[i].u] + list[i].val;
			}
		}

		for (int i = 1; i <= cnt; i++) {
			if (dis[list[i].u] < limit
					&& dis[list[i].u] + list[i].val < dis[list[i].v]) {
				return true;
			}
		}

		return false;
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}

class p3259___edge {
	int u, v, val;

	p3259___edge(int u, int v, int val) {
		this.u = u;
		this.v = v;
		this.val = val;
	}
}
