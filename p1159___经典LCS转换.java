import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p1159___¾­µäLCS×ª»» {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static short n;
	static String str;
	static int N = 5100;
	static short arr[] = new short[N], brr[] = new short[N];
	static short dp[][] = new short[N][N];

	public static void main(String[] args) throws IOException {
		while ((str = br.readLine()) != null) {
			n = Short.parseShort(str);
			str = br.readLine();
			for (int i = 0; i < n; i++) {
				arr[i + 1] = (short) str.charAt(i);
				brr[i + 1] = (short) str.charAt(n - i - 1);
			}

			for (int i = 0; i <= n; i++)
				Arrays.fill(dp[i], (short) 0);

			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= n; j++)
					if (arr[i] == brr[j])
						dp[i][j] = (short) (dp[i - 1][j - 1] + 1);
					else
						dp[i][j] = (short) Math.max(dp[i - 1][j], dp[i][j - 1]);

			out.println(n - dp[n][n]);
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