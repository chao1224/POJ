import java.util.Scanner;

public class p3070___¼òµ¥ì³²¨ÄÇÆõ¶î {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n;
		int a, b, c, x, y, z;
		int t = 10000;
		int t1, t2, t3;
		while (scan.hasNext()) {
			x = y = z = b = c = 1;
			a = 0;
			n = scan.nextInt();
			if (n == -1)
				break;
			if (n == 0)
				System.out.println(a);
			else if (n == 1)
				System.out.println(b);
			else if (n == 2)
				System.out.println(c);
			else {
				n -= 1;
				while (n > 0) {
					if ((n & 1) == 1) {
						// by+cz ay+bz
						// bx+cy ax+by
						t1 = ((a % t) * (x % t) + (b % t) * (y % t)) % t;
						t2 = ((b % t) * (x % t) + (c % t) * (y % t)) % t;
						t3 = ((c % t) * (z % t) + (b % t) * (y % t)) % t;
						x = t1;
						y = t2;
						z = t3;
					}
					// c*c+b*b b*c+b*a
					// b*c+b*a b*b+a*a
					t1 = ((a % t) * (a % t) + (b % t) * (b % t)) % t;
					t2 = ((b % t) * (c % t) + (b % t) * (a % t)) % t;
					t3 = ((c % t) * (c % t) + (b % t) * (b % t)) % t;
					a = t1;
					b = t2;
					c = t3;
					n >>= 1;
				}
				System.out.println(x);
			}
		}
	}
}