import urllib.parse
import urllib.request

'''
parse = urllib.parse.urlparse("http://www.naver.com/abcd;a=1?b=2#b")
print(parse.scheme)
print(parse)
print(parse[0])
'''
'''
parse = urllib.parse.urlsplit("http://www.naver.com/abcd;a=1?b=2#b")
parse = list(parse) #parse의 값이 튜플형태라 변환을 하고자 하면 list로 바꾸어 준다
parse[1] = "www.daum.net"
unparse = urllib.parse.urlunsplit(parse)    #urlunparse와 동일
print(unparse)
'''
'''
parse = urllib.parse.urlparse("http://www.naver.com?a=1&b=2&c=3&d=4")
qs = urllib.parse.parse_qs(parse.query)     #사전형형태로 쿼리문을 반환
qsl = urllib.parse.parse_qsl(parse.query)   #리스트 타입으로 쿼리문을 반환

print(qs)
print(qsl)
'''
'''
url = "http://www.naver.com/a/b/"
print(urllib.parse.urljoin(url, '/c'))
'''

# print(urllib.parse.quote('파이썬'))
# print(urllib.parse.unquote('%ED%8C%8C%EC%9D%B4%EC%8D%AC'))

url = "http://search.naver.com/search.naver?query=%ED%8C%8C%EC%9D%B4%EC%8D%AC"
response_urllib = urllib.request.urlopen(url)
byte_data = response_urllib.read()
text_data = byte_data.decode()
print(text_data)

