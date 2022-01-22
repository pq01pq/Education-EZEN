import re

# print("abcdef\n")
# print(r'abcedf\n')
# raw string : 문자열 앞에 r을 붙여 해당 문자열이 구성된 그대로 문자열로 반환

# search() 메소드 : 첫번째 패턴을 찾으면 match 객체를 반환
# 패턴을 찾지 못하면 None 반환

# m = re.search(r'abc', '123abcdef')
# m = re.search(r'\d\d\d\w', '112abcdef199')
# m = re.search(r'..\w\w.', '@#$%ABCDEFG')
# m = re.search(r'[cbm]at', 'dat')
# m = re.search(r'[0-4]haha', '5haha')
# m = re.search(r'[abc.^]aron', '^aron')
# m = re.search(r'[^abc]aron', 'caron')
# m = re.search(r'\sand', 'apple and banana')
# m = re.search(r'\Sand', 'apple and banana')
# m = re.search(r'\.and', 'aand')

# m = re.search(r'a[bcd]*b', 'abcbdccb')
# a로 시작, b나 c나 d중 아무거나 중간에 0번이상 나오고 b로 끝남
# ab, abcb, abcbdccb 후보, 제일 긴 문자열이 매칭됨

# m = re.search(r'b\w+a', 'banbnb')
# b로 시작하고 a로 끝나고 사이에 문자가 한번이상 나와야함

# m = re.search(r'i+', 'piigiii')
# i가 1번이상 나오는 맨 앞의 제일 긴 i로 이뤄진 문자열

# m = re.search(r'https?', 'http://www.ezen.com')
# n = re.search(r'https?', 'https://www.ezen.com')
# s가 1개 있거나 없는 문자열

# m = re.search(r'^b\w+a', 'banana')
# n = re.search(r'^b\w+a', 'cabana')
# ^ : 문자열 처음부터 비교

m = re.search(r'b\w+a$', 'banana')
n = re.search(r'b\w+a$', 'cabanap')
# $ : 문자열 마지막부터 비교

# print(m.start())
# print(m.end())
# print(m.group())
print(m)
print(n)


