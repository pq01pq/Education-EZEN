import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('./auto-mpg.csv', header=None)

df.columns = ['mpg', 'cylinders', 'displacement', 'horsepower', 'weight',
              'acceleration', 'model year', 'origin', 'name']

'''
히스토그램 : 변수가 하나인 단변수 데이터의 빈도수를 그래프로 표현
x축을 같은 크기의 여러 구간으로 나누고, 각 구간에 속하는 데이터 값의 개수를 y축에 표시
구간을 나누는 간격의 크기에 따라 빈도가 달라지고 히스토그램의 모양이 변한다
'''

df['horsepower'].plot(kind='hist', bins=20, color='coral', figsize=(10, 5))
plt.show()
