# -*- coding: utf-8 -*-
"""선형회귀_수업

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1tBaoG5X8ZlQbRx4E9AGELs7WmnncaGp1
"""

# !python --version

import numpy as np

data = [1,2,3,5]

arr = np.array(data)

print(arr.shape)

print(arr.dtype)

np.zeros((10,2))

import pandas as pd

import matplotlib.pyplot as plt

a = np.array([5, 10, 15, 20])
b = np.array([10, 15, 25, 30])

result = pd.DataFrame({
    "a" : a,
    "b" : b
})

print(result)

plt.plot(a, b, 'o')

height = np.array([183, 150, 180, 197, 160, 175])
height = height.reshape(-1,1)

math = np.array([85, 45, 80, 99, 45, 75])

from sklearn.linear_model import LinearRegression

line_fitter = LinearRegression()

line_fitter.fit(height, math)
#fit()함수 : line_fitter.coef_ : 기울기 저장
#            line_fitter.intercept_ : 절편을 저장

score_predict = line_fitter.predict(height)

plt.plot(height, math, 'x')
plt.plot(height, score_predict)
plt.show()

line_fitter.coef_

line_fitter.intercept_

#성능 평가
from sklearn.metrics import mean_squared_error

print("Mean_Sqaured_Error :", mean_squared_error(score_predict, math))

print("RMSE :", mean_squared_error(score_predict, math)**0.5)

print('score: ', line_fitter.score(height, math))

