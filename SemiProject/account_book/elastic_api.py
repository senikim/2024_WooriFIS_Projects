from datetime import datetime
from elasticsearch import Elasticsearch
from elasticsearch_dsl import Search, A
client = Elasticsearch('http://localhost:9200')
INDEX = 'bank_salad_02'
def set_range(month):
    start_date = f"2023-{month:02d}-01"
    end_date = f"2023-{month:02d}-31"
    return start_date,end_date
def search_index(month):
    start_date, end_date = set_range(month)
    s = Search(index=INDEX).using(client).query("match_all")
    s = s.filter('range', 날짜={'gte': start_date, 'lte': end_date})
    s = s.extra(size=500)
    response = s.execute()
    # print("쿼리?: ", s.to_dict())
    # print('인덱스 조회 결과: ', response[0].to_dict())
    return response
def remove_content(content_id):
    client.delete(index=INDEX, id=content_id)
    print("제거되었습니다.")
def get_total_price(month):
    start_date, end_date = set_range(month)
    s = Search(index=INDEX).using(client).query("match_all")
    s = s.filter('range', 날짜={'gte': start_date, 'lte': end_date})
    s.aggs.bucket('positive_amounts', 'range', field='금액', ranges=[{"from": 0.01}]).metric('sum_positive', 'sum', field='금액')
    s.aggs.bucket('negative_amounts', 'range', field='금액', ranges=[{"to": 0}]).metric('sum_negative', 'sum', field='금액')
    response = s.execute()
    total_positive = response.aggregations.positive_amounts.buckets[0].sum_positive.value
    total_negative = response.aggregations.negative_amounts.buckets[0].sum_negative.value
    return total_positive, total_negative
def search_index2(transection_category, transection_content, transection_date, transection_method, transection_bill, transection_small_category, transection_type):
    doc = {
    '대분류': transection_category,
    '내용': transection_content,
    '날짜': transection_date,
    '결제수단': transection_method,
    '금액' : transection_bill,
    '소분류' : transection_small_category,
    '타입' : transection_type
}
    resp = client.index(index=INDEX, body=doc)
    print(resp['result'])



# from datetime import datetime
# from elasticsearch import Elasticsearch
# from elasticsearch_dsl import Search
# client = Elasticsearch('http://localhost:9200')
# INDEX = 'bank_salad_02'

# def search_index(month):
#     start_date = f"2023-{month:02d}-01"
#     end_date = f"2023-{month:02d}-31"
#     s = Search(index=INDEX).using(client).query("match_all")
#     s = s.filter('range', 날짜={'gte': start_date, 'lte': end_date})
#     s = s.extra(size=500)
#     response = s.execute()
#     return response

# # 전체 데이터에 삽입할 수 있도록
# # 인덱스 생성
# def search_index2(transection_category, transection_content, transection_date, transection_method, transection_bill, transection_small_category, transection_type):

#     doc = {
#     '대분류': transection_category,
#     '내용': transection_content,
#     '날짜': transection_date,
#     '결제수단': transection_method,
#     '금액' : transection_bill,
#     '소분류' : transection_small_category,
#     '타입' : transection_type
# }
    
#     resp = client.index(index=INDEX, body=doc)
#     print(resp['result'])

###############################################
# def search_index3():
#     # 인덱스 조회
#     s = Search(index=INDEX).using(client).query("match_all")
#     s = s.source(fields = ["날짜", "타입", "대분류", "소분류", "내용", "결제수단", "금액"])
#     s = s.extra(size=500)
#     print("쿼리?: ", s.to_dict())
#     response2 = s.execute()
#     return response2