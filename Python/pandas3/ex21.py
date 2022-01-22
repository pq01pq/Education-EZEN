import seaborn as sns
import pandas as pd

titanic = sns.load_dataset('titanic')
df = pd.DataFrame(titanic.loc[:, ['age', 'sex', 'class', 'fare', 'survived']])
# print(len(df))

grouped = df.groupby(['class'])

# for key, group in grouped:
#     print('* key :', key)
#     print('* number :', len(group))
#     print(group.head())
# print()

# filter() : 조건식을 가진 함수를 전달하면 조건이 참인 그룹만 남음
# 데이터의 갯수가 200개 이상인 그룹만 필터링하여 데이터프레임으로 나타내기
'''
group_filter = grouped.filter(lambda x: len(x) >= 200)
print(group_filter)
print(type(group_filter))
'''

# age열의 평균이 30보다 작은 그룹만을 필터링하여 데이터프레임으로 나타내기


# def min(x):
group_filter = grouped.filter(lambda x: x.age.mean() < 30)
print(group_filter.tail(10))
print(type(group_filter))
