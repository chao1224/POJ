import java.awt.Stroke;
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

public class p1192___Ê÷ÐÎdp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);
	static int n, m;
	static int x[], y[], c[], dp[][];

	public static void main(String[] args) throws IOException {
		n = nextInt();
		x = new int[n + 1];
		y = new int[n + 1];
		c = new int[n + 1];
		dp = new int[n + 1][2];
		for (int i = 1; i <= n; i++) {
			x[i] = nextInt();
			y[i] = nextInt();
			c[i] = nextInt();
		}
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 0;
			dp[i][1] = c[i];
		}
		dfs(1, 0);
		out.println(Math.max(dp[1][0], dp[1][1]));
		out.flush();
		out.close();
	}

	static void dfs(int child, int root) {
		for (int i = 1; i <= n; i++) {
			if (i != root
					&& (Math.abs(x[i] - x[child]) + Math.abs(y[i] - y[child])) == 1) {
				dfs(i, child);
				dp[child][0] = Math.max(dp[child][0], Math.max(dp[i][0],
						dp[i][1]));
				dp[child][1] = Math.max(dp[child][1], dp[child][1]
						+ Math.max(dp[i][0], dp[i][1]));
			}
		}
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
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
