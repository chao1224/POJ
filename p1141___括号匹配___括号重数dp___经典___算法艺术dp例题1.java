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

public class p1141___括号匹配___括号重数dp___经典___算法艺术dp例题1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		String str, ans[][];
		char ch;
		int len, dp[][];
		while ((str = br.readLine()) != null) {
			str.replaceAll(" ", "");
			if (str.equals(""))
				out.println();
			else {
				len = str.length();
				dp = new int[len + 1][len + 1];
				ans = new String[len + 1][len + 1];
				for (int i = 1; i <= len; i++) {
					dp[i][i] = 1;
					ch = str.charAt(i - 1);
					if (ch == '(' || ch == ')')
						ans[i][i] = "()";
					else
						ans[i][i] = "[]";
					ans[i][i - 1] = "";
				}
				for (int p = 1; p < len; p++) {
					for (int i = 1; i <= len - p; i++) {
						int j = i + p;
						dp[i][j] = Integer.MAX_VALUE;
						if (str.charAt(i - 1) == '('
								&& str.charAt(j - 1) == ')') {
							if (dp[i][j] > dp[i + 1][j - 1]) {
								dp[i][j] = dp[i + 1][j - 1];
								ans[i][j] = "(" + ans[i + 1][j - 1] + ")";
							}
						}
						if (str.charAt(i - 1) == '['
								&& str.charAt(j - 1) == ']') {
							if (dp[i][j] > dp[i + 1][j - 1]) {
								dp[i][j] = dp[i + 1][j - 1];
								ans[i][j] = "[" + ans[i + 1][j - 1] + "]";
							}
						}

						for (int k = i; k < j; k++) {
							if (dp[i][j] > dp[i][k] + dp[k + 1][j]) {
								dp[i][j] = dp[i][k] + dp[k + 1][j];
								ans[i][j] = ans[i][k] + ans[k + 1][j];
							}
						}
					}
				}
				out.println(ans[1][len]);
			}
		}
		out.flush();
		out.close();
	}
}
