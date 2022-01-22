import pandas as pd

student1 = pd.Series({'국어': 100, '영어': 70, '수학': 80})
student2 = pd.Series({'수학': 100, '국어': 70, '영어': 90})

print(student1)
print(student2)

percentage = student1 / 10

print(percentage)

addition = student1 + student2
print(addition)