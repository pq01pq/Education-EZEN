import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('./auto-mpg.csv', header=None)

df.columns = ['mpg', 'cylinders', 'displacement', 'horsepower', 'weight',
              'acceleration', 'model year', 'origin', 'name']

cylinders_size = df.cylinders / df.cylinders.max() * 500

# 산점도
df.plot(kind='scatter', x='weight', y='mpg', marker='+', c=cylinders_size, s=50,
        figsize=(10, 5), alpha=0.3, cmap='viridis')

plt.savefig('./scatter.png')
plt.savefig('./scatter_trans.png', transparent=True)
