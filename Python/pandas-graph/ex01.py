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

mask = (df['전출지별'] == '서울특별시') & (df['전입지별'] != '서울특별시')

df_seoul = df[mask]
df_seoul = df_seoul.drop(['전출지별'], axis=1)
df_seoul.rename({'전입지별': '전입지'}, axis=1, inplace=True)
df_seoul.set_index('전입지', inplace=True)
print(df_seoul)

# 서울에서 경기도로 이동한 이구 데이터 값만 선택

df_seoul_kyungi = pd.DataFrame(df_seoul.loc['경기도'])
print(df_seoul_kyungi)

# 스타일 지정
plt.style.use('ggplot')

fig = plt.figure(figsize=(10, 10))
ax1 = fig.add_subplot(2, 1, 1)  # 행의 갯수, 열의 갯수, 위치
ax2 = fig.add_subplot(2, 1, 2)

ax1.plot(df_seoul_kyungi, 'o', markersize=10)
ax2.plot(df_seoul_kyungi, marker='o', markerfacecolor='green', markersize=10, color='olive',
         linewidth=2, label='서울 -> 경기')
ax2.legend(loc='best')
ax1.set_ylim(50000, 800000)
ax2.set_ylim(50000, 800000)
ax1.set_xticklabels(df_seoul_kyungi.index, rotation=75)
ax2.set_xticklabels(df_seoul_kyungi.index, rotation=75)
'''
# 그림 사이즈 지정(가로 14인치, 세로 5인치
plt.figure(figsize=(14, 5))

# x축 눈금 라벨 회전하기
plt.xticks(rotation='vertical')

# title과 x축 y축 설정
plt.title('서울 -> 경기 인구 이동')
plt.xlabel('기간')
plt.ylabel('이동 인구수')
# plt.plot(df_seoul_kyungi.index, df_seoul_kyungi.values)
plt.plot(df_seoul_kyungi)

# 범례 설정
plt.legend(labels=['서울 -> 경기'], loc='best')
'''

plt.show()
