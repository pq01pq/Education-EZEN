from socket import *
from threading import *
from tkinter import *
import sys


class Client(Thread):
    def __init__(self, clientSocket):
        super(Client, self).__init__()
        self.clientSocket = clientSocket

    def run(self):
        while True:
            message = clientSocket.recv(65535).decode().strip()
            chatArea.insert(END, message + '\n')


def send():
    message = inputArea.get(0.0, END)
    inputArea.delete(0.0, END)
    clientSocket.send(message.encode())


window = Tk()
window.title('Gaga Live Client')
window.geometry("400x500")

chatArea = Text(window, width=54, height=30)
inputArea = Text(window, width=44, height=5)
inputButton = Button(window, text='전송', width=8, height=4, command=send)

chatArea.place(x=10, y=10)
inputArea.place(x=10, y=420)
inputButton.place(x=330, y=420)

chatArea.insert(END, '*** Welcome to Gaga Live ***\n')

clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect(('192.168.0.197', 12345))     # 튜플로 인자 전달
client = Client(clientSocket)
client.start()

window.mainloop()
