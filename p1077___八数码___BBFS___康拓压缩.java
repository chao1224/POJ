import java.io.*;
import java.util.*;

public class p1077___°ËÊýÂë___BBFS___¿µÍØÑ¹Ëõ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int limit = 362880;
	static int front[] = new int[limit], rear[] = new int[limit], head1, tail1,
			head2, tail2;
	static int pre[][] = new int[2][limit];
	static int pre2[][] = new int[2][limit];
	static int hash1[] = new int[limit], hash2[] = new int[limit];
	static int goal = 123456789;
	static int dir[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static char invert1[] = { 'r', 'l', 'd', 'u' };
	static char invert2[] = { 'l', 'r', 'u', 'd' };
	static int stack[];
	static int step, ans;

	static int fac[] = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };
	static int count;

	static void init() {
		Arrays.fill(hash1, -1);
		Arrays.fill(hash2, -1);
		Arrays.fill(pre[0], -1);
		Arrays.fill(pre[1], -1);
		Arrays.fill(pre2[0], -1);
		Arrays.fill(pre2[1], -1);
		head1 = tail1 = head2 = tail2 = 0;
		step = 0;
		ans = -1;
	}

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
			init();
			oldState = 0;
			for (int i = 0; i < 9; i++) {
				ch = stoke.nextToken().charAt(0);
				if (ch == 'x')
					oldState = oldState * 10 + 9;
				else
					oldState = oldState * 10 + ch - '0';
			}
			neoHash = encodeHash(oldState);
			front[tail1++] = oldState;
			hash1[neoHash] = 0;
			rear[tail2++] = goal;
			hash2[0] = 0;

			loop: while (head1 < tail1 || head2 < tail2) {
				while (head1 < tail1) {
					s = oldState = front[head1];
					oldHash = encodeHash(oldState);
					if (hash1[oldHash] > step)
						break;
					head1++;

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
						if (!isValid(tx, ty))
							continue;
						tid = tx * 3 + ty;
						temp = record[id];
						record[id] = record[tid];
						record[tid] = temp;

						neoState = encodeState(record);
						neoHash = encodeHash(neoState);
						if (hash1[neoHash] == -1) {
							hash1[neoHash] = step + 1;
							front[tail1++] = neoState;
							pre[0][neoHash] = oldHash;
							pre[1][neoHash] = i;
						}
						if (hash2[neoHash] > -1) {
							ans = neoState;
							break loop;
						}
						temp = record[id];
						record[id] = record[tid];
						record[tid] = temp;

					}
				}

				while (head2 < tail2) {
					s = oldState = rear[head2];
					oldHash = encodeHash(oldState);
					if (hash2[oldHash] > step)
						break;
					head2++;

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
						if (!isValid(tx, ty))
							continue;
						tid = tx * 3 + ty;
						temp = record[id];
						record[id] = record[tid];
						record[tid] = temp;

						neoState = encodeState(record);
						neoHash = encodeHash(neoState);
						if (hash2[neoHash] == -1) {
							rear[tail2++] = neoState;
							hash2[neoHash] = step + 1;
							pre2[0][neoHash] = oldHash;
							pre2[1][neoHash] = i;
						}
						if (hash1[neoHash] > -1) {
							ans = neoState;
							break loop;
						}

						temp = record[id];
						record[id] = record[tid];
						record[tid] = temp;
					}

				}

				step++;
			}

			if (ans == -1) {
				out.println("unsolvable");
			} else {
				stack = new int[hash1[neoHash] + hash2[neoHash] + 10];
				int top = 0;
				neoHash = encodeHash(ans);
				s = neoHash;
				while (pre[0][s] != -1) {
					stack[top++] = pre[1][s];
					s = pre[0][s];
				}
				while (top-- > 0)
					out.print(invert1[stack[top]]);
				s = neoHash;
				top = 0;
				while (pre2[0][s] != -1) {
					stack[top++] = pre2[1][s];
					s = pre2[0][s];
				}
				for (int i = 0; i < top; i++)
					out.print(invert2[stack[i]]);
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