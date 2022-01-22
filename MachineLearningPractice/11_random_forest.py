import pandas as pd
import numpy as np
import seaborn as sns
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn import metrics
import matplotlib.pyplot as plt


iris = load_iris()
# print(iris.DESCR)
# print(iris.target_names)
# print(iris.feature_names)
# print(iris.target)

x = iris.data
y = iris.target
df = pd.DataFrame(x, columns=['sepal_width(cm)', 'sepal_length(cm)', 'petal_width(cm)', 'petal_length(cm)'])
# print(df.head())

x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.3)
# 70% train, 30% test

clf = RandomForestClassifier(n_estimators=100)
# n_estimators : 랜덤포레스트 안에 만들어지는 의사결정나무 갯수

clf.fit(x_train, y_train)  # 훈련시킴

y_pred = clf.predict(x_test)
print('Accuracy:', metrics.accuracy_score(y_test, y_pred))

feature_imp = pd.Series(
    clf.feature_importances_, index=iris.feature_names
).sort_values(ascending=False)
# print(feature_imp)

sns.barplot(x=feature_imp, y=feature_imp.index)
plt.xlabel('Feature importance score')
plt.ylabel('Features')
plt.title('Feature importance visualizing')
plt.show()
