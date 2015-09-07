import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p1036___DP___ø…“‘Õº¬€ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, k, t, dp[][], max[];
	static node arr[];

	public static void main(String[] args) throws IOException {
		n = nextInt();
		k = nextInt();
		t = nextInt();
		arr = new node[n];
		int i;
		for (i = 0; i < n; i++)
			arr[i] = new node(nextInt());
		for (i = 0; i < n; i++)
			arr[i].p = nextInt();
		for (i = 0; i < n; i++) {
			arr[i].s = nextInt();
		}
		Arrays.sort(arr);

		dp = new int[2][k + 1];
		max = new int[k + 1];
		for (int x[] : dp)
			Arrays.fill(x, 0);
		int time, door, cur = 0, pre = 1, ans = 0;
		for (time = t; time >= 0; time--) {
			cur = 1 - cur;
			pre = 1 - pre;
			Arrays.fill(dp[cur], 0);
			for (door = 0; door <= k; door++) {
				dp[cur][door] = dp[pre][door];
				if (door > 0)
					dp[cur][door] = Math.max(dp[cur][door], dp[pre][door - 1]);
				if (door < k)
					dp[cur][door] = Math.max(dp[cur][door], dp[pre][door + 1]);
			}

			// Arrays.fill(max, 0);
			for (i = 0; i < n; i++) {
				if (arr[i].t == time)
					dp[cur][arr[i].s] += arr[i].p;
			}
			// for (i = 0; i <= k; i++) {
			// dp[cur][i] += max[i];
			// ans = Math.max(ans, dp[cur][i]);
			// }
		}
		out.println(dp[cur][0]);

		out.flush();
		out.close();
	}

	static class node implements Comparable<node> {
		int t, s, p;

		node(int t) {
			this.t = t;
		}

		public int compareTo(node o) {
			if (this.t < o.t)
				return -1;
			else
				return 1;
		}
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static int nextint() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
