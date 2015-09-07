import java.io.*;
import java.util.*;

public class p1830___高斯消元___异或___同hdu3364 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n;
	static int start[], end[], matrix[][];

	public static void main(String[] args) throws IOException {
		int k = nextInt();
		while (k-- > 0) {
			n = nextInt();
			start = new int[n];
			for (int i = 0; i < n; i++)
				start[i] = nextInt();
			end = new int[n];
			for (int i = 0; i < n; i++)
				end[i] = nextInt();
			matrix = new int[n][n + 1];
			for (int i = 0; i < n; i++) {
				matrix[i][i] = 1;
				matrix[i][n] = start[i] ^ end[i];
			}

			int a, b;
			while (true) {
				a = nextInt();
				b = nextInt();
				if (a == 0 && b == 0)
					break;
				matrix[b - 1][a - 1] = 1;
			}

			int ans = gauss(matrix, n, n + 1);
			if (ans > -1) {
				out.printf("%.0f\n", Math.pow(2.0, ans));
			} else {
				out.println("Oh,it's impossible~!!");
			}
		}
		out.flush();
		out.close();
	}

	static int gauss(int a[][], int n, int m) {
		int row, col, maxr;
		int temp;
		for (row = 0, col = 0; row < n && col < m - 1; row++, col++) {
			maxr = row;
			for (int i = row + 1; i < n; i++)
				if (a[i][col] > a[maxr][col])
					maxr = i;
			if (a[maxr][col] == 0) {
				row--;
				continue;
			}
			if (maxr != row)
				for (int j = col; j < m; j++) {
					temp = a[row][j];
					a[row][j] = a[maxr][j];
					a[maxr][j] = temp;
				}
			for (int i = row + 1; i < n; i++)
				if (a[i][col] > 0)
					for (int j = col; j < m; j++)
						a[i][j] ^= a[row][j];
		}
		for (int i = row; i < n; i++)
			if (a[i][col] == 1)
				return -1;

		return m - 1 - row;
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