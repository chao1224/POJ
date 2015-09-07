

import java.util.Scanner;

public class p1006 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a, b, c, d, time = 1;
		while (true) {
			a = scan.nextInt();
			b = scan.nextInt();
			c = scan.nextInt();
			d = scan.nextInt();
			if (a == -1 && b == -1 && c == -1)
				break;
			int sum = 5544 * a + 14421 * b + 1288 * c-d;
			sum %= 21252;
			if (sum <= 0)
				sum += 21252;
			System.out.println("Case " + time++
					+ ": the next triple peak occurs in " + sum
					+ " days.");
		}
	}
}
