from threading import Thread
import time


def my_thread(val):
    for i in range(val):
        print('aaa')
        time.sleep(2)


t1 = Thread(target=my_thread, args=(3,))
t1.start()
for i in range(3):
    print('main')
    time.sleep(1)

print('end main')
