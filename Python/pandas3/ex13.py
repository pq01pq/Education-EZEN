import seaborn as sns
import pandas as pd

'''
열 순서 변경
DataFrame 객체[재구성한 열 이름의 리스트]
'''

titanic = sns.load_dataset('titanic')
df = titanic.loc[0:4, 'survived':'age']
print(df)
print()
'''
columns = list(df.columns.values)
print(columns)

columns_sort = sorted(columns)
print(columns_sort)

df_sorted = df[columns_sort]
print(df_sorted)

columns_reverse = list(reversed(columns_sort))
df_reverse = df[columns_reverse]
print(df_reverse)
'''

columns_customed = ['pclass', 'sex', 'age', 'survived']
df_customed = df[columns_customed]
print(df_customed)

