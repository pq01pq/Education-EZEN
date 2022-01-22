import seaborn as sns
import matplotlib.pyplot as plt
from matplotlib import font_manager, rc

titanic = sns.load_dataset('titanic')

print(titanic.info())

sns.set_style('darkgrid')

fig = plt.figure(figsize=(7, 7))
ax1 = fig.add_subplot(2, 2, 1)
ax2 = fig.add_subplot(2, 2, 2)
ax3 = fig.add_subplot(2, 2, 3)
# 회귀직선
'''
sns.regplot(x='age', y='fare', data=titanic, ax=ax1)
sns.regplot(x='age', y='fare', data=titanic, ax=ax2, fit_reg=False)
'''
# 히스토그램
'''
sns.distplot(titanic['fare'], ax=ax1)
sns.distplot(titanic['fare'], ax=ax2, hist=False)
sns.distplot(titanic['fare'], ax=ax3, kde=False)
'''
# 히트맵
'''
table = titanic.pivot_table(index=['sex'], columns=['class'], aggfunc='size')
sns.heatmap(table,                  # 데이터프레임
            annot=True, fmt='d',    # 데이터 값 표시 유무, 정수형 포맷
            cmap ='YlGnBu',         # 컬러 맵
            linewidth=.5,           # 구분선
            cbar=False              # 컬러바 표시 여부
)
'''

plt.show()
