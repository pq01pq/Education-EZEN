from socket import *


serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('192.168.0.197', 12345))     # 튜플로 인자 전달
serverSocket.listen(0)
clientSocket, addr = serverSocket.accept()
data = clientSocket.recv(65535)
print('receive data: ', data.decode())
