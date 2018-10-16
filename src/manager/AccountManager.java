package manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;

import DTO.Account;
import DTO.Bank;

public class AccountManager {
	public String getInputUserName() {
		
		String result 	= null;
		String inputTxt = null;
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			printLog(	"�̸��� �Է����ּ���.(�ѱ۸� ����)"	);
			
			inputTxt = sc.nextLine();
			
			if( this.isValidUserName( inputTxt ) ) {
				result = inputTxt;
				break;
			}
		}
		
		return result;
	}
	
	public String getInputPhone() {
		
		String result = null;
		String inputTxt = null;
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			printLog("��ȭ��ȣ�� �Է����ּ���.");
			printLog("ex) 01012345678");
			
			inputTxt = sc.next();
			
			if (this.isValidPhone(inputTxt)) {
				result = inputTxt;
				break;
			}
			
		}
		return result;
	}
	
	public String getInputPassword() {
		
		String result = null;
		String inputTxt = null;
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			printLog("��й�ȣ�� �Է����ּ���.");
			printLog("(���� + ����, 6-20��)");
			
			inputTxt = sc.next();
			
			if (this.isValidPassword(inputTxt)) {
				result = encryptSHA256(inputTxt);
				break;
			}
		}
		return result;
	}
	
	public String getAccountNum(String bankName) {
		
		BankManager bm = new BankManager();
		ArrayList<Bank> bankList = bm.getBankList();
		String bankCode = null;
		
		long ts = System.currentTimeMillis();
		
		int randomValue = (int)(Math.random() * 8999) + 1000;
		
		for (int i = 0; i < bankList.size(); i++) {
			if (bankName.equals(bankList.get(i).getName())) {
				bankCode = bankList.get(i).getCode();
			}
		}
		return bankCode + "-" + ts + "-" + randomValue;
		
	}
	
	public int getFirstMoney(String bankName) {
		
		if (bankName == "�ϳ�") {
			return 1000;
		}
		else {
		return 0;
		}
	}
	
	public void createAccount() throws IOException {
		
		BankManager bm = new BankManager();
		
		String path = getPath();
		
		FileWriter fw = new FileWriter(path, true);
		
		String bankName = bm.getInputBankName();
		String userName = getInputUserName();
		String phone = getInputPhone();
		String pwd = getInputPassword();
		String accountNum = getAccountNum(bankName);
		int money = getFirstMoney(bankName);
		
		String data = accountNum + " " + bankName + " " + userName + " " + phone + " " + pwd + " " +  money + "\r\n";
		fw.write(data);
		fw.close();
		
		printLog("���°� �����Ǿ����ϴ�.");
		printLog("-----------------------------------");
	}
	
	public ArrayList<Account> getAccountList() throws IOException {
		
		String path = getPath();
		
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line = null;
		String[] split = null;
		
		ArrayList<Account> accountList = new ArrayList<Account>();
		
		while((line = reader.readLine()) != null) {
			split = line.split(" ");
			
			accountList.add(new Account(split[0], split[1], split[2], split[3], split[4], Integer.valueOf(split[5])));
		}
		return accountList;
	}
	
	public void setAccoutList(ArrayList<Account> accountList) throws IOException {
		
		String path = getPath();
		
		FileWriter fw = new FileWriter(path, false);
		String data = null;
		
		for (int i = 0; i < accountList.size(); i++) {
			data = accountList.get(i).getAccountNum() + " " + accountList.get(i).getBankName() + " " + accountList.get(i).getUserName() + " " + accountList.get(i).getPhone() + " " + accountList.get(i).getPwd() + " " + accountList.get(i).getMoney() + "\r\n";
			fw.write(data);
		}
		fw.close();
	}
	
	public void depositMoney() throws IOException {
		
		ArrayList<Account> accountList = this.getAccountList(); 
		Scanner scan = new Scanner(System.in);
		String accountNum = null;
		String pwd = null;
		int money = 0;
		int index = 0;
		
		printLog("���¹�ȣ�� �Է��ϼ���.");
		accountNum = scan.next();
		printLog("��й�ȣ�� �Է��ϼ���.");
		pwd = scan.next();
		printLog("�Ա��� �ݾ��� �Է��ϼ���.");
		money = scan.nextInt();
		
		pwd = encryptSHA256(pwd);
		index = retrieveIndexByAccountNumber(accountNum);
		if (index == -1) {
			printLog("���¹�ȣ�� �������� �ʽ��ϴ�.");
		}
		else {
			if(!pwd.equals(accountList.get(index).getPwd())){
				printLog("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				return;
			}
			else if (isValidMoney(money)){
			accountList.get(index).setMoney((accountList.get(index).getMoney() + money));
			}
			else {
				printLog("�ݾ��� ����� �Է����ּ���.");
			}
		}
		System.out.println("���� �ܰ�� " + accountList.get(index).getMoney() + "���Դϴ�.");
		printLog("----------------------------------------------");
		setAccoutList(accountList);
}
		
	
	private boolean isValidUserName(String inputTxt) {
		String userNameRegExp = "^[��-�R]*$";
		if (inputTxt.matches(userNameRegExp)) {
			return true;
		}
		return false;
	}
	
	private boolean isValidPhone(String inputTxt) {
		String phoneRegExp = "^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$";
		if (inputTxt.matches(phoneRegExp)) {
			return true;
		}
		return false;
	}
	
	private boolean isValidPassword(String inputTxt) {
		String passworldRegExp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}$";
		if (inputTxt.matches(passworldRegExp)) {
			return true;
		}
		return false;
	}
	
	private boolean isValidMoney(int inputInt) {
		if (inputInt > 0) {
			return true;
		}
		return false;
	}
	public int retrieveIndexByAccountNumber(String accountNum) throws IOException {
		ArrayList<Account> accountList = this.getAccountList();
		for(int i = 0; i < accountList.size(); i++) {
			if(accountNum.equals(accountList.get(i).getAccountNum())){
				return i;
			}
		}
		return -1;
	}
	
	private static String encryptSHA256(String str) {
		String SHA = null;
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return SHA;
	}
	
	public String getPath() {
		String path = "C:\\Users\\brain\\Desktop\\�극�ο����ڸ���\\�ڹ�\\������\\AccountList.txt";
		return path;
	}
	public void printLog( String log ) {
		System.out.println( log );
	}
}
