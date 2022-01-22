import os
import sqlite3

con = sqlite3.connect(os.getcwd() + '/grade')
cur = con.cursor()
try:
    cur.execute("create table grade (name char(20), kor int, eng int)")
except sqlite3.OperationalError:
    pass
con.close()

print('학생 관리')
while True:
    print('1.입력 2.보기 3.수정 4.삭제 0.종료')
    try:
        index = int(input('> '))
    except ValueError:
        print('숫자만 입력')
        continue

    if index == 0:
        break
    elif index == 1:
        name = input('이름: ')
        try:
            kor = int(input('국어: '))
            eng = int(input('영어: '))
        except ValueError:
            print('숫자만 입력')
            continue

        sql = "insert into grade values('{}',{},{})".format(name, kor, eng)
        con = sqlite3.connect(os.getcwd() + '/grade')
        cur = con.cursor()
        cur.execute(sql)
        con.commit()
        con.close()

    elif index == 2:
        sql = "select * from grade"
        con = sqlite3.connect(os.getcwd() + '/grade')
        cur = con.cursor()
        cur.execute(sql)
        results = cur.fetchall()
        if len(results) == 0:
            print('학생 없음')
        else:
            print('이름', '국어', '영어', sep='\t')
            for result in results:
                print(result[0], result[1], result[2], sep='\t\t')
        con.close()

    elif index == 3:
        name = input('수정할 이름: ')
        sql = "select name from grade where name='{}'".format(name)
        con = sqlite3.connect(os.getcwd() + '/grade')
        cur = con.cursor()
        cur.execute(sql)
        result = cur.fetchone()
        con.close()
        if result is None or result[0] != name:
            print('존재하지 않는 학생')
        else:
            try:
                kor = int(input('국어: '))
                eng = int(input('영어: '))
            except ValueError:
                print('숫자만 입력')
                continue
            sql = "update grade set kor={}, eng={} where name='{}'".format(kor, eng, name)
            con = sqlite3.connect(os.getcwd() + '/grade')
            cur = con.cursor()
            cur.execute(sql)
            con.commit()
            con.close()

    elif index == 4:
        name = input('삭제할 이름: ')
        sql = "select name from grade where name='{}'".format(name)
        con = sqlite3.connect(os.getcwd() + '/grade')
        cur = con.cursor()
        cur.execute(sql)
        result = cur.fetchone()
        con.close()
        if result is None or result[0] != name:
            print('존재하지 않는 학생')
        else:
            sql = "delete from grade where name='{}'".format(name)
            con = sqlite3.connect(os.getcwd() + '/grade')
            cur = con.cursor()
            cur.execute(sql)
            con.commit()
            con.close()

    else:
        print('잘못된 인덱스')
