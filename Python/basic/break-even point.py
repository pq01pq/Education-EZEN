'''
fixedPrice = 1000
variablePrice = 70
sellPrice = 80

i = 1
try:
    while sellPrice * i <= fixedPrice + variablePrice * i:
        i += 1
    print('손익분기점 : {}대'.format(i))
except OverflowError:
    print('파산입니다')
'''

binaryStr = '110101010110100101001'
number = 0
base = 2
multiplier = 1
for digit in binaryStr[-1:-(len(binaryStr) + 1):-1]:
    number += int(digit) * multiplier
    multiplier *= base
print(number)

decimalNum = 11;
binaryList = list()
while decimalNum > 0:
    remainder = decimalNum % base
    binaryList.append(str(remainder))
    decimalNum = decimalNum // base
binaryList.reverse()
binaryStr = ''
for digit in binaryList:
    binaryStr += digit
print(binaryStr)
