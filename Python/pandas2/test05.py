import pandas as pd

data = {
    'name': ['철수', '영희', '수미'],
    '국어': [100, 90, 80],
    '영어': [90, 95, 90],
    '수학': [100, 80, 95]
}

data2 = {
    'c0': [1, 2, 3],
    'c1': [4, 5, 6],
    'c2': [7, 8, 9],
    'c3': [10, 11, 12],
    'c4': [13, 14, 15]
}

df = pd.DataFrame(data)
df.set_index('name', inplace=True)
print(df)

print()
df2 = pd.DataFrame(data2)
df2.set_index('c0', inplace=True)
print(df2)

# df.to_csv('./df_csv_sample.csv')

# df.to_json('./df_json_sample.json')

# df.to_excel('./df_excel_sample.xlsx')

# 2개 이상의 데이터를 엑셀로 보내기
writer = pd.ExcelWriter('./df_excelwriter.xlsx')
df.to_excel(writer, sheet_name='sheet1')
df2.to_excel(writer, sheet_name='sheet2')
writer.save()
