import java.awt.Stroke;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class p1613___SPFA___º”œﬁ÷∆ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);
	static int n, m;

	public static void main(String[] args) throws IOException {
		int source, destination, u, v, val, cnt;
		while (true) {
			stoke = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stoke.nextToken());
			if (n == 0)
				break;
			m = Integer.parseInt(stoke.nextToken());
			source = Integer.parseInt(stoke.nextToken());
			destination = Integer.parseInt(stoke.nextToken());

			SPFA sp = new SPFA(n, 2 * m);
			for (int i = 1; i <= m; i++) {
				stoke = new StringTokenizer(br.readLine());
				u = Integer.parseInt(stoke.nextToken());
				v = Integer.parseInt(stoke.nextToken());
				val = Integer.parseInt(stoke.nextToken());
				sp.addEdge(u, v, val);
				sp.addEdge(v, u, val);

				cnt = 0;
				while (stoke.hasMoreTokens())
					sp.edge[sp.index - 1].time[++cnt] = sp.edge[sp.index].time[cnt] = Integer
							.parseInt(stoke.nextToken());

			}

			int ans = sp.ShortestPath(source, destination);
			if (ans < Integer.MAX_VALUE)
				out.println(ans);
			else
				out.println('*');
		}
		out.flush();
		out.close();
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}

	static class SPFA {
		int n, head[], index;
		edge edge[];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int inQ[], path[], dist[], inf = Integer.MAX_VALUE;

		SPFA(int n, int m) {
			this.n = n;
			head = new int[n + 1];
			Arrays.fill(head, -1);
			edge = new edge[m + 1];
			index = 0;
		}

		void addEdge(int u, int v, int val) {
			index++;
			edge[index] = new edge(v, val);
			edge[index].next = head[u];
			head[u] = index;
		}

		int smallest(int dis, edge e) {
			int t = Integer.MAX_VALUE;
			for (int i = 0; i < e.time.length; i += 2) {
				dis = Math.max(dis, e.time[i]);
				if (dis == Integer.MAX_VALUE)
					break;
				if (dis >= e.time[i] && dis + e.val <= e.time[i + 1]) {
					t = dis + e.val;
					break;
				}
			}
			return t;
		}

		int ShortestPath(int source, int destination) {
			queue.add(source);
			dist = new int[n + 1];
			Arrays.fill(dist, inf);
			dist[source] = 0;
			inQ = new int[n + 1];
			inQ[source] = 1;
			path = new int[n + 1];
			Arrays.fill(path, -1);

			int u, temp;
			while (!queue.isEmpty()) {
				u = queue.poll();
				inQ[u] = 0;
				for (int i = head[u]; i != -1; i = edge[i].next) {
					temp = smallest(dist[u], edge[i]);
					// out.println(u + " -> " + edge[i].v + "  :   " + temp);
					// out.println(dist[u]);
					if (dist[edge[i].v] > temp) {
						dist[edge[i].v] = temp;
						path[edge[i].v] = u;
						if (inQ[edge[i].v] == 0) {
							inQ[edge[i].v] = 1;
							queue.add(edge[i].v);
						}
					}
				}
			}
			return dist[destination];
		}
	}

	static class edge {
		int v, val, next, time[];

		edge(int v, int val) {
			this.v = v;
			this.val = val;
			time = new int[34];
			Arrays.fill(time, Integer.MAX_VALUE);
			time[0] = 0;
		}
	}
}
