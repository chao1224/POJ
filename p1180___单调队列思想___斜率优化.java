import java.io.*;
import java.util.*;

public class p1180___单调队列思想___斜率优化 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, s, t[], f[];
	static long dp[], sumT[], sumF[];
	static long inf = 1000000000000000000l;

	public static void main(String[] args) throws IOException {
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			s = nextInt();
			t = new int[n + 2];
			f = new int[n + 2];
			sumT = new long[n + 2];
			sumF = new long[n + 2];
			for (int i = 1; i <= n; i++) {
				t[i] = nextInt();
				f[i] = nextInt();
			}
			for (int i = n; i >= 1; i--) {
				sumT[i] = sumT[i + 1] + t[i];
				sumF[i] = sumF[i + 1] + f[i];
			}

			dp = new long[n + 2];
			Arrays.fill(dp, inf);
			dp[n + 1] = 0;

			int queue[] = new int[n + 100], head = 0, tail = 0;
			dp[n + 1] = 0;
			dp[n] = sumF[n] * (s + sumT[n]);
			queue[tail++] = n + 1;
			for (int i = n; i >= 1; i--) {
				while (head + 1 < tail
						&& G(queue[head + 1], queue[head]) <= sumF[i]
								* S(queue[head + 1], queue[head]))
					head++;
				dp[i] = dp[queue[head]] + (s + sumT[i] - sumT[queue[head]])
						* (sumF[i]);
				while ((head + 1 < tail)
						&& G(queue[tail - 1], queue[tail - 2])
								* S(i, queue[tail - 1]) > G(i, queue[tail - 1])
								* S(queue[tail - 1], queue[tail - 2]))
					tail--;
				queue[tail++] = i;
			}

			out.println(dp[1]);

		}
		out.flush();
		out.close();
	}

	static long G(int x, int y) {
		return dp[x] - dp[y];
	}

	static long S(int x, int y) {
		return sumT[x] - sumT[y];
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