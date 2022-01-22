import pandas as pd
import warnings
import matplotlib.pyplot as plt
from matplotlib import font_manager, rc

font_path = './D2Coding-Ver1.3.2-20180524.ttf'
font_name = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font_name)

with warnings.catch_warnings(record=True):
    warnings.simplefilter('always')
    df = pd.read_excel('시도별전출입인구수.xlsx', engine='openpyxl', header=0)
df = df.fillna(method='ffill')
# print(df)

# 서울에서 다른 지역으로 이동한 데이터만 추출하여 df_seoul에 저장

df = pd.DataFrame(df)

mask = (df['전출지별'] == '서울특별시') &\
       ((df['전입지별'] == '경기도') | (df['전입지별'] == '강원도') | (df['전입지별'] == '충청북도') | (df['전입지별'] == '충청남도'))

df_seoul_leave = df[mask]
df_seoul_leave.drop('전출지별', axis=1, inplace=True)
df_seoul_leave.rename({'전입지별': '전입지'}, axis=1, inplace=True)
df_seoul_leave.set_index(['전입지'], inplace=True)

print(df_seoul_leave)

# 여러개의 그래프를 한번에 나타내기
# 서울 -> 경기, 충남, 충북, 강원의 인구이동을 4개의 그래프로 나타내
col_years = list(map(str, range(2000, 2018)))

df_4 = df_seoul_leave.loc[['경기도', '강원도', '충청북도', '충청남도'], col_years]

print(df_4)

# 스타일 지정
plt.style.use('ggplot')

fig = plt.figure(figsize=(15, 8))
ax1 = fig.add_subplot(2, 2, 1)  # 행의 갯수, 열의 갯수, 위치
ax2 = fig.add_subplot(2, 2, 2)
ax3 = fig.add_subplot(2, 2, 3)
ax4 = fig.add_subplot(2, 2, 4)

ax1.plot(col_years, df_4.loc['경기도', :],
        marker='o', markerfacecolor='green',
        markersize=5, color='olive',
        linewidth=2, label='서울 -> 경기')

ax2.plot(col_years, df_4.loc['강원도', :],
        marker='o', markerfacecolor='red',
        markersize=5, color='olive',
        linewidth=2, label='서울 -> 강원')

ax3.plot(col_years, df_4.loc['충청북도', :],
        marker='o', markerfacecolor='red',
        markersize=5, color='olive',
        linewidth=2, label='서울 -> 충북')

ax4.plot(col_years, df_4.loc['충청남도', :],
        marker='o', markerfacecolor='red',
        markersize=5, color='olive',
        linewidth=2, label='서울 -> 충남')

ax1.legend(loc='best')
ax1.set_title('서울 -> 경기', size=20)
ax1.set_xlabel('기간', size=12)
ax1.set_ylabel('이동 인구수', size=12)

ax2.legend(loc='best')
ax2.set_title('서울 -> 강원', size=20)
ax2.set_xlabel('기간', size=12)
ax2.set_ylabel('이동 인구수', size=12)

ax3.legend(loc='best')
ax3.set_title('서울 -> 충북', size=20)
ax3.set_xlabel('기간', size=12)
ax3.set_ylabel('이동 인구수', size=12)

ax4.legend(loc='best')
ax4.set_title('서울 -> 충남', size=20)
ax4.set_xlabel('기간', size=12)
ax4.set_ylabel('이동 인구수', size=12)

ax1.set_xticklabels(col_years, rotation=60)
ax2.set_xticklabels(col_years, rotation=60)
ax3.set_xticklabels(col_years, rotation=60)
ax4.set_xticklabels(col_years, rotation=60)

plt.show()

