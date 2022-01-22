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

mask = ((df['전출지별'] == '서울특별시') & (df['전입지별'] == '경기도')) | ((df['전출지별'] == '경기도') & (df['전입지별'] == '서울특별시'))

df_sk = df[mask]
df_sk.rename({'전입지별': '전입지', '전출지별': '전출지'}, axis=1, inplace=True)
df_sk.set_index(['전입지', '전출지'], inplace=True)

print(df_sk)

# 여러개의 그래프를 한번에 나타내기
# 서울 -> 경기, 경기 -> 서울
col_years = list(map(str, range(1970, 2018)))

df_2 = df_sk.loc[[('서울특별시', '경기도'), ('경기도', '서울특별시')], col_years]

print(df_2)

# 스타일 지정
plt.style.use('ggplot')

fig = plt.figure(figsize=(20, 5))
ax = fig.add_subplot(1, 1, 1)  # 행의 갯수, 열의 갯수, 위치

ax.plot(col_years, df_2.loc[('서울특별시', '경기도'), :],
         marker='o', markerfacecolor='green',
         markersize=10, color='olive',
         linewidth=2, label='서울 -> 경기')

ax.plot(col_years, df_2.loc[('경기도', '서울특별시'), :],
         marker='o', markerfacecolor='red',
         markersize=10, color='olive',
         linewidth=2, label='경기 -> 서울')

ax.legend(loc='best')
ax.set_title('서울 -> 경기, 경기 -> 서울 인구이동', size=20)
ax.set_xlabel('기간', size=12)
ax.set_ylabel('이동 인구수', size=12)

ax.set_xticklabels(col_years, rotation=90)

plt.show()

