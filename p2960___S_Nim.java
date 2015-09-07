import java.io.*;
import java.security.KeyStore.Entry;
import java.util.*;

public class p2960___S_Nim {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, ans;
	static int k, s[];
	static int N = 10000;
	static int sg[] = new int[N + 1];
	static boolean vis[] = new boolean[N];
	static int h;

	static void init() {
		Arrays.sort(s, 1, k + 1);
		for (int i = 0; i <= N; i++) {
			Arrays.fill(vis, false);
			for (int j = 1; j <= k && s[j] <= i; j++)
				vis[sg[i - s[j]]] = true;
			for (int j = 0;; j++)
				if (!vis[j]) {
					sg[i] = j;
					break;
				}
		}
	}

	public static void main(String[] args) throws IOException {
		while (true) {
			k = nextInt();
			if (k == 0)
				break;
			s = new int[k + 1];
			for (int i = 1; i <= k; i++)
				s[i] = nextInt();
			init();

			n = nextInt();
			while (n-- > 0) {
				ans = 0;
				h = nextInt();
				while (h-- > 0)
					ans ^= sg[nextInt()];
				if (ans > 0)
					out.print("W");
				else
					out.print("L");
			}
			out.println();
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

	static float nextFloat() throws IOException {
		in.nextToken();
		return (float) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}