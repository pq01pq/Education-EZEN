import seaborn as sns
import pandas as pd

titanic = sns.load_dataset('titanic')
df = titanic.loc[:, ['age', 'fare']]
print(df.head(10))
print()


def missing_value(x):
    return pd.DataFrame(x).isnull()


def missing_count(x):
    return missing_value(x).sum()


def total_number_missing(x):
    return missing_count(x).sum()


'''
result_df = df.pipe(missing_value)
print(result_df.head(10))
'''
'''
result_df = df.pipe(missing_count)
print(result_df.head())
print(type(result_df))
'''

result_value = df.pipe(total_number_missing)
print(result_value)
print(type(result_value))
