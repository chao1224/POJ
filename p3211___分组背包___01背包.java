import java.io.*;
import java.util.*;

public class p3211___分组背包___01背包 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int color, piece;
	static TreeMap<String, Integer> map = new TreeMap<String, Integer>();

	public static void main(String[] args) throws IOException {
		String str;
		int colorBelong[], time[];
		int dp[];

		while (true) {
			color = nextInt();
			piece = nextInt();
			if (color == 0 && piece == 0)
				break;

			map.clear();
			for (int i = 1; i <= color; i++) {
				str = next();
				map.put(str, i);
			}

			time = new int[piece + 1];
			colorBelong = new int[piece + 1];
			for (int i = 1; i <= piece; i++) {
				time[i] = nextInt();
				str = next();
				colorBelong[i] = map.get(str);
			}

			int V;
			dp = new int[100010];
			int ans = 0;
			loop: for (int c = 1; c <= color; c++) {
				V = 0;
				for (int i = 1; i <= piece; i++)
					if (colorBelong[i] == c)
						V += time[i];

				Arrays.fill(dp, -1);
				dp[0] = 1;
				for (int i = 1; i <= piece; i++) {
					if (colorBelong[i] == c) {
						for (int v = V / 2; v >= time[i]; v--)
							if (dp[v - time[i]] == 1)
								dp[v] = 1;
					}
				}

				for (int v = V / 2; v >= 0; v--) {
					if (dp[v] == 1) {
						ans += V - v;
						continue loop;
					}
				}
			}

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

	static float nextFloat() throws IOException {
		in.nextToken();
		return (float) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}