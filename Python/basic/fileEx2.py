import sys, os

cwd = os.getcwd()
# print(cwd)

print('회원관리 프로그램')
try:
    open('{}/member.txt'.format(cwd), mode='r', encoding='utf-8')
except FileNotFoundError:
    open('{}/member.txt'.format(cwd), mode='w', encoding='utf-8')

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
        file = open('{}/member.txt'.format(cwd), mode='r', encoding='utf-8')
        members = file.readlines()
        file.close()
        for member in members:
            if member.split(',')[0] == name:
                print('존재하는 이름')
                break
        else:
            phone = input('전화번호: ')
            address = input('주소: ')
            file = open('{}/member.txt'.format(cwd), mode='a', encoding='utf-8')
            file.write('{},{},{}\n'.format(name, phone, address))
            file.close()
    elif index == 2:
        file = open('{}/member.txt'.format(cwd), mode='r', encoding='utf-8')
        print(file.read())
        file.close()
    elif index == 3:
        name = input('수정할 이름: ')
        file = open('{}/member.txt'.format(cwd), mode='r', encoding='utf-8')
        members = file.readlines()
        file.close()
        for i in range(0, len(members)):
            if members[i].split(',')[0] == name:
                phone = input('전화번호: ')
                address = input('주소: ')
                members[i] = '{},{},{}\n'.format(name, phone, address)
                file = open('{}/member.txt'.format(cwd), mode='w', encoding='utf-8')
                file.writelines(members)
                file.close()
        else:
            print('없는 이름')
    elif index == 4:
        name = input('삭제할 이름: ')
        file = open('{}/member.txt'.format(cwd), mode='r', encoding='utf-8')
        members = file.readlines()
        file.close()
        for i in range(0, len(members)):
            if members[i].split(',')[0] == name:
                members.pop(i)
                file = open('{}/member.txt'.format(cwd), mode='w', encoding='utf-8')
                file.writelines(members)
                file.close()
                break
        else:
            print('없는 이름')
    else:
        print('잘못된 인덱스')
