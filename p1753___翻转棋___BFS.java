import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p1753___·­×ªÆå___BFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static String str;
	static int change[] = new int[16];
	static boolean vis[] = new boolean[1 << 17];

	static class state {
		int s, step;

		state(int s, int step) {
			this.s = s;
			this.step = step;
		}
	}

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				int temp = 0, num = i * 4 + j;
				if (i > 0)
					temp |= 1 << (num - 4);
				if (i < 3)
					temp |= 1 << (num + 4);
				if (j > 0)
					temp |= 1 << (num - 1);
				if (j < 3)
					temp |= 1 << (num + 1);
				change[num] = temp | (1 << num);
			}

		int origin = 0;
		for (int i = 0; i < 4; i++) {
			str = br.readLine();
			for (int j = 0; j < 4; j++)
				if (str.charAt(j) == 'b')
					origin = origin | 1 << (i * 4 + j);
		}

		state q[] = new state[1 << 17];
		state temp = new state(origin, 0);
		int h = 0, t = 0;
		q[t++] = temp;
		vis[origin] = true;

		int tmp, limit = (1 << 16) - 1;
		boolean flag = false;
		while (h < t) {
			temp = q[h++];
			// out.println(Integer.toBinaryString(temp.s));
			if (temp.s == 0 || temp.s == limit) {
				out.println(temp.step);
				flag = true;
				break;
			}
			for (int i = 0; i < 16; i++) {
				tmp = temp.s ^ change[i];
				if (vis[tmp])
					continue;
				q[t++] = new state(tmp, temp.step + 1);
				vis[tmp] = true;
			}
		}

		if (!flag)
			out.println("Impossible");

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