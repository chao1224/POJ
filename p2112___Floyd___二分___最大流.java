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

public class p2112___Floyd___二分___最大流 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int k, c, n, m, Inf = 100000, matrix[][];
	static p2112___Dinic p2112___Dinic;

	public static void main(String[] args) throws IOException {
		k = nextInt();
		c = nextInt();
		n = k + c;
		m = nextInt();
		matrix = new int[k + c + 1][k + c + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++) {
				matrix[i][j] = nextInt();
				if (matrix[i][j] == 0)
					matrix[i][j] = Inf;
			}

		for (int i = 1; i <= n; i++)
			matrix[i][i] = 0;

		for (int mid = 1; mid <= n; mid++)
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= n; j++) {
					// attention
					// this is matrix[i][mid]
					// not matrix[i][j]
					if (matrix[i][mid] == Inf)
						continue;
					if (matrix[i][mid] + matrix[mid][j] < matrix[i][j])
						matrix[i][j] = matrix[i][mid] + matrix[mid][j];
				}

		p2112___Dinic = new p2112___Dinic(n);

		int l = 0, r = Inf, mid, ans;
		p2112___Dinic.sign = new int[n + 2][n + 2];
		p2112___Dinic.used = new int[n + 2];
		while (l < r) {
			mid = (l + r) >> 1;
			ans = 0;
			buildGraph(mid);
			while (p2112___Dinic.bfs()) {
				ans += p2112___Dinic.dfs(0, Inf);
			}
			if (ans >= c)
				r = mid;
			else
				l = mid + 1;
		}

		out.println(r);

		out.flush();
		out.close();
	}

	static void buildGraph(int max) {
		p2112___Dinic.map = new int[n + 2][n + 2];
		for (int i = k + 1; i <= n; i++)
			p2112___Dinic.map[0][i] = 1;
		for (int i = 1; i <= k; i++)
			p2112___Dinic.map[i][n + 1] = m;
		for (int i = k + 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if (matrix[i][j] <= max)
					p2112___Dinic.map[i][j] = 1;
			}
		}
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
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

class p2112___Dinic {
	static int n, used[], map[][], sign[][];

	p2112___Dinic(int n) {
		this.n = n;
	}

	static int dfs(int v, int sum) {
		if (v == n + 1)
			return sum;
		int s = sum, t;
		for (int i = 0; i <= n + 1; i++) {
			if (sign[v][i] != 0) {
				t = dfs(i, Math.min(map[v][i], s));
				map[v][i] -= t;
				map[i][v] += t;
				s -= t;
			}
		}
		return sum - s;
	}

	static boolean bfs() {
		Arrays.fill(used, 0);
		for (int i = 0; i <= n + 1; i++)
			Arrays.fill(sign[i], 0);
		int queue[] = new int[n * 30];
		queue[0] = 0;
		used[0] = 1;
		int t = 1, f = 0;
		while (f < t) {
			for (int i = 0; i <= n + 1; i++) {
				if (used[i] == 0 && map[queue[f]][i] != 0) {
					queue[t++] = i;
					used[i] = 1;
					sign[queue[f]][i] = 1;
				}
			}
			f++;
		}
		if (used[n + 1] != 0)
			return true;
		else
			return false;

	}

}
