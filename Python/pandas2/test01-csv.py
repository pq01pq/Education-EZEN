import pandas as pd

file_path = './read_csv_sample.csv'

df = pd.read_csv(file_path)
print(df)

print()
df2 = pd.read_csv(file_path, header=None)
print(df2)

print()
df3 = pd.read_csv(file_path, index_col='c2')
print(df3)

print()
df4 = pd.read_csv(file_path, index_col=None)
print(df4)
'''
옵션   설명
path       파일의 위치, URL
sep           텍스트 데이터를 필드별로 구분하는 문자
header       열이름으로 사용될 행의 번호(header없이 첫문장부터 데이터면 None으로 지정)
index_col   행 인덱스로 사용할 열의 번호 또는 열 이름
names        열이름으로 사용할 문자열의 리스트
skiprows    처음 몇줄을 skip할지 설정, 행의 번호를 담은 리스트로도 가능
'''
