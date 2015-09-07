import java.io.*;
import java.util.*;

public class p1077___°ËÊýÂë¾­µäÎÊÌâ___BFS___¿µÍØÑ¹Ëõ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int limit = 362880;
	static int queue[] = new int[limit], head, tail;
	static int pre[][] = new int[2][limit];
	static int hash[] = new int[limit];
	static int goal = 123456789;
	static int dir[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static char invert[] = { 'r', 'l', 'd', 'u' };
	static int stack[];

	static int fac[] = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };
	static int count;

	static int encodeHash(int state) {
		int neoHash = 0;
		int record[] = new int[9];
		for (int i = 8; i >= 0; i--) {
			record[i] = state % 10;
			state /= 10;
		}

		for (int i = 8; i >= 0; i--) {
			count = 0;
			for (int j = i + 1; j <= 8; j++)
				if (record[j] < record[i])
					count++;
			neoHash += count * fac[8 - i];
		}
		return neoHash;
	}

	static int encodeHash(int[] record) {
		int neoHash = 0;
		for (int i = 8; i >= 0; i--) {
			count = 0;
			for (int j = i + 1; j <= 8; j++)
				if (record[j] < record[i])
					count++;
			neoHash += count * fac[8 - i];
		}
		return neoHash;
	}

	static int encodeState(int[] record) {
		int neoState = 0;
		for (int i = 0; i <= 8; i++)
			neoState = neoState * 10 + record[i];
		return neoState;
	}

	static boolean isValid(int x, int y) {
		if (x >= 0 && x < 3 && y >= 0 && y < 3)
			return true;
		return false;
	}

	static String str;
	static StringTokenizer stoke;

	public static void main(String[] args) throws IOException {

		char ch;
		int x = 0, y = 0, id = 0, tx, ty, tid, temp, oldState, s, oldHash, neoState, neoHash, record[] = new int[9];
		while ((str = br.readLine()) != null) {
			stoke = new StringTokenizer(str);
			oldState = 0;
			for (int i = 0; i < 9; i++) {
				ch = stoke.nextToken().charAt(0);
				if (ch == 'x')
					oldState = oldState * 10 + 9;
				else
					oldState = oldState * 10 + ch - '0';
			}
			neoHash = encodeHash(oldState);
			Arrays.fill(hash, -1);
			Arrays.fill(pre[0], -1);
			Arrays.fill(pre[1], -1);
			head = tail = 0;
			queue[tail++] = oldState;
			hash[neoHash] = 0;

			loop: while (head < tail) {
				s = oldState = queue[head++];
				oldHash = encodeHash(oldState);

				for (int i = 8; i >= 0; i--) {
					record[i] = s % 10;
					s /= 10;
					if (record[i] == 9) {
						x = i / 3;
						y = i % 3;
						id = i;
					}
				}
				for (int i = 0; i < 4; i++) {
					tx = x + dir[i][0];
					ty = y + dir[i][1];
					if (isValid(tx, ty)) {
						tid = tx * 3 + ty;
						temp = record[id];
						record[id] = record[tid];
						record[tid] = temp;

						neoState = encodeState(record);
						neoHash = encodeHash(neoState);

						if (hash[neoHash] == -1) {
							hash[neoHash] = hash[oldHash] + 1;
							queue[tail++] = neoState;
							pre[0][neoHash] = oldHash;
							pre[1][neoHash] = i;
							if (neoState == goal)
								break loop;
						}

						temp = record[id];
						record[id] = record[tid];
						record[tid] = temp;
					}
				}

			}

			if (pre[0][0] == -1) {
				out.println("unsolvable");
			} else {
				stack = new int[hash[0] + 10];
				int top = 0;
				s = 0;
				while (pre[0][s] != -1) {
					stack[top++] = pre[1][s];
					s = pre[0][s];
				}
				while (top-- > 0)
					out.print(invert[stack[top]]);
				out.println();
			}
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

	static int nextint() throws IOException {
		in.nextToken();
		return (int) in.nval;
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