import urllib.request
data = urllib.request.urlopen("https://www.naver.com")
read_data = data.read()

print(type(read_data))

f = open("naver.html", 'wb')
f.write(read_data)
f.close()
