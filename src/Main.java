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
		
		//bm.getInputBankName(); �����ؼ� ������ ��.
		//���� �޼���� �ּ� ���� �ڵ带 �� ���� ����.
		//���� Ŭ������ ����ƽ �޼���� �߰�����.
		
		switch (inputInt) {
			case 1 : // ���¸����
				am.createAccount();
				bm.getBankMenu();
				break;
			case 2 : // �Ա�
				am.depositMoney();
				bm.getBankMenu();
		}
//		if(inputTxt.equals("���¸����")) {
//			am.createAccount();
//			
//		} else if(inputTxt.equals("�Ա�")) {
//			am.depositMoney();
//		} else if(inputTxt.equals("���")) {
//			//�Ա� 
//		} else if(inputTxt.equals("������ü")) {
//			 //������ü �޼���
//		} else {
//			//�ش������ ������ �ٽ� �Է� �޴� ���
//		}
	}
}
