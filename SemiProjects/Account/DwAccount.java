package bankSystem;

import java.text.MessageFormat;
import java.time.LocalDate;
//import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DwAccount extends Account {
	// 기존고객 제외, 새로 계좌를 개설한 고객에 대해서만 
	// -> 처음 잔액은 무조건 0, 입금을 받은 뒤에만 출금/송금 가능한 상태
	
	ArrayList<String> dwDbList = Dblog.DbList;

	// 인스턴스 변수
//	static int balance = 0;
		// 초기 잔액은 0
	// 접근 제어어 고려 필요
	// Q. 아래 변수들이 꼭 인스턴스 변수로 있어야 할까?
	double depositBill;	
	double withdrawBill;
	double remittanceBill;
	
	// 입력된 계좌 list
	static Map<String, String> accountMap = new HashMap<>();
	
	// 오버로딩
	DwAccount() {
		super();
		DwAccount.accountMap.put(accountNum, name);
	}

	
	// 메서드
	// 1. 입금
		// 1) 입금할 금액 입력 -> scanner
		// 2) 현재 잔액 출력
		// 3) LogDB에 데이터 전송
	
	void deposit() {
		// 금액 입력
		System.out.println("------------------------------");
		System.out.println("|"+this.name+"님, 환영합니다. 입금을 진행합니다.|");
		System.out.println("------------------------------");
		
		Scanner in = new Scanner(System.in);
		System.out.println(this.name+"님의 비밀번호 네 자리를 입력하세요: ");
		
		int flag1 = 1;
		while (flag1 == 1) {
			int inputPasswd = in.nextInt();
			if (this.passwd == inputPasswd) {
				System.out.println("입금할 금액을 입력해 주세요: ");
				
				// deposit bill > 0 인 금액만 입금
				int flag2 = 1;
				while (flag2 == 1) {
					double depositBill = in.nextDouble();
					
					if (depositBill > 0) {
						System.out.println(depositBill+"원을 입금합니다.");
						this.depositBill = depositBill;
						this.balance += depositBill;
						System.out.println("현재 잔액 :"+this.balance);
						flag2 = 0;
						
					} else {
						System.out.println("입금할 금액을 잘못 입력하셨습니다. 다시 입력해 주세요: ");
					}
				}
		
			flag1 = 0;
		
			} else {
			System.out.println("비밀번호가 틀렸습니다. 다시 입력해 주세요: ");
	        dwDbList.add(String.format("%s에 %s님이 %s계좌의 비밀번호를 틀렸습니다.", LocalDate.now(), name, accountNum));
	        Dblog.saveArrayListToFile(dwDbList, "src/banksystem/DwAccountLog.txt");
		}
	}
		
		// deposit 로그를 logDB에 데이터 전송
		dwDbList.add(LocalDate.now()+", "+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))+"에 "+this.name+"고객님이 고객님의 계좌에 "+this.depositBill+"원 을 입금하셨습니다.");
		Dblog.saveArrayListToFile(dwDbList, "src/banksystem/DwAccountLog.txt");
	}
	
	
	
	// 2. 출금
		// 1) 출금할 금액 입력 : this.balance > 출금금액 일때만 작동
		// 2) 현재 잔액 출력
		// 3) LogDB에 데이터 전송
	void withDraw() {
		// 출금할 금액 입력
		System.out.println("------------------------------");
		System.out.println("|"+this.name+"님, 환영합니다. 출금을 진행합니다.|");
		System.out.println("------------------------------");
		
		Scanner in = new Scanner(System.in);
		System.out.println(this.name+"님의 비밀번호 네 자리를 입력하세요: ");
		
		int flag1 = 1;
		while (flag1 == 1) {
			int passwd = in.nextInt();
					
			if (this.passwd == passwd) {
				
			System.out.println("출금할 금액을 입력해 주세요: ");
			
			// 현재 잔액 > 출금금액
			int flag2 = 1;
			while (flag2 == 1) {
				double withdrawBill = in.nextDouble();
				
				if (withdrawBill >= 0 && this.balance >= withdrawBill) {
					System.out.println(withdrawBill+"원을 출금합니다.");
					this.withdrawBill = withdrawBill;
					this.balance -= withdrawBill;
					System.out.println("현재 잔액:"+this.balance);
					flag2 = 0;
					
				} else if (withdrawBill < 0) {
					System.out.println("출금하실 금액은 0원 이상이어야 합니다. 출금 금액을 다시 입력해 주세요: ");
				
				} else {
					System.out.println("잔액이 부족합니다. 출금하실 금액을 다시 입력해 주세요: ");
				}
			}
			flag1 = 0;
		} else {
			System.out.println("비밀번호가 틀렸습니다. 비밀번호를 다시 입력해 주세요: ");
	        dwDbList.add(String.format("%s에 %s님이 %s계좌의 비밀번호를 틀렸습니다.", LocalDate.now(), name, accountNum));
	        Dblog.saveArrayListToFile(dwDbList, "src/banksystem/DwAccountLog.txt");
		}
		}
		
		// 출금 정보를 LogDB에 전송
		dwDbList.add(LocalDate.now()+", "+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))+"에 "+this.name+"고객님이 고객님의 계좌에서 "+this.withdrawBill+"원 을 출금하셨습니다.");
//		dwDbList.add(MessageFormat.format("{}, {}에 {}님이 본인 계좌에서 {}원을 출금하셨습니다.", LocalDate.now(), LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), this.withdrawBill));
		Dblog.saveArrayListToFile(dwDbList, "src/banksystem/DwAccountLog.txt");
	}
	
	// 3. 송금
		// 1) 송금 정보 입력 : 송금보낼 계좌 입력, 금액 입력
			// - 다른 사람 계좌가 존재하는 계좌인지 확인
			// - 잔액 > 송금할 금액일 때만 동작
		// 2) 잔액>송금일 때, 내 계좌 -= 송금금액, 남의 계좌 += 송금금액 // 이거는 못할듯? 계좌에 연결된 금액이 없어서
		// 3) 현재 잔액 출력
		// 4) LogDB에 데이터 전송

	void remittance(Account account) {
		
		System.out.println("------------------------------");
		System.out.println("|"+this.name+"님, 환영합니다. 송금을 진행합니다.|");
		System.out.println("------------------------------");
		
		Scanner in = new Scanner(System.in);
		System.out.println(this.name+"님의 비밀번호 네 자리를 입력하세요: ");
		int inputPasswd = in.nextInt();
		
		int flag1 = 1;
		while (flag1 == 1) {
			if (this.passwd == inputPasswd) {
								
				System.out.println("송금할 금액을 입력해 주세요: ");
				double remittanceBill = in.nextDouble();
				
				// 송금받을 계좌번호가 accountMap에 있을 때만 송금
				if (accountMap.containsKey(account.accountNum) && this.balance >= remittanceBill) {
					System.out.println(account.name+"님 에게 "+remittanceBill+"원을 송금합니다.");
					this.remittanceBill = remittanceBill;
					
					// 나의 계좌에서 -
					this.balance -= remittanceBill;
					System.out.println("현재 잔액: "+this.balance);
					
					// 남의 계좌에 +
					account.balance += remittanceBill;
					flag1 = 0;
				
				} else {
					System.out.println("송금할 계좌번호를 찾을 수 없습니다. 다시 입력해 주세요: ");
			        dwDbList.add(String.format("%s에 %s님이 %s계좌의 비밀번호를 틀렸습니다.", LocalDate.now(), name, accountNum));
			        Dblog.saveArrayListToFile(dwDbList, "src/banksystem/DwAccountLog.txt");
				}
			}
		}
		
		// 로그 데이터 전송
		dwDbList.add(LocalDate.now()+", "+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))+"에 "+this.name+"고객님이 고객님의 계좌에서 "+account.name+"님 에게 "+this.remittanceBill+"원 을 송금하셨습니다.");
		Dblog.saveArrayListToFile(dwDbList, "src/banksystem/DwAccountLog.txt");
	}
	
	
	// 4. 입출금 계좌 확인

	public void dwInfo() {
	System.out.println("------------------------------");
	System.out.println("|"+this.name+"님, 환영합니다. 입출금 계좌를 조회합니다.|");
	System.out.println("------------------------------");
	
	System.out.println(this.name+"님의 비밀번호 네 자리를 입력하세요.");
	Scanner sc = new Scanner(System.in);
	int input = sc.nextInt();
	
	if (input == passwd) {
        System.out.println("고객명: " + name);
        System.out.println("나이: " + age);
        System.out.println("직업: " + job);
        System.out.println("개설 목적: " + purpose);
        System.out.println("수입: " + income);
        System.out.println("계좌번호: " + accountNum);
        System.out.println("현재 계좌 잔액: "+ this.balance);
	} else {
		System.out.println("비밀번호가 틀립니다.");
        dwDbList.add(String.format("%s에 %s님이 %s계좌의 비밀번호를 틀렸습니다.", LocalDate.now(), name, accountNum));
        Dblog.saveArrayListToFile(dwDbList, "src/banksystem/DwAccountLog.txt");
	}

	// 실행
	dwDbList.add(String.format("%s에 %s님이 계좌 정보를 확인했습니다..", LocalDate.now(), name));
    Dblog.saveArrayListToFile(dwDbList, "src/banksystem/DwAccountLog.txt");
}
}
