import java.io.*;
import java.security.KeyStore.Entry;
import java.util.*;

public class p3460___IDA_star {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, pathLimit;
	static int N = 18;
	static int record[] = new int[N];
	static int temp[] = new int[N];
	static int[] goal = new int[N];
	static int ans;

	static long ConvertToStatus(int[] arr) {
		long state = 0l;
		for (int i = 0; i < n; i++)
			state = state * 100l + arr[i];
		return state;
	}

	static void ConvertToRecord(long state, int[] arr) {
		for (int i = n - 1; i >= 0; i--) {
			arr[i] = (int) (state % 100);
			state /= 100;
		}
	}

	static int h() {
		int sum = 0;
		for (int i = 0; i < n - 1; i++)
			if (record[i] + 1 != record[i + 1])
				sum++;
		if (record[n - 1] != n)
			sum++;
		return (sum + 2) / 3;
	}

	static void move(int[] arr, int f, int m, int e) {
		int i, j = f;
		for (i = m + 1; i <= e; i++, j++)
			temp[j] = arr[i];
		for (i = f; i <= m; i++, j++)
			temp[j] = arr[i];
		for (i = f; i <= e; i++)
			arr[i] = temp[i];
	}

	static boolean dfs(int g) {
		int h = h();

		if (h == 0)
			return true;

		if (pathLimit < g + h())
			return false;

		for (int f = 0; f < n; f++)
			for (int m = f; m < n; m++)
				for (int e = m + 1; e < n; e++) {
					move(record, f, m, e);
					if (dfs(g + 1))
						return true;
					move(record, f, f + e - m - 1, e);
				}

		return false;

	}

	public static void main(String[] args) throws IOException {
		int test = nextInt();
		while (test-- > 0) {
			n = nextInt();
			for (int i = 0; i < n; i++)
				record[i] = nextInt();
			for (int i = 0; i < n; i++)
				goal[i] = i + 1;

			boolean flag = false;
			for (pathLimit = 1; pathLimit <= 4; pathLimit++) {
				flag = dfs(0);
				if (flag)
					break;
			}

			if (flag)
				out.println(pathLimit);
			else
				out.println("5 or more");

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