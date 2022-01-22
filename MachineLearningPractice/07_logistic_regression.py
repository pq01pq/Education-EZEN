import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression


iris = load_iris()
# print(iris)
print(iris.target_names)
# print(iris.feature_names)
# print(iris.data[0:5])
print(iris.target)
# target data 0, 1, 2 : ['setosa' 'versicolor' 'virginica']

x = iris.data
y = iris.target
# x = iris data, y = target data 즉, 꽃 종류들

# df = pd.DataFrame(x, columns=['sepal_width(cm)', 'sepal_length(cm)', 'petal_width(cm)', 'petal_length(cm)'])
# print(df.head())

df = pd.DataFrame(iris.data, columns=iris.feature_names)
df['species'] = np.array([iris.target_names[i] for i in iris.target])

sns.pairplot(df, hue='species')
# 3차원 이상의 데이터에서 주로 사용
# hue='species' : 카테고리형 데이터가 섞여있을 때 이것의 값에 따라 카테고리 색상을 다르게 함

# plt.show()

x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.3)
# 70%는 training data, 30%는 test data

logistic = LogisticRegression()
logistic.fit(x, y)  # 훈련시킴
W, b = logistic.coef_, logistic.intercept_

print(logistic.score(x_train, y_train))
# x, y 둘 다 training data로 훈련시켰을 때 값
print(logistic.score(x_test, y_test))

