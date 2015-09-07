

import java.util.Scanner;

public class p1007 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int row = scan.nextInt();
		int line = scan.nextInt();
		String[] str = new String[line];
		int[] ine = new int[line];
		for (int i = 0; i < line; i++) {
			str[i] = scan.next();
			ine[i] = converseSort(str[i]);
		}

		int min;
		for (int i = 0; i < line - 1; i++) {
			min = i;
			for (int j = i + 1; j < line; j++) {
				if (ine[j] < ine[min]){
					min=j;
				}
			}
	//		swap(ine[i], ine[min]);
	//		swapStr(str[i], str[min]);
			int temp;
			temp = ine[i];
			ine[i] = ine[min];
			ine[min] = temp;
			String tempStr;
			tempStr = str[i];
			str[i] = str[min];
			str[min] = tempStr;
		}

		for (int i = 0; i < line; i++) {
			System.out.println(str[i]);
		}
	}

	public static int converseSort(String str) {
		int result = 0;
		for (int i = 0; i < str.length(); i++)
			for (int j = i + 1; j < str.length(); j++)
				if (str.charAt(i) > str.charAt(j))
					result++;
		return result;
	}
}
