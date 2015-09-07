import java.io.*;
import java.security.KeyStore.Entry;
import java.util.*;

public class p1085___极大极小搜索___alpha_beta___剪枝 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n;
	static int N = 11;
	static int goal = (1 << 18) - 1;
	static int map[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 3, 4, 0, 0, 0, 0, 0 },
			{ 0, 1, 2, 0, 0, 5, 6, 0, 0, 0, 0 },
			{ 0, 0, 3, 0, 0, 7, 0, 9, 10, 0, 0 },
			{ 0, 0, 4, 5, 7, 0, 8, 0, 11, 12, 0 },
			{ 0, 0, 0, 6, 0, 8, 0, 0, 0, 13, 14 },
			{ 0, 0, 0, 0, 9, 0, 0, 0, 15, 0, 0 },
			{ 0, 0, 0, 0, 10, 11, 0, 15, 0, 16, 0 },
			{ 0, 0, 0, 0, 0, 12, 13, 0, 16, 0, 17 },
			{ 0, 0, 0, 0, 0, 0, 14, 0, 0, 17, 0 } };
	static int record[] = { 7, 152, 52, 352, 34304, 3200, 71680, 12544, 155648 };
	// { (1 << 0) | (1 << 1) | (1 << 2),
	// (1 << 3) | (1 << 4) | (1 << 7), (1 << 2) | (1 << 4) | (1 << 5),
	// (1 << 5) | (1 << 6) | (1 << 8), (1 << 9) | (1 << 10) | (1 << 15),
	// (1 << 7) | (1 << 10) | (1 << 11),
	// (1 << 11) | (1 << 12) | (1 << 16),
	// (1 << 8) | (1 << 12) | (1 << 13), (1 << 13) | (1 << 14) | (1 << 17) };
	static int index = 0;

	static int getTriangleNum(int old, int neo, int sum) {
		for (int i = 0; i < 9; i++)
			if ((record[i] & old) != record[i]
					&& (record[i] & neo) == record[i])
				sum++;
		return sum;
	}

	static int MaxSearch(int state, int alpha, int a, int b) {
		if (a >= 5)
			return 1;
		if (b >= 5)
			return -1;
		if (state == goal)
			return a > b ? 1 : -1;
		int neo, max = -1, c, temp;
		for (int i = 0; i < 18; i++)
			if (((1 << i) & state) == 0) {
				neo = state | (1 << i);
				c = getTriangleNum(state, neo, a);
				if (c > a)
					temp = MaxSearch(neo, alpha, c, b);
				else
					temp = MinSearch(neo, max, c, b);
				max = Math.max(max, temp);
				if (temp >= alpha)
					return max;
			}
		return max;
	}

	static int MinSearch(int state, int beta, int a, int b) {
		if (a >= 5)
			return 1;
		if (b >= 5)
			return -1;
		if (state == goal)
			return a > b ? 1 : -1;
		int neo, min = 1, c, temp;
		for (int i = 0; i < 18; i++)
			if (((1 << i) & state) == 0) {
				neo = state | (1 << i);
				c = getTriangleNum(state, neo, b);
				if (c > b)
					temp = MinSearch(neo, beta, a, c);
				else
					temp = MaxSearch(neo, min, a, c);
				min = Math.min(min, temp);
				if (temp <= beta)
					return min;
			}
		return min;
	}

	public static void main(String[] args) throws IOException {
		int ttt = nextInt(), turn;
		int state, a, b, temp, ans, u, v, t, neo;
		for (int test = 1; test <= ttt; test++) {
			n = nextInt();
			state = turn = a = b = 0;
			for (int i = 1; i <= n; i++) {
				u = nextInt();
				v = nextInt();
				t = map[u][v];
				neo = state | (1 << t);
				temp = getTriangleNum(state, neo, 0);
				if (temp > 0) {
					if ((turn & 1) == 0)
						a += temp;
					else
						b += temp;
				} else
					turn++;
				state = neo;
			}

			if ((turn & 1) == 0)
				ans = MaxSearch(state, 1, a, b);
			else
				ans = MinSearch(state, -1, a, b);

			if (ans > 0)
				out.printf("Game %d: A wins. \n", test);
			else
				out.printf("Game %d: B wins. \n", test);
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