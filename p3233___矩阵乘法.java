import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class p3233___矩阵乘法 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int mod, n, e[][];

	public static void main(String[] args) throws IOException {
		n = nextInt();
		int k = nextInt();
		mod = nextInt();
		int[][] a = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				a[i][j] = nextInt();
		e = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			e[i][i] = 1;

		a = make(a, k);

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < n; j++)
				out.print(a[i][j] + " ");
			out.println(a[i][n]);
		}
		out.flush();
		out.close();
	}

	static int[][] make(int[][] a, int k) {
		if (k == 1)
			return a;
		if ((k & 1) == 1) {
			return add(fastPow(a, k), make(a, k - 1));
		}
		k >>= 1;
		return (multiply(add(fastPow(a, k), e), make(a, k)));
	}

	static int[][] multiply(int a[][], int b[][]) {
		int c[][] = new int[n + 1][n + 1];
		int temp;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++) {
				temp = 0;
				for (int k = 1; k <= n; k++) {// 此处注意不要忘记mod
					temp += (a[i][k] * b[k][j]) % mod;
					temp %= mod;
				}
				c[i][j] = temp % mod;
			}
		return c;
	}

	static int[][] fastPow(int[][] a, int p) {
		int c[][] = new int[n + 1][n + 1];
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

	static int[][] add(int[][] a, int[][] b) {
		int[][] c = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				c[i][j] = (a[i][j] + b[i][j]) % mod;
		return c;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
