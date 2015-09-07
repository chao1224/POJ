import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1465___BFS___余数剪枝 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, number[];
	static long visit[];
	static String ans;
	static String str;

	// BFS+除余剪枝，当所有余数都取到仍未找出时，break
	public static void main(String[] args) throws IOException {
		while (true) {
			str = br.readLine();
			if (str == null)
				break;
			if (str.equals(""))
				continue;
			n = Integer.parseInt(str);
			m = Integer.parseInt(br.readLine());
			number = new int[m];
			visit = new long[n + 1];
			for (int i = 0; i < m; i++)
				number[i] = Integer.parseInt(br.readLine());

			Arrays.sort(number);
			if (n == 0)
				out.println(0);
			else {
				ans = "0";
				bfs();
				out.println(ans);
			}
		}
		out.flush();
		out.close();
	}

	static void bfs() {
		LinkedList<String> list = new LinkedList<String>();

		int left;
		for (int i = 0; i < m; i++) {
			if (number[i] == 0)
				continue;
			left = number[i] % n;
			if (left == 0) {
				ans = number[i] + "";
				return;
			} else if (visit[left] == 0) {
				visit[left] = 1;
				list.add(number[i] + "");
			}
		}

		String pre, cur;
		while (!list.isEmpty()) {
			pre = list.poll();
			for (int j = 0; j < m; ++j) {
				cur = pre + number[j];
				left = MOD(cur, n);
				if (visit[left] == 0) {
					visit[left] = 1;
					if (left == 0) {
						ans = cur;
						break;
					}
					list.add(cur);
				}
			}
		}
	}

	static int MOD(String str, int n) {
		int k = 0;
		for (int i = 0; i < str.length(); ++i) {
			k = (k * 10 + str.charAt(i) - '0') % n;
		}
		return k;

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
