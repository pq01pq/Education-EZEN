import numpy as np
'''
#### 브로드캐스팅
- Shape이 같은 두 ndarray에 대한 연산은 각 원소별로 진행
- 연산되는 두 ndarray가 다른 Shape을 갖는 경우 브로드 캐스팅(Shape을 맞춤) 후 진행
https://numpy.org/doc/stable/user/basics.broadcasting.html#general-broadcasting-rules
- 뒷 차원에서 부터 비교하여 Shape이 같거나, 차원 중 값이 1인 것이 존재하면 가능
- 예) https://www.tutorialspoint.com/numpy/images/array.jpg
'''
'''
x = np.arange(15).reshape(3, 5)
y = np.random.rand(15).reshape(3, 5)
print(x)
print(y)
print(x*y)
'''
'''
#Scalar(상수)와의 연산
x = np.arange(15).reshape(3, 5)
print(x % 2 == 0)
'''
'''
a = np.arange(12).reshape(4, 3)
b = np.arange(100, 103)
c = np.arange(1000, 1004)
d = b.reshape(1, 3)

print(a+d)
print()
print(a)
print()
print(b)
print()
print(c)
print()
print(a+b)
# print(a+c)
# 오류발생!! 뒷차원부터 비교하여 같을때만 연산이 된다
'''
