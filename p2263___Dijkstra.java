import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class p2263___Dijkstra {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, r, adj[][], begin, end, dis[], ans, limit = 10010;
	static boolean visit[];
	static HashMap<String, Integer> map = new HashMap<String, Integer>();

	public static void main(String[] args) throws IOException {
		int testcase = 0, tmp, num, na, nb;
		String a, b;
		while (true) {
			n = nextInt();
			r = nextInt();
			if (n == 0 && r == 0)
				break;
			testcase++;
			map.clear();
			num = 0;
			adj = new int[n + 1][n + 1];

			for (int i = 1; i <= r; i++) {
				a = next();
				b = next();
				tmp = nextInt();
				if (!map.containsKey(a))
					map.put(a, ++num);
				if (!map.containsKey(b))
					map.put(b, ++num);
				na = map.get(a);
				nb = map.get(b);
				adj[na][nb] = adj[nb][na] = tmp;
			}

			begin = map.get(next());
			end = map.get(next());

			Dijkstra(begin);

			if (testcase != 1)
				out.println();
			out.println("Scenario #" + testcase);
			out.println(ans + " tons");
		}

		out.flush();
		out.close();
	}

	static void Dijkstra(int v0) {
		visit = new boolean[n + 1];
		Arrays.fill(visit, false);
		dis = new int[n + 1];
		for (int i = 1; i <= n; i++)
			dis[i] = adj[v0][i];
		visit[v0] = true;

		int max, k;
		for (int i = 1; i <= n; i++) {
			max = 0;
			k = 0;
			for (int j = 1; j <= n; j++) {
				if (!visit[j] && dis[j] > max) {
					k = j;
					max = dis[j];
				}
			}
			if (k == end) {
				ans = dis[k];
				return;
			}
			visit[k] = true;
			for (int j = 1; j <= n; j++) {
				if (!visit[j] && adj[k][j] > 0) {
					dis[j] = Math.max(Math.min(dis[k], adj[k][j]), dis[j]);
				}
			}
		}
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
