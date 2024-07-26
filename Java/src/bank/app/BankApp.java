package bank.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankApp {
	private static Scanner sc = new Scanner(System.in);
	private static List<Account> accounts = new ArrayList<Account>();
	
	public static void main(String[] args) {
		
		boolean run = true;
		
		while(run) {
			System.out.println("------------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
			System.out.println("------------------------------------------");
			System.out.print("선택> ");
			
			int selectNo = Integer.parseInt(sc.nextLine());
			if(selectNo == 1) {
				createAccount();
			}else if(selectNo ==2) {
				accountList();
			}else if(selectNo ==3) {
				deposit();
			}else if(selectNo ==4) {
				withdraw();
			}else if(selectNo ==5) {
				run= false;
			}	
		}
		
		sc.close();
		System.out.println("프로그램 종료");
	}
	
	public static void createAccount() {
		System.out.println("-----------------계좌생성-----------------");
		System.out.print("계좌번호 : ");
		String ano  = sc.nextLine();
		System.out.print("계좌주 : ");
		String owner = sc.nextLine();
		System.out.print("초기 입금액 : ");
		int balance = Integer.parseInt(sc.nextLine());
		Account account = new Account(ano,owner,balance);
		accounts.add(account);
		System.out.println("결과 : 계좌가 생성되었습니다.");
		
	}
	
	public static void accountList() {
		System.out.println("-----------------계좌목록-----------------");
		accounts.stream().forEach(t -> 
			System.out.println(t.getAno() + " "+ t.getOwner()+" "+t.getBalance()));
		System.out.println();
	}
	
	public static void deposit() {
		System.out.println("------------------예금------------------");
		System.out.print("계좌번호 : ");
		String ano  = sc.nextLine();
		System.out.print("예금액 : ");
		int balance = Integer.parseInt(sc.nextLine());
		Account acc = findAccount(ano);
		if(acc==null) {
			System.out.println("결과 : 동일한 계좌가 없습니다.");
		}else {
			int update = acc.getBalance()+balance;
			acc.setBalance(update);
			System.out.println("결과 : 예금이 성공되었습니다.");	
		}
		
	}
	
	public static void withdraw() {
		System.out.println("------------------출금------------------");
		System.out.print("계좌번호 : ");
		String ano  = sc.nextLine();
		System.out.print("출금액 : ");
		int balance = Integer.parseInt(sc.nextLine());
		
		Account acc = findAccount(ano);
		if( acc == null) {
			System.out.println("결과 : 동일한 계좌가 없습니다.");
		}else {
			if(acc.getBalance() < balance) {
				System.out.println("결과 : 잔액이 부족합니다.");
			}else {
				int update = acc.getBalance() - balance;
				acc.setBalance(update);
				System.out.println("결과 : 출금이 성공되었습니다.");
			}
			
		}
		
	}
	
	public static Account findAccount(String account) {
		
		for(int i=0;i<accounts.size();i++) {
			if(accounts.get(i).getAno().equals(account)) {
				return accounts.get(i);
			}
		}
		return null;
	}
		
	

}
