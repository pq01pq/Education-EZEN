import os
import random
from threading import Thread
import time
from tkinter import *


running = True
gacha = bool()
window = Tk()
window.geometry("300x400")
controlButtons = list()
gameButtons = list()
digdogImage = PhotoImage(file=os.getcwd() + '\\digdog.jpg')


class Timer(Thread):
    sec = 0

    def run(self):
        while running:
            time.sleep(1)
            self.sec += 1
            controlButtons[1] = Button(window, text=str(timer.sec), width=10, height=2)


timer = Timer()


class Digdog(Thread):
    index = int()

    def run(self):
        while not gacha:
            self.index = int(random.random() * 9) + 1
            gameButtons[self.index] = Button(window, image=digdogImage, width=10, height=5)
            time.sleep(2)
            gameButtons[self.index] = Button(window, width=10, height=5)


def killDigdog(index):
    if index == Digdog.index:
        gacha = False
        time.sleep(1)
        gacha = True
        Digdog().start()


def start():
    timer().start()


controlButtons.append(Button(window, text='시작', width=10, height=2, command=start))
controlButtons.append(Button(window, text='0', width=10, height=2))
controlButtons.append(Button(window, text='종료', width=10, height=2, command=window.quit))
controlButtons[0].place(x=0, y=30)
controlButtons[1].place(x=100, y=30)
controlButtons[2].place(x=200, y=30)

for i in range(9):
    gameButtons.append(Button(window, width=10, height=5))

xPos, yPos = 0, 100
for i in range(0, 3):
    for j in range(0, 3):
        gameButtons[3*i + j].place(x=xPos, y=yPos, command=killDigdog(3*i + j))
        xPos += 100
    xPos = 0
    yPos += 100
window.mainloop()
