import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p1324___贪吃蛇BFS经典题 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m, l, k;
	static short dp[][][] = new short[21][21][1 << 14];
	// 6553700
	static short q[][] = new short[1500000][3];
	static int rock[][] = new int[410][2];
	static boolean vis[][] = new boolean[21][21];
	// represent up, left, right and down respectively
	// up(-1,0) is 0
	// left(0,-1) is 1
	// right(0,1) is 2
	// down(1,0) is 3
	// every token of direction can reflex its number in 四进制
	static short dir[][] = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

	static void check(int x, int y, int state) {
		int a, b;
		for (int i = 0; i < l - 1; i++) {
			int choice = (state >> (i * 2)) % 4;
			if (choice == 0) {
				// down
				a = x - 1;
				b = y;
			} else if (choice == 1) {
				// left
				a = x;
				b = y - 1;
			} else if (choice == 2) {
				// right
				a = x;
				b = y + 1;
			} else {
				// up
				a = x + 1;
				b = y;
			}
			x = a;
			y = b;
			vis[x][y] = true;
		}
	}

	static boolean valid(int a, int b) {
		if (a >= 1 && a <= n && b >= 1 && b <= m)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		int test = 0;
		while (true) {
			n = nextInt();
			m = nextInt();
			l = nextInt();
			if (n == 0 && m == 0 && l == 0)
				break;
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= m; j++)
					Arrays.fill(dp[i][j], (short) -1);

			short x = (short) nextInt(), y = (short) nextInt(), a = 0, b = 0, c, d;

			short X = (short) x, Y = (short) y;
			short s = 0;

			for (int i = 0; i < l - 1; i++) {
				a = (short) nextInt();
				b = (short) nextInt();
				c = (short) (a - x);
				d = (short) (b - y);
				if (c == 0) {
					if (d == 1)
						// to the right
						s = (short) (s | (2 << (i * 2)));
					else
						// to the left
						s = (short) (s | (1 << (i * 2)));
				} else {
					if (c == -1)
						// to the down
						s = (short) (s | (0 << (i * 2)));
					else
						// to the up
						s = (short) (s | (3 << (i * 2)));
				}
				x = a;
				y = b;
			}

			int h = 1, t = 2;
			q[h][0] = X;
			q[h][1] = Y;
			q[h][2] = s;
			dp[X][Y][s] = 0;
			// out.println(X + " " + Y + " " + Integer.toBinaryString(s));

			k = nextInt();
			for (int i = 1; i <= k; i++) {
				rock[i][0] = nextInt();
				rock[i][1] = nextInt();
			}

			int state;
			int ans = -1;
			while (h < t) {
				x = q[h][0];
				y = q[h][1];
				state = q[h][2];
				h++;
				// out.println(x + " " + y + " " +
				// Integer.toBinaryString(state));
				if (x == 1 && y == 1) {
					ans = dp[x][y][state];
					break;
				}
				for (int i = 1; i <= n; i++)
					Arrays.fill(vis[i], false);
				for (int i = 1; i <= k; i++)
					vis[rock[i][0]][rock[i][1]] = true;
				vis[x][y] = true;
				check(x, y, state);
				for (int i = 0; i < 4; i++) {
					a = (short) (x + dir[i][0]);
					b = (short) (y + dir[i][1]);
					// 这里注意一下，是3-i
					// 因为查找的是从当前的头A到下一个头B的位置
					// 而我们要记录的是B是从A的什么方向过来的
					s = (short) ((state << 2 | (3 - i)) % (1 << (2 * l - 2)));
					if (valid(a, b) && !vis[a][b] && dp[a][b][s] == -1) {
						// System.out.println(a + " " + b + " "
						// + Integer.toBinaryString(s));
						vis[a][b] = true;
						dp[a][b][s] = (short) (dp[x][y][state] + 1);
						q[t][0] = a;
						q[t][1] = b;
						q[t][2] = s;
						t++;
					}
				}
			}

			out.println("Case " + (++test) + ": " + ans);
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

	static int nextint() throws IOException {
		in.nextToken();
		return (int) in.nval;
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