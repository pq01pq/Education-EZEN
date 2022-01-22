import pandas as pd
import warnings
import matplotlib.pyplot as plt
from et_xmlfile import xmlfile
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
# south = south.loc['합계']

north.reset_index(inplace=True)
north.drop('국가', axis=1, inplace=True)
north.set_index('전력별', inplace=True)
north.replace({'-': 0}, inplace=True)
# north = north.loc['합계']

south.rename(index={'합계': '총발전량'}, inplace=True)
north.rename(index={'합계': '총발전량'}, inplace=True)

south = south.loc['총발전량']
north = north.loc['총발전량']
print(south)
print(north)
print()

# 스타일 지정
plt.style.use('ggplot')
plt.rcParams['axes.unicode_minus'] = False   # 마이너스 부호 출력 설정

# ax = north.plot(kind='area', stacked=True, alpha=0.2, figsize=(20, 10))
ax = south.plot(kind='area', stacked=False, alpha=0.2, figsize=(20, 10))
ax.set_title('대한민국 총발전량', size=30, color='brown', weight='bold')
ax.set_xlabel('기간', size=20, color='blue')
ax.set_ylabel('발전량', size=20, color='blue')
ax.legend(loc='best', fontsize=15)

plt.show()

ax = north.plot(kind='area', stacked=False, alpha=0.2, figsize=(20, 10))
ax.set_title('북조선 총발전량', size=30, color='brown', weight='bold')
ax.set_xlabel('기간', size=20, color='blue')
ax.set_ylabel('발전량', size=20, color='blue')
ax.legend(loc='best', fontsize=15)

plt.show()

