import java.io.*;
import java.util.*;

public class p3517___约瑟夫环数学解法___写法一 {
	static int n, m, s, k;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			n = scan.nextInt();
			m = scan.nextInt();
			s = scan.nextInt();
			if (n == 0 && m == 0 && s == 0)
				break;
			k = Joesphus(n - 1, m, 0);
			k = (k + s) % n;
			if (k == 0)
				k = n;
			System.out.println(k);
		}
	}

	static int Joesphus(int n, int m, int k) {
		for (int c = 2; c <= n; c++)
			k = (k + m) % c;
		return k + 1;
	}
}
