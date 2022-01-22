import pandas as pd
import numpy as np

df = pd.read_csv('./auto-mpg.csv', header=None)

df.columns = ['mpg', 'cylinders', 'displacement', 'horsepower', 'weight',
              'acceleration', 'model year', 'origin','name']

# print(df.head())

# 단위 환산
'''
mpg_to_kpl = 1.60934 / 3.78541
# print(mpg_to_kpl)
df['kpl'] = (df['mpg'] * mpg_to_kpl).round(2)
print(df)
'''
# 자료형 변환
'''
print(df.info())
print(df['horsepower'].unique())
df['horsepower'].replace('?', np.NaN, inplace=True)
df.dropna(subset=['horsepower'], axis=0, inplace=True)
df['horsepower'] = df['horsepower'].astype(float)
print(df.info())
'''
print(df['origin'].unique())
df['origin'].replace({1: 'USA', 2: 'EU', 3: 'Japan'}, inplace=True)
print(df['origin'].unique())
df['origin'] = df['origin'].astype('category')  # category : 범주형 자료
print(df['origin'].dtypes)
print(df.info())
