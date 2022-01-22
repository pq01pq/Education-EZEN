import pandas as pd

exam_data = {
    '수학': [90, 80, 70],
    '영어': [98, 89, 95],
    '음악': [85, 95, 100],
    '체육': [100, 90, 90]
}
print(exam_data)

df = pd.DataFrame(exam_data, index=['철수', '영희', '수미'])
print(df)

'''
print()
df2 = df[:]
print(df2)
print()
df2.drop('수미', inplace=True)
print(df2)

print()
df2.drop(['철수', '영희'], axis=0, inplace=True)
print(df2)

print()
df3 = df[:]
df3.drop('수학', axis=1, inplace=True)
print(df3)

# axis 0 : 행, axis 1 : 열

print()
df3.drop(['영어', '체육'], axis=1, inplace=True)
print(df3)

print()
label = df.loc['철수']
print(label)
label = df.iloc[0]
print(label)
print()
label = df.loc[['철수', '수미']]
print(label)

label = df.iloc[[0, 2]]
print(label)
print()
label = df.loc['철수':'수미']
print(label)
print()
label = df.iloc[0:2]
print(label)
'''

print()
math = df['수학']
print(math)
print(type(math))

print()
eng = df.영어
print(eng)
print(type(eng))

print()
mat_eng = df[['수학', '영어']]
print(mat_eng)
print(type(mat_eng))

print()
mat = df[['수학']]
print(mat)
print(type(mat))
