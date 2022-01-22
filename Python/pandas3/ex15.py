import seaborn as sns
import pandas as pd

titanic = sns.load_dataset('titanic')

pd.set_option('display.max_columns', 15)

# 함께 탑승한 형제 또는 배우자의 수가 3, 4, 5명인 승객만 따로 추출

mask1 = titanic['sibsp'] == 3
mask2 = titanic['sibsp'] == 4
mask3 = titanic['sibsp'] == 5
df_boolean = titanic[mask1 | mask2 | mask3]

isin_filter = titanic['sibsp'].isin([3, 4, 5])
# print(isin_filter)
# print()
df_boolean = titanic[isin_filter]
print(df_boolean.head())
