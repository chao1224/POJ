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

public class p2337___欧拉回路___输出边的序号 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m;
	static int father[] = new int[27], visit[] = new int[27];
	static int indegree[] = new int[27], outdegree[] = new int[27];
	static int ans[] = new int[5000], num;
	static int head[] = new int[27], toll;
	static edge edge[] = new edge[5000];
	static String record[] = new String[5000];
	static int start;

	static void init() {
		Arrays.fill(indegree, 0);
		Arrays.fill(outdegree, 0);
		toll = 0;
		Arrays.fill(head, -1);
		num = 0;
		for (int i = 0; i <= 26; i++)
			father[i] = i;
		Arrays.fill(visit, 0);
	}

	public static void main(String[] args) throws IOException {
		int ttt = nextInt(), a, b;
		String str;
		while (ttt-- > 0) {
			n = nextInt();
			init();
			for (int i = 0; i < n; i++)
				record[i] = next();
			Arrays.sort(record, 0, n);
			start = 1 + record[0].charAt(0) - 'a';

			for (int i = n - 1; i >= 0; i--) {
				// for (int i = 0; i <= n - 1; i++) {
				str = record[i];
				// out.println(str);
				a = 1 + str.charAt(0) - 'a';
				b = 1 + str.charAt(str.length() - 1) - 'a';
				addEdge(a, b);
				union(a, b);
				visit[a] = visit[b] = 1;
			}

			if (!check()) {
				out.println("***");
			} else {
				dfs(start);
				for (int i = num - 1; i > 0; i--)
					// for (int i = 0; i < num - 1; i++)
					out.print(record[n - 1 - ans[i]] + ".");
				// out.println(record[ans[num - 1]]);
				out.println(record[n - 1 - ans[0]]);
			}
		}

		out.flush();
		out.close();
	}

	static int find(int x) {
		if (father[x] != x)
			father[x] = find(father[x]);
		return father[x];
	}

	static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if (ra != rb)
			father[ra] = rb;
	}

	static void addEdge(int u, int v) {
		edge[toll] = new edge(v);
		edge[toll].next = head[u];
		head[u] = toll++;
		outdegree[u]++;
		indegree[v]++;
	}

	static class edge {
		int v, next;
		boolean mark;

		edge(int v) {
			this.v = v;
			mark = false;
		}
	}

	static void dfs(int u) {
		for (int i = head[u]; i != -1; i = edge[i].next) {
			if (edge[i].mark)
				continue;
			edge[i].mark = true;
			dfs(edge[i].v);
			ans[num++] = i;
		}
	}

	static boolean check() {
		int i, root = 0;
		for (i = 1; i <= 26; i++)
			if (visit[i] == 1) {
				root = find(i);
				break;
			}

		for (i = i + 1; i <= 26; i++)
			if (visit[i] == 1 && root != find(i))
				return false;

		int inum = 0;
		for (i = 1; i <= 26; i++) {
			if (indegree[i] == outdegree[i] + 1) {
				inum++;
			} else if (indegree[i] + 1 == outdegree[i]) {
				inum++;
				start = i;
			} else if (indegree[i] != outdegree[i])
				return false;
		}

		if (inum > 2)
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

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}
