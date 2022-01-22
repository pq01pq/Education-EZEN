'''
s = 'Hello, Python!!'
for c in s:
    print(c, end=' ')
'''
'''
l = ['red','green','blue']
for x in l:
    print(x)
'''
'''
colorPencil = {'red':8, 'black':10, 'blue':5, 'green':3}
for color in colorPencil:
    print(color, colorPencil.get(color))

for color, count in colorPencil.items():
    print(color, count)
'''
'''
for i in range(10):
    print(i, end=' ')

for i in range(1,10,2):
    print(i, end=' ')
'''
'''
sum = 0
for i in range(1,101):
    sum += i
print(sum)
'''
'''
nums = list()
for num in range(1,11):
    nums.append(num)
print(nums)
'''
'''
s = 'abcdefghijklmnopqrstuvwxyz'
s2 = ''
l = list(s)
l.reverse()
for c in l:
    s2 += c
print(s2)
'''
'''
l1 = ['apple','MELON','kiwi','ORANGE','peAR']
l2 = list()
for string in l1:
    if string.upper() == string:
        l2.append(string)
print(l1)
print(l2)

L1 = ['apple', 'MELON', 'kiwi', 'ORANGE', 'peAR']
L2 = []

for i in range(len(L1)) :  
    for j in range(len(L1[i])) :
        if L1[i][j] >= 'A' and L1[i][j] <= 'Z' :
            continue
        else :
            break
    # else절은 for문의 값들이 다 실행되고 탈출해야지만 실행
    else :
        L2.append(L1[i])

print("L1 = ", L1)
print("L2 = ", L2)
'''
'''
# 1
for i in range(0,5):
    for j in range(0,5):
        print(5*i + (j+1), end='\t')
    print()
print('--------------------------------------')
# 2

for i in range(0,5):
    for j in range(5,0,-1):
        print((i+1) + 5*(j-1), end='\t')
    print()

for i in range(0,5):
    for j in range(-4,1):
        print(5*(-j) + (i+1), end='\t')
    print()
print('--------------------------------------')
# 3

for i in range(5,0,-1):
    for j in range(0,5):
        print(5*i - j, end='\t')
    print()

for i in range(-4,1):
    for j in range(-4,1):
        print(5*(-i) + (-j+1), end='\t')
    print()
print('--------------------------------------')
# 4

for i in range(5,0,-1):
    for j in range(0,5):
        print(i + 5*j, end='\t')
    print()

for i in range(-4,1):
    for j in range(0,5):
        print(5*j + (-i+1), end='\t')
    print()
'''
'''
while True:
    try:
        n = int(input('행렬 크기:'))
    except ValueError:
        print('숫자만 입력하라고 했어요 안했어요??')
    for i in range(0,n):
        for j in range(0,n):
            print(n*i + (j+1), end='\t')
        print()
    print('--------------------------------------')
'''
'''
ab = list('나랏말싸미듕귁에달아문자와로서로사맛디아니할씌이런젼차로어린백성이니르고져할배이셔도마참내제뜨슬시러펴디못할놈이아니라내이를어엿비너겨새로스물여덟자를맹가노니사람마다수비익혀날로쑤메편안킈하고져할따라미니라')
while True:
    try:
        n = int(input('행렬 크기:'))
    except ValueError:
        print('숫자만 입력하라고 했어요 안했어요??')
        break
    for i in range(0,n):
        for j in range(0,n):
            if j % 2 == 0:
                print(ab[(n*j + i) % len(ab)], end=' ')
            else:
                print(ab[(n*j + (n - i - 1)) % len(ab)], end=' ')
        print()
'''
'''
ab = list('ABCDEFGHIJKLMNOPQRSTUVWXYZ')
while True:
    try:
        n = int(input('행렬 크기:'))
    except ValueError:
        print('숫자만 입력하라고 했어요 안했어요??')
        break
    for i in range(0,n):
        for j in range(0,n):
            if j < n - i - 1:
                print(' ', end=' ')
            else:
                print(ab[(i + n*(i+j-n+1) - (i+j-n+1)*(i+j-n+2)//2) % len(ab)], end=' ')
        print()
'''
'''
ab = list('ABCDEFGHIJKLMNOPQRSTUVWXYZ')
while True:
    try:
        n = int(input('행렬 크기:'))
    except ValueError:
        print('숫자만 입력하라고 했어요 안했어요??')
        break
    for i in range(0,n):
        for j in range(0,n):
            if j < n - i - 1:
                print(' ', end=' ')
            else:
                print(ab[(i*(i+1)//2 + (i+j-n+1)) % len(ab)], end=' ')
        print()
'''
'''
while True:
    print('홀수 입력. 짝수 입력시 +1 됨')
    try:
        n = int(input('마방진 크기:'))
    except ValueError:
        print('숫자만 입력하라고 했어요 안했어요??')
        break
    if n % 2 == 0:
        n += 1
        
    sudoku = list()
    for i in range(0,n):
        sudokuRow = list()
        for j in range(0,n):
            sudokuRow.append(0)
        sudoku.append(sudokuRow)
        
    i, j, k = 0, (n - 1)//2, 1
    while(k <= n**2):
        if sudoku[i][j] == 0:
            sudoku[i][j] = k
            i = (i - 1) % n
            j = (j + 1) % n
            k += 1
        else:
            i = (i + 2) % n
            j = (j - 1) % n

    print()     
    for i in range(0,n):
        for j in range(0,n):
            print(sudoku[i][j], end='\t')
        print()
        print()
'''

ab = list('ABCDEFGHIJKLMNOPQRSTUVWXYZ')
while True:
    try:
        n = int(input('행렬 크기:'))
    except ValueError:
        print('숫자만 입력하라고 했어요 안했어요??')
        break
    
    rhombus = list()
    for i in range(0,n):
        rhombusRow = list()
        for j in range(0,n):
            rhombusRow.append(' ')
        rhombus.append(rhombusRow)
        
    k = 0
    index = 0
    while k < (n+1)//2:
        i, j = k, (n-1)//2
        while j >= k:
            rhombus[i][j] = ab[index]
            i += 1
            j -= 1
            index = (index + 1) % len(ab)
        if n % 2 == 0:
            j += 1
        else:
            j += 2
        
        while j <= (n - 1)//2:
            rhombus[i][j] = ab[index]
            i += 1
            j += 1
            index = (index + 1) % len(ab)
        if n % 2 == 0:
            i -= 1
        else:
            i -= 2
        
        while j <= n - k - 1:
            rhombus[i][j] = ab[index]
            i -= 1
            j += 1
            index = (index + 1) % len(ab)
        if n % 2 == 0:
            j -= 1
        else:
            j -= 2
        
        while j >= (n + 1)//2:
            rhombus[i][j] = ab[index]
            i -= 1
            j -= 1
            index = (index + 1) % len(ab)
        k += 1

    print()     
    for i in range(0,n):
        for j in range(0,n):
            print(rhombus[i][j], end='  ')
        print()
