import java.io.*;
import java.util.*;

public class p1276___Âã___¶àÖØ±³°ü {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, v, dp[];

	public static void main(String[] args) throws IOException {
		int amount[], cost[];
		while (in.nextToken() != in.TT_EOF) {
			v = (int) in.nval;
			n = nextInt();
			dp = new int[v + 1];
			amount = new int[n + 1];
			cost = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				amount[i] = nextInt();
				cost[i] = nextInt();
			}

			dp[0] = 1;
			for (int i = 1; i <= n; i++)
				MultiplePack(cost[i], amount[i]);
			int ans = v;
			for (; ans >= 0; ans--)
				if (dp[ans] == 1)
					break;
			out.println(ans);

		}

		out.flush();
		out.close();
	}

	static void MultiplePack(int cost, int amount) {
		int k = 1;
		while (k < amount) {
			ZeroPack(cost * k);
			amount -= k;
			k *= 2;
		}
		ZeroPack(cost * amount);
	}

	static void ZeroPack(int cost) {
		for (int i = v; i >= cost; i--)
			if (dp[i] == 0 && dp[i - cost] == 1)
				dp[i] = 1;
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