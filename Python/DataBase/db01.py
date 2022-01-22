import os
import sqlite3

con = sqlite3.connect(os.getcwd() + '/testDB')
cur = con.cursor()
try:
    cur.execute('create table testTable (id char(4), name char(15), email char(20), birth int)')
except sqlite3.OperationalError:
    pass

cur.execute("insert into testTable values('aaa', 'aaaa', 'aaa@aa.aa', 1990)")
cur.execute("insert into testTable values('bbb', 'bbbb', 'bbb@bb.bb', 1991)")
cur.execute("insert into testTable values('ccc', 'cccc', 'ccc@cc.cc', 1992)")
cur.execute("insert into testTable values('ddd', 'dddd', 'ddd@dd.dd', 1993)")

con.commit()
