import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error


height= np.array([183, 150, 180, 197, 160, 175])
height = height.reshape(-1, 1)
math = np.array([85, 45, 80, 99, 45, 75])

line_filter = LinearRegression()

line_filter.fit(height, math)  # 훈련시킴
# fit()함수
# line_filter.coef_ : 기울기 저장
# line_filter.intercept_ : 절편 저장
score_predict = line_filter.predict(height)

print(line_filter.coef_, line_filter.intercept_)

print('MSE :', mean_squared_error(score_predict, math))
print('RMSE :', mean_squared_error(score_predict, math)**0.5)
print('score :', line_filter.score(height, math))

# plt.plot(height, math, 'x')
# plt.plot(height, score_predict)
# plt.show()



