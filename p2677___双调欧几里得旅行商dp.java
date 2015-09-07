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

public class p2677___双调欧几里得旅行商dp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		while (in.nextToken() != in.TT_EOF) {
			int n = (int) in.nval;
			int x[] = new int[n + 1], y[] = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				x[i] = nextInt();
				y[i] = nextInt();
			}

			double[][] d = new double[n + 1][n + 1];
			double[][] w = new double[n + 1][n + 1];
			for (int i = 1; i <= n; i++)
				for (int j = i + 1; j <= n; j++)
					w[i][j] = Math.sqrt((x[i] - x[j]) * (x[i] - x[j])
							+ (y[i] - y[j]) * (y[i] - y[j]));

			d[1][2] = w[1][2];
			for (int j = 3; j <= n; j++) {
				for (int i = 1; i < j - 1; i++)
					d[i][j] = d[i][j - 1] + w[j - 1][j];
				d[j - 1][j] = Double.MAX_VALUE;
				double temp;
				for (int k = 1; k <= j - 2; k++) {
					temp = d[k][j - 1] + w[k][j];
					if (temp < d[j - 1][j])
						d[j - 1][j] = temp;
				}
			}
			d[n][n] = d[n - 1][n] + w[n - 1][n];

			out.println(String.format("%.2f", d[n][n]));
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