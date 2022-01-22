import numpy as np

'''
***axis(축) 이해 및 axis를 파라미터로 갖는 함수 활용하기
axis 이해하기
 - 몇몇 함수에는 axis keyword 파라미터가 존재
 - axis값이 없는 경우에는 전체 데이터에 대해 적용
 - axis값이 있는 경우에는, 해당 axis를 따라서 연산 적용

* axis를 파라미터로 갖는 함수를 이용하기
- 거의 대부분의 연산 함수들이 axis 파라미터를 사용
- 이 경우, 해당 값이 주어졌을 때, 해당 axis를 따라서 연산이 적용
- 따라서 결과는 해당 axis가 제외된 나머지 차원의 데이터만 남게 됨
- 예) np.sum, np.mean, np.any 등등
'''

'''
x = np.arange(15)
print(x)
print()
print(np.sum(x, axis=0))
'''
'''
x = np.arange(15).reshape(3, 5)
print(x)
print()
print(np.sum(x, axis=0))    # 같은열의 값들을 모두 더함
print(np.sum(x, axis=1))    # 같은행의 값들을 모두 더함
'''
x = np.arange(36).reshape(3, 4, 3)
print(x)
print()
print(np.sum(x, axis=0))
print()
print(np.sum(x, axis=1))
print()
print(np.sum(x, axis=2))
print()
print(np.sum(x, axis=-3))
print()
print(np.sum(x, axis=(0, 2)))

