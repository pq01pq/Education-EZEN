import seaborn as sns
import pandas as pd

titanic = sns.load_dataset('titanic')
df = pd.DataFrame(titanic.loc[:, ['age', 'sex', 'class', 'fare', 'survived']])
print(df.head(10))

'''
그룹 연산
특정 기준을 적용하여 몇 개의 그룹으로 분할하여 처리하는 것 : 그룹 연산
1단계 - 분할(split) : 데이터를 특정 조건에 의하여 분할
2단계 - 적용(apply) : 데이터를 집계, 변환, 필터링하는데 필요한 메소드 적용
3단계 - 결합(combine) : 2단계의 처리 결과를 하나로 결합
'''
'''
print('승객수 : ', len(df))

grouped = df.groupby(['class'])
print(grouped)
print()

for key, group in grouped:
    print('* key :', key)
    print('* number :', len(group))
    print(group.head())
print()

average = grouped.mean()
print(average)
print()

group3 = grouped.get_group('Third')
print(group3.head())
'''

grouped = df.groupby(['class', 'sex'])
for key, group in grouped:
    print('* key :', key)
    print('* number :', len(group))
    print(group.head())
print()

average = grouped.mean()
print(average)
print()

group3 = grouped.get_group(('Third', 'male'))
print(group3.head())
