import requests

'''
url = 'https://withhive.com/games/32'
response = requests.get(url=url)
print(response.status_code)
print(response.text)
'''
'''
url = 'https://search.naver.com/search.naver'
param = {'query': '스털링 JP'}
response = requests.get(url=url, params=param)
print(response.text)
'''

url = 'https://comic.naver.com/webtoon/list'
params = {
    'titleId': '670143',
    'weedkay': 'wed'
}
response = requests.get(url=url, params=params)
print(response.text)



