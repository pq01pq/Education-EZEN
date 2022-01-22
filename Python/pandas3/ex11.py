import seaborn as sns
import pandas as pd
'''
데이터프레임의 각 열에 함수 맵핑
DataFrame 객체.apply(맵핑함수, axis=0
'''
titanic = sns.load_dataset('titanic')
df = titanic.loc[:, ['age', 'fare']]
print(df.head(10))
print()

'''
def missing_value(series):
    return pd.Series(series).isnull()


result = df.apply(missing_value, axis=0)
print(result.head(10))
'''

'''
def min_max(x):
    x = pd.Series(x)
    return x.max() - x.min()


result = df.apply(min_max)
print(result.head(10))
print(type(result))
'''


def add_two_obj(a, b):
    return a + b


df['ten'] = 10
df['add'] = df.apply(lambda x: add_two_obj(x['age'], x['ten']), axis=1)
print(df.head(10))
