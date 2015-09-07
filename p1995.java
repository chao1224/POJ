import java.util.Scanner;

public class p1995 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int turn = scan.nextInt();
		int m;
		int h;
		long[] a;
		long[] b;
		for (int tt = 0; tt < turn; tt++) {
			m = scan.nextInt();
			h = scan.nextInt();
			a = new long[h];
			b = new long[h];
			for (int i = 0; i < h; i++) {
				a[i] = scan.nextInt();
				b[i] = scan.nextInt();
			}

			int sum = 0;
			int r;
			for (int i = 0; i < h; i++) {
				r = 1;
				while (b[i] > 0) {
					if ((b[i] & 1) == 1) {
						r *= (a[i] % m);
						r = r % m;
					}
					a[i] = (a[i] % m) * (a[i] % m);
					b[i] >>= 1;
				}
				sum += r % m;
			}
			sum = sum % m;
			System.out.println(sum);
		}
	}
}