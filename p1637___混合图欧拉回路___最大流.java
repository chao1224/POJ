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

public class p1637___混合图欧拉回路___最大流 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);
	static int n, m;

	static int inDegree[], outDegree[], source, destination;
	static Dinic dinic;

	public static void main(String[] args) throws IOException {
		int test = nextInt();
		int u, v, val;
		while (test-- > 0) {
			n = nextInt();
			m = nextInt();

			inDegree = new int[n + 1];
			outDegree = new int[n + 1];

			dinic = new Dinic(n + 2, 2 * m + 2 * n);
			source = n + 2;
			destination = n + 1;

			for (int i = 1; i <= m; i++) {
				u = nextInt();
				v = nextInt();
				val = nextInt();
				outDegree[u]++;
				inDegree[v]++;
				if (val == 0)
					dinic.addEdge(u, v, 1);
			}
			if (get())
				out.println("possible");
			else
				out.println("impossible");
		}

		out.flush();
		out.close();
	}

	static boolean get() {
		int temp, sum = 0;
		for (int i = 1; i <= n; i++) {
			temp = outDegree[i] - inDegree[i];
			if ((temp & 1) == 1)
				return false;
		}
		for (int i = 1; i <= n; i++) {
			temp = (outDegree[i] - inDegree[i]) / 2;
			if (temp > 0) {
				sum += temp;
				dinic.addEdge(source, i, temp);
			} else if (temp < 0)
				dinic.addEdge(i, destination, -temp);
		}
		if (sum == dinic.getDinic(source, destination))
			return true;
		return false;
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
					if (level[edge[i].v] == -1 && edge[i].val > 0) {
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
				if (edge[i].val > 0 && level[edge[i].v] == level[vert] + 1) {
					temp = dfs(edge[i].v, destination, Math.min(sum,
							edge[i].val));
					edge[i].val -= temp;
					edge[i ^ 1].val += temp;
					sum -= temp;
				}
			}
			return s - sum;
		}

		static class edge {
			int v, next;
			int val;

			edge(int v, int val) {
				this.v = v;
				this.val = val;
			}
		}
	}

}