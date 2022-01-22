import numpy as np

'''
1. boolean indexing의 이해
- ndarray 인덱싱 시, bool 리스트를 전달하여 True인 경우만 필터링
* 브로드캐스팅을 활용하여 ndarray로부터 bool list 얻기
'''

x = np.random.randint(1, 100, size=10)
print(x)
# even_mask = x % 2 == 0
print(x[x % 2 == 0])
print(x[x > 30])
print(x[(x % 2 == 0) & (x > 30)])

#### 예제) 2019년 7월 서울 평균기온 데이터
# - 평균기온이 25도를 넘는 날수는?
# - 평균기온이 25를 넘는 날의 평균 기온은?"
temp = np.array(
    [23.9, 24.4, 24.1, 25.4, 27.6, 29.7,
    26.7, 25.1, 25.0, 22.7, 21.9, 23.6,
    24.9, 25.9, 23.8, 24.7, 25.6, 26.9,
    28.6, 28.0, 25.1, 26.7, 28.1, 26.5,
    26.3, 25.9, 28.4, 26.1, 27.5, 28.1, 25.8]
)

hot = temp[temp > 25]
print(str(len(hot)) + '일')
print('평균 : %.2f℃' % (np.mean(hot)))
