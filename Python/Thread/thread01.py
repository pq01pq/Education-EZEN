import threading


class Messenger(threading.Thread):
    def run(self):
        for _ in range(10):
            print(threading.currentThread().getName())


send = Messenger(name='aaa')
send2 = Messenger(name='bbb')

send.start()
send.join() # 스레드를 모두 실행
send2.start()
send2.join()
