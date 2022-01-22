name = input('이름을 입력: ')
print(name, '님 반갑습니다.')

kor = int(input('국어점수: '))
print('국어점수: ', kor, '점')

avg = float(input('평균: '))
print('평균:', avg, '점')

print("{}님의 국어점수는 {}점이고 평균은{}점입니다.".format(name, kor, avg))
print("%s님의 국어점수는 %d점이고 평균은 %.2f점입니다."%(name, kor, avg))
