import java.io.*;
import java.util.*;

public class p3480___Anti_Nim {
	static Scanner scan = new Scanner(System.in);
	static PrintWriter out = new PrintWriter(System.out);

	static int n;

	public static void main(String[] args) throws IOException {
		int test = scan.nextInt();
		while (test-- > 0) {
			n = scan.nextInt();

			boolean flag = true;
			int ans = 0, temp;
			for (int i = 1; i <= n; i++) {
				temp = scan.nextInt();
				ans ^= temp;
				if (temp > 1)
					flag = false;
			}

			if (flag) {
				if (ans == 0)
					out.println("John");
				else
					out.println("Brother");
			} else if (ans > 0)
				out.println("John");
			else
				out.println("Brother");
		}

		out.flush();
		out.close();
	}

}