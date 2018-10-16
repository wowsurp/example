package DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Account {
	
	private String accountNum;
	private String bankName;
	private String userName;
	private String phone;
	private String pwd;

	private int money;

	private ArrayList<HashMap<Date, Integer>> dealHistory;

	public Account(String accountNum, String bankName, String userName, String phone, String pwd, int money) {
		this.accountNum = accountNum;
		this.bankName = bankName;
		this.userName = userName;
		this.phone = phone;
		this.pwd = pwd;
		this.money = money;
	}
	
	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<HashMap<Date, Integer>> getDealHistory() {
		return dealHistory;
	}

	public void setDealHistory(ArrayList<HashMap<Date, Integer>> dealHistory) {
		this.dealHistory = dealHistory;
	}
	
	
	
}
