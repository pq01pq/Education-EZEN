import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn import metrics


iris = load_iris()
# print(iris.keys())
# print(iris['target_names'])
# print(iris['feature_names'])
# print(iris['target'])

x = iris['data']
y = iris['target']

x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.3)

knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(x_train, y_train)
print('Accuracy:', knn.score(x_test, y_test))

k_range = range(1, 50)  # 범위 : 1~50
scores = dict()
scores_list = list()
for k in k_range:
    knn = KNeighborsClassifier(n_neighbors=k)
    knn.fit(x_train, y_train)  # 훈련시킴
    y_prediction = knn.predict(x_test)
    scores[k] = metrics.accuracy_score(y_test, y_prediction)
    scores_list.append(metrics.accuracy_score(y_test, y_prediction))
# k의 지정해놓은 범위 내에서 어떤 k값이 가장 정확도가 높은지 찾고자 함

plt.plot(k_range, scores_list)
plt.xlabel('k value')
plt.ylabel('Accuracy')
plt.show()
