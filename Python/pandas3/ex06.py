import pandas as pd

# date_range() 함수를 이용하여 여러개의 날짜가 들어있는
# 배열 형태의 시계열 데이터를 만들 수 있음
'''
ts_ms = pd.date_range(
    start='2019-01-01',
    end=None,
    periods=6,
    freq='3MS',
    tz='Asia/Seoul'
)
# MS : 월의 시작
# M : 월의 마지막
# 3M : 3달간격 (prefix)

print(ts_ms)
'''

pr_m = pd.period_range(
    start='2019-01-01',
    end=None,
    periods=6,
    freq='H'
)
print(pr_m)
