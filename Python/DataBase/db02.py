import os
import sqlite3

con = sqlite3.connect(os.getcwd() + '/testDB')
cur = con.cursor()
cur.execute("select * from testTable")
while True:
    row = cur.fetchone()
    if row == None:
        break
    id = row[0]
    name = row[1]
    email = row[2]
    birth = row[3]
    print(id, name, email, birth)

con.close()
