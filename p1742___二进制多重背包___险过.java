import java.io.*;
import java.util.*;

public class p1742___二进制多重背包___险过 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, v, dp[];

	public static void main(String[] args) throws IOException {

		while (true) {
			n = nextInt();
			v = nextInt();
			if (n == 0 && v == 0)
				break;
			dp = new int[v + 1];
			int cost[] = new int[n + 1];
			for (int i = 1; i <= n; i++)
				cost[i] = nextInt();
			int amount[] = new int[n + 1];
			for (int i = 1; i <= n; i++)
				amount[i] = nextInt();

			dp[0] = 1;
			for (int i = 1; i <= n; i++)
				MultiplePack(cost[i], amount[i]);

			int ans = 0;
			for (int i = 1; i <= v; i++)
				if (dp[i] == 1)
					ans++;
			out.println(ans);
		}

		out.flush();
		out.close();
	}

	static void MultiplePack(int cost, int amount) {
		if (cost * amount >= v) {
			CompletePack(cost);
		} else {
			int k = 1;
			while (k < amount) {
				ZeroPack(cost * k);
				amount -= k;
				k *= 2;
			}
			ZeroPack(cost * amount);
		}
	}

	static void CompletePack(int cost) {
		for (int i = cost; i <= v; i++)
			if (dp[i] == 0 && dp[i - cost] == 1)
				dp[i] = 1;
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