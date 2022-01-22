import pandas as pd
import matplotlib.pyplot as plt
from matplotlib import font_manager, rc

font_path = './D2Coding-Ver1.3.2-20180524.ttf'
font_name = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font_name)

# 스타일 지정
plt.style.use('ggplot')
plt.rcParams['axes.unicode_minus'] = False   # 마이너스 부호 출력 설정

df = pd.DataFrame(pd.read_excel('./경기도인구데이터.xlsx'))
df.set_index('구분', inplace=True)
df = df.transpose()
print(df)

ax = df.plot(figsize=(10, 5))
ax.legend(loc='right')
ax.set_title('경기도 인구변화')
ax.set_xlabel('기간')
ax.set_ylabel('명')

plt.show()
