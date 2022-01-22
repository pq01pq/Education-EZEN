import sys, os

cwd = os.getcwd()
# print(cwd)

print('성적관리 프로그램')
while True:
    print('1.입력 2.출력 3.수정 4.삭제 0.종료')
    try:
        index = int(input('>> '))
    except ValueError:
        print('숫자만 입력')
        continue
    
    if index == 0:
        sys.exit()
    elif index == 1:
        name = input('이름: ')
        while True:
            try:
                kor = int(input('국어: '))
                eng = int(input('영어: '))
                break
            except ValueError:
                print('숫자만 입력')
        file = open('{}/grade.txt'.format(cwd), mode='a', encoding='utf-8')
        file.write('{},{},{}\n'.format(name, kor, eng))
        file.close()
    elif index == 2:
        file = open('{}/grade.txt'.format(cwd), mode='r', encoding='utf-8')
        print(file.read())
        file.close()
    elif index == 3:
        name = input('수정할 이름: ')
        file = open('{}/grade.txt'.format(cwd), mode='r', encoding='utf-8')
        grades = file.readlines()
        file.close()
        for i in range(0, len(grades)):
            if grades[i].split(sep=',')[0] == name:
                while True:
                    try:
                        kor = int(input('국어: '))
                        eng = int(input('영어: '))
                        break
                    except ValueError:
                        print('숫자만 입력')
                grades[i] = '{},{},{}\n'.format(name, kor, eng)
                file = open('{}/grade.txt'.format(cwd), mode='w', encoding='utf-8')
                file.writelines(grades)
                file.close()
                break
        else:
            print('존재하지 않는 이름')
            continue
    elif index == 4:
        name = input('삭제할 이름: ')
        file = open('{}/grade.txt'.format(cwd), mode='r', encoding='utf-8')
        grades = file.readlines()
        file.close()
        for i in range(0, len(grades)):
            info = grades[i].split(sep=',')
            if info[0] == name:
                grades.pop(i)
                file = open('{}/grade.txt'.format(cwd), mode='w', encoding='utf-8')
                file.writelines(grades)
                file.close()
                break
        else:
            print('존재하지 않는 이름')
            continue
    else:
        print('잘못된 인덱스')