'''
i = 1
sum = 0
while i <= 10:
    sum += i
    i += 1
    if i == 5:
        break
else:
    print('error')
print(sum)
'''
'''
i = 1
while True:
    print('hello, python')
    i += 1
    if i > 5:
        break
'''
'''
while True:
    minute = int()
    try:
        minute = int(input('분:'))
    except ValueError:
        print('숫자만 입력하라고 했어요 안했어요??')
        break
    if minute < 0:
        print('음수 입력하지 말라고 했어요 안했어요??')
        continue
    hour = minute // 60
    minute = minute % 60

    print('%d시간 %d분'%(hour, minute))
'''
'''
n = 8
half = n // 2
for i in range(1,n + 1):
    odd = 2 * i - 1
    if i <= half:
        print(' ' * (n - half - i),'*' * (2 * i - 1), ' ' * (n - half - i), sep='')
    else:
        print(' ' * (i - half - 1) ,'*' * ((2 * ((n - 1) // 2) + 1) - (2 * (i - half -1))), ' ' * (i - half - 1), sep='')
'''
'''
f1 = 1;
f2 = 1;
fibo = '1,1'
for i in range(3,9):
    f3 = f2 + f1
    f1 = f2
    f2 = f3
    fibo += ',' + str(f2)
print(fibo)
'''
'''
while True:
    commonDivisors = set()
    n = int()
    try:
        n = int(input('수 입력:'))
    except ValueError:
        print('숫자만 입력하라고 했어요 안했어요??')
        break
    for i in range(1,n):
        if i * i > n:
            break
        if n / i == float(n // i):
            commonDivisors.append(i)
            commonDivisors.append(n // i)
    commonDivisors = list(commonDivisors)
    commonDivisors.sort()
    print(commonDivisors)
'''
'''
names = list()
while True:
    name = input('이름:')
    isEnd = False
    for preName in names:
        if name == 'none':
            isEnd = True
        elif name == preName:
            print('존재하는 이름')
    if isEnd:
        break;
    names.append(name)
print(names)
'''
'''
def euclidGCD(a, b):
    if a < b:
        temp = a
        a = b
        b = temp
    if b == 0:
        return a
    else:
        return euclidGCD(b, a % b)
'''
'''
while True :
    try:
        a = int(input('수1 입력:'))
        b = int(input('수2 입력:'))
    except ValueError:
        print('숫자만 입력하라고 했어요 안했어요??')
        break
    print(euclidGCD(a,b))
'''
'''
while True:
    primes = set()
    n = int()
    try:
        n = int(input('수 입력:'))
    except ValueError:
        print('숫자만 입력하라고 했어요 안했어요??')
        break
    
    for i in range(2,n):
        isPrime = True
        for j in range(2, i - 1):
            if j ** 2 > i:
                break
            if euclidGCD(i, j) != 1:
                isPrime  = False
                break

        if isPrime:
            primes.add(i)
        
    primes = list(primes)
    primes.sort()
    print(primes)
'''

import sys
people = dict()
while True:
    print('1.입력 2.보기 3.삭제 4.수정 5.종료')
    try:
        index = int(input('[localhost ~]$ '))
    except ValueError:
        print('숫자만 입력하라고 했어요 안했어요??')
        continue
    
    if index == 1:
        name = input('이름: ')
        phone = input('번호: ')
        people[name] = phone
        print('입력 완료')
    elif index == 2:
        print(people)
    elif index == 3:
        name = input('이름: ')
        if people.get(name) == None:
            print('없는 이름')
        else:
            people.pop(name)
            print('삭제 완료')
    elif index == 4:
        name = input('이름: ')
        if people.get(name) == None:
            print('없는 이름')
        else:
            newPhone = input('수정할 번호: ')
            people[name] = newPhone
            print('수정 완료')
    elif index == 5:
        print('프로그램 종료')
        sys.exit()
    else:
        print('인덱스를 제대로 입력하라고 했어요 안했어요??')

    
