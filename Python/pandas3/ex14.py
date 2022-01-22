import seaborn as sns

titanic = sns.load_dataset('titanic')

'''
#나이가 10대인 승객만 따로 선택
mask1 = (titanic.age >= 10) & (titanic.age < 20)
print(mask1.head(10))
df_teenage = titanic.loc[mask1, :]
#print(df_teenage)
'''

# 나이가 10세미만이고 여성인 승객만 따로 선택
mask2 = (titanic.age < 10) & (titanic.sex == 'female')
df_female_under10 = titanic.loc[mask2, ['age', 'sex', 'alive']]
print(df_female_under10)
