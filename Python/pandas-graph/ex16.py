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

df = pd.DataFrame(df)

mask = (df['전출지별'] == '서울특별시')
df_seoul_leave = df[mask]
df_seoul_leave.drop('전출지별', axis=1, inplace=True)
df_seoul_leave.rename({'전입지별': '전입지'}, axis=1, inplace=True)
df_seoul_leave = df_seoul_leave.iloc[2:, :]
df_seoul_leave.set_index(['전입지'], inplace=True)
df_seoul_leave = df_seoul_leave['2016']
# print(df_seoul_leave)

mask = (df['전입지별'] == '서울특별시')
df_seoul_arrive = df[mask]
df_seoul_arrive.drop('전입지별', axis=1, inplace=True)
df_seoul_arrive.rename({'전출지별': '전출지'}, axis=1, inplace=True)
df_seoul_arrive = df_seoul_arrive.iloc[2:, :]
df_seoul_arrive.set_index(['전출지'], inplace=True)
df_seoul_arrive = df_seoul_arrive['2016']

fig = plt.figure(figsize=(10, 10))
ax1 = fig.add_subplot(2, 1, 1)
ax2 = fig.add_subplot(2, 1, 2)

# 파이차트
ax1.pie(df_seoul_leave, autopct='%1.1f%%', startangle=10)
ax2.pie(df_seoul_arrive, autopct='%1.1f%%', startangle=10)

ax1.set_title('서울 -> 타시도 인구 이동', size=30, color='brown', weight='bold')
ax2.set_title('타시도 -> 서울 인구 이동', size=30, color='brown', weight='bold')

ax1.legend(labels=df_seoul_leave.index, loc='right')
ax2.legend(labels=df_seoul_arrive.index, loc='right')

plt.axis('equal')
plt.show()

