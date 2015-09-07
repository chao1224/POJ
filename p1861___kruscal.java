import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1861___kruscal {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m;

	public static void main(String[] args) throws IOException {
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			m = nextInt();
			kruscal MST = new kruscal(n);
			for (int i = 1; i <= m; i++)
				MST.addEdge(nextInt(), nextInt(), nextInt());
			MST.solve();
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

	static class kruscal {
		int n, index, parent[], rank[];
		PriorityQueue<node> que = new PriorityQueue<node>();
		LinkedList<node> list = new LinkedList<node>();

		kruscal(int n) {
			this.n = n;
			this.index = 0;

			parent = new int[n + 1];
			for (int i = 1; i <= n; i++)
				parent[i] = i;
			rank = new int[n + 1];
			Arrays.fill(rank, 1);
		}

		void addEdge(int u, int v, int val) {
			que.add(new node(u, v, val));
		}

		int find(int i) {
			if (parent[i] != i)
				parent[i] = find(parent[i]);
			return parent[i];
		}

		void union(int ra, int rb) {
			if (rank[ra] > rank[rb])
				parent[rb] = ra;
			else {
				if (rank[ra] == rank[rb])
					rank[rb]++;
				parent[ra] = rb;
			}
		}

		void solve() {
			node temp;
			int ra, rb, max = 0;
			while (!que.isEmpty()) {
				temp = que.poll();
				ra = find(temp.u);
				rb = find(temp.v);
				if (ra != rb) {
					union(ra, rb);
					max = Math.max(temp.val, max);
					list.add(temp);
				}
			}
			out.println(max);
			out.println(n - 1);
			while (!list.isEmpty()) {
				temp = list.pollFirst();
				out.println(temp.u + " " + temp.v);
			}
			out.println();
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