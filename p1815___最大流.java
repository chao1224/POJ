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

public class p1815___×î´óÁ÷ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, a, b, source, destination;

	public static void main(String[] args) throws IOException {
		n = nextInt();
		source = nextInt();
		destination = nextInt();

		Dinic dinic = new Dinic(n * 2);
		for (int i = 1; i <= n; i++)
			dinic.addEdge(i, i + n, 1);
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				if (nextInt() == 1)
					dinic.addEdge(i + n, j);
		dinic.addEdge(source, source + n);
		dinic.addEdge(destination, destination + n);
		destination += n;

		if (dinic.cap[source + n][destination - n] == 0)
			dinic.getDinic(source, destination);
		else
			out.println("NO ANSWER!");

		out.flush();
		out.close();
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
		int cap[][], flow[][], inf = Integer.MAX_VALUE;
		int n, level[], queue[];

		Dinic(int n) {
			this.n = n;
			cap = new int[n + 1][n + 1];
			flow = new int[n + 1][n + 1];
			level = new int[n + 1];
			queue = new int[n + 1];
		}

		void addEdge(int u, int v) {
			this.cap[u][v] = inf;
		}

		void addEdge(int u, int v, int cap) {
			this.cap[u][v] += cap;
		}

		boolean bfs(int source, int destination) {
			Arrays.fill(level, -1);
			level[source] = 0;

			int temp, start = 0, end = 1;
			queue[start] = source;
			while (start < end) {
				temp = queue[start++];
				for (int i = 1; i <= n; i++) {
					if (level[i] == -1 && cap[temp][i] > flow[temp][i]) {
						queue[end++] = i;
						level[i] = level[temp] + 1;
						if (i == destination)
							return true;
					}
				}
			}
			return false;
		}

		int dfs(int vert, int destination, int sum) {
			if (vert == destination)
				return sum;
			int s = sum, temp;
			for (int i = 1; i <= n; i++) {
				if (cap[vert][i] > flow[vert][i] && level[i] == level[vert] + 1) {
					temp = dfs(i, destination, Math.min(sum, cap[vert][i]
							- flow[vert][i]));
					flow[vert][i] += temp;
					cap[i][vert] -= temp;
					sum -= temp;
				}
			}
			return s - sum;
		}

		void getDinic(int source, int destination) {
			int sum = 0;
			while (bfs(source, destination))
				sum += dfs(source, destination, inf);
			out.println(sum);
			int temp = sum, k, t = n / 2;
			int set[] = new int[sum + 1], cnt = 0;
			for (int i = 1; i <= t; i++) {
				if (i == source || i == destination - t || flow[i][i + t] == 0)
					continue;
				k = 0;

				cap[i][i + t] = 0;
				for (int j = 1; j <= n; j++)
					Arrays.fill(flow[j], 0);
				while (bfs(source, destination))
					k += dfs(source, destination, inf);

				if (k != temp) {
					set[cnt++] = i;
					temp = k;
				} else {
					cap[i][i + t] = 1;
				}

			}
			for (int c = 0; c < sum; c++)
				out.print(set[c] + " ");
		}
	}

}