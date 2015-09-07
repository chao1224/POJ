import java.awt.Stroke;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class p2195___·ÑÓÃÁ÷ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, k, cap[][], cost[][];

	public static void main(String[] args) throws IOException {
		int source = 0, destination = 0, val;
		int row, col, person[][] = new int[300][2], pCount, house[][] = new int[300][2], hCount;
		String str;
		char ch;
		while (true) {
			StringTokenizer stoke = new StringTokenizer(br.readLine());
			row = Integer.parseInt(stoke.nextToken());
			col = Integer.parseInt(stoke.nextToken());
			if (row == 0 && col == 0)
				break;
			pCount = hCount = 0;

			for (int i = 1; i <= row; i++) {
				str = br.readLine();
				for (int j = 0; j < col; j++) {
					ch = str.charAt(j);
					if (ch == 'm') {
						person[++pCount][0] = i;
						person[pCount][1] = j;
					} else if (ch == 'H') {
						house[++hCount][0] = i;
						house[hCount][1] = j;
					}
				}
			}

			n = pCount * 2 + 1;
			source = 0;
			destination = n;
			cap = new int[n + 1][n + 1];
			cost = new int[n + 1][n + 1];
			for (int i = 1; i <= pCount; i++) {
				cap[source][i] = 1;
				cost[source][i] = 0;
			}
			for (int j = 1; j <= pCount; j++) {
				cap[j + pCount][destination] = 1;
				cost[j + pCount][destination] = 0;
			}
			for (int i = 1; i <= pCount; i++) {
				for (int j = 1; j <= pCount; j++) {
					cap[i][j + pCount] = 1;
					cost[i][j + pCount] = Math.abs(person[i][0] - house[j][0])
							+ Math.abs(person[i][1] - house[j][1]);
					cost[j + pCount][i] = -cost[i][j + pCount];
				}
			}

			out.println(maxFlow(source, destination));

		}

		out.flush();
		out.close();
	}

	static int pre[];

	static int maxFlow(int s, int t) {
		int ans = 0;
		while (spfa(s, t))
			ans += argument(s, t);
		return ans;
	}

	static int argument(int s, int t) {
		int min = Integer.MAX_VALUE, flow = 0;
		for (int i = t; i != s; i = pre[i])
			min = Math.min(min, cap[pre[i]][i]);
		for (int i = t; i != s; i = pre[i]) {
			cap[pre[i]][i] -= min;
			cap[i][pre[i]] += min;
			flow += cost[pre[i]][i] * min;
		}
		return flow;
	}

	static boolean spfa(int s, int t) {
		int inf = Integer.MAX_VALUE;
		int dist[] = new int[n + 1];
		Arrays.fill(dist, inf);
		int visit[] = new int[n + 1];
		int queue[] = new int[n + 1];
		pre = new int[n + 1];

		int begin = 0, end = 1, u, v;
		visit[s] = 1;
		dist[s] = 0;
		while (begin != end) {
			u = queue[begin++];
			begin %= n;
			visit[u] = 0;
			for (int i = 0; i <= n; i++) {
				if (cap[u][i] > 0 && dist[i] > dist[u] + cost[u][i]) {
					dist[i] = dist[u] + cost[u][i];
					pre[i] = u;
					if (visit[i] == 0) {
						visit[i] = 1;
						queue[end++] = i;
						end %= n;
					}
				}
			}
		}

		if (dist[t] == inf)
			return false;
		return true;
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static char nextChar() throws IOException {
		in.nextToken();
		return in.sval.charAt(0);
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}

}