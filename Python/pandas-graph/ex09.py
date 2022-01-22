import pandas as pd
import warnings
import matplotlib.pyplot as plt
from matplotlib import font_manager, rc


def a(x):
        if str(x) == '-':
                return 0
        return x


font_path = './D2Coding-Ver1.3.2-20180524.ttf'
font_name = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font_name)

df = pd.read_excel('./남북한발전전력량.xlsx')
df = pd.DataFrame(df.fillna(method='ffill'))
df.rename(columns={'전력량 (억㎾h)': '국가', '발전 전력별': '전력별'}, inplace=True)
df.set_index('국가', inplace=True)
print(df)

print()
north = pd.DataFrame(df.drop('남한', axis=0))
print(north)

print()
north.reset_index(inplace=True)
north.drop('국가', axis=1, inplace=True)
north.set_index('전력별', inplace=True)
north.drop('원자력', axis=0, inplace=True)
north = north.T
# north.reset_index(inplace=True)
# south.replace({'-': 0}, inplace=True)
print(north)

north.rename(columns={'합계': '총발전량'}, inplace=True)
print(north)
north['총발전량 - 1년'] = north['총발전량'].shift(1)
north['증감율'] = ((north['총발전량'] / north['총발전량 - 1년']) - 1) * 100
print(north)
# 여러개의 그래프를 한번에 나타내기

# 스타일 지정
plt.style.use('ggplot')
plt.rcParams['axes.unicode_minus'] = False   # 마이너스 부호 출력 설정

# 2축 그래프 그리기
ax1 = north[['수력', '화력']].plot(kind='bar', figsize=(10, 5), width=0.7, stacked=True)
ax2 = ax1.twinx()
ax2.plot(north.index, north.증감율, ls='--', marker='o', markersize=10,
         color='green', label='전년대비 증감율(%)')

ax1.set_ylim(0, 500)
ax2.set_ylim(-50, 50)

ax1.set_xlabel('연도', size=20)
ax1.set_ylabel('발전량(억 KWh)')
ax2.set_ylabel('전년 대비 증감율(%)')

plt.title('북한 전력 발전량 (1990 ~ 2016)', size=30)
ax1.legend(loc='upper left')

plt.show()

