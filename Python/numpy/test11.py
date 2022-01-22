import numpy as np

# linalg 서브모듈 함수 활용하여 선형대수 연산하기
# np.linalg.inv

# 역행렬 : 두 행렬을 곱하여 단위행렬이 되는 두 정사각행렬

x = np.random.rand(3, 3, 3)
print(x)
print()
y = np.linalg.inv(x)
print(y)
print()

print(x @ y)
print()
print(np.matmul(x, y))

# x : 홍학, y = 호랑이
# x + y = 25
# 2x + 4y = 64 -> x + 2y = 32
animal = np.array([
    [1, 1],
    [2, 4]
])

animalInv = np.linalg.inv(animal)
animalCount = np.array([25, 64])
ans = np.matmul(animalInv, animalCount)
print(ans)

A = np.array([[1, 1], [2, 4]])
B = np.array([25, 64])
x = np.linalg.solve(A, B)
print(x)
print(np.allclose(A@x, B))