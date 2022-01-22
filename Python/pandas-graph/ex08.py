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

mask = (df['전출지별'] == '서울특별시')

df_seoul_leave = df[mask]
df_seoul_leave.drop('전출지별', axis=1, inplace=True)
df_seoul_leave.rename({'전입지별': '전입지'}, axis=1, inplace=True)
df_seoul_leave.set_index(['전입지'], inplace=True)

print(df_seoul_leave)

# 여러개의 그래프를 한번에 나타내기
# 서울 -> 경기, 충남, 충북, 강원의 인구이동을 4개의 그래프로 나타내
col_years = list(map(str, range(1970, 2018)))

df_seoul_leave = df_seoul_leave.loc[['전라남도', '강원도', '충청북도', '충청남도'], col_years]

df_seoul_leave['합계'] = df_seoul_leave.sum(axis=1)
print(df_seoul_leave)

df_total = df_seoul_leave[['합계']].sort_values(by='합계', ascending=True)
df_total.plot(kind='barh', color='blue', width=0.5, figsize=(10, 5))
plt.show()

'''
df_seoul_leave = df_seoul_leave.transpose()

print(df_seoul_leave)

df_seoul_leave.plot(kind='bar', figsize=(15, 7), width=0.8, color=['green', 'orange', 'blue', 'red'])

plt.show()
'''
'''
# 스타일 지정
plt.style.use('ggplot')

fig = plt.figure(figsize=(15, 7))
ax = fig.add_subplot(1, 1, 1)  # 행의 갯수, 열의 갯수, 위치

ax.plot(col_years, df_4.loc['경기도', :],
        marker='o', markerfacecolor='green',
        markersize=5, color='olive',
        linewidth=2, label='서울 -> 경기')

ax.legend(loc='best')
ax.set_title('서울 -> 경기', size=20)
ax.set_xlabel('기간', size=12)
ax.set_ylabel('이동 인구수', size=12)

ax.set_xticklabels(col_years, rotation=60)

plt.show()
'''