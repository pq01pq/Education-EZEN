import pandas as pd

exam_data = {
    '이름': ['철수', '영희', '수미'],
    '수학': [90, 80, 70],
    '영어': [98, 89, 95],
    '음악': [85, 95, 100],
    '체육': [100, 90, 90]
}
print(exam_data)
df = pd.DataFrame(exam_data)
'''
df = pd.DataFrame(exam_data)
df.set_index('이름', inplace=True)
print(df)
print()
a = df.loc['영희', '음악']
print('영희의 음악점수:', a)
print()
a = df.iloc[1, 2]
print('영희의 음악점수:', a)
print()
a = df.loc['수미', ['음악', '체육']]
print(a)
print()
a = df.iloc[2, [2, 3]]
print(a)
print()
a = df.loc['수미', '영어':'체육']
print(a)
print()
a = df.iloc[2, 1:]
print(a)
print()
df.iloc[0][2] = 90
df.loc[0, 2] = 90
df.loc['철수', '음악'] = 90
df.loc['철수', '음약'] = 90
print(df)

df.loc['영희', ['영어', '음악']] = 93, 97
'''
'''
# df.transpose()
df.T
print(df)
'''
ndf = df.set_index(['영어', '음악'])
print(ndf)