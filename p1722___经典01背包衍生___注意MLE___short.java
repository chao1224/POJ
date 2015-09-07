import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Map.Entry;

public class p1722___经典01背包衍生___注意MLE___short {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		short n = nextShort();
		short t = nextShort();
		short[] arr = new short[n + 1];
		for (short i = 1; i <= n; i++)
			arr[i] = nextShort();

		short mid = (short) (100 * n);
		short dp[][] = new short[n + 1][n * 200 + 1];
		dp[1][mid + arr[1]] = 0;
		dp[2][mid + arr[1] - arr[2]] = (short) (mid + arr[1]);
		for (short i = 3; i <= n; i++) {
			for (short j = 0; j <= 200 * n; j++) {
				if (dp[i - 1][j] != 0) {
					dp[i][j + arr[i]] = j;
					dp[i][j - arr[i]] = j;
				}
			}
		}

		short ans[] = new short[n];
		short cur = (short) (t + mid), pre;
		for (short i = 1; i < n; i++) {
			pre = dp[n + 1 - i][cur];
			if (cur + arr[n + 1 - i] == pre)
				ans[n - i] = -1;
			else
				ans[n - i] = 1;
			cur = pre;
		}

		short cnt = 0;
		for (short i = 1; i < n; i++) {
			if (ans[i] == 1) {
				out.println(i - cnt);
				cnt++;
			}
		}
		for (short i = 1; i < n; i++)
			if (ans[i] == -1)
				out.println(1);

		out.flush();
		out.close();
	}

	static short nextShort() throws IOException {
		in.nextToken();
		return (short) in.nval;
	}

}
