package scheduleEx;

public interface Schedule {
	
	/*
	 * // Schedule -> 인터페이스 혹은 추상클래스로 구현
            -> getNextCall() 
            -> sendCallToAgent()
            
		// 자식클래스   
 			-> LeastJob  
                - 1. 대기열에서 고객을 순서대로 넘깁니다
                - 2. 상담전화 대기열이 가장 짧은 상담원에게 배분합니다.

			-> RoundRobin
                - 1. 대기열에서 고객을 순서대로 넘깁니다
                - 2. 상담전화를 전화를 마친 상담원에게 배분합니다.
                            
             -> Priority 
                - 1. 대기열에서 우선순위가 높은 고객을 먼저 줄세웁니다.
                - 2. 상담전화를 일 잘하는 상담원에게 먼저 배분합니다.
	 */
	
	// 메서드
	default void getNextCall() {
		System.out.println("다음 상담전화를 연결합니다.");
	}
	void sendCallToAgent();
	void run();
	
}
