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

public class p3723___Kruskal {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, m;

	public static void main(String[] args) throws IOException {
		int test = nextInt(), r, u, v, val;
		while (test-- > 0) {
			n = nextInt();
			m = nextInt();
			r = nextInt();
			init();
			while (r-- > 0) {
				u = nextInt() + 1;
				v = nextInt() + 1 + n;
				val = nextInt();
				addEdge(u, v, -val);
			}
			long ans = 10000 * (m + n) - solve();
			out.println(ans);
		}
		out.flush();
		out.close();
	}

	static int N = 20100, index, parent[] = new int[N], rank[] = new int[N];
	static PriorityQueue<node> que = new PriorityQueue();

	static void init() {
		que.clear();
		Arrays.fill(rank, 1);
		for (int i = 1; i <= n + m; i++)
			parent[i] = i;
	}

	static void addEdge(int u, int v, int val) {
		que.add(new node(u, v, val));
	}

	static int find(int i) {
		if (parent[i] != i)
			parent[i] = find(parent[i]);
		return parent[i];
	}

	static void union(int ra, int rb) {
		if (rank[ra] > rank[rb])
			parent[rb] = ra;
		else {
			if (rank[ra] == rank[rb])
				rank[rb]++;
			parent[ra] = rb;
		}
	}

	static long solve() {
		node temp;
		int ra, rb;
		long sum = 0;

		while (!que.isEmpty()) {
			temp = que.poll();
			ra = find(temp.u);
			rb = find(temp.v);
			if (ra != rb) {
				union(ra, rb);
				sum -= temp.val;
			}
		}
		return sum;
	}

	static class node implements Comparable<node> {
		int u, v;
		int val;

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
