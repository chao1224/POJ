//3629
//1417
//2352
//1038

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p3687___Ææ¹ÖµÄÍØÆËÅÅÐò {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static int n, m, degree[], matrix[][], path[];
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static boolean no;

	public static void main(String[] args) throws IOException {
		int t = nextInt(), a, b;
		while (t-- > 0) {
			n = nextInt();
			m = nextInt();
			matrix = new int[n + 1][n + 1];
			for (int i = 1; i <= m; i++) {
				a = nextInt();
				b = nextInt();
				matrix[a][b] = 1;
			}
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (matrix[i][j] == 0)
						for (int k = 1; k <= n; k++) {
							if (matrix[i][k] == 1 && matrix[k][j] == 1)
								matrix[i][j] = 1;
						}
				}
			}

			// figure out whether there is a loop
			no = false;
			for (int i = 1; i <= n; i++) {
				if (matrix[i][i] == 1) {
					no = true;
					break;
				}
			}

			if (no) {
				out.println(-1);
			} else {
				// figure the degree of each point
				degree = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= n; j++)
						if (matrix[i][j] == 1)
							degree[i]++;
				}

				PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
				path = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					if (degree[i] == 0) {
						queue.add(-i);
						degree[i] = -1;
					}
				}

				int cur, cnt = n;
				while (!queue.isEmpty()) {
					cur = -queue.poll();
					path[cur] = cnt--;
					for (int j = 1; j <= n; j++) {
						if (matrix[j][cur] == 1) {
							matrix[j][cur] = 0;
							degree[j]--;
						}
						for (int i = 1; i <= n; i++)
							if (degree[i] == 0) {
								degree[i] = -1;
								queue.add(-i);
							}
					}
				}
				for (int i = 1; i < n; i++)
					out.print(path[i] + " ");
				out.println(path[n]);
			}
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
}
