from socket import *
from threading import *
import sys


class Client(Thread):
    def __init__(self, clientSocket):
        super(Client, self).__init__()
        self.clientSocket = clientSocket

    def run(self):
        global received
        while True:
            message = clientSocket.recv(65535).decode().strip()
            if message != '':
                print(message)
            received = True


name = input('이름>> ')
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect(('192.168.0.197', 12345))     # 튜플로 인자 전달
clientSocket.send(name.encode())
client = Client(clientSocket)
client.start()
print('Welcome to Gaga Live')

while True:
    message = input('[나] ')
    if message == 'exit':
        sys.exit()
    clientSocket.send(message.encode())
