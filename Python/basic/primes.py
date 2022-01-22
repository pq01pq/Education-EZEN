import math


def primesof(num):
    primes = []
    for i in range(2, num + 1):
        for j in range(2, int(math.sqrt(i)) + 1):
            if i % j == 0:
                break
        else:
            primes.append(i)
    return tuple(primes)


while True:
    try:
        n = int(input('수: '))
    except ValueError:
        print('숫자만 입력')
        continue
    if n <= 0:
        break
    print(primesof(n))
