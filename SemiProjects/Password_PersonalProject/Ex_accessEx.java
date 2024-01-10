package oop_ex3_access;

public class accessEx {

	public static void main(String[] args) {

		MyPrivate private1 = new MyPrivate("password123");  // 초기 비밀번호 지정
		
//		System.out.println(private1.password);
			// [Error : The field MyPrivate.password is not visible]
			// [MyPrivate에 생성된 인스턴스 변수인 password을 private으로 접근을 막아두었기 때문에 인스턴스로도 password 변수를 출력할 수 없음]
		
		// 1. 비밀번호 확인하기
		private1.printGetter();
			// [MyPrivate 안의 printGetter() 메서드는 public으로 작성되었기 때문에 작동함.]
		
		// 2. 비밀번호 수정하기
		private1.modifySetter();
		
		System.out.println("-------");
		private1.printGetter();	// 확인
	}

}
