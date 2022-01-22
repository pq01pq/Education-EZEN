import pandas as pd
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
south = pd.DataFrame(df.drop('북한', axis=0))
print(south)

print()
south.reset_index(inplace=True)
south.drop('국가', axis=1, inplace=True)
south.set_index('전력별', inplace=True)
print(south)

south_sum = south.iloc[0]
print(south_sum)

# 스타일 지정
plt.style.use('ggplot')

# 그림 사이즈 지정(가로 14인치, 세로 5인치
plt.figure(figsize=(14, 5))

# x축 눈금 라벨 회전하기
plt.xticks(rotation='vertical')

# title과 x축 y축 설정
plt.title('남한 전력 합계')
plt.xlabel('기간')
plt.ylabel('발전량')
# plt.plot(df_seoul_kyungi.index, df_seoul_kyungi.values)
plt.plot(south_sum)

# 범례 설정
plt.legend(labels=['발전량'], loc='best')

plt.show()
