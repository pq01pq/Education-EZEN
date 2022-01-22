import seaborn as sns

'''
데이터프레임 원소에 함수 맵핑
DataFrame 객체.applymap(맵핑함수)
데이터프레임의 개별 원소에 특정 함수를 맵핑하려면 applymap() 메소드를 활용
맵핑함수에 데이터프레임의 각 원소를 하나씩 넣어서 리턴값으로 돌려받음
원소의 원래 위치에 맵핑함수의 리턴값을 입력하여 동일한 데이터 프레임이 만들어짐
'''
titanic = sns.load_dataset('titanic')
df = titanic.loc[:, ['age', 'fare']]
print(df.head(10))


def add_10(n):
    return n + 10


df_map = df.applymap(add_10)
print(df_map.head(10))
