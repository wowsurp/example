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
			
			printLog(	"어느 은행에서 계좌를 만들지 입력해주세요."	);
			printLog(	"농협, 국민, 신한, 하나 중 택1"				);
			
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
		
		bankList.add( new Bank( "농협" ,	"001" ) );
		bankList.add( new Bank( "국민" ,	"002" ) );
		bankList.add( new Bank( "신한" ,	"003" ) );
		bankList.add( new Bank( "하나" ,	"004" ) );
		
		return bankList;
	}
	
	public void getBankMenu() {
		printLog("[은행 관리]");
		printLog("이용할 번호를 입력해주세요.");
		printLog("1. 계좌만들기");
		printLog("2. 입금");
		printLog("3. 출금");
		printLog("4. 계좌이체");
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
