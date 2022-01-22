import seaborn as sns

df = sns.load_dataset('titanic')
#print(len(df))

print(df['age'].head(10))

#mean_age = df['age'].mean(axis=0)
median_age = df['age'].median(axis=0)

#print(mean_age)
#df['age'].fillna(mean_age, inplace=True)
df['age'].fillna(median_age, inplace=True)
print(df['age'].head(10))

#NaN값이 500개 이상인 열을 삭제
#df_thresh = df.dropna(axis=1, thresh=500)
#print(df_thresh.columns)
#age열에 나이 데이터가 없는 모든 행을 삭제
#df_age = df.dropna(subset=['age'], how='any', axis=0)
#print(len(df_age))


#nan_deck = df['deck'].value_counts(dropna=False)
#print(nan_deck)

#print(df.head())
#print(df.head().notnull())
#print(df.isnull().sum(axis=0))

'''
print(df.head())
print(df.info())
'''
