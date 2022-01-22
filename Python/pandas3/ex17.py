import pandas as pd

# IPyhton 디스플레이 설정 변경
pd.set_option('display.max_columns', 10)                  # 출력할 최대 열의 개수
pd.set_option('display.max_colwidth', 20)                 # 출력할 열의 너비
pd.set_option('display.unicode.east_asian_width', True)   # 유니코드 사용 너비 조정

df1 = pd.DataFrame(pd.read_excel("./stock price.xlsx", index_col='id'))
df2 = pd.DataFrame(pd.read_excel("./stock valuation.xlsx", index_col='id'))
print(df1)
print()
print(df2)
print()

# 데이터프레임 병합
# pandas.merge(df_left, df_right, how='inner', on=None)
'''
# 교집합
merge_inner = pd.merge(df1, df2)
print(merge_inner)
print()
# 합집합
merge_outer = pd.merge(df1, df2, how='outer', on='id')
print(merge_outer)
print()
# 왼쪽 프레임 기준, 키값 분리
merge_left = pd.merge(df1, df2, how='left', left_on='stock_name', right_on='name')
print(merge_left)
print()
# 오른쪽 프레임 기준, 키값 분리
merge_right = pd.merge(df1, df2, how='right',
                       left_on='stock_name', right_on='name')
print(merge_right)
print()

price = df1[df1['price'] < 50000]
print(price)
print()
value = pd.merge(price, df2)
print(value)
'''

# 데이터프레임 결합 : join()
# 행 인덱스를 기준으로 결합
# on 옵션의 기준이 기본 행임
df3 = df1.join(df2)
print(df3)
print()
df4 = df1.join(df2, how='inner')
print(df4)
