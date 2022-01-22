import pandas as pd

df = pd.read_csv('./auto-mpg.csv', header=None)
df.columns = ['mpg', 'cylinders', 'displacement', 'horsepower', 'weight',
              'acceleration', 'model year', 'origin', 'name']
print(df.head(n=10))
print(df.tail(n=10))
print()
print(df.shape)
print()
print(df.info())
print()
print(df.dtypes)
print()
print(df.mpg.dtypes)
print(df['mpg'].dtypes)

# 기술정보 통계 - 산술데이터를 갖는 열에 대한 카운트, 평균, 최댓값, 등등을 요약 출력
print()
print(df.describe())
print()
print(df.describe(include='all'))

print()
df_co = df.count()
print(df_co)
print()
print(df_co.mpg)
print()
print(type(df_co))

print()
mpg_co = df.mpg.count()
print(mpg_co)

print()
unique_values = df['origin'].value_counts()
print(unique_values)
print()
print(type(unique_values))

# 평균 꺼내기
print()
print(df.mean())
print()
mpg_mean = df['mpg'].mean()
print(mpg_mean)
print(type(mpg_mean))
print()
means = df[['mpg', 'weight']].mean()
print(means)
print(type(means))

# 중간값
print()
print(df.median())

# 최댓값
print()
print('mpg max ', df['mpg'].max())

# 최솟값
print()
print('mpg max ', df['mpg'].min())

# 표준편차
print()
print(df.std())
print()
print(df['mpg'].std())

# 상관계수
print()
print(df.corr())
print(df[['mpg', 'weight']].corr())


