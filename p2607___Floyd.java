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

public class p2607___Floyd {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);
	static int n, m, f, inf = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		int u, v, val;

		f = nextInt();
		n = nextInt();
		int point[] = new int[n + 1];
		int adj[][] = new int[n + 1][n + 1];
		for (int i = 1; i <= f; i++)
			point[nextInt()] = 1;
		for (int i = 1; i <= n; i++) {
			Arrays.fill(adj[i], inf);
			adj[i][i] = 0;
		}

		while (in.nextToken() != in.TT_EOF) {
			u = (int) in.nval;
			v = nextInt();
			val = nextInt();
			adj[u][v] = adj[v][u] = Math.min(adj[u][v], val);
		}
		for (int k = 1; k <= n; k++)
			for (int i = 1; i <= n; i++) {
				if (adj[i][k] == inf)
					continue;
				for (int j = 1; j <= n; j++) {
					if (adj[k][j] == inf)
						continue;
					if (adj[i][j] > adj[i][k] + adj[k][j])
						adj[i][j] = adj[i][k] + adj[k][j];
				}
			}

		int min = inf, max, fff, vertex = 1;
		for (int i = 1; i <= n; i++) {
			if (point[i] == 0) {
				max = 0;
				for (int j = 1; j <= n; j++) {
					if (point[j] == 0) {
						fff = Integer.MAX_VALUE;
						for (int k = 1; k <= n; k++) {
							if (point[k] == 1)
								fff = Math.min(fff, adj[k][j]);
						}
						fff = Math.min(fff, adj[i][j]);
						max = Math.max(max, fff);
					}
				}
				if (max < min) {
					min = max;
					vertex = i;
				}
			}
		}
		out.println(vertex);

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

}