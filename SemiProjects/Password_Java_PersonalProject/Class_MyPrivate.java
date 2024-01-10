package oop_ex3_access;

import java.util.Scanner;

// 같은 클래스에서만 접근 가능함.
public class MyPrivate {
	
	// 비밀번호 숨기기
	private String password;
	
	// Password 수정하는 method 만들기
	// 밖의 클래스에서는 password 변수에 접근하지 못하도록 막고
	// setter를 이용해서 password를 바꿀 수 있도록 만들어주기
	
	// 생성자 - 초기 비밀번호 생성
	MyPrivate(String pw){
		this.password = pw;
	}
		// [생성자의 파라미터 pw 값을 입력하면 현재 클래스의 인스턴스 변수에 그 값이 씌워짐]
	
	// 메서드
	
	// getter - private 변수를 조회만 할 수 있게 하는 메서드
	public void printGetter() {
		System.out.println(this.password);
	}
	
	// setter - 비밀번호 변경
	// 생성자를 만들지 않고도 기존 비밀번호를 변경하는 메서드
	public void modifySetter() {
		Scanner in = new Scanner(System.in);
		
		int flag = 1;
		while (flag==1) {
			System.out.println("변경할 비밀번호를 입력하세요: ");
			String newpw = in.next();
			
			if (this.password.equals(newpw)) {
				System.out.println("이전 비밀번호와 동일한 비밀번호가 입력되었습니다. 비밀번호를 다시 입력해 주세요");
			} else {
				this.password = newpw;
				System.out.println("비밀번호가 성공적으로 변경되었습니다.");
				flag = 0;
			}
	}
	
	}
}
