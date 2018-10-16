package manager;

import java.util.ArrayList;
import java.util.Scanner;

import DTO.Bank;

public class BankManager {
	
	public String getInputBankName() {
		
		String result 	= null;
		String inputTxt = null;
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			printLog(	"��� ���࿡�� ���¸� ������ �Է����ּ���."	);
			printLog(	"����, ����, ����, �ϳ� �� ��1"				);
			
			inputTxt = sc.nextLine();
			
			if( this.checkIsVaildBank( inputTxt ) ) {
				result = inputTxt;
				break;
			}
		}
		
		return result;
	}
	
	public ArrayList<Bank> getBankList(){
		
		ArrayList< Bank > bankList = new ArrayList< Bank >();
		
		bankList.add( new Bank( "����" ,	"001" ) );
		bankList.add( new Bank( "����" ,	"002" ) );
		bankList.add( new Bank( "����" ,	"003" ) );
		bankList.add( new Bank( "�ϳ�" ,	"004" ) );
		
		return bankList;
	}
	
	public void getBankMenu() {
		printLog("[���� ����]");
		printLog("�̿��� ��ȣ�� �Է����ּ���.");
		printLog("1. ���¸����");
		printLog("2. �Ա�");
		printLog("3. ���");
		printLog("4. ������ü");
	}
	
	private boolean checkIsVaildBank( String inputTxt ) {
		
		boolean result = false;
		
		String tempBankName = null;
		
		Bank tempBank = null;
		
		ArrayList< Bank > bankList = this.getBankList();
		
		for( int i = 0; i < bankList.size(); i++ ) {
			
			tempBank = bankList.get( i );
			tempBankName = tempBank.getName();
			
			if( tempBankName.equals( inputTxt ) ) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public void printLog( String log ) {
		System.out.println( log );
	}
	
	
	
}
