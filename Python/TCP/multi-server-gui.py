from socket import *
from threading import *
from tkinter import *

clientSockets = list()


class Server(Thread):
    def run(self):
        while True:
            clientSocket, addr = serverSocket.accept()
            chatArea.insert(END, '{}님이 접속하셨습니다.\n'.format(addr[1]))
            for client in clientSockets:
                client.send('{}님이 접속하셨습니다.'.format(addr[1]).encode())
            client = Client(clientSocket, addr)
            clientSockets.append(clientSocket)
            client.start()


class Client(Thread):
    def __init__(self, clientSocket, addr):
        super(Client, self).__init__()
        self.clientSocket = clientSocket
        self.addr = addr

    def run(self):
        while True:
            message = self.clientSocket.recv(65535).decode().strip()
            chatArea.insert(END, '[{}] {}\n'.format(self.addr[1], message))
            for clientSocket in clientSockets:
                if clientSocket.getpeername()[1] == self.addr[1]:
                    clientSocket.send('[나] {}'.format(message).encode())
                else:
                    clientSocket.send('[{}] {}'.format(self.addr[1], message).encode())


def send():
    message = inputArea.get(0.0, END)
    inputArea.delete(0.0, END)
    chatArea.insert(END, '[관리자] {}'.format(message))
    for clientSocket in clientSockets:
        clientSocket.send('[관리자] {}'.format(message).encode())


window = Tk()
window.title('Gaga Live Server')
window.geometry("400x500")

chatArea = Text(window, width=54, height=30)
inputArea = Text(window, width=44, height=5)
inputButton = Button(window, text='전송', width=8, height=4, command=send)

chatArea.place(x=10, y=10)
inputArea.place(x=10, y=420)
inputButton.place(x=330, y=420)

serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('192.168.0.197', 12345))     # 튜플로 인자 전달
serverSocket.listen(0)
server = Server()
server.start()

chatArea.insert(END, '*** Gaga Live Start ***\n')

window.mainloop()
