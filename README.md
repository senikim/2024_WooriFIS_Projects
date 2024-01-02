# WooriFIS_Projects

## 👩‍🏫 1차 기술세미나
✅ Topic : 금융권 AI Tasks 소개

✅ 나의 역할 : Fraud Detection System 소개 및 구현

<img src = "https://github.com/senikim/WooriFIS_Projects/assets/113660954/58bece71-1d22-4d50-a677-9b531adc25fe" width = "800" height = "400"/>
<img src = "https://github.com/senikim/WooriFIS_Projects/assets/113660954/0beebef5-cc44-4df9-8783-d571f4f58d88" width = "800" height = "400"/>
<img src = "https://github.com/senikim/WooriFIS_Projects/assets/113660954/44811ca9-e8ae-468b-bfe4-dc43bc5163f3" width = "800" height = "400"/>
<img src = "https://github.com/senikim/WooriFIS_Projects/assets/113660954/4d48ba5f-b5a3-4843-8130-fd14eae97354" width = "800" height = "400"/>
<img src = "https://github.com/senikim/WooriFIS_Projects/assets/113660954/b16d251c-01da-4c9d-8b89-d35013ffbbdb" width = "800" height = "400"/>
<img src = "https://github.com/senikim/WooriFIS_Projects/assets/113660954/bad1aec9-7eb4-4e23-bf1a-d9c3ab4fa98f" width = "800" height = "400"/>

autoenocder 결과 정상거래인데 이상거래로 잘못 판별된 데이터가 많은 것을 볼 수 있다. 여기서 내가 예상하는 문제점은 1) epoch 수가 10번으로 매우 적었고, 하이퍼 파라미터를 조정하지 않았다는 점, 2) 과적합 - 모델이 학습 데이터에 너무 맞춰져서 특정 데이터에 대해서만 과도하게 학습될 경우, 학습 데이터(train dataset)에 속하지 않는 (test dataset)에 있는 정상데이터에 대한 재구성 오차가 커질 수도 있다는 점이다. 또한, 이상 거래인데 재구성 오차가 0에 가까워 정상거래로 잘못 판별된 경우도 있는데, 여기서의 문제점은 과적합 - 정상데이터에 대해서만 과도하게 학습되어 이상 데이터인데도 정상 데이터에 가깝게 재구성이 되기 때문인 것 같다.
따라서 과적합을 방지하고 하이퍼파라미터를 조정하여 성능을 향상시키는 것을 향후 과제로 설정했다.
