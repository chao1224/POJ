import java.io.*;
import java.util.*;

public class p2244___约瑟夫环小变形___数学解法 {
	static int n, k;

	static int Joesphus(int n, int m, int k) {
		for (int c = 2; c <= n - 1; c++)
			k = (k + m - 1) % c + 1;
		return k;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		loop: while (scan.hasNext()) {
			n = scan.nextInt();
			if (n == 0)
				break;
			for (int m = 2;;) {
				if (1 + Joesphus(n, m, 1) == 2) {
					System.out.println(m);
					continue loop;
				}
				m++;
			}
		}
	}

}
