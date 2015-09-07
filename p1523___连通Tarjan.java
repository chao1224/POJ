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
import java.util.*;

public class p1523___连通Tarjan {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n = 1100, m = 1000100;
	static int head[] = new int[n], index;
	static edge edge[] = new edge[m];
	static int low[] = new int[n], dfn[] = new int[n], visit[] = new int[n],
			subnet[] = new int[n];

	public static void main(String[] args) throws IOException {
		int a, b, step = 0;
		while (true) {
			a = nextInt();
			if (a == 0)
				break;
			step++;
			if (step > 1)
				out.println();
			out.println("Network #" + step);

			Arrays.fill(head, -1);
			index = -1;
			Arrays.fill(low, -1);
			Arrays.fill(dfn, -1);
			Arrays.fill(visit, 0);
			Arrays.fill(subnet, 0);

			b = nextInt();
			addEdge(a, b);

			while (true) {
				a = nextInt();
				if (a == 0)
					break;
				b = nextInt();
				addEdge(a, b);
			}

			visit[1] = 1;
			dfn[1] = low[1] = 1;
			dfs(1);

			int find = 0;
			// 表示如果是根节点，则在要去掉多余的一条，这一条在后面输出的时候每一个点+1显示出来
			if (subnet[1] >= 1)
				subnet[1]--;
			for (int i = 1; i < n; i++)
				if (subnet[i] > 0) {
					find = 1;
					// 在这里显示
					out.println("  SPF node " + i + " leaves "
							+ (subnet[i] + 1) + " subnets");
				}
			if (find == 0)
				out.println("  No SPF nodes");

		}

		out.flush();
		out.close();
	}

	static void dfs(int u) {
		int v;
		for (int i = head[u]; i != -1; i = edge[i].next) {
			v = edge[i].v;
			if (visit[v] == 0) {
				visit[v] = 1;
				dfn[v] = low[v] = dfn[u] + 1;
				dfs(v);
				low[u] = Math.min(low[v], low[u]);
				if (low[v] >= dfn[u])
					subnet[u]++;
			} else {
				low[u] = Math.min(low[u], dfn[v]);
			}
		}
	}

	static void addEdge(int u, int v) {
		++index;
		edge[index] = new edge(v);
		edge[index].next = head[u];
		head[u] = index;

		++index;
		edge[index] = new edge(u);
		edge[index].next = head[v];
		head[v] = index;
	}

	static class edge {
		int v, next;

		edge(int v) {
			this.v = v;
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

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}