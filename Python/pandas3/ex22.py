import seaborn as sns
import pandas as pd

titanic = sns.load_dataset('titanic')
df = pd.DataFrame(titanic.loc[:, ['age', 'sex', 'class', 'fare', 'survived']])
# print(len(df))
'''
grouped = df.groupby(['class'])

# 그룹 객체에 함수를 맵핑 : apply()
# 집계 : 각 그룹의 요약 통계 정보를 집계
agg_grouped = grouped.apply(lambda x: x.describe())
print(agg_grouped)
print()


def z_score(x):
    x = pd.Series(x)
    return (x - x.mean()) / x.std()


age_z = grouped.age.apply(z_score)
print(age_z.head(10))
'''
grouped = df.groupby(['class', 'sex'])
gdf = grouped.mean()
print(gdf)
print()
print(gdf.loc['First'])
print()
print(gdf.loc[('First', 'female')])
print(gdf.xs('male', level='sex'))

