import java.io.*;
import java.util.*;

public class p3629___约瑟夫环___学习队列使用 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, x, c[];

	public static void main(String[] args) throws IOException {

		int n = nextInt();
		int k = nextInt();
		int p = nextInt();

		Queue<Integer> q = new LinkedList<Integer>();
		// int b=q.peek();
		// 向队列添加元素
		for (int i = 0; i < k; i++) {
			q.offer(i + 1);
		}
		int[] arr = new int[k / n];
		int s = 0;
		for (int w = 1; w <= k / n; w++, s++) {
			for (int i = 1; i < n; i++) {
				q.poll();
				for (int j = 1; j <= p; j++)
					q.offer(q.poll());// offer(E e)将指定的元素插入此队列
			}
			arr[s] = q.poll();
			for (int j = 1; j <= p; j++)
				q.offer(q.poll());

		}
		Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);

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