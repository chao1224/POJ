import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class p2411___״�R___��ש {
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
						if (f[i][j] != 0) // ��֦��Ϊ0û�б�Ҫ���ǣ�
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
			f[i + 1][to] += f[i][from];// ����I+1�б�ռȥ�ĸ��ӵ�2����ΪJJӦ�ÿ��Զ��f[i][j]�ķ���
		} else if ((to & (1 << col)) == 0)// ��ʾ�� col ���ܷ�1/0
		{
			dfs(i, from, to | (1 << col), col + 1);// ��1
			if (col < m - 1 && (to & (1 << (col + 1))) == 0)
				dfs(i, from, to, col + 2);// ��0����ռ2��
		} else
			dfs(i, from, to & ~(1 << col), col + 1);// ����ط�1����1,0����0��ֻ��1<<col����ʾ��1��Ϊ0
		// ��ʾ�˴�ֻ�ܷ�0����Ϊ���ŷ�
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
