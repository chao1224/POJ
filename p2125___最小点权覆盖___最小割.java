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

public class p2125___最小点权覆盖___最小割 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, m, inDegree[], outDegree[];

	static int match[][], cx[], cy[], mark[];

	public static void main(String[] args) throws IOException {
		// int test = nextInt();
		int cnt = 0, u, v;
		while (in.nextToken() != in.TT_EOF) {

			n = (int) in.nval;
			m = nextInt();
			inDegree = new int[n + 1];
			outDegree = new int[n + 1];
			for (int i = 1; i <= n; i++)
				inDegree[i] = nextInt();
			for (int i = 1; i <= n; i++)
				outDegree[i] = nextInt();

			Dinic dinic = new Dinic(n * 2 + 2, n * 2 + 2 * m);
			int source = 0, destination = n * 2 + 1;
			for (int i = 1; i <= m; i++) {
				u = nextInt();
				v = nextInt();
				dinic.addEdge(u, v + n);
			}
			for (int i = 1; i <= n; i++) {
				dinic.addEdge(source, i, outDegree[i]);
				dinic.addEdge(i + n, destination, inDegree[i]);
			}

			if (cnt == 0)
				cnt = 1;
			else
				out.println();
			out.println(dinic.getDinic(source, destination));
			dinic.dfsMinCut(source);

			int ans = 0, path[] = new int[2 * n];
			for (int i = 1; i <= n; i++) {
				if (dinic.hash[i] == 0)
					path[ans++] = i;
				if (dinic.hash[i + n] == 1)
					path[ans++] = i + n;
			}

			out.println(ans);
			for (int i = 0; i < ans; i++)
				if (path[i] > n)
					out.println(path[i] - n + " +");
				else
					out.println(path[i] + " -");
		}
		out.flush();
		out.close();
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

	static class Dinic {
		int inf = Integer.MAX_VALUE;
		int n, m, level[], queue[];

		int head[], index, hash[];
		edge edge[];

		Dinic(int nn, int mm) {
			this.n = nn;
			this.m = 2 * mm;
			edge = new edge[m];
			head = new int[n + 1];
			Arrays.fill(head, -1);
			index = 0;
			level = new int[n + 1];
			queue = new int[n + 1];
			hash = new int[n + 1];
		}

		int getDinic(int source, int destination) {
			int sum = 0;
			while (bfs(source, destination))
				sum += dfs(source, destination, inf);
			return sum;
		}

		void addEdge(int u, int v) {
			edge[index] = new edge(v, inf);
			edge[index].next = head[u];
			head[u] = index;
			index++;

			edge[index] = new edge(u, 0);
			edge[index].next = head[v];
			head[v] = index;
			index++;
		}

		void addEdge(int u, int v, int cap) {
			edge[index] = new edge(v, cap);
			edge[index].next = head[u];
			head[u] = index;
			index++;

			edge[index] = new edge(u, 0);
			edge[index].next = head[v];
			head[v] = index;
			index++;
		}

		boolean bfs(int source, int destination) {
			Arrays.fill(level, -1);
			level[source] = 0;

			int temp, start = 0, end = 1;
			queue[start] = source;
			while (start < end) {
				temp = queue[start];
				for (int i = head[temp]; i != -1; i = edge[i].next) {
					if (level[edge[i].v] == -1 && edge[i].cap > edge[i].flow) {
						queue[end++] = edge[i].v;
						level[edge[i].v] = level[temp] + 1;
					}
				}
				start++;
			}
			return level[destination] != -1;
		}

		int dfs(int vert, int destination, int sum) {
			if (vert == destination)
				return sum;
			int s = sum, temp;
			for (int i = head[vert]; i != -1; i = edge[i].next) {
				if (edge[i].cap > edge[i].flow
						&& level[edge[i].v] == level[vert] + 1) {
					temp = dfs(edge[i].v, destination, Math.min(sum,
							edge[i].cap - edge[i].flow));
					edge[i].flow += temp;
					edge[i ^ 1].flow -= temp;
					sum -= temp;
				}
			}
			return s - sum;
		}

		void dfsMinCut(int u) {
			hash[u] = 1;
			int v;
			for (int i = head[u]; i != -1; i = edge[i].next) {
				v = edge[i].v;
				if (hash[v] == 0 && edge[i].cap > edge[i].flow) {
					dfsMinCut(v);
				}
			}
		}

		static class edge {
			int v, next;
			int cap, flow;

			edge(int v, int val) {
				this.v = v;
				this.cap = val;
				this.flow = 0;
			}
		}
	}
}