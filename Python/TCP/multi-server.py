from socket import *
from threading import *


class Client(Thread):
    def __init__(self, clientSocket, addr, name):
        super(Client, self).__init__()
        self.clientSocket = clientSocket
        self.addr = addr
        self.name = name

    def run(self):
        while True:
            message = self.clientSocket.recv(65535).decode().strip()
            print('[{}] {}'.format(self.name, message))
            for clientSocket in clientSockets:
                if clientSocket.getpeername()[1] == self.addr[1]:
                    clientSocket.send(' '.encode())
                else:
                    clientSocket.send('[{}] {}'.format(self.name, message).encode())


serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('192.168.0.197', 12345))     # 튜플로 인자 전달
serverSocket.listen(0)
clientSockets = list()
print('Gaga Live')

while True:
    clientSocket, addr = serverSocket.accept()
    name = clientSocket.recv(65535).decode().strip()
    print('{}님이 접속하셨습니다.'.format(name))
    for client in clientSockets:
        client.send('{}님이 접속하셨습니다.'.format(name).encode())
    client = Client(clientSocket, addr, name)
    clientSockets.append(clientSocket)
    client.start()
