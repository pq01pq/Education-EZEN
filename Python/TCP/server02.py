from socket import *


serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('192.168.0.197', 12345))     # 튜플로 인자 전달
serverSocket.listen(0)
clientSocket, addr = serverSocket.accept()
while True:
    data = clientSocket.recv(65535)
    decoded = data.decode().strip()
    if decoded == 'exit':
        break
    print('receive data: ', decoded)
    clientSocket.send(('sended : ' + decoded).encode())
