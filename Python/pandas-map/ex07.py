import pandas as pd

df = pd.DataFrame({'c1': ['a', 'a', 'b', 'a', 'b'],
                  'c2': [1, 1, 1, 2, 2],
                  'c3': [1, 1, 2, 2, 2]})

print(df)
'''
# duplicated() 메소드 : 중복된 행 검사
df_dup = df.duplicated()
print(df_dup)

col_dup = df['c2'].duplicated()
print(col_dup)
'''
# drop_duplicates() 메소드 : 중복데이터 삭제
df2 = df.drop_duplicates()
print(df2)

df3 = df.drop_duplicates(subset=('c2', 'c3'))
print(df3)
