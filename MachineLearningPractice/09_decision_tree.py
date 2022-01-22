import pandas as pd
import numpy as np
import seaborn as sns
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn import metrics
from sklearn.tree import export_graphviz
import pydotplus
import IPython.display as display
from IPython.display import Image


iris = load_iris()
# print(iris.DESCR)

x = iris.data
y = iris.target

df = pd.DataFrame(x, columns=['sepal_width(cm)', 'sepal_length(cm)', 'petal_width(cm)', 'petal_length(cm)'])
print(df.head())

x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.3)
# 데이터 중에서 70%는 training data고 30%가 test data

# 의사결정나무 생성
clf = DecisionTreeClassifier()

# 의사결정나무 훈련
clf = clf.fit(x_train, y_train)  # 훈련시킴

# 테스트 데이터 셋을 통해서 예측
y_pred = clf.predict(x_test)

# 모델이 얼마나 정확한지 알 수 있음
print('Accuracy:', metrics.accuracy_score(y_test, y_pred))

dot_data = export_graphviz(clf, out_file=None,
                           feature_names=['sepal_width(cm)', 'sepal_length(cm)', 'petal_width(cm)', 'petal_length(cm)'],
                           class_names=iris.target_names, filled=True, rounded=True,
                           special_characters=True)
graph = pydotplus.graph_from_dot_data(dot_data)
Image(graph.create_jpg())
# google colab에서 실행
