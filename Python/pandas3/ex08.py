import pandas as pd

df = pd.read_csv('./stock-data.csv')
df['new_Date'] = pd.to_datetime(df['Date'])

df.drop('Date', axis=1, inplace=True)
df.rename(columns={'new_Date': 'Date'}, inplace=True)
df.set_index('Date', inplace=True)

print(df.index)
df_y = df.loc['2018']
print(df_y)
df_m = df.loc['2018-06']
print(df_m)

df_m_col = df.loc['2018-07', 'Start':'High']
print(df_m_col)

df_d = df.loc['2018-07-02']
print(df_d)

df_d_range = df['2018-06-25':'2018-07-20']
print(df_d_range)
