import pandas as pd
import numpy as np

df = pd.read_csv('./auto-mpg.csv', header=None)

df.columns = ['mpg', 'cylinders', 'displacement', 'horsepower', 'weight',
              'acceleration', 'model year', 'origin','name']

df['horsepower'].replace('?', np.NaN, inplace=True)
df.dropna(subset=['horsepower'], axis=0, inplace=True)
df['horsepower'] = df['horsepower'].astype(float)

# np.histogram 함수 : 해당 열과 값을 주면 그 값으로 경계값의 리스트를 구함
count, bin_dividers = np.histogram(df['horsepower'], bins=3)
print(bin_dividers)

bin_names = ['저출력', '중간출력', '고출력']
# pd.cut 함수 : 각 데이터를 3개의 bin에 할당
df['hp_bin'] = pd.cut(x=df['horsepower'], bins=bin_dividers,
                      labels=bin_names, include_lowest=True)
print(df[['horsepower', 'hp_bin']].head(15))

# pd.get_dummies()
horsepower_dummies = pd.get_dummies(df['hp_bin'])
print(horsepower_dummies.head(15))
