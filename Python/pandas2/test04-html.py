import pandas as pd


url = './sample.html'
tables = pd.read_html(url)
print(len(tables))
print()
for i in range(len(tables)):
    print('tables[%d]' % i)
    print(tables[i])
    print()

df = tables[1]
df.set_index(['name'], inplace=True)
print(df)
