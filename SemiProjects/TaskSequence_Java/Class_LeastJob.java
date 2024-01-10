package scheduleEx;
public class LeastJob implements Schedule {
	
	void waitingLJ() {
		System.out.println("대기열이 가장 짧은 상담원을 찾고 있습니다");
	}
	
	public void sendCallToAgent() {
		System.out.println("상담원을 연결합니다...");
	}
	
	public void run() {
		getNextCall();
		waitingLJ();
		sendCallToAgent();
	}
}
