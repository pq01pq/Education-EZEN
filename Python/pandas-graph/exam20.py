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
north = pd.DataFrame(df.loc['북한'])
print(south)
print(north)
print()

south.reset_index(inplace=True)
south.drop('국가', axis=1, inplace=True)
south.set_index('전력별', inplace=True)
south.replace({'-': 0}, inplace=True)

north.reset_index(inplace=True)
north.drop('국가', axis=1, inplace=True)
north.set_index('전력별', inplace=True)
north.replace({'-': 0}, inplace=True)
print(south)
print(north)
print()

# 여러개의 그래프를 한번에 나타내기
col_years = list(map(str, range(1990, 2017)))
south = south.loc[['합계', '수력', '화력', '원자력', '신재생'], col_years]
north = north.loc[['합계', '수력', '화력', '원자력'], col_years]
print(south)
print(north)
print()

# 스타일 지정
plt.style.use('ggplot')

fig = plt.figure(figsize=(15, 5))
ax1 = fig.add_subplot(2, 1, 1)  # 행의 갯수, 열의 갯수, 위치
ax2 = fig.add_subplot(2, 1, 2)

ax1.plot(col_years, south.loc['수력', :],
        marker='o', markerfacecolor='blue',
        markersize=10, color='olive',
        linewidth=2, label='수력')

ax1.plot(col_years, south.loc['화력', :],
        marker='o', markerfacecolor='red',
        markersize=10, color='olive',
        linewidth=2, label='화력')

ax1.plot(col_years, south.loc['원자력', :],
        marker='o', markerfacecolor='yellow',
        markersize=10, color='olive',
        linewidth=2, label='원자력')

ax1.plot(col_years, south.loc['신재생', :],
        marker='o', markerfacecolor='green',
        markersize=10, color='olive',
        linewidth=2, label='신재생')

ax1.legend(loc='best')
ax1.set_title('대한민국 종목별 발전량', size=20)
ax1.set_xlabel('년도', size=12)
ax1.set_ylabel('발전량 (억kWh)', size=12)

ax1.set_xticklabels(col_years, rotation=60)

ax2.plot(col_years, north.loc['수력', :],
        marker='o', markerfacecolor='blue',
        markersize=10, color='olive',
        linewidth=2, label='수력')

ax2.plot(col_years, north.loc['화력', :],
        marker='o', markerfacecolor='red',
        markersize=10, color='olive',
        linewidth=2, label='화력')

ax2.plot(col_years, north.loc['원자력', :],
        marker='o', markerfacecolor='yellow',
        markersize=10, color='olive',
        linewidth=2, label='원자력')

ax2.legend(loc='best')
ax2.set_title('북조선족공산주의인민공산국 종목별 발전량', size=20)
ax2.set_xlabel('년도', size=12)
ax2.set_ylabel('발전량 (억kWh)', size=12)

ax2.set_xticklabels(col_years, rotation=60)

plt.show()

