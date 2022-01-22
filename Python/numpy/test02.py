import numpy as np


'''
v1 = np.logspace(0, 2, 4)
print(v1)
print(type(v1))
'''
'''
v1 = np.linspace(1, 10, 3)  # [ 1.   5.5 10. ]
print(v1)
print(type(v1))
'''
'''
x = np.eye(5)   # E
print(x)
print(type(x))
'''
'''
x = np.full((3, 4), 7)
print(x)
print(type(x))
'''
'''
x = np.empty((3, 4))
print(x)
print(type(x))
'''
'''
x = np.zeros((5, 5))
print(x)
print(type(x))
'''
'''
x = np.ones((5, 5))
print(x)
print(type(x))
'''
'''
x = np.arange(10).reshape(2, 5)
print(x)
'''
'''
x = np.array([1, 2, 3, 4])
print(x)
print(type(x))

y = np.array([[1, 2, 3], [4, 5, 6]])
print(y)
print(type(y))
'''
z = np.array(
    [
        [
            [1, 2, 3],
            [4, 5, 6]
        ],
        [
            [7, 8, 9],
            [10, 11, 12]
        ]
    ]
)
'''
z = np.arange(36).reshape(3, 4, 3)
print(z)
print(type(z))
'''