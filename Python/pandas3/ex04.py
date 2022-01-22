import pandas as pd
import numpy as np

'''
시계열 데이터
주식, 환율 등 금융데이터를 다루기 위해 개발된 판다스는
시계열 데이터를 다루는 유용한 기능을 제공함
Timestamp : 특정한 시점을 기록하는 방식

Period : 두 시점 사이의 일정한 기간을 나타내는 방식
'''
df = pd.read_csv('./stock-data.csv')
print(df.head(10))
# 문자열 데이터 Date를 판다스 Timestamp로 변환
df['new_Date'] = pd.to_datetime(df['Date'])
print(df.head(10))
print(df.info())
print(type(df['new_Date'][0]))

# 시계열 값으로 변환된 열을 새로운 행 인덱스로 지정하고 기존 날짜열을 삭제
df.drop('Date', axis=1, inplace=True)
df.rename(columns={'new_Date': 'Date'}, inplace=True)
print(df.info())
df.set_index('Date', inplace=True)
print(df)

