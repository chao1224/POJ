import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

public class p1724___Dijkstra___队列实现 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int k, n, r, head[];
	static boolean visit[];
	static p1724___road edge[];
	static PriorityQueue<p1724___node> queue;

	public static void main(String[] args) throws IOException {
		int testcase = nextInt(), u, v, len, toll;
		queue = new PriorityQueue<p1724___node>();
		while (testcase-- > 0) {
			k = nextInt();
			n = nextInt();
			r = nextInt();
			edge = new p1724___road[r + 1];
			head = new int[n + 1];

			for (int i = 1; i <= r; i++) {
				u = nextInt();
				v = nextInt();
				len = nextInt();
				toll = nextInt();
				edge[i] = new p1724___road(v, len, toll);
				edge[i].next = head[u];
				head[u] = i;
			}

			out.println(dijkstra());

		}

		out.flush();
		out.close();
	}

	static int dijkstra() {
		queue.clear();
		p1724___node tmp, cur;
		tmp = new p1724___node(1, 0, 0);
		queue.add(tmp);

		while (!queue.isEmpty()) {
			tmp = queue.poll();
			if (tmp.id == n)
				return tmp.len;

			for (int i = head[tmp.id]; i > 0; i = edge[i].next) {
				if (tmp.cost + edge[i].toll <= k) {
					cur = new p1724___node(edge[i].u, tmp.len + edge[i].len, tmp.cost
							+ edge[i].toll);
					queue.add(cur);
				}
			}
		}

		return -1;
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}

class p1724___road {
	int u, len, toll, next;

	p1724___road(int u, int len, int toll) {
		this.u = u;
		this.len = len;
		this.toll = toll;
	}

}

class p1724___node implements Comparable<p1724___node> {
	int id, len, cost;

	p1724___node(int id, int len, int cost) {
		this.id = id;
		this.len = len;
		this.cost = cost;
	}

	public int compareTo(p1724___node o) {
		if (this.len < o.len)
			return -1;
		else if (this.len > o.len)
			return 1;
		else if (this.cost < o.cost)
			return -1;
		else if (this.cost > o.cost)
			return 1;
		else
			return 0;
	}
}