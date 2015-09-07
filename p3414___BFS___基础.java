import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p3414___BFS___»ù´¡ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int A, a, B, b, C, c;
	static boolean vis[][];

	static class state {
		int a, b, step;
		state pre;
		String str;

		state(int x, int y, int s) {
			a = x;
			b = y;
			step = s;
		}
	}

	public static void main(String[] args) throws IOException {
		A = a = nextInt();
		B = b = nextInt();
		C = c = nextInt();

		vis = new boolean[a + 1][b + 1];
		state temp = new state(0, 0, 0), ans = null;
		state q[] = new state[a * b * 10];
		int h = 1, t = 1;
		q[t++] = temp;
		vis[0][0] = true;

		while (h < t) {
			temp = q[h++];
			a = temp.a;
			b = temp.b;
			if (a == C || b == C) {
				ans = temp;
				break;
			}
			if (a < A && !vis[A][b]) {
				q[t++] = new state(A, b, temp.step + 1);
				q[t - 1].pre = temp;
				q[t - 1].str = "FILL(1)";
				vis[A][b] = true;
			}
			if (a > 0 && !vis[0][b]) {
				q[t++] = new state(0, b, temp.step + 1);
				q[t - 1].pre = temp;
				q[t - 1].str = "DROP(1)";
				vis[0][b] = true;
			}
			if (b < B && !vis[a][B]) {
				q[t++] = new state(a, B, temp.step + 1);
				q[t - 1].pre = temp;
				q[t - 1].str = "FILL(2)";
				vis[a][B] = true;
			}
			if (b > 0 && !vis[a][0]) {
				q[t++] = new state(a, 0, temp.step + 1);
				q[t - 1].pre = temp;
				q[t - 1].str = "DROP(2)";
				vis[a][0] = true;
			}
			if (a + b >= A) {
				if (!vis[A][a + b - A]) {
					q[t++] = new state(A, a + b - A, temp.step + 1);
					q[t - 1].pre = temp;
					q[t - 1].str = "POUR(2,1)";
					vis[A][a + b - A] = true;
				}
			} else {
				if (!vis[a + b][0]) {
					q[t++] = new state(a + b, 0, temp.step + 1);
					q[t - 1].pre = temp;
					q[t - 1].str = "POUR(2,1)";
					vis[a + b][0] = true;
				}
			}
			if (a + b >= B) {
				if (!vis[a + b - B][B]) {
					q[t++] = new state(a + b - B, B, temp.step + 1);
					q[t - 1].pre = temp;
					q[t - 1].str = "POUR(1,2)";
					vis[a + b - B][B] = true;
				}
			} else {
				if (!vis[0][a + b]) {
					q[t++] = new state(0, a + b, temp.step + 1);
					q[t - 1].pre = temp;
					q[t - 1].str = "POUR(1,2)";
					vis[0][a + b] = true;
				}
			}
		}

		if (ans == null)
			out.println("impossible");
		else {
			out.println(ans.step);
			String stack[] = new String[ans.step + 10];
			int top = 0;
			while (ans != null) {
				stack[++top] = ans.str;
				ans = ans.pre;
			}
			top--;
			while (top > 0) {
				out.println(stack[top]);
				top--;
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