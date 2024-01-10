package scheduleEx;
public class RoundRobin implements Schedule {
	
	@Override
	public void sendCallToAgent() {
		System.out.println("다음 상담원에게 연결합니다.");
	}
	public void run() {
		getNextCall();
		sendCallToAgent();
	}
}
