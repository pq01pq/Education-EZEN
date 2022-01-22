import pandas as pd

df = pd.read_csv('./stock-data.csv')
df['new_Date'] = pd.to_datetime(df['Date'])

df['year'] = df['new_Date'].dt.year
df['month'] = df['new_Date'].dt.month
df['day'] = df['new_Date'].dt.day

print(df.head(10))
