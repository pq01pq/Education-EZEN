import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('./auto-mpg.csv', header=None)

df.columns = ['mpg', 'cylinders', 'displacement', 'horsepower', 'weight',
              'acceleration', 'model year', 'origin', 'name']

# 파이차트
df['count'] = 1
print(df)
df_origin = df.groupby('origin').sum()
print(df_origin)

df_origin.index = ['USA', 'EU', 'JAPAN']

df_origin['count'].plot(kind='pie', figsize=(7, 5), autopct='%.1f%%',
                        startangle=90, colors=['red', 'green', 'blue'])
plt.title('Model Origin', size=20)
plt.axis('equal')

plt.show()
