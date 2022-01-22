import numpy as np

x = np.arange(15).reshape(3, 5)
y = np.random.rand(15).reshape(3, 5)
print(x)
print()
print(y)

# 연산 함수 : add, subtract, multiply, divide
'''
print()
# temp1 = np.add(x, y)
temp1 = x + y
print(temp1)

print()
# temp2 = np.subtract(x, y)
temp2 = x - y
print(temp2)
'''
'''
통계 함수 : 평균, 분산, 중앙, 최대, 최소값 등 통계 관련 함수가 내장
평균 : np.mean(y) or y.mean()
최대 : np.max(y)
최대값의 index : np.argmax(y)
분산 : np.var(y)
표준편차 : np.std(y)
중앙값 : np.median(y)

'''
'''
print()
print(np.mean(x))
print()
print(np.std(x))
'''
'''
집계 함수 : 합계(sum), 누적합계(cumsum)등등 계산 가능
'''
'''
print()
print(np.sum(y))
print(np.cumsum(y))
'''
'''
 - any함수 : 특정 조건을 만족하는 것이 하나라도 있으면 True, 아니면 False
 - all함수 : 모든 원소가 특정 조건을 만족한다면 True, 아니면 False
 - where함수 : 조건에 따라 선별적으로 값을 선택 가능
'''
print()
z = np.random.randn(10)
print(np.where(z > 0, z, 0))


