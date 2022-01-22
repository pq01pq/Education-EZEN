from operator import attrgetter
import selenium
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import requests
from bs4 import BeautifulSoup
import pandas as pd
import matplotlib.pyplot as plt
from et_xmlfile import xmlfile
from matplotlib import font_manager, rc

# selenium

path = 'C:/Python/EZEN/tool/chromedriver.exe'
driver = webdriver.Chrome(path)

driver.get('http://www.nate.com')

driver.execute_script('document.querySelector("a[href=\'//news.nate.com/\']").click()')
time.sleep(5)
driver.execute_script('document.querySelector("a[href=\'//news.nate.com/rank/?mid=n1000\']").click()')
time.sleep(5)

# soup

req = requests.get(driver.current_url)
soup = BeautifulSoup(req.text, 'xml')
# print(soup)

span = soup.find()
spans_1_50 = soup.find_all('span', attrs={'class': 'medium'})
print(spans_1_50)

driver.execute_script('document.querySelector("a[href=\'//news.nate.com/rank/interest?sc=all&p=day&date=20210902&page=2\']").click()')
time.sleep(5)

spans_51_100 = soup.find_all('span', attrs={'class': 'medium'})
print(spans_51_100)

broadcasts = list()
i = 100
for span in spans_1_50:
    broadcasts.append([span.text, 1, i])
    i -= 1
for span in spans_51_100:
    broadcasts.append([span.text, 1, i])
    i -= 1
for i in range(0, 5):
    broadcasts[i][0] = broadcasts[i][0][-11::-1][-1::-1]
print(broadcasts)

# index = list(set(broadcasts))
df = pd.DataFrame(data=broadcasts, columns=['name', 'count', 'weight'])
print(df)

df_count = pd.DataFrame(df.groupby('name').sum())
print(df_count)

# pandas

font_path = '../D2Coding-Ver1.3.2-20180524.ttf'
font_name = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font_name)

fig = plt.figure(figsize=(10, 10))
'''
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
'''
df_count.sort_values('count', ascending=False, inplace=True)

ax1 = fig.add_subplot(2, 2, 1)
ax1 = df_count['count'].plot(kind='bar', stacked=False, alpha=0.2, figsize=(15, 10))
ax1.set_title('뉴스 Top 100 방송사 게시 횟수', size=20, weight='bold')
ax1.set_xlabel('방송사', size=20)
ax1.set_ylabel('게시 횟수', size=20)
ax1.legend(loc='right', fontsize=15)

ax2 = fig.add_subplot(2, 2, 2)
ax2 = df_count['count'].plot(kind='pie', figsize=(7, 5), autopct='%.1f%%',
                        startangle=90, colors=['red', 'green', 'blue'])
ax2.set_title('뉴스 Top 100 방송사 빈도', size=20, weight='bold')
# ax2.legend(labels=df_count.index, loc='right')

df_count.sort_values('weight', ascending=False, inplace=True)

ax3 = fig.add_subplot(2, 2, 3)
ax3 = df_count['weight'].plot(kind='bar', stacked=False, alpha=0.2, figsize=(15, 10))
ax3.set_title('뉴스 Top 100 방송사 영향력 크기', size=20, weight='bold')
ax3.set_xlabel('방송사', size=20)
ax3.set_ylabel('영향력', size=20)
ax3.legend(loc='right', fontsize=15)

ax4 = fig.add_subplot(2, 2, 4)
ax4 = df_count['weight'].plot(kind='pie', figsize=(7, 5), autopct='%.1f%%',
                        startangle=90, colors=['red', 'green', 'blue'])
ax4.set_title('뉴스 Top 100 방송사 영향력 빈도', size=20, weight='bold')
# ax4.legend(labels=df_count.index, loc='right')

# plt.axis('equal')
plt.show()
