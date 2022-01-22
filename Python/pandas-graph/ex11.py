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

# 서울 -> '충청남도', '충청북도', '강원도', '전라남도'
df = pd.DataFrame(df)

mask = (df['전출지별'] == '서울특별시')
df_seoul_leave = df[mask]
df_seoul_leave.drop('전출지별', axis=1, inplace=True)
df_seoul_leave.rename({'전입지별': '전입지'}, axis=1, inplace=True)
df_seoul_leave.set_index(['전입지'], inplace=True)

col_years = list(map(str, range(1970, 2018)))
df_seoul_leave = df_seoul_leave.loc[['충청남도', '충청북도', '강원도', '전라남도'], col_years]
# df_seoul_leave = df_seoul_leave.transpose()

print(df_seoul_leave)

'''
면적그래프
 - 각 열의 데이터를 선 그래프로 구현, 선그래프와 x축 사이의 공간에 색이 입혀진다
 - 색의 투명도는 기본 0.5 이고, 범위는 0 ~ 1이다
 - plot메소드의 kind는 area 옵션이며, 그래프 누적 여부의 옵션은 stacked=True(기본값)
'''
# 스타일 지정
plt.style.use('ggplot')
plt.rcParams['axes.unicode_minus'] = False   # 마이너스 부호 출력 설정

ax = df_seoul_leave.plot(kind='area', stacked=True, alpha=0.2, figsize=(20, 10))
ax.set_title('서울 -> 타시도 인구 이동', size=30, color='brown', weight='bold')
ax.set_xlabel('기간', size=20, color='blue')
ax.set_ylabel('이동 인구 수', size=20, color='blue')
ax.legend(loc='best', fontsize=15)


plt.show()
