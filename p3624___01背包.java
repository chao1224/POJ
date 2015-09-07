import java.io.*;
import java.util.*;

public class p3624___01±³°ü {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(System.out);

	static int n, V;
	static int dp[];

	static void ZeroPack(int cost, int weight) {
		for (int i = V; i >= cost; i--)
			dp[i] = Math.max(dp[i], dp[i - cost] + weight);
	}

	public static void main(String[] args) throws IOException {

		n = nextInt();
		V = nextInt();
		dp = new int[V + 1];

		int weight, cost;
		for (int i = 1; i <= n; i++) {
			cost = nextInt();
			weight = nextInt();
			ZeroPack(cost, weight);
		}

		out.println(dp[V]);

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

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static float nextFloat() throws IOException {
		in.nextToken();
		return (float) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}