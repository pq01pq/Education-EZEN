import requests
from bs4 import BeautifulSoup

req = requests.get('https://www.naver.com')
html = req.text
soup = BeautifulSoup(html, 'html.parser')

'''
print(soup.title)
print(soup.title.name)
print(soup.title.string)
'''
'''
print(soup.img)
print(soup.img['src'])
print(soup.img['height'])
'''
'''
# find_soup = soup.find_all('li', class_='nav_item')
# find_soup = soup.find_all('li', attrs={'class': 'nav_item'})
find_soup = soup.find_all(string='자동완성')
print(find_soup)

for s in find_soup:
    print(s.string)
'''

# print(soup.select_one('a'))
print(soup.select('div > ul'))

text = soup.find('span', attrs={'class': 'blind'})
print(text.get_text())
print(text.get('class'))
print(text.string)
