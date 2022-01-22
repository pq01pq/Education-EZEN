from bs4 import BeautifulSoup
import requests
import urllib.request


req = requests.get("http://www.naver.com")
html = req.text
# print(html)

req = urllib.request.urlopen("http://www.naver.com")
byte_data = req.read()
html = byte_data.decode()
# print(html)

soup = BeautifulSoup(html, 'html.parser')
result = soup.find_all('a', 'api_link')
print(result)

print(BeautifulSoup("<a></p>", "html.parser"))
print(BeautifulSoup("<a></p>", "lxml"))
print(BeautifulSoup("<a></p>", "xml"))
