import java.io.*;
import java.util.*;

public class p3260___”≈ªØ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, v = 500000, t, inf = 1000000;

	public static void main(String[] args) throws IOException {
		int amount[], cost[];
		n = nextInt();
		t = nextInt();
		amount = new int[n + 1];
		cost = new int[n + 1];
		for (int i = 1; i <= n; i++)
			cost[i] = nextInt();
		for (int i = 1; i <= n; i++)
			amount[i] = nextInt();

		int[] dp = new int[v + 1];
		Arrays.fill(dp, inf);
		dp[0] = 0;
		for (int i = 1; i <= n; i++)
			MultiplePack(cost[i], amount[i], dp);

		int[] dp2 = new int[v + 1];
		Arrays.fill(dp2, inf);
		dp2[0] = 0;
		for (int i = 1; i <= n; i++)
			CompletePack(cost[i], dp2);

		int min = inf;
		for (int i = t; i <= v; i++)
			min = Math.min(min, dp[i] + dp2[i - t]);

		if (min == inf)
			out.println(-1);
		else
			out.println(min);

		out.flush();
		out.close();
	}

	static void MultiplePack(int cost, int amount, int[] dp) {
		int k = 1;
		while (k < amount) {
			ZeroPack(cost * k, k, dp);
			amount -= k;
			k *= 2;
		}
		ZeroPack(cost * amount, amount, dp);
	}

	static void CompletePack(int cost, int dp[]) {
		for (int i = cost; i <= v; i++)
			if (dp[i - cost] < inf)
				dp[i] = Math.min(dp[i], dp[i - cost] + 1);
	}

	static void ZeroPack(int cost, int amount, int[] dp) {
		for (int i = v; i >= cost; i--)
			if (dp[i - cost] < inf)
				dp[i] = Math.min(dp[i], dp[i - cost] + amount);
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