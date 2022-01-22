import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.linear_model import  LogisticRegression
from sklearn.metrics import accuracy_score
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.neighbors import KNeighborsClassifier


train = pd.DataFrame(pd.read_csv('./train.csv', encoding='utf-8'))
# print(train)
test = pd.DataFrame(pd.read_csv('./test.csv', encoding='utf-8'))
# print(test)

# print(train.info())
# print(test.info())

# print(train.head())
# print(test.head())
# print(train.describe(include='all'))
# print(train.columns)
# print(pd.isnull(train).sum())
# print(pd.isnull(test).sum())
# sns.set()


def pivot(feature):
    pclass_pivot = train.pivot_table(index=[feature], values='Survived')
    pclass_pivot.plot.bar()
    plt.show()

# pivot('Sex')
# pivot('SibSp')
# pivot('Pclass')


train_test = [train, test]
for dataset in train_test:
    dataset["Title"] = dataset['Name'].str.extract(r'([A-Za-z]+)\. ', expand=False)
# 이름에서 성별 확인 가능 단어 추출 Mr. Mrs.
# print(test['Title'].value_counts)
# print(train['Title'].value_counts)
title_mapping = {'Mr': 0, 'Miss': 1, 'Mrs': 2, 'Ms': 2,
                 'Master': 3, 'Dr': 3, 'Rev': 3, 'Col': 3, 'Mile': 3,
                 'Major': 3, 'Lady': 3, 'Capt': 3, 'Sir': 3, 'Don': 3, 'Mme': 3, 'Jonkheer': 3,
                 'Countess': 3}
for dataset in train_test:
    dataset['Title'] = dataset['Title'].map(title_mapping)
# print(train.head())
# print(pd.isnull(train).sum())
# print(pd.isnull(test).sum())
test['Title'].fillna(test['Title'].mean(), inplace=True)
train['Title'].fillna(train['Title'].mean(), inplace=True)
# print(pd.isnull(test).sum())
# print(pd.isnull(train).sum())

df1 = train
df1['Age'].fillna(df1['Age'].median(), inplace=True)

df2 = test
df2['Age'].fillna(df2['Age'].median(), inplace=True)

# 결측치를 중위값으로 처리
# print(train['Age'].isnull().sum())

# print(train['Embarked'].value_counts())
train['Embarked'] = train['Embarked'].fillna('S')
# 빈값들 S로 넣기

train['Embarked'][train['Embarked'] == 'S'] = 0
train['Embarked'][train['Embarked'] == 'C'] = 1
train['Embarked'][train['Embarked'] == 'Q'] = 2
# training값 mapping 시키기

test['Embarked'][test['Embarked'] == 'S'] = 0
test['Embarked'][test['Embarked'] == 'C'] = 1
test['Embarked'][test['Embarked'] == 'Q'] = 2

# print(train.head())
# print(train['Embarked'].isnull().sum())
test['Fare'].fillna(train['Fare'].mean(), inplace=True)
# 평균값 채우기

sex_map = {'male': 0, 'female': 1}
train['Sex'] = train['Sex'].map(sex_map)
test['Sex'] = test['Sex'].map(sex_map)

train.drop(['Name'], axis=1, inplace=True)
test.drop(['Name'], axis=1, inplace=True)

train.drop(['PassengerId'], axis=1, inplace=True)
test.drop(['PassengerId'], axis=1, inplace=True)

train.drop(['Cabin'], axis=1, inplace=True)
test.drop(['Cabin'], axis=1, inplace=True)

train.drop(['Ticket'], axis=1, inplace=True)
test.drop(['Ticket'], axis=1, inplace=True)

# print(train.head())
# print(test.head())
# print(train.isnull().sum())
# print(test.isnull().sum())

pred = train.drop(['Survived'], axis=1)
target = train['Survived']

x_train, x_test, y_train, y_test = train_test_split(pred, target, test_size=0.3)
# 예측해야될 것이 survived이기 때문에 따로 떼어내서 예측을 해봄

# 로지스틱 회귀
logr = LogisticRegression()
logr.fit(x_train, y_train)
y_pred = logr.predict(x_test)
acc_logr = round(accuracy_score(y_pred, y_test) * 100, 2)
print(acc_logr)

# 결정 트리
decision_tree = DecisionTreeClassifier()
decision_tree.fit(x_train, y_train)
y_pred = decision_tree.predict(x_test)
acc_decision_tree = round(accuracy_score(y_pred, y_test) * 100, 2)
print(acc_decision_tree)

# 랜덤 포레스트
random_forest = RandomForestClassifier()
random_forest.fit(x_train, y_train)
y_pred = random_forest.predict((x_test))
acc_random_forest = round(accuracy_score(y_pred, y_test) * 100, 2)
print(acc_random_forest)

# knn
knn = KNeighborsClassifier()
knn.fit(x_train, y_train)
y_pred = knn.predict(x_test)
acc_knn = round(accuracy_score(y_pred, y_test) * 100, 2)
print(acc_knn)

models = pd.DataFrame({
    'Model': ['KNN', 'Logistic Regression', 'Random Forest', 'Decision Tree'],
    'Score': [acc_knn, acc_logr, acc_random_forest, acc_decision_tree]
})
models.sort_values(by='Score', ascending=False)
print(models)

