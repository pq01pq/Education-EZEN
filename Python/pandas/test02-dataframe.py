import pandas as pd

'''
dict_data = {'c0': [1, 2, 3], 'c1': [4, 5, 6], 'c2': [7, 8, 9]}

df = pd.DataFrame(dict_data)
print(dict_data)
print(df)
print(type(df))
'''

list_data = [[15, '남', '노원중'], [14, '여', '상계중']]
df = pd.DataFrame(list_data, index=['철수', '영희'], columns=['나이', '성별', '학교'])
print(df)
print(df.index)
print(df.columns)
# print()
# df.index = ['학생1', '학생2']
# print(df)
# print()
# df.columns = ['연령', '남녀', '소속']
# print(df)

print()
df.rename(columns={'나이': '연령', '성별': '남녀', '학교': '소속'}, inplace=True)
print(df)

print()
df.rename(index={'철수': '학생1', '영희': '학생2'}, inplace=True)
print(df)

