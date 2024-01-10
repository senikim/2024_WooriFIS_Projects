💡 프로젝트 설명
- Private 변수를 이용하여 외부에서 비밀번호를 보지 못하게 하고
- 특정 메서드 (printGetter, modifySetter)를 이용해서만 password를 볼 수 있고, 변경할 수 있게끔 만들기

📚 추후 과제
- 이런 단순한 형태만으로 만들어지는게 맞는지... 궁금
- 초기 비밀번호를 지정할 때 생성자를 만들면서 지정해줘야 하는데 이 비밀번호를 누적해서 변경되게 하고 싶음
- Private을 이용해서 method 만들어보기
- interface 까지 만들어서 Class 변수랑 메서드를 적용시켜보고 싶음.

📌 핵심은 다음 코드

![image](https://github.com/senikim/WooriFIS_Projects/assets/113660954/ebb802a2-92fd-4383-bc92-032ebc15b4ed)

- 위 코드 돌렸을 때 private 제어 때문에 password가 안보이는 거
