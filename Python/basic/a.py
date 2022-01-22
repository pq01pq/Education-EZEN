class A:
    def __init__(self, value):
        self.value = value

    def __sub__(self, other):
        return self.value - other.value

    def __eq__(self, other):
        return self - other == 0

    def __lt__(self, other):
        return self - other < 0

    def __gt__(self, other):
        return self - other > 0

    def __le__(self, other):
        return self - other <= 0

    def __ge__(self, other):
        return self - other >= 0

    def __repr__(self):
        return str(self.value)


a1 = A(2)
a2 = A(1)
# print(a1 >= a2)
a_list = [a1, a2]
print(a_list)
a_list.sort()
print(a_list)
print(a1)
