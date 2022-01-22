import seaborn as sns
import pandas as pd

pd.set_option('display.max_columns', 10)                  # 출력할 최대 열의 개수
pd.set_option('display.max_colwidth', 20)                 # 출력할 열의 너비
pd.set_option('display.unicode.east_asian_width', True)   # 유니코드 사용 너비 조정

titanic = sns.load_dataset('titanic')
df = pd.DataFrame(titanic.loc[:, ['age', 'sex', 'class', 'fare', 'survived']])
print(df.head())

pdf1 = pd.pivot_table(df,   # 피벗할 데이터프레임
                      index='class',    # 행 위치에 들어갈 열
                      columns='sex',    # 열 위치에 들어갈 열
                      values='age',     # 데이터로 사용할 열
                      aggfunc=pd.DataFrame.mean)    # 데이터 집계 함수
print(pdf1.head())
print()

pdf2 = pd.pivot_table(df,   # 피벗할 데이터프레임
                      index=['class', 'sex'],   # 행 위치에 들어갈 열
                      columns='survived',    # 열 위치에 들어갈 열
                      values=['age', 'fare'],     # 데이터로 사용할 열
                      aggfunc=[pd.DataFrame.mean, max])    # 데이터 집계 함수
print(pdf2.head())
print(pdf2.index)
print(pdf2.columns)
'''
# 행 인덱스가 First인 행을 출력
print(pdf2.xs('First'))
print()

# 행 인덱스가 First이면서 female인 행을 선택
print(pdf2.xs(('First', 'female')))
print()

# 행 인덱스의 sex 레벨이 male인 행을 선택
print(pdf2.xs('male', level='sex'))
'''

# 열 선택
print(pdf2.xs('mean', axis=1))
print(pdf2.xs(('mean', 'age'), axis=1))

print(pdf2.xs(1, level='survived', axis=1))

