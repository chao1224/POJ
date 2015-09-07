

import java.util.Scanner;
import java.math.*;

public class p1001 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			for (int i = 0; i < 6; i++) {
				BigDecimal db = new BigDecimal(scan.next());
				db = db.pow(scan.nextInt());
				String str = db.stripTrailingZeros().toPlainString();
				if (str.startsWith("0")) {
					str = str.substring(1);
				}
				System.out.println(str);
			}
		}
	}
}
