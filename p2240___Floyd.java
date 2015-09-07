import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class p2240___Floyd {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, a, b;
	static String curType[], stra, strb;
	static HashMap<String, Integer> map;
	static double tmp, adj[][];

	public static void main(String[] args) throws IOException {
		int testcase = 0;
		map = new HashMap<String, Integer>();
		while (true) {
			n = nextInt();
			if (n == 0)
				break;
			testcase++;
			map.clear();
			curType = new String[n + 1];
			adj = new double[n + 1][n + 1];
			for (int i = 1; i <= n; i++)
				map.put(next(), i);

			m = nextInt();
			for (int i = 1; i <= m; i++) {
				stra = next();
				tmp = nextDouble();
				strb = next();
				adj[map.get(stra)][map.get(strb)] = tmp;
			}

			if (Floyd())
				out.println("Case " + testcase + ": Yes");
			else
				out.println("Case " + testcase + ": No");
		}
		out.flush();
		out.close();
	}

	static boolean Floyd() {
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					// adj[i][j] = Math.max(adj[i][j], adj[i][k] * adj[k][j]);
					if (adj[i][k] * adj[k][j] > adj[i][j])
						adj[i][j] = adj[i][k] * adj[k][j];
				}
			}
		}
		for (int i = 1; i <= n; i++)
			// 注意，此处一定得是1.0，如果是1，会wa
			if (adj[i][i] > 1.0)
				return true;
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
