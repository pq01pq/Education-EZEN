import pandas as pd

df1 = pd.DataFrame({'a': ['a0', 'a1', 'a2', 'a3'],
                    'b': ['b0', 'b1', 'b2', 'b3'],
                    'c': ['c0', 'c1', 'c2', 'c3']},
                   index=[0, 1, 2, 3])

df2 = pd.DataFrame({'a': ['a2', 'a3', 'a4', 'a5'],
                    'b': ['b2', 'b3', 'b4', 'b5'],
                    'c': ['c2', 'c3', 'c4', 'c5'],
                    'd': ['d2', 'd3', 'd4', 'd5']},
                   index=[2, 3, 4, 5])

#데이터 프레임 합치기
# 2개의 데이터프레임을 위 아래 행방향으로 이어붙이듯 연결(axis=0)
result1 = pd.concat([df1, df2])
print(result1)
print()
# ignore_index=True 옵션 : 기존index무시, 새로 index를 설정
result2 = pd.concat([df1, df2], ignore_index=True)
print(result2)
print()
# axis=1 옵션
result3 = pd.concat([df1, df2], axis=1)
print(result3)
print()
# join='inner' 옵션 : 교집합
result4 = pd.concat([df1, df2], axis=1, join='inner')
print(result4)

'''
sr1 = pd.Series(['e0', 'e1', 'e2', 'e3'], name='e')
sr2 = pd.Series(['f0', 'f1', 'f2'], name='f', index=[3, 4, 5])
sr3 = pd.Series(['g0', 'g1', 'g2', 'g3'], name='g')

print(sr1)
print(sr2)
print(sr3)

result5 = pd.concat([df1, sr1], axis=1)
print(result5)
result6 = pd.concat([df2, sr2], axis=1)
print(result6)

result7 = pd.concat([sr1, sr3], axis=0)
print(result7)
'''
