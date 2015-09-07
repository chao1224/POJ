import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class p3150___矩阵快速幂优化 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, mod;

	public static void main(String[] args) throws IOException {
		n = nextInt();
		mod = nextInt();
		int d = nextInt();
		int time = nextInt();
		long[] arr = new long[n + 1];
		for (int i = 1; i <= n; i++)
			arr[i] = nextInt();
		long[][] a = new long[n + 1][n + 1];

		int j;
		for (int i = 1; i <= n; i++) {
			if (i - d <= 0) {
				for (j = i - d + n; j <= n; j++)
					a[j][i] = 1;
				for (j = 0; j <= i + d; j++)
					a[j][i] = 1;
			} else if (i + d > n) {
				for (j = i - d; j <= n; j++)
					a[j][i] = 1;
				for (j = 1; j <= i + d - n; j++)
					a[j][i] = 1;
			} else
				for (j = i - d; j <= i + d; j++)
					a[j][i] = 1;
		}

		// for (int i = 1; i <= n; i++) {
		// for (j = 1; j <= n; j++)
		// out.print(a[i][j] + "  ");
		// out.println();
		// }
		// out.flush();

		arr = multiplyFinal(arr, fastPow(a, time));

		for (int i = 1; i < n; i++)
			out.print(arr[i] + " ");
		out.println(arr[n]);

		out.flush();
		out.close();
	}

	static long[][] multiply(long a[][], long b[][]) {
		long c[][] = new long[a.length][b[0].length];
		int temp;

		for (int j = 1; j < b[0].length; j++) {
			temp = 0;
			for (int k = 1; k < a[0].length; k++) {
				temp += (a[1][k] % mod) * (b[k][j] % mod) % mod;
				temp %= mod;
			}
			c[1][j] = temp % mod;
		}

		for (int i = 2; i <= n; i++) {
			c[i][1] = c[i - 1][n];
			for (int j = 2; j <= n; j++) {
				c[i][j] = c[i - 1][j - 1];
			}
		}

		return c;

	}

	static long[] multiplyFinal(long a[], long b[][]) {
		long c[] = new long[a.length];
		int temp;
		for (int j = 1; j < b[0].length; j++) {
			temp = 0;
			for (int k = 1; k < a.length; k++) {
				temp += (a[k] % mod) * (b[k][j] % mod) % mod;
				temp %= mod;
			}
			c[j] = temp % mod;
		}
		return c;
	}

	static long[][] fastPow(long[][] a, int p) {
		long c[][] = new long[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			c[i][i] = 1;

		while (p > 0) {
			if ((p & 1) == 1) {
				c = multiply(c, a);
			}
			p >>= 1;
			a = multiply(a, a);
		}
		return c;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}