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
south = pd.DataFrame(df.drop('북한', axis=0))
print(south)

print()
south.reset_index(inplace=True)
south.drop('국가', axis=1, inplace=True)
south.set_index('전력별', inplace=True)
# south.replace({'-': 0}, inplace=True)
print(south)

# 여러개의 그래프를 한번에 나타내기
# 서울 -> 경기, 경기 -> 서울
col_years = list(map(str, range(1990, 2017)))

df_4 = south.loc[['수력', '화력', '원자력', '신재생'], col_years]
df_4.iloc[3] = df_4.iloc[3].apply(a)
print(df_4)

# 스타일 지정
plt.style.use('ggplot')

fig = plt.figure(figsize=(15, 5))
ax = fig.add_subplot(1, 1, 1)  # 행의 갯수, 열의 갯수, 위치

ax.plot(col_years, df_4.loc['수력', :],
        marker='o', markerfacecolor='blue',
        markersize=10, color='olive',
        linewidth=2, label='수력')

ax.plot(col_years, df_4.loc['화력', :],
        marker='o', markerfacecolor='red',
        markersize=10, color='olive',
        linewidth=2, label='화력')

ax.plot(col_years, df_4.loc['원자력', :],
        marker='o', markerfacecolor='yellow',
        markersize=10, color='olive',
        linewidth=2, label='원자력')

ax.plot(col_years, df_4.loc['신재생', :],
        marker='o', markerfacecolor='green',
        markersize=10, color='olive',
        linewidth=2, label='신재생')

ax.legend(loc='best')
ax.set_title('대한민국 종목별 발전량', size=20)
ax.set_xlabel('년도', size=12)
ax.set_ylabel('발전량 (억kWh)', size=12)

ax.set_xticklabels(col_years, rotation=90)

plt.show()

