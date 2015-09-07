import java.io.*;
import java.util.*;

public class p3017___��������___���ȶ���___�Ż�dp {
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
				// ������һ����Ϊ�˽����ж϶����Ƿ�Ϊ��
				if (head < tail) {
					// ������зǿ�
					// ��ô���Ǿ���Ҫ��b[i]�͵������е���ѡ��һ��k��Ϊ��Ѿ���
					queue.add(dp[q[tail - 1]] + arr[i]);
					temp = dp[b[i]] + arr[q[head]];
					dp[i] = Math.min(queue.peek(), temp);
				} else {
					// �������Ϊ��
					// ��ô��Ѿ��߾���b[i]
					// ����ʱ��ʵ���ȶ����ڼ�����a[i]֮���ֻ��a[i]��ôһ������
					// ���Դ�ʱdp[i]=dp[b[i]]+arr[i]
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
