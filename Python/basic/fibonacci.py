
def fibo(n):
    fibs = []
    if n <= 0:
        return tuple()
    elif n == 1:
        fibs.append(1)
        return tuple(fibs)
    elif n == 2:
        fibs.append(1)
        fibs.append(1)
        return tuple(fibs)

    fibs = list(fibo(n - 1))
    fibs.append(fibs[len(fibs) - 1] + fibs[len(fibs) - 2])
    return tuple(fibs)


while True:
    try:
        num = int(input('수: '))
    except ValueError:
        print('숫자만 입력')
        continue
    if num <= 0:
        break
    print(fibo(num))
