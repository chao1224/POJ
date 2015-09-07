import java.io.*;
import java.util.*;

public class p3017___单调队列___优先队列___优化dp {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(bf);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static PriorityQueue<Long> queue = new PriorityQueue<Long>();
	static long arr[], dp[], temp;  
	static int b[], q[];

	public static void main(String[] args) throws IOException {
		boolean ok;
		loop: while (in.nextToken() != in.TT_EOF) {
			int n = (int) in.nval;
			long k = nextLong();
			ok = false;
			arr = new long[n + 1];
			for (int i = 1; i <= n; i++) {
				arr[i] = nextLong();
				if (arr[i] > k)
					ok = true;
			}
			if (ok) {
				out.println(-1);
				continue loop;
			}

			int index = 0;
			b = new int[n + 1];
			long sum = arr[1];

			queue.clear();
			dp = new long[n + 1];
			q = new int[n + 100];
			int head = 1, tail = 1;
			q[tail++] = 1;
			dp[1] = arr[1];
			for (int i = 2; i <= n; i++) {

				sum += arr[i];
				while (sum > k) {
					index++;
					sum -= arr[index];
				}
				b[i] = index;

				while (head < tail && q[head] < b[i]) {
					if (head + 1 < tail)
						queue.remove(dp[q[head]] + arr[q[head + 1]]);
					head++;
				}
				while (head < tail && arr[q[tail - 1]] <= arr[i]) {
					if (head + 1 < tail)
						queue.remove(dp[q[tail - 2]] + arr[q[tail - 1]]);
					tail--;
				}
				// 进行这一步是为了进行判断队列是否为空
				if (head < tail) {
					// 如果队列非空
					// 那么我们就需要在b[i]和单调队列当中选择一个k作为最佳决策
					queue.add(dp[q[tail - 1]] + arr[i]);
					temp = dp[b[i]] + arr[q[head]];
					dp[i] = Math.min(queue.peek(), temp);
				} else {
					// 如果队列为空
					// 那么最佳决策就是b[i]
					// 而此时其实优先队列在加入了a[i]之后就只有a[i]这么一个数字
					// 所以此时dp[i]=dp[b[i]]+arr[i]
					temp = dp[b[i]] + arr[i];
					dp[i] = temp;
				}
				q[tail++] = i;
			}

			out.println(dp[n]);
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

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}
