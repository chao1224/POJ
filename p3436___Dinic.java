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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class p3436___Dinic {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, f, p;
	static int a[][], b[][], cost[];

	public static void main(String[] args) throws IOException {
		p = nextInt();
		n = nextInt();

		a = new int[n + 1][p];
		b = new int[n + 1][p];
		cost = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			cost[i] = nextInt();
			for (int j = 0; j < p; j++)
				a[i][j] = nextInt();
			for (int j = 0; j < p; j++)
				b[i][j] = nextInt();
		}

		int begin[] = new int[p], end[] = new int[p];
		Arrays.fill(end, 1);

		Dinic dinic = new Dinic(n * 2 + 2, 1000);
		int source = n * 2 + 1, destination = n * 2 + 2;
		for (int i = 1; i <= n; i++) {
			dinic.addEdge(i, i + n, cost[i]);

			if (cmp(begin, a[i]))
				dinic.addEdge(source, i);

			if (cmp2(b[i], end))
				dinic.addEdge(i + n, destination);

			for (int j = i + 1; j <= n; j++) {
				if (cmp(b[i], a[j])) {
					dinic.addEdge(i + n, j);
					// out.println(i + " " + j);
				}
				if (cmp(b[j], a[i])) {
					dinic.addEdge(j + n, i);
					// out.println(j + " " + i);
				}
			}
		}

		out.print(dinic.getDinic(source, destination));

		int u, num = 0;
		int output[][] = new int[n * n][3];
		for (int i = n + 1; i <= 2 * n; i++) {
			for (int j = dinic.head[i]; j != -1; j = dinic.edge[j].next) {
				u = dinic.edge[j].v;
				if (u != destination && dinic.edge[j].flow > 0) {
					num++;
					output[num][0] = i - n;
					output[num][1] = u;
					output[num][2] = dinic.edge[j].flow;
				}
			}
		}
		out.println(" " + num);
		for (int i = 1; i <= num; i++)
			out.println(output[i][0] + " " + output[i][1] + " " + output[i][2]);

		out.flush();
		out.close();
	}

	static boolean cmp(int up[], int down[]) {
		for (int i = 0; i < p; i++) {
			if (up[i] != down[i] && down[i] != 2)
				return false;
		}
		return true;
	}

	static boolean cmp2(int a[], int b[]) {
		for (int i = 0; i < p; i++)
			if (a[i] != b[i])
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

	static class Dinic {
		int inf = Integer.MAX_VALUE;
		int n, m, level[], queue[];

		int head[], index;
		edge edge[];

		Dinic(int n, int m) {
			this.n = n;
			this.m = m;
			edge = new edge[m + 1];
			head = new int[n + 1];
			Arrays.fill(head, -1);
			index = 0;
			level = new int[n + 1];
			queue = new int[n + 1];
		}

		int getDinic(int source, int destination) {
			int sum = 0, temp;
			while (bfs(source, destination)) {
				temp = dfs(source, destination, inf);
				if (temp == 0)
					break;
				sum += temp;
			}
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

		void addDoubleEdge(int u, int v, int cap) {
			edge[index] = new edge(v, cap);
			edge[index].next = head[u];
			head[u] = index;
			index++;

			edge[index] = new edge(u, cap);
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

						if (edge[i].v == destination)
							return true;
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

		static class edge {
			int v, next;
			int cap, flow;

			edge(int v, int cap) {
				this.v = v;
				this.cap = cap;
				this.flow = 0;
			}
		}
	}

}