import pandas as pd

dates = ['2019-01-01', '2020-03-01', '2021-06-01']
ts_dates = pd.to_datetime(dates)
print(ts_dates)

# Timestamp를 Period로 변환
# freq D : 1일, M : 한달, A : 1년
pr_day = ts_dates.to_period(freq='D')
print(pr_day)
pr_month = ts_dates.to_period(freq='M')
print(pr_month)
pr_annual = ts_dates.to_period(freq='A')
print(pr_annual)
