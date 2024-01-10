package scheduleEx;

import java.util.Scanner;

public class ScheduleEx {

	public static void main(String[] args) {
		
		RoundRobin rr = new RoundRobin();
		LeastJob lj = new LeastJob();
		Priority pri = new Priority();

		Scanner in = new Scanner(System.in);
		System.out.println("어떤 업무인지 입력하세요. R/r : RoundRobin, L/l : LeastJob, P/p : Priority, Q/q : 시스템 종료");
		
		boolean isNotEnd = true;
		// flag 변수 이용해서도 가능 - int flag = 1;
		
		while (isNotEnd) {
			String input = in.next();
			input = input.toUpperCase();
			
			switch (input) {
				case "R":
					System.out.println("RoundRobin 방식으로 진행됩니다.");
					rr.run();
					break;
				case "L":
					System.out.println("LeastJob 방식으로 진행됩니다.");
					lj.run();
					break;
				case "P":
					System.out.println("Priority 방식으로 진행됩니다.");
					pri.run();
					break;
				case "Q":
					isNotEnd = false;
					// break; while 문이 break 되어버려서 case 안에서는 안써도 됨
					
			}
		}
		
		System.out.println("시스템이 종료됩니다.");
	
	}

}
