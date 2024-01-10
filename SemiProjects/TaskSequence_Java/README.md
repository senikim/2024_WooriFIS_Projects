💡 상황
  1. Schedule : 인터페이스 혹은 추상클래스로 구현
     
     - getNextCall()
     - sendCallToAgent()
  2. 자식클래스         

     - LeastJob  
       - 1. 대기열에서 고객을 순서대로 넘깁니다
       - 2. 상담전화 대기열이 가장 짧은 상담원에게 배분합니다.
        
      - RoundRobin
        - 1. 대기열에서 고객을 순서대로 넘깁니다
        - 2. 상담전화를 전화를 마친 상담원에게 배분합니다.
                            
      - Priority 
         - 1. 대기열에서 우선순위가 높은 고객을 먼저 줄세웁니다.
         - 2. 상담전화를 일 잘하는 상담원에게 먼저 배분합니다.

📁 UML

<img src = "https://github.com/senikim/WooriFIS_Projects/assets/113660954/8d309bbd-1ba1-45e2-a3a7-431cf5114320" width = "800" height = "400"/>
