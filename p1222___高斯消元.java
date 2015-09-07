import java.io.*;
import java.util.*;

public class p1222___高斯消元 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int matrix[][] = new int[30][31];

	public static void main(String[] args) throws IOException {
		int ttt = nextInt(), a, b, c, d, e;
		for (int test = 1; test <= ttt; test++) {
			for (int i = 0; i < 30; i++)
				Arrays.fill(matrix[i], 0);

			for (int i = 0; i < 30; i++)
				matrix[i][30] = nextInt();

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 6; j++) {
					a = 6 * i + j;
					b = a - 1;
					c = a + 1;
					d = a - 6;
					e = a + 6;
					matrix[a][a] = 1;
					// 这里有坑
					// 一定要注意
					// 假设a是24 b=23 实际上不可以
					if (j > 0 && b >= 0 && b < 30)
						matrix[b][a] = 1;
					if (j < 5 && c >= 0 && c < 30)
						matrix[c][a] = 1;
					if (i > 0 && d >= 0 && d < 30)
						matrix[d][a] = 1;
					if (i < 4 && e >= 0 && e < 30)
						matrix[e][a] = 1;
				}
			}

			gauss(matrix, 30, 30);

			out.println("PUZZLE #" + test);
			for (int i = 0; i < 30; i++) {
				a = matrix[i][30];
				if ((i + 1) % 6 == 0)
					out.println(a);
				else
					out.print(a + " ");
			}

			// gauss2(matrix, 30, 30);
			// out.println("PUZZLE #" + test);
			// for (int i = 0; i < 5; i++) {
			// for (int j = 0; j < 6; j++) {
			// int temp = i * 6 + j;
			// out.print(key[temp] + " ");
			// }
			// out.println();
			// }
		}
		out.flush();
		out.close();
	}

	static int key[] = new int[30];

	static int gauss2(int[][] A, int m, int n) {
		int i = 0, j = 0, k, r, t;
		while (i < m && j < n) {
			r = i;
			for (k = i; k < m; k++)
				if (A[k][j] > 0) {
					r = k;
					break;
				}
			if (A[r][j] > 0) {
				if (r != i)
					for (k = 0; k <= n; k++) {
						// swap(A[r][k], A[i][k]);
						int temp = A[r][k];
						A[r][k] = A[i][k];
						A[i][k] = temp;
					}
				for (t = i + 1; t < m; t++)
					if (A[t][j] > 0)
						for (k = i; k <= n; k++)
							A[t][k] ^= A[i][k];
				i++;
			}
			j++;
		}
		for (k = i; k < m; k++)
			if (A[k][n] > 0)
				return -1;

		for (j = i - 1; j >= 0; j--) {
			int tmp = 0;
			for (k = j + 1; k < n; k++)
				if (A[j][k] > 0)
					tmp ^= key[k];
			tmp ^= A[j][n];
			key[j] = tmp;
		}

		return i;
	}

	static void gauss(int a[][], int r, int c) {
		int row, col, maxr;
		for (row = 0, col = 0; row < r && col < c; row++, col++) {
			maxr = row;
			for (int i = row + 1; i < r; i++)
				if (Math.abs(a[i][col]) > Math.abs(a[maxr][col]))
					maxr = i;
			if (a[maxr][col] == 0) {
				row--;
				continue;
			}
			if (maxr != row)
				for (int j = col; j < c + 1; j++) {
					int temp = a[row][j];
					a[row][j] = a[maxr][j];
					a[maxr][j] = temp;
				}

			for (int i = row + 1; i < r; i++)
				if (a[i][col] != 0)
					for (int j = col; j < c + 1; j++)
						a[i][j] ^= a[row][j];
		}

		for (int i = r - 1; i >= 0; i--) {
			int temp = a[i][r];
			for (int j = i + 1; j < r; j++) {
				if (a[i][j] > 0)
					temp ^= a[j][r];
			}
			a[i][r] = temp;
		}
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