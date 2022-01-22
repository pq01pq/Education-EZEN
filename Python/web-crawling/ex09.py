import requests
from bs4 import BeautifulSoup

req = requests.get('https://movie.naver.com/movie/sdb/rank/rmovie.naver')
html = req.text
soup = BeautifulSoup(html, 'html.parser')

movie_ranking = soup.find_all('div', class_='tit3')
for i in range(len(movie_ranking)):
    print('{:2}위 : {}'.format(i + 1, movie_ranking[i].get_text().strip()))

