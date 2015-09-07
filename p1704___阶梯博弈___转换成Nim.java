import java.io.*;
import java.util.*;

public class p1704___½×ÌÝ²©ÞÄ___×ª»»³ÉNim {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, arr[];

	// http://blog.csdn.net/shahdza/article/details/7858032
	public static void main(String[] args) throws IOException {
		int test = nextInt();
		while (test-- > 0) {
			n = nextInt();
			arr = new int[n + 1];
			for (int i = 1; i <= n; i++)
				arr[i] = nextInt();
			int ans = 0;
			Arrays.sort(arr);
			for (int i = n; i >= 1; i -= 2)
				ans ^= arr[i] - arr[i - 1] - 1;
			if (ans > 0)
				out.println("Georgia will win");
			else
				out.println("Bob will win");

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