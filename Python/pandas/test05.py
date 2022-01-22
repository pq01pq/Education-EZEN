import pandas as pd

dict_data = {'c0': [1, 2, 3], 'c1': [4, 5, 6], 'c2': [7, 8, 9], 'c3': [10, 11, 12], 'c4': [13, 14, 15]}

# 딕셔너리 데이터 프레임으로 변환하기, index를 지정
df = pd.DataFrame(dict_data, index=['r0', 'r1', 'r2'])
print(df)

print()
# 인덱스를 [r0, r1, r2, r3, r4]로 지정
new_index = ['r0', 'r1', 'r2', 'r3', 'r4']
ndf = df.reindex(new_index, fill_value=0)  # Default >> 지정 안할 시에 NaN
print(ndf)

print()
# ndf = df.reset_index()
# ndf = df.sort_index(ascending=False)
ndf = df.sort_values(by='c2', ascending=False)
print(ndf)
