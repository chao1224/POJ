import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

public class p1679___MST唯一性 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m;

	public static void main(String[] args) throws IOException {
		int testcase = nextInt(), u, v, w, cnt, a, b, ra, rb, ans, adj[][], mst[][];
		p1679___edge tmp;
		PriorityQueue<p1679___edge> queue = new PriorityQueue<p1679___edge>();
		while (testcase-- > 0) {
			n = nextInt();
			m = nextInt();
			queue.clear();
			adj = new int[n + 1][n + 1];
			for (int i = 1; i <= m; i++) {
				u = nextInt();
				v = nextInt();
				w = nextInt();
				adj[u][v] = adj[v][u] = w;
				tmp = new p1679___edge(u, v, w);
				queue.add(tmp);
			}

			init(n);
			cnt = 1;
			ans = 0;
			mst = new int[n + 1][n + 1];
			while (!queue.isEmpty()) {
				tmp = queue.poll();
				a = tmp.u;
				b = tmp.v;
				ra = find(a);
				rb = find(b);
				if (ra != rb) {
					cnt++;
					union(ra, rb);
					ans += tmp.w;
					mst[a][b] = mst[b][a] = tmp.w;
					adj[a][b] = adj[b][a] = 0;
				}
			}
			if (cnt == n) {
				boolean mark = true;
				// 求出adj、mst每个点最小的带权边
				int adjRecord[] = new int[n + 1];
				int mstRecord[] = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= n; j++) {
						if (adj[i][j] > 0) {
							if (adjRecord[i] == 0)
								adjRecord[i] = adj[i][j];
							else
								adjRecord[i] = Math
										.min(adjRecord[i], adj[i][j]);
						}
						if (mst[i][j] > 0) {
							if (mstRecord[i] == 0)
								mstRecord[i] = mst[i][j];
							else
								mstRecord[i] = Math
										.min(mstRecord[i], mst[i][j]);
						}

					}
					// 这里adjRecord一定要大于0，因为mst的权边可能为0
					if (adjRecord[i] == mstRecord[i] && adjRecord[i] > 0) {
						mark = false;
						break;
					}
				}

				if (mark)
					out.println(ans);
				else
					out.println("Not Unique!");
			} else
				out.println("Not Unique!");
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

	static int father[], rank[];

	static void init(int n) {
		father = new int[n + 1];
		rank = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			father[i] = i;
			rank[i] = 1;
		}
	}

	static int find(int x) {
		if (x == father[x])
			return x;
		father[x] = find(father[x]);
		return father[x];
	}

	static void union(int ra, int rb) {
		if (rank[ra] > rank[rb]) {
			father[rb] = ra;
		} else {
			if (rank[ra] == rank[rb])
				rank[rb]++;
			father[ra] = rb;
		}
	}

}

class p1679___edge implements Comparable<p1679___edge> {
	int u, v, w;

	p1679___edge(int u, int v, int w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	public int compareTo(p1679___edge o) {
		if (this.w < o.w)
			return -1;
		else if (this.w > o.w)
			return 1;
		else
			return 0;
	}

}
