from socket import *


clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect(('192.168.0.197', 12345))     # 튜플로 인자 전달
while True:
    data = input('전송>> ')
    clientSocket.send(data.encode())
    if data == 'exit':
        break
    data = clientSocket.recv(65535)
    print(data.decode().strip())
