import math


class Comp:
    def __init__(self, real, imaginary):
        self.real = real
        self.imaginary = imaginary

    def __add__(self, other):
        real = self.real + other.real
        imaginary = self.imaginary + other.imaginary
        return Comp(real, imaginary)

    def __sub__(self, other):
        real = self.real - other.real
        imaginary = self.imaginary - other.imaginary
        return Comp(real, imaginary)

    def __mul__(self, other):
        real = self.real * other.real - self.imaginary * other.imaginary
        imaginary = self.real * other.imaginary + self.imaginary * other.real
        return Comp(real, imaginary)

    def __len__(self):
        return math.sqrt(str(self.real) + str(self.imaginary))

    def __eq__(self, other):
        if self.real == other.real and self.imaginary == other.imaginary:
            return True
        else:
            return False

    def __ne__(self, other):
        return not self == other

    def __str__(self):
        if self.real == 0:
            if self.imaginary == 0:
                return str(0)
            else:
                return str(self.imaginary) + 'i'
        else:
            if self.imaginary == 0:
                return str(self.real)
            elif self.imaginary > 0:
                return str(self.real) + '+' + str(self.imaginary)
            else:
                return str(self.real) + str(self.imaginary)


n1 = Comp(1, 1)
n2 = Comp(1, -1)
n3 = Comp(-1, 1)
n4 = Comp(-1, -1)

print(n1)
print(n2)
print(n1 + n2)
print(n1 - n2)
print(n1 * n2)

print(n1 == n2)
print(n1 != n2)
