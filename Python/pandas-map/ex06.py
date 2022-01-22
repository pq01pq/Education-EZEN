import seaborn as sns
import pandas as pd

# df = pd.DataFrame(sns.load_dataset('titanic'))
# print(df[])
# towns = pd.DataFrame(df.loc[:, 'embark_town'])
# towns['count'] = 1
# # print(towns)
# town_count = towns.groupby('embark_town').sum()
# town_count.reset_index(inplace=True)
# # print(town_count)
# # print(town_count.max())
# max_town = town_count.max()['embark_town']
# print(max_town)
#
# df['embark_town'].fillna(max_town, inplace=True)
# print(df)

df = sns.load_dataset('titanic')

#829줄에 NaN 데이터가 있습니다
print(df['embark_town'][825:833])
'''
# 제일 많이 출현한 값을 추출합니다.
most_freq = df['embark_town'].value_counts(dropna=True).idxmax()
print(most_freq)

# embark_town 열의 NaN값을 승선도시 중에서 가장 많이 출현한 값으로 치환하기
df['embark_town'].fillna(most_freq, inplace=True)

# embark_town 열 829행의 NaN 데이터 출력 (NaN 값이 most_freq 값으로 대체)
print(df['embark_town'][825:833])
'''
# fillna(method=ffill(직전값), bfill(다음값))
df['embark_town'].fillna(method='bfill', inplace=True)
print(df['embark_town'][825:833])

