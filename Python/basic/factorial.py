
def factorial(n):
    fact = []
    if n < 0:
        return tuple(fact)
    elif n == 0:
        fact.append(1)
        return tuple(fact)
    elif n == 1:
        fact.append(1)
        fact.append(1)
        return tuple(fact)

    fact = list(factorial(n - 1))
    fact.append(n * fact[len(fact) - 1])
    return tuple(fact)


while True:
    try:
        num = int(input('수: '))
    except ValueError:
        print('숫자만 입력')
        continue
    if num < 0:
        break
    print(factorial(num))
