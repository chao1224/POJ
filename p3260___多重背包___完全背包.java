import java.io.*;
import java.util.*;

public class p3260___多重背包___完全背包 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, v, dp[], t, inf = 1000000;

	public static void main(String[] args) throws IOException {
		int amount[], cost[], max;
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			t = nextInt();
			max = t;
			amount = new int[n + 1];
			cost = new int[n + 1];
			for (int i = 1; i <= n; i++)
				cost[i] = nextInt();
			for (int i = 1; i <= n; i++) {
				amount[i] = nextInt();
				max = Math.max(max, amount[i] * cost[i]);
			}
			v = 500000;
			dp = new int[v + 1];
			Arrays.fill(dp, inf);
			dp[0] = 0;

			for (int i = 1; i <= n; i++)
				MultiplePack(cost[i], amount[i]);
			for (int i = 1; i <= n; i++)
				CompletePack(cost[i]);

			if (dp[t] == inf)
				out.println(-1);
			else
				out.println(dp[t]);
		}
		out.flush();
		out.close();
	}

	static void MultiplePack(int cost, int amount) {
		int k = 1;
		while (k < amount) {
			ZeroPack(cost * k, k);
			amount -= k;
			k *= 2;
		}
		ZeroPack(cost * amount, amount);
	}

	static void CompletePack(int cost) {
		for (int i = v - cost; i >= 0; i--)
			if (dp[i + cost] < inf)
				dp[i] = Math.min(dp[i], dp[i + cost] + 1);
	}

	static void ZeroPack(int cost, int amount) {
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