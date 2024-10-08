import pandas as pd
import psycopg2
import uuid

conn = psycopg2.connect(
    host="localhost",
    database="calorie-tracker-app",
    user="postgres",
    password="B182sucks"
)

cur = conn.cursor()

file_path = '/home/arthurbf/Downloads/sr28abxl/ABBREV.xlsx'
df = pd.read_excel(file_path)

for index, row in df.iterrows():
    food_id = str(uuid.uuid4())

    name = row['Shrt_Desc']
    calories_per_serving = row['Energ_Kcal']
    proteins_per_serving = row['Protein_(g)']
    fats_per_serving = row['Lipid_Tot_(g)']
    carbs_per_serving = row['Carbohydrt_(g)']

    cur.execute("""
        INSERT INTO TB_FOODS (id, name, calories_per_serving, proteins_per_serving, fats_per_serving, carbs_per_serving) 
        VALUES (%s, %s, %s, %s, %s, %s)
    """, (food_id, name, calories_per_serving, proteins_per_serving, fats_per_serving, carbs_per_serving))

conn.commit()

cur.close()
conn.close()
