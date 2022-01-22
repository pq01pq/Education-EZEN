import seaborn as sns

'''
시리즈 원소에 함수 맵핑
Series객체.apply(매핑 함수)
- 시리즈 객체에 apply()메소드를 적용하면 인자로 전달되는 맵핑함수에 시리즈의 모든
  원소를 하나씩 입력하고 함수의 리턴값을 돌려받는다.
  시리즈 원소의 갯수만큼 리턴값을 받아서 같은 크기의 시리즈 객체로 반환한다
'''
titanic = sns.load_dataset('titanic')
df = titanic.loc[:, ['age', 'fare']]
print(df.head(10))
print()
df['ten'] = 10


def add_10(n):
    return n + 10


def add_two_obj(a, b):
    return a + b


'''
sr1 = df['age'].apply(add_10)
print(sr1.head(10))
print(type(sr1))
'''
'''
sr2 = df['age'].apply(add_two_obj, b=10)
print(sr2.head(10))
print(type(sr2))
'''

sr3 = df['age'].apply(lambda x: add_10(x))
print(sr3.head(10))
print(type(sr3))
