import re

'''
string = 'pq01pq@gmail.com'
m = re.search(r'(\w+)@(.+)', string)
print(m)
print()
print(m.group(0))   # 전체
print(m.group(1))   # 그룹 1
print(m.group(2))   # 그룹 2 ...
'''
'''
m = re.search(r'pi{3,5}g', 'piiiiig')
# i가 3~5번 반복
print(m)
'''
'''
m = re.search(r'<.+?>', '<html>haha</html>')
print(m)
m = re.search(r'a{3,5}?', 'aaaaa')
print(m)
'''
'''
m = re.match(r'\d\d\d', 'my number is 123')
print(m)
m = re.match(r'\d\d\d', '123 is my number')
print(m)
'''
'''
m = re.findall(r'[\w]+@[\w.]+', 'pq01pq@gmail.com lol pq01pq@korea.ac.kr')
print(m)
'''
'''
m = re.sub(r'[\w]+@[\w.]+', 'great', 'pq01pq@gmail.com lol pq01pq@korea.ac.kr', count=1)
print(m)
'''

