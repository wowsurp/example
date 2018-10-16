import java.io.IOException;
import java.util.Scanner;

import manager.AccountManager;
import manager.BankManager;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BankManager bm = new BankManager();
		AccountManager am = new AccountManager();
		
		bm.getBankMenu();
		
		Scanner sc = new Scanner(System.in);
		
		int inputInt = sc.nextInt();
		
		//bm.getInputBankName(); 참고해서 구현할 것.
		//메인 메서드는 주석 외의 코드를 더 넣지 말것.
		//메인 클레스에 스태틱 메서드는 추가가능.
		
		switch (inputInt) {
			case 1 : // 계좌만들기
				am.createAccount();
				bm.getBankMenu();
				break;
			case 2 : // 입금
				am.depositMoney();
				bm.getBankMenu();
		}
//		if(inputTxt.equals("계좌만들기")) {
//			am.createAccount();
//			
//		} else if(inputTxt.equals("입금")) {
//			am.depositMoney();
//		} else if(inputTxt.equals("출금")) {
//			//입금 
//		} else if(inputTxt.equals("계좌이체")) {
//			 //계좌이체 메서드
//		} else {
//			//해당사항이 없을때 다시 입력 받는 기능
//		}
	}
}
