import java.io.*;
import java.util.*;

public class p1742___∆”Àÿ∞Ê∂‡÷ÿ±≥∞¸TLE {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n, v, c[], w[], num[], dp[], k;
		while (true) {
			n = nextInt();
			v = nextInt();
			if (n == 0 && v == 0)
				break;
			c = new int[n + 1];
			w = new int[n + 1];
			num = new int[n + 1];
			for (int i = 1; i <= n; i++)
				c[i] = nextInt();
			for (int i = 1; i <= n; i++)
				num[i] = nextInt();
			dp = new int[v + 1];

			dp[0] = 1;
			for (int i = 1; i <= n; i++)
				for (int j = v; j >= 1; j--)
					for (k = 0; k <= num[i]; k++)
						if (j - k * c[i] >= 0 && dp[j - k * c[i]] == 1)
							dp[j] = 1;

			int ans = 0;
			for (int i = 1; i <= v; i++)
				if (dp[i] == 1)
					ans++;
			out.println(ans);
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