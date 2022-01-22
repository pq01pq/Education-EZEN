import pandas as pd
import warnings
import matplotlib.pyplot as plt
from matplotlib import font_manager, rc

font_path = './D2Coding-Ver1.3.2-20180524.ttf'
font_name = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font_name)

df = pd.read_excel('./남북한발전전력량.xlsx')
df = pd.DataFrame(df.fillna(method='ffill'))
df.rename(columns={'전력량 (억㎾h)': '국가', '발전 전력별': '전력별'}, inplace=True)
df.set_index('국가', inplace=True)
print(df)

print()
south = pd.DataFrame(df.loc['남한'])
print(south)

print()
south.reset_index(inplace=True)
south.drop('국가', axis=1, inplace=True)
south.set_index('전력별', inplace=True)
south = south.T
south.replace({'-': 0}, inplace=True)
print(south)

south.rename(columns={'합계': '총발전량'}, inplace=True)
print(south)
south['총발전량 - 1년'] = south['총발전량'].shift(1)
south['증감율'] = ((south['총발전량'] / south['총발전량 - 1년']) - 1) * 100
print(south)
# 여러개의 그래프를 한번에 나타내기

# 스타일 지정
plt.style.use('ggplot')
plt.rcParams['axes.unicode_minus'] = False   # 마이너스 부호 출력 설정

# 2축 그래프 그리기
ax1 = south[['화력', '원자력', '수력', '신재생']].plot(
    kind='bar', figsize=(10, 5), width=0.8, stacked=True, color=['red', 'yellow', 'blue', 'green'])
ax2 = ax1.twinx()
ax2.plot(south.index, south.증감율, ls='--', marker='o', markersize=10,
         color='black', label='전년대비 증감율(%)')

ax1.set_ylim(0, 6000)
ax2.set_ylim(-50, 50)

ax1.set_xlabel('연도', size=20)
ax1.set_ylabel('발전량(억 KWh)')
ax2.set_ylabel('전년 대비 증감율(%)')

plt.title('대한민국 전력 발전량 (1990 ~ 2016)', size=30)
ax1.legend(loc='upper left')
ax2.legend(loc='upper right')

plt.show()

