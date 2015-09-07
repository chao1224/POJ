import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p2148___��״����___���kС��Ԫ��___���� {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, c[];

	public static void main(String[] args) throws IOException {
		n = nextInt();
		c = new int[n + 1];
		for (int i = 1; i <= n; i++)
			insert(i, 1);

		int record[] = new int[n], ans[] = new int[n + 1];
		for (int i = 1; i < n; i++)
			record[i] = nextInt();
		record[0] = 0;
		// �˴�record[0]Ϊ�㣬Ȼ�������0
		int temp, begin, end, mid;
		for (int i = n - 1; i >= 0; i--) {
			temp = record[i] + 1;
			begin = 1;
			end = n;
			// Ѱ�ҵ�һ��sum(k)Ϊtemp��������Ϊ��kС����
			while (begin <= end) {
				mid = (begin + end) >> 1;
				if (sum(mid) >= temp)
					end = mid - 1;
				else
					begin = mid + 1;
			}
			ans[i + 1] = begin;
			insert(begin, -1);
		}

		for (int i = 1; i <= n; i++)
			out.println(ans[i]);

		out.flush();
		out.close();
	}

	static int lowbit(int a) {
		return a & -a;
	}

	static void insert(int x, int v) {
		while (x <= n) {
			c[x] += v;
			x += lowbit(x);
		}
	}

	static int sum(int x) {
		int s = 0;
		while (x > 0) {
			s += c[x];
			x -= lowbit(x);
		}
		return s;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}