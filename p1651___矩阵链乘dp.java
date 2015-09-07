import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class p1651___æÿ’Û¡¥≥Àdp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int[] p = new int[n + 1];
		for (int i = 1; i <= n; i++)
			p[i] = nextInt();
		int[][] m = new int[n + 1][n + 1];

		int temp, u;
		for (int l = 2; l <= n; l++) {
			for (int i = 1; i + l - 1 <= n; i++) {
				int j = i + l - 1;
				temp = Integer.MAX_VALUE;
				u = -1;
				for (int k = i; k < j; k++) {
					u = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
					if (temp > u) {
						temp = u;
					}
				}
				m[i][j] = temp;
			}
		}

		out.println(m[2][n]);

		out.flush();
		out.close();
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}