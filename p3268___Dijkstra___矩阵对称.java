import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

public class p3268___Dijkstra___矩阵对称 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, x, adj1[][], adj2[][], ans;
	static boolean visit[];
	static int inWay[], outWay[], min, tmp;

	public static void main(String[] args) throws IOException {
		// 注意此题矩阵不容易对称
		// 解决方法 重新开一个数组
		n = nextInt();
		m = nextInt();
		x = nextInt();
		adj1 = new int[n + 1][n + 1];
		adj2 = new int[n + 1][n + 1];
		int a, b, w;
		for (int i = 1; i <= m; i++) {
			a = nextInt();
			b = nextInt();
			w = nextInt();
			adj1[a][b] = w;
			adj2[b][a] = w;
		}
		visit = new boolean[n + 1];
		outWay = new int[n + 1];
		Dijkstra(x, outWay, adj1);

		inWay = new int[n + 1];
		// for (int i = 1; i <= n; i++) {
		// for (int j = 1; j <= n; j++) {
		// System.out.println(adj[i][j] + "  " + adj[j][i]);
		// adj[i][j] = adj[i][j] ^ adj[j][i];
		// adj[j][i] = adj[i][j] ^ adj[j][i];
		// adj[i][j] = adj[i][j] ^ adj[j][i];
		// System.out.println(adj[i][j] + "  " + adj[j][i]);
		// }
		// }
		Dijkstra(x, inWay, adj2);

		// for (int i = 1; i <= n; i++) {
		// for (int j = 1; j <= n; j++) {
		// System.out.print(adj2[i][j] + "  ");
		// }
		// System.out.println();
		// }

		ans = 0;
		for (int i = 1; i <= n; i++) {
			if (i == x)
				continue;
			if (inWay[i] + outWay[i] > ans)
				ans = inWay[i] + outWay[i];
		}
		out.println(ans);

		out.flush();
		out.close();
	}

	static void Dijkstra(int line, int[] arr, int[][] adj) {
		for (int i = 1; i <= n; i++) {
			arr[i] = adj[line][i];
			if (arr[i] == 0)
				arr[i] = Integer.MAX_VALUE;
		}
		arr[line] = 0;
		Arrays.fill(visit, false);
		visit[line] = true;

		for (int i = 1; i < n; i++) {
			min = Integer.MAX_VALUE;
			for (int j = 1; j <= n; j++) {
				if (!visit[j] && arr[j] < min) {
					tmp = j;
					min = arr[j];
				}

			}
			visit[tmp] = true;
			for (int j = 1; j <= n; j++) {
				if (!visit[j] && arr[tmp] + adj[tmp][j] < arr[j]
						&& adj[tmp][j] != 0)
					arr[j] = arr[tmp] + adj[tmp][j];
			}
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

class edge implements Comparable<edge> {
	int u, v, w;

	edge(int u, int v, int w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	public int compareTo(edge o) {
		if (this.w < o.w)
			return -1;
		else if (this.w > o.w)
			return 1;
		else
			return 0;
	}

}
