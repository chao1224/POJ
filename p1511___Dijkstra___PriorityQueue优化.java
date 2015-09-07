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

public class p1511___Dijkstra___PriorityQueue”≈ªØ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);
	static int n, m;

	public static void main(String[] args) throws IOException {
		int test = nextInt(), u, v, val;
		Dijkstra sp1, sp2;
		for (int it = 1; it <= test; it++) {
			n = nextInt();
			m = nextInt();
			sp1 = new Dijkstra(n, m);
			sp2 = new Dijkstra(n, m);
			for (int i = 1; i <= m; i++) {
				u = nextInt();
				v = nextInt();
				val = nextInt();
				sp1.addEdge(u, v, val);
				sp2.addEdge(v, u, val);
			}
			long ans = sp1.ShortestPath(1) + sp2.ShortestPath(1);
			out.println(ans);
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

	static class Dijkstra {
		int n, m, index = 0;
		int head[];
		edge edge[];
		PriorityQueue<node> queue = new PriorityQueue<node>();

		Dijkstra(int n, int m) {
			this.n = n;
			head = new int[n + 1];
			Arrays.fill(head, -1);
			edge = new edge[m + 1];
			index = 0;
		}

		void addEdge(int u, int v, int val) {
			index++;
			edge[index] = new edge();
			edge[index].v = v;
			edge[index].val = val;
			edge[index].next = head[u];
			head[u] = index;
		}

		long ShortestPath(int source) {
			short visit[] = new short[n + 1];
			int dist[] = new int[n + 1];
			queue.add(new node(source, source, 0));

			node temp;
			int v;
			while (!queue.isEmpty()) {
				temp = queue.poll();
				v = temp.v;
				if (visit[v] == 1)
					continue;
				visit[v] = 1;
				dist[v] = temp.val;
				for (int i = head[v]; i != -1; i = edge[i].next) {
					if (visit[edge[i].v] == 0)
						queue
								.add(new node(v, edge[i].v, edge[i].val
										+ dist[v]));
				}
			}

			long sum = 0;
			for (int i = 1; i <= n; i++)
				sum += dist[i];
			return sum;
		}
	}

	static class edge {
		int v, next = -1, val;

		edge() {
		}

		edge(int u, int v, int val) {
			this.v = v;
			this.val = val;
		}
	}

	static class node implements Comparable<node> {
		int u, v, val;

		node(int u, int v, int val) {
			this.u = u;
			this.v = v;
			this.val = val;
		}

		public int compareTo(node o) {
			if (this.val < o.val)
				return -1;
			else if (this.val > o.val)
				return 1;
			return 0;
		}
	}
}
