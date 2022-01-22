import urllib.request

'''
url = 'https://withhive.com/games/32'
resp = req.get(url=url)
print(resp.status_code)
print(resp.text)
'''
'''
url = 'https://withhive.com/games/32'
resp_urllib = urllib.request.urlopen(url)
byte_data = resp_urllib.read()
text_data = byte_data.decode('utf-8')
print(text_data)
'''
'''
url = 'https://withhive.com/games/32'
req = urllib.request.Request(url)
print(req)
print(req.full_url)
print(req.type)
print(req.host)
'''
'''
url = 'https://withhive.com/games/32'
# req = urllib.request.Request(url)
# resp1 = urllib.request.urlopen(req)
resp2 = urllib.request.urlopen(url)
# print(resp1)
print(resp2)
# print(resp1.geturl())
print(resp2.geturl())
# print(resp1.getheaders())
print(resp2.getheaders())
'''

url = 'https://postfiles.pstatic.net/MjAxODA1MzBfMTM5/MDAxNTI3NjUwODYwOTI0.bE37VxYchVky2e3Aj9pJtrNEWxpgn3aH1NEI8IUeZPYg.KBX7GqBjyyhECwBufN7_dM6hFMYNIie_UVsYJ3rG0GMg.JPEG.gudals4347/image_4986960001527650837014.jpg?type=w966'
new_name = 'ezen.jpg'
# 웹상의 이미지를 다운로드
urllib.request.urlretrieve(url, new_name)

url = 'https://massnoun.com/web/product/medium/202011/0a184a9b425a7500c312a5befddf2f1a.jpg'
new_name = 'hoodie.jpg'
urllib.request.urlretrieve(url, new_name)


