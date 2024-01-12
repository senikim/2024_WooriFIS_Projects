package bankSystem;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class bankEx {

	private static final int HashMap = 0;

	public static void main(String[] args) {
//		
// while문으로 반복해서 새로운 고객 정보 입력
		
		// while
		boolean upcontinue = true;
		boolean downcontinue = true;
		boolean downContinue2 = true;
		
		while (upcontinue) {
			Scanner sc = new Scanner(System.in);
			System.out.println("1: 입출금 계좌 개설 / 2: 대출 신청 / 0: 시스템 종료");
			int button = sc.nextInt();
			
			switch(button) {
				case 1:
					DwAccount 고객 = new DwAccount();
					System.out.println(고객.name+"님의 신규 입출금 계좌를 개설했습니다.");
					
					Scanner sc2 = new Scanner(System.in);
					
					while (downcontinue) {
						System.out.println("1: 입금 / 2: 출금 / 3: 송금 / 4: 입출금 계좌 조회 / 0: 입출금 서비스 종료");
						int dwService = sc2.nextInt();
						switch (dwService) {
							case 1:	// 입금
								고객.deposit();
								break;
							case 2: // 출금
								고객.withDraw();
								break;
//							case 3: // 송금
//								Scanner sc3 = new Scanner(System.in);
//								String sendToname = sc3.next();
//								고객.remittance(sendToname);
									// [String으로 받은 입력값을 Account 인스턴스로 변환할 수 없기 때문에 오류가 발생]
							case 4:
								고객.dwInfo();
								break;
							case 0:
								System.out.println("계좌 개설 서비스를 종료하고, 초기 화면으로 돌아갑니다.");
								downcontinue = false;
						}
					}
					break;
					
				case 2:
					System.out.println("신규 대출을 신청합니다.");
					LoanAccount loan = new LoanAccount();
						// [Trial : 처음 입력할 때 대출 상품 조건에 맞지 않는 경우 "0"번을 눌러 시스템을 종료하려고 했으나
						//  LoanAccount의 경우에는 생성자를 만들자마자 setAdditionalInfo()가 실행되어서
						//  setAdditionalInfo() 안에서 while문을 걸어도 초기화면(대출 상품 선택 전)으로 돌아갈 수 없음 = 중도 종료 불가]
					System.out.println("대출 조회를 시작합니다.");
					loan.run();
					break;
				
				case 0:
					System.out.println("은행 시스템을 종료합니다.");
					upcontinue = false;
					break;
				
				default:
					System.out.println("입력값을 다시 입력하세요. 1: 입금 / 2: 출금 / 3: 송금 / 4: 입출금 계좌 조회 / 0: 서비스 종료 ");
			}
		}
		
		// 송금
		DwAccount example1 = new DwAccount();
		DwAccount example2 = new DwAccount();
		example2.deposit();
		example2.remittance(example1);
		// 계좌 잔액 확인
		example1.dwInfo();
		
	}
}

