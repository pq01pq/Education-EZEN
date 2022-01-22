import numpy as np

'''
1. ravel 함수
    - 다차원 배열을 1차원으로 변경
    - 내부데이터는 그대로 유지한채로 형태만 바꿔줌
    - 'order'파라미터 : default는 C
        - 'C' : row 우선 변경
        - 'F' : column 우선 변경
'''

x = np.arange(15).reshape(3, 5)

print(x)
print()
'''
print(x.ravel())
print()
print(x.ravel(order='F'))
print()
'''
'''
y = x.ravel()
print(y)
x[0, 0] = 100
print(x)
print(y)
'''

'''
2. flatten() 함수
    - 다차원 배열을 1차원으로 변경
    - 내부데이터를 복사하여 생성
    - ravel과 차이점 : ravel 함수로 바꾼 값은 데이터를 변경하면
                        원본도 변화가 있는데, flatten 함수를 이용하면 원본과 따로 관리됨
    - 'order'파라미터 : default는 C
        - 'C' : row 우선 변경
        - 'F' : column 우선 변경
'''
print()
y = x.flatten(order='F')
print(y)
x[0, 0] = 100
print(x)
print(y)
'''
3. reshape() 함수
    - array의 shape를 다른 차원으로 변경
    - 주의할 점은 reshape한 후의 결과의 갯수는 이전과 같아야 함
    - 이미지 데이터 벡터화 - 이미지는 기본적으로 2차원, 3차원이나
      트레이닝을 위해 1차원으로 변경하여 사용됨
'''
print()
'''
x = np.arange(36)
print(x)
print(x.shape)
print(x.ndim)
'''
'''
x = np.arange(36).reshape(6, -1)
print(x)
print(x.shape)
print(x.ndim)
'''
x = np.arange(36).reshape(3, 3, 4)
print(x)
print(x.shape)
print(x.ndim)
