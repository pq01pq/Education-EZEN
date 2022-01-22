from socket import *


clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect(('192.168.0.197', 12345))     # 튜플로 인자 전달
clientSocket.send('hello'.encode())
