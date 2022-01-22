import seaborn as sns
import pandas as pd

titanic = sns.load_dataset('titanic')
df = pd.DataFrame(titanic.loc[:, ['age', 'sex', 'class', 'fare', 'survived']])

grouped = df.groupby(['class'])

# 정규화 표준편차 z
'''
age_mean = grouped.age.mean()
print(age_mean)
print()

age_std = grouped.age.std()
print(age_std)
print()

for key, group in grouped.age:
    group_z = (group - age_mean.loc[key]) / age_std.loc[key]
    print('- key:', key)
    # print('- group:', group)
    # print(group)
    print(group_z)
print()
'''


def z_score(x):
    x = pd.Series(x)
    return (x - x.mean()) / x.std()


age_z = grouped.age.transform(z_score)
print(age_z)
print(age_z.loc[[1, 9, 0]])
print(len(age_z))
print(age_z.loc[0:9])
print(type(age_z))
