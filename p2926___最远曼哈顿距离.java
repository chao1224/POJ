import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p2926___×îÔ¶Âü¹þ¶Ù¾àÀë {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n;
	static int N = 100010;
	static double point[][] = new double[N][6];

	static double inf = Double.MAX_VALUE;

	static int op[][] = { { -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, 1 },
			{ -1, -1, -1, 1, -1 }, { -1, -1, -1, 1, 1 }, { -1, -1, 1, -1, -1 },
			{ -1, -1, 1, -1, 1 }, { -1, -1, 1, 1, -1 }, { -1, -1, 1, 1, 1 },
			{ -1, 1, -1, -1, -1 }, { -1, 1, -1, -1, 1 }, { -1, 1, -1, 1, -1 },
			{ -1, 1, -1, 1, 1 }, { -1, 1, 1, -1, -1 }, { -1, 1, 1, -1, 1 },
			{ -1, 1, 1, 1, -1 }, { -1, 1, 1, 1, 1 }, { 1, -1, -1, -1, -1 },
			{ 1, -1, -1, -1, 1 }, { 1, -1, -1, 1, -1 }, { 1, -1, -1, 1, 1 },
			{ 1, -1, 1, -1, -1 }, { 1, -1, 1, -1, 1 }, { 1, -1, 1, 1, -1 },
			{ 1, -1, 1, 1, 1 }, { 1, 1, -1, -1, -1 }, { 1, 1, -1, -1, 1 },
			{ 1, 1, -1, 1, -1 }, { 1, 1, -1, 1, 1 }, { 1, 1, 1, -1, -1 },
			{ 1, 1, 1, -1, 1 }, { 1, 1, 1, 1, -1 }, { 1, 1, 1, 1, 1 } };

	public static void main(String[] args) throws IOException {

		n = nextInt();
		for (int i = 1; i <= n; i++)
			for (int j = 0; j < 5; j++)
				point[i][j] = nextDouble();

		double max, min, ans = -1;

		double temp;
		for (int mark = 0; mark < 32; mark++) {
			max = -inf;
			min = inf;
			for (int i = 1; i <= n; i++) {
				temp = 0;
				for (int j = 0; j < 5; j++)
					temp += point[i][j] * op[mark][j];

				max = Math.max(max, temp);
				min = Math.min(min, temp);
			}
			ans = Math.max(ans, max - min);
		}

		out.printf("%.2f", ans);
		out.println();

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