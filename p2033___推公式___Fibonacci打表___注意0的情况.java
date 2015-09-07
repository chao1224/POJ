import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p2033___推公式___Fibonacci打表___注意0的情况 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n;
	static String str;
	static int arr[];
	static boolean vis[];
	static long fibonacci[] = { 1l, 1l, 2l, 3l, 5l, 8l, 13l, 21l, 34l, 55l,
			89l, 144l, 233l, 377l, 610l, 987l, 1597l, 2584l, 4181l, 6765l,
			10946l, 17711l, 28657l, 46368l, 75025l, 121393l, 196418l, 317811l,
			514229l, 832040l, 1346269l, 2178309l, 3524578l, 5702887l, 9227465l,
			14930352l, 24157817l, 39088169l, 63245986l, 102334155l, 165580141l,
			267914296l, 433494437l, 701408733l, 1134903170l, 1836311903l,
			2971215073l, 4807526976l, 7778742049l, 12586269025l, 20365011074l,
			32951280099l, 53316291173l, 86267571272l, 139583862445l,
			225851433717l, 365435296162l, 591286729879l, 956722026041l,
			1548008755920l, 2504730781961l, 4052739537881l, 6557470319842l,
			10610209857723l, 17167680177565l, 27777890035288l, 44945570212853l,
			72723460248141l, 117669030460994l, 190392490709135l,
			308061521170129l, 498454011879264l, 806515533049393l,
			1304969544928657l, 2111485077978050l, 3416454622906707l,
			5527939700884757l, 8944394323791464l, 14472334024676221l,
			23416728348467685l, 37889062373143906l, 61305790721611591l,
			99194853094755497l, 160500643816367088l, 259695496911122585l,
			420196140727489673l, 679891637638612258l, 1100087778366101931l,
			1779979416004714189l, 2880067194370816120l, 4660046610375530309l,
			7540113804746346429l };

	// static int ans;
	// static void dfs(int len, int n, boolean[] vis) {
	// if (len > n) {
	// if (vis[len - 1])
	// ans++;
	// return;
	// } else {
	// vis[len] = true;
	// dfs(len + 1, n, vis);
	// if (vis[len - 1]) {
	// vis[len] = false;
	// dfs(len + 1, n, vis);
	// }
	// vis[len] = false;
	// }
	// }

	public static void main(String[] args) throws IOException {

		// for (int i = 1; i <= 50; i++) {
		// boolean vis[] = new boolean[i + 1];
		// vis[0] = true;
		// ans = 0;
		// dfs(1, i, vis);
		// System.out.println(i + " " + ans);
		// }

		// fibonacci[1] = 1l;
		// fibonacci[2] = 2l;
		// for (int i = 3; i <= 91; i++) {
		// fibonacci[i] = (long) fibonacci[i - 2] + (long) fibonacci[i - 1];
		// if (fibonacci[i] < 0)
		// out.println(i);
		// System.out.print(fibonacci[i] + "l, ");
		// }
		// System.out.println();

		loop: while (!(str = br.readLine()).equals("0")) {
			n = str.length();
			arr = new int[n + 1];
			vis = new boolean[n + 2];
			for (int i = 1; i <= n; i++)
				arr[i] = str.charAt(i - 1) - '0';

			long ans = 1l;
			int j;

			for (int i = 1; i <= n; i++)
				if (arr[i] == 0)
					vis[i] = vis[i - 1] = true;

			for (int i = 1; i <= n; i++) {
				if (vis[i])
					continue;
				vis[i] = true;
				j = i + 1;
				for (; j <= n && !vis[j]; j++) {
					int t = arr[j - 1] * 10 + arr[j];
					if (t > 26)
						break;
					vis[j] = true;
				}
				ans = (long) ans * fibonacci[j - i];
			}

			out.println(ans);
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

	static short nextShort() throws IOException {
		in.nextToken();
		return (short) in.nval;
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