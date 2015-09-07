import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class p1041___按照边的字典序最小输出欧拉回路 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m;
	static path[] e = new path[5000];
	static int toll;
	static int indegree[], outdegree[];

	public static void main(String[] args) throws IOException {
		int x, y, z, start, end, u, v;
		Euler euler = new Euler(2000, 100000);
		while (true) {
			x = nextInt();
			y = nextInt();
			if (x == 0 && y == 0)
				break;
			toll = 0;
			z = nextInt();
			e[toll++] = new path(x, y, z);

			start = Math.min(x, y);
			end = Math.max(x, y);

			while (true) {
				x = nextInt();
				y = nextInt();
				if (x == 0 && y == 0)
					break;
				start = Math.min(start, Math.min(x, y));
				end = Math.max(end, Math.max(x, y));
				z = nextInt();
				e[toll++] = new path(x, y, z);
			}

			euler.init();
			Arrays.sort(e, 0, toll);
			indegree = new int[end + 1];
			outdegree = new int[end + 1];
			for (int i = 0; i < toll; i++) {
				// out.println(e[i].u + " " + e[i].v + " " + e[i].id);
				euler.addEdge(e[i].u, e[i].v, e[i].id);
				euler.addEdge(e[i].v, e[i].u, e[i].id);
				outdegree[e[i].u]++;
				indegree[e[i].v]++;
			}

			if (!check(start, end))
				out.println("Round trip does not exist.");
			else {
				euler.euler(start);
				for (int i = euler.cnt - 1; i > 0; i--)
					out.print(euler.ans[i] + " ");
				out.println(euler.ans[0]);
			}
		}

		out.flush();
		out.close();
	}

	static boolean check(int start, int end) {
		for (int i = start; i <= end; i++)
			if ((Math.abs(indegree[i] - outdegree[i]) & 1) == 1)
				return false;
		return true;
	}

	static class path implements Comparable<path> {
		int u, v, id;

		path(int u, int v, int val) {
			this.u = u;
			this.v = v;
			this.id = val;
		}

		public int compareTo(path o) {
			// if (this.u < o.u)
			// return -1;
			// else if (this.u > o.u)
			// return 1;
			// else
			if (this.id < o.id)
				return 1;
			else if (this.id > o.id)
				return -1;
			return 0;
		}
	}

	static class Euler {
		static int n, m;

		static int head[], toll;
		static edge edge[];

		Euler(int nn, int mm) {
			this.n = nn;
			this.m = mm;
			head = new int[n + 1];
			edge = new edge[m];
		}

		static int ans[];
		static int cnt;

		static void init() {
			toll = 0;
			Arrays.fill(head, -1);
			n = 0;
			cnt = 0;
			ans = new int[m];
		}

		static void addEdge(int u, int v) {
			edge[toll] = new edge(v);
			edge[toll].next = head[u];
			head[u] = toll++;
		}

		// 这里的加边的id是为了预防出现要求按照边的序号输出
		static void addEdge(int u, int v, int id) {
			edge[toll] = new edge(v, id);
			edge[toll].next = head[u];
			head[u] = toll++;
		}

		static class edge {
			int v, next, id;
			boolean mark;

			edge(int v, int id) {
				this.v = v;
				this.id = id;
				mark = false;
			}

			edge(int v) {
				this.v = v;
				mark = false;
			}
		}

		void dfs(int u) {
			for (int i = head[u]; i != -1; i = edge[i].next) {
				if (edge[i].mark)
					continue;
				edge[i].mark = true;
				dfs(edge[i].v);
			}
			out.println(u);
		}

		// euler解决的是要求我们按照边的序号输出的问题
		void euler(int u) {
			for (int i = head[u]; i != -1; i = edge[i].next) {
				if (edge[i].mark)
					continue;
				edge[i].mark = true;
				edge[i ^ 1].mark = true;
				// System.out.println(u + " -> " + edge[i].v + " : " +
				// edge[i].id);
				euler(edge[i].v);
				ans[cnt++] = edge[i].id;
			}
		}

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

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}
