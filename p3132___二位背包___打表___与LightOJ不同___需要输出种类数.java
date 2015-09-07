import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p3132___二位背包___打表___与LightOJ不同___需要输出种类数 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, k;
	static int N = 1200;
	static int num = 196;
	static int prime[] = { 1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
			43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109,
			113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181,
			191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257,
			263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337,
			347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419,
			421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491,
			499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587,
			593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659,
			661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751,
			757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839,
			853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937,
			941, 947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013, 1019,
			1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091,
			1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 1153, 1163, 1171,
			1181, 1187, 1193 };

	// static int prime[] = new int[N];

	public static void main(String[] args) throws IOException {
		// for (int i = 2; i < N; i++) {
		// if (prime[i] == 0)
		// for (int j = 2; j * i < N; j++)
		// prime[i * j] = 1;
		// }
		// int count = 0;
		// for (int i = 2; i < N; i++)
		// if (prime[i] == 0) {
		// out.print(i + ", ");
		// count++;
		// }
		// out.println();
		// out.println(count);

		while (true) {
			n = nextInt();
			k = nextInt();
			if (n == 0 && k == 0)
				break;
			int dp[][] = new int[k + 1][n + 1];
			dp[0][0] = 1;
			for (int index = 1; index <= num; index++) {
				for (int i = k; i >= 1; i--)
					for (int j = n; j >= prime[index]; j--)
						if (dp[i - 1][j - prime[index]] > -1)
							dp[i][j] += dp[i - 1][j - prime[index]];
			}
			out.println(dp[k][n]);
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