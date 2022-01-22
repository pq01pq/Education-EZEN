import threading
import time
import random
import sys


running = True


class Timer(threading.Thread):
    sec = 0

    def run(self):
        while running:
            time.sleep(1)
            self.sec += 1


comNum = int(random.random() * 100) + 1
print('[computer] 1~100 맞춰봐라 ㅋㅋ')
timer = Timer(name='timer')
timer.start()
while True:
    try:
        myNum = int(input('[me] '))
    except ValueError:
        print('[computer] 숫자만 입력하라고 ㅋㅋ')
        continue

    if myNum == comNum:
        print('[computer] {}초만에 맞춤 ㅋㅋ'.format(timer.sec))
        running = False
        break
    elif myNum > comNum:
        print('[computer] 다운 ㅋㅋ')
    else:
        print('[computer] 업 ㅋㅋ')

    if timer.sec >= 120:
        print('[computer] 타임아웃 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ')
        break
