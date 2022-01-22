import numpy as np


x = np.random.uniform(1.0, 3.0, size=(4, 5))
print(x)

'''
np.random.seed(100)
x = np.random.randn(3, 4)
print(x)
y = np.random.normal(size=(3, 4))
print(y)
'''
'''
# choice 함수 : 주어진 1차원 ndarray로부터 랜덤으로 샘플링
x = np.array([1, 2, 3, 4, 5, 6, 7, 8])
y = np.random.choice(x, size=(2, 2))
print(x)
print(y)
z = np.random.choice(100, size=(3, 4), replace=False)   # replace : 중복허용
# ndarray타입이 아닌 정수값이 주어지면 arange(정수값)함수와 동일
print(z)
'''
# randn 함수 : n(normal distribution;정규분포)
# seed : 랜덤값을 동일하게 다시 생성하여 사용하고자 할 때(고정된 랜덤값)
'''
np.random.seed(1001)
x = np.random.randn(3, 4)
print(x)
'''
'''
x = np.random.randint(1, 100, size=(3, 5))
print(x)
'''

# rand 함수
'''
x = np.random.rand(2, 3)
print(x)
'''