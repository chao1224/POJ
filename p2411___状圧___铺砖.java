import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class p2411___状圧___铺砖 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m;
	static long f[][];

	public static void main(String[] args) throws IOException {
		while (true) {
			out.println("Case:");
			n = nextInt();
			m = nextInt();
			if (n == 0 && m == 0)
				break;
			if ((n & 1) == 1 && (m & 1) == 1)
				out.println(0);
			else {
				f = new long[n + 1][1 << m];
				for (int i = 0; i < f.length; i++)
					Arrays.fill(f[i], 0);

				f[0][0] = 1;
				for (int i = 0; i < n; i++)
					for (int j = 0; j < (1 << m); j++)
						if (f[i][j] != 0) // 剪枝（为0没有必要考虑）
						{
							dfs(i, j, j, 0);
						}
				out.println(f[n][0]);
			}
		}
		out.flush();
		out.close();
	}

	static void dfs(int i, int from, int to, int col) {
		if (col == m) {
			out.println(Integer.toBinaryString(from) + "   "
					+ Integer.toBinaryString(to));
			f[i + 1][to] += f[i][from];// 等于I+1行被占去的格子的2进制为JJ应该可以多放f[i][j]的方略
		} else if ((to & (1 << col)) == 0)// 表示第 col 列能放1/0
		{
			dfs(i, from, to | (1 << col), col + 1);// 放1
			if (col < m - 1 && (to & (1 << (col + 1))) == 0)
				dfs(i, from, to, col + 2);// 放0（横占2格）
		} else
			dfs(i, from, to & ~(1 << col), col + 1);// 其余地方1还是1,0还是0，只有1<<col处表示有1变为0
		// 表示此处只能放0，即为竖着放
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
