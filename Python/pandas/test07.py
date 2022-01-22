import pandas as pd
import seaborn as sns

titanic = sns.load_dataset('titanic')
# print(titanic)
df = titanic.loc[:, ['age', 'fare']]
# print(df)
addition = df + 10
print(df.head())
print(addition.head())

subtraction = addition - df
print(subtraction.head())