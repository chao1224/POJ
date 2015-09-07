import java.io.*;
import java.util.*;

public class p1014___Âã___ÍêÈ«±³°ü {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, v = 20000, dp[];

	public static void main(String[] args) throws IOException {
		int num[] = new int[7], sum;
		int cost[] = { 0, 1, 2, 3, 4, 5, 6 };
		int test = 0;
		while (true) {
			sum = 0;
			for (int i = 1; i <= 6; i++) {
				num[i] = nextInt();
				sum += num[i] * cost[i];
			}
			if (sum == 0)
				break;
			dp = new int[sum + 1];
			v = sum;

			dp[0] = 1;
			for (int i = 1; i <= 6; i++)
				MultiplePack(cost[i], num[i]);

			if (test > 0)
				out.println();
			test++;
			out.println("Collection #" + test + ":");
			if ((sum & 1) == 1)
				out.println("Can't be divided.");
			else if (dp[sum / 2] == 0)
				out.println("Can't be divided.");
			else
				out.println("Can be divided.");
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