# -*- coding: utf-8 -*-
"""타이타닉_수업ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1kMbhit5YdEDE1sKj5sHqRblSWqUoV748
"""

from google.colab import files
uploaded = files.upload()

import pandas as pd
import io

df = pd.read_csv(io.StringIO(uploaded['train.csv'].decode('utf-8')))
#train 데이터셋을 불러오고 데이터프레임으로 변환

train = df
print(train)

df0 = pd.read_csv(io.StringIO(uploaded['test.csv'].decode('utf-8')))

test = df0
print(test)

train.info()

test.info()

train.head()

test.head()

train.describe(include= 'all')

print(train.columns)
#train data feature들 확인

print(pd.isnull(train).sum())

print(pd.isnull(test).sum())

import matplotlib.pyplot as plt
import seaborn as sns
sns.set()

def pivot(feature) :
  pclass_pivot = train.pivot_table(index=[feature],values = "Survived")
  pclass_pivot.plot.bar()
  plt.show()

pivot('Sex')

pivot('SibSp')

pivot('Pclass')

train_test_data = [train, test] #test 데이터와 train 데이터 합치기

for dataset in train_test_data :
  dataset["Title"] = dataset['Name'].str.extract('([A-za-z]+)\. ', expand=False)

  #이름에서 성별 확인 가능 단어 추출

test['Title'].value_counts

train['Title'].value_counts

title_mapping = {"Mr" : 0, "Miss": 1, "Mrs": 2,
                 "Master": 3, "Dr": 3, "Rev": 3, "Col": 3, "Ms": 2, "Mile": 3, "Major": 3, "Lady": 3, "Capt": 3,
                 "Sir": 3, "Don": 3, "Mme": 3, "Jonkheer": 3, "Countess": 3}
for dataset in train_test_data:
  dataset['Title'] = dataset['Title'].map(title_mapping)
  
  #성별 요인들을 0~3까지로 매핑시키기

train.head()

print(pd.isnull(train).sum())

print(pd.isnull(test).sum())

test["Title"].fillna(df["Title"].mean(), inplace=True)#평균값으로 채우기

train["Title"].fillna(df["Title"].mean(), inplace=True)#평균값으로 채우기

print(pd.isnull(test).sum())

print(pd.isnull(train).sum())

df1 = train
df1['Age'].fillna(df1['Age'].median(),inplace=True)

df2 = test
df2['Age'].fillna(df2['Age'].median(),inplace=True)

#결측치를 중위값으로 처리

train['Age'].isnull().sum()

test['Age'].isnull().sum()

train['Embarked'].value_counts()

train["Embarked"] = train["Embarked"].fillna("S")
#빈값들 s로 넣기

train["Embarked"][train["Embarked"] == "S"] = 0
train["Embarked"][train["Embarked"] == "C"] = 1
train["Embarked"][train["Embarked"] == "Q"] = 2

#train값 mapping 시키기

test["Embarked"][test["Embarked"] == "S"] = 0
test["Embarked"][test["Embarked"] == "C"] = 1
test["Embarked"][test["Embarked"] == "Q"] = 2

train.head()

train['Embarked'].isnull().sum()

test['Embarked'].isnull().sum()

test["Fare"].fillna(df["Fare"].mean(), inplace=True)
#평균값으로 채우기

train = train.drop(['Name'], axis = 1)
test = test.drop(['Name'], axis = 1)

train = train.drop(['PassengerId'], axis = 1)
test = test.drop(['PassengerId'], axis = 1)

train = train.drop(['Titlte'], axis = 1)
test = test.drop(['Titlte'], axis = 1)

train = train.drop(['Cabin'], axis = 1)
test = test.drop(['Cabin'], axis = 1)

train = train.drop(['Ticket'], axis = 1)
test = test.drop(['Ticket'], axis = 1)

sex_mapping = {"male": 0, "female":1}
train['Sex'] = train['Sex'].map(sex_mapping)
test['Sex'] = test['Sex'].map(sex_mapping)

train.head()

test.head()

train.isnull().sum()

test.isnull().sum()

from sklearn.model_selection import train_test_split

pred = train.drop(['Survived'], axis = 1)
target = train["Survived"]

x_train, x_test, y_train, y_test = train_test_split(pred, target, test_size = 0.3)

#예측해야될거가 survived이기 때문에 따로 떼어내서 이걸로 예측을 해보는거

#로지스틱 회귀

from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score


logr = LogisticRegression()
logr.fit(x_train, y_train)
y_pred = logr.predict(x_test)
acc_logr = round(accuracy_score(y_pred, y_test) * 100, 2)
print(acc_logr)

#결정트리

from sklearn.tree import DecisionTreeClassifier

decisiontree = DecisionTreeClassifier()
decisiontree.fit(x_train, y_train)
y_pred = decisiontree.predict(x_test)
acc_decisiontree = round(accuracy_score(y_pred, y_test) * 100, 2)
print(acc_decisiontree)

#랜덤 포레스트

from sklearn.ensemble import RandomForestClassifier

randomforest = RandomForestClassifier()
randomforest.fit(x_train,y_train)
y_pred = randomforest.predict(x_test)
acc_randomforest = round(accuracy_score(y_pred, y_test) * 100, 2)
print(acc_randomforest)

#knn

from sklearn.neighbors import KNeighborsClassifier

knn = KNeighborsClassifier()
knn.fit(x_train, y_train)
y_pred = knn.predict(x_test)
acc_knn = round(accuracy_score(y_pred, y_test) * 100, 2)
print(acc_knn)

models = pd.DataFrame({
    'Model' : ['KNN', 'Logistic Regression', 'Random Forest', 'Deicision Tree'],
    'Score' : [acc_knn, acc_logr, acc_randomforest, acc_decisiontree]
})

models.sort_values(by='Score', ascending=False)

