import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p2033___¼òµ¥µÝÍÆ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n;
	static String str;
	static int arr[];
	static long dp[];

	public static void main(String[] args) throws IOException {

		loop: while (!(str = br.readLine()).equals("0")) {
			n = str.length();
			arr = new int[n + 1];
			dp = new long[n + 1];
			for (int i = 1; i <= n; i++)
				arr[i] = str.charAt(i - 1) - '0';

			dp[0] = 1;
			dp[1] = 1;
			for (int i = 2; i <= n; i++) {
				if (arr[i] > 0)
					dp[i] = dp[i - 1];
				int temp = arr[i - 1] * 10 + arr[i];
				if (temp <= 26 && arr[i - 1] > 0)
					dp[i] = (long) dp[i] + (long) dp[i - 2];
			}

			out.println(dp[n]);
		}

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

	static short nextShort() throws IOException {
		in.nextToken();
		return (short) in.nval;
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