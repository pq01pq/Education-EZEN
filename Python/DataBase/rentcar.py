import os
import sqlite3
from tkinter import *

def manage():
    pass

def detail():
    pass

try:
    con = sqlite3.connect(os.getcwd() + '/member')
    cur = con.cursor()
    cur.execute("create table member (id char(20), password char(20), name char(30))")
    cur.execute("insert into member(id,password,name) values('admin', 'admin', '관리자')")
    con.commit()
    con.close()
except sqlite3.OperationalError:
    pass
try:
    con = sqlite3.connect(os.getcwd() + '/car')
    cur = con.cursor()
    cur.execute("create table car (id int, name char(30), plate char(20)")
    con.close()
except sqlite3.OperationalError:
    pass
try:
    con = sqlite3.connect(os.getcwd() + '/rent')
    cur = con.cursor()
    cur.execute("create table rent (id int, member_id char(20), car_id int, date date)")
    con.close()
except sqlite3.OperationalError:
    pass



window = Tk()
window.title('So Car')
window.geometry("800x500")

menuFrame = Frame(master=window)
menuFrame.pack(side=TOP)

insertButton = Button(master=menuFrame, text='차량 등록')
insertButton.pack()

# 번호 차종 번호판
mainFrame = Frame(master=window)
mainFrame.pack()

columnFrame = Frame(master=mainFrame)
columnFrame.grid()

idTitleLabel = Label(master=columnFrame, text='번호', width=20, height=2, bg='white', borderwidth=2, relief=RIDGE)
carNameTitleLabel = Label(master=columnFrame, text='차종', width=50, height=2, bg='white', borderwidth=2, relief=RIDGE)
plateTitleLabel = Label(master=columnFrame, text='번호판', width=30, height=2, bg='white', borderwidth=2, relief=RIDGE)
idTitleLabel.grid(row=0, column=0)
carNameTitleLabel.grid(row=0, column=1)
plateTitleLabel.grid(row=0, column=2)

carFrame = Frame(master=mainFrame, bg='white')
carFrame.grid()


con = sqlite3.connect(os.getcwd() + '/car')
cur = con.cursor()
cur.execute("select * from car")
cars = cur.fetchall()
con.close()
for i in range(0, len(cars)):
    idLabel = Label(master=carFrame, text=cars[i][0], width=20, height=5)
    carNameButton = Button(master=carFrame, text=cars[i][1], width=20, height=5, command=detail)
    plateLabel = Label(master=carFrame, text=cars[i][2], width=20, height=5)

window.mainloop()
