package 演習課題.mondai1;

import java.util.Scanner;

public class Mondai02 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		while(true) {
//		while(sc.hasNext()) {
			System.out.println("何か数字を入力してください。");
//			Scanner sc = new Scanner(System.in);

			if(true == sc.hasNextInt()) {
				int tmp = sc.nextInt();
				System.out.print("入力した数字は:" + tmp + " です。");
				sc.next();
			}

//			if(true == sc.hasNextLine()) {
//				String tmp = sc.nextLine();
//				System.out.print("入力した文字は:" + tmp + " です。");
//				sc.next();
//			}

			if(false == sc.hasNext()) {
				sc.close();
				break;
			}

			sc.close();
		}
//		sc.close();
	}

}
