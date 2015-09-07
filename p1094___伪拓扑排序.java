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
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p1094___Œ±Õÿ∆À≈≈–Ú {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static String str;
	static StringTokenizer sToke;
	static int n, m, indegree[], outdegree[], matrix[][];
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static boolean none, once;

	public static void main(String[] args) throws IOException {
		int a, b;
		while (true) {
			str = br.readLine();
			sToke = new StringTokenizer(str);
			n = Integer.parseInt(sToke.nextToken());
			m = Integer.parseInt(sToke.nextToken());
			if (n == 0 && m == 0)
				break;
			indegree = new int[n];
			outdegree = new int[n];
			matrix = new int[n][n];
			none = false;
			for (int i = 1; i <= m; i++) {
				str = br.readLine();
				a = str.charAt(0) - 'A';
				b = str.charAt(2) - 'A';
				matrix[a][b] = 1;

				for (int ii = 0; ii < n; ii++) {
					for (int jj = 0; jj < n; jj++) {
						for (int k = 0; k < n; k++) {
							if (matrix[ii][k] == 1 && matrix[k][jj] == 1)
								matrix[ii][jj] = 1;
						}
					}
				}

				// none correct
				for (int ii = 0; ii < n; ii++)
					if (matrix[ii][ii] == 1) {
						none = true;
						break;
					}
				if (none) {
					out.println("Inconsistency found after " + i
							+ " relations.");
					for (; i < m; i++)
						br.readLine();
					break;
				} else {
					Arrays.fill(indegree, 0);
					Arrays.fill(outdegree, 0);
					for (int ii = 0; ii < n; ii++) {
						for (int jj = 0; jj < n; jj++) {
							if (matrix[ii][jj] != 0) {
								outdegree[ii]++;
								indegree[jj]++;
							}
						}
					}
					once = true;
					for (int ii = 0; ii < n; ii++) {
						if (indegree[ii] + outdegree[ii] != n - 1) {
							once = false;
							break;
						}
					}

					// just one
					if (once) {
						out.print("Sorted sequence determined after " + i
								+ " relations: ");

						int cnt = 0;
						while (cnt < n)
							for (int ii = 0; ii < n; ii++) {
								if (indegree[ii] == cnt) {
									out.print((char) (ii + 'A'));
									cnt++;
									break;
								}
							}
						out.println(".");
						for (; i < m; i++)
							br.readLine();
						break;
					}
				}
			}
			if (!once && !none)
				out.println("Sorted sequence cannot be determined.");

		}

		out.flush();
		out.close();
	}
}
