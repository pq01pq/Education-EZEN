import seaborn as sns
import pandas as pd

titanic = sns.load_dataset('titanic')
df = pd.DataFrame(titanic.loc[:, ['age', 'sex', 'class', 'fare', 'survived']])

grouped = df.groupby(['class'])
'''
# 각 그룹에 대한 모든 열의 표준편차를 집계하여 데이터프레임으로 나타내기
std_all = grouped.std()
print(std_all)
print(type(std_all))

# 각 그룹에 대한 fare열의 표준편차를 집계하여 시리즈로 나타내기
std_fare = grouped.fare.std()
print(std_fare)
print(type(std_fare))
'''

# 집계연산을 처리하는 사용자 정의 함수를 그룹객체에 정의하기 : agg() 메소드 사용


def min_max(x):
    x = pd.DataFrame(x)
    return x.max() - x.min()


agg_minmax = grouped.agg(min_max)
print(agg_minmax)
print()

agg_min = grouped.agg([min, max])
print(agg_min)
print()

agg_sep = grouped.agg({'fare': ['min', 'max'], 'age': 'mean'})
print(agg_sep)


