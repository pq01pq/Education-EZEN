import pandas as pd


'''
# k,v 구조를 가지는 딕셔너리를 만들고, 변수 dict_data에 저장
dict_data = {'a': 1, 'b': 2, 'c': 3}

# 판다스 Series() 함수로 딕셔너리를 변환해서 sr에 저장
sr = pd.Series(dict_data)
print(sr)
print(type(sr))
'''
'''
list_data = ['2010-05-07', 3.14, '홍길동', 100, True]
sr = pd.Series(list_data)
print(sr)

# 인덱스 배열만 변수 idx에 저장할 수 있음
idx = sr.index
print(idx)

# 데이터 값만 변수 val에 저장할 수 있음
val = sr.values
print(val)
'''

tu_data = ('홍길동', '2020-05-07', '남', True)
sr = pd.Series(tu_data, index=['이름', '생일', '성별', '학생'])
print(sr)
print()
print(sr['이름'])
print(sr[0])
print()
print(sr[['이름', '성별']])
print(sr[[0, 2]])
print(sr[0:2])  # 이상, 미만
print(sr['이름':'성별'])    # 이상, 이하
