import pandas as pd
import folium

df = pd.DataFrame(pd.read_excel('./서울지역 대학교 위치.xlsx'))
df.columns = ['학교명', '위도', '경도']
df.set_index('학교명', inplace=True)
# print(df.head(10))

seoul_map = folium.Map(location=[37.55, 126.98],
                       tiles='Stamen Terrain',
                       zoom_start=12)

for name, lat, long in zip(df.index, df.위도, df.경도):
    # folium.Marker([lat, long], popup=name).add_to(seoul_map)
    folium.CircleMarker([lat, long],
                        radius=10,
                        color='brown',
                        fill=True,
                        fill_color='coral',
                        fill_opacity=0.7,
                        popup=name
                        ).add_to(seoul_map)

# seoul_map.save('./seoul_colleges.html')
seoul_map.save('./seoul_colleges2.html')
