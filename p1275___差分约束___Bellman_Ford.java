import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p1275___差分约束___Bellman_Ford {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, r[] = new int[25], t[] = new int[25], dis[] = new int[25],
			middle;
	static p1275___path[] edge;

	public static void main(String[] args) throws IOException {
		int testcase = nextInt(), tmp, max, cnt, begin, end;
		while (testcase-- > 0) {
			Arrays.fill(t, 0);
			max = 0;
			for (int i = 1; i <= 24; i++) {
				r[i] = nextInt();
				max = Math.max(r[i], max);
			}
			n = nextInt();
			for (int i = 1; i <= n; i++) {
				tmp = nextInt();
				t[tmp + 1]++;
			}

			edge = new p1275___path[73];
			cnt = 0;
			for (int i = 1; i <= 24; i++) {
				cnt++;
				edge[cnt] = new p1275___path(i - 1, i, t[i]);
				cnt++;
				edge[cnt] = new p1275___path(i, i - 1, 0);
			}
			for (int i = 9; i <= 24; i++) {
				cnt++;
				edge[cnt] = new p1275___path(i, i - 8, -r[i]);
			}

			begin = 0;
			// 注意，end=n，而非max（r[i]中最大的）
			end = n;
			max = -1;
			while (begin <= end) {
				middle = (begin + end + 1) >> 1;
				cnt = 64;
				edge[0] = new p1275___path(24, 0, -middle);
				for (int i = 1; i <= 8; i++) {
					cnt++;
					edge[cnt] = new p1275___path(i, i - 8 + 24, middle - r[i]);
				}
				if (Bellman_Ford()) {
					max = middle;
					end = middle - 1;
				} else {
					begin = middle + 1;
				}
			}

			if (max == -1)
				out.println("No Solution");
			else
				out.println(max);

		}

		out.flush();
		out.close();
	}

	static boolean Bellman_Ford() {
		Arrays.fill(dis, 2 * n);
		dis[0] = 0;
		for (int time = 0; time <= 24; time++) {
			for (int i = 0; i <= 72; i++) {
				if (dis[edge[i].v] > dis[edge[i].u] + edge[i].val)
					dis[edge[i].v] = dis[edge[i].u] + edge[i].val;

			}
		}
		for (int i = 0; i <= 72; i++) {
			if (dis[edge[i].v] > dis[edge[i].u] + edge[i].val)
				return false;
		}
		return true;
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}

class p1275___path {
	int u, v, val, next;

	p1275___path(int u, int v, int val) {
		this.u = u;
		this.v = v;
		this.val = val;
		this.next = -1;
	}
}
