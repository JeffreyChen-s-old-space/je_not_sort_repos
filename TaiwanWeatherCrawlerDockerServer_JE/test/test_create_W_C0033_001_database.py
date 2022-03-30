import configparser
import datetime

from je_database import sqlite_core
from je_taiwan_open_data_core import GovernmentOpenDataCore

config = configparser.ConfigParser()
config.read('key.ini')
key = config['APIKEY']['GovernmentOpenDataKey']
core = GovernmentOpenDataCore(key)

print(datetime.datetime.now())

# 天氣特報 - 各別縣市地區目前之天氣警特報情形
data_flag = "W-C0033-001"
core.parse_url = f"https://opendata.cwb.gov.tw/api/v1/rest/datastore/{data_flag}?Authorization={key}"
data = core.parse_response_content()['records'].get('location')

sql = sqlite_core(r"Hazard_Weather.sqlite", table_name="Hazard_Weather")

sql.create_table("CREATE TABLE IF NOT EXISTS Hazard_Weather("
                 "LocationName VARCHAR (10) PRIMARY KEY,"
                 "Hazards VARCHAR (50))")

for i in range(len(data)):
    temp_hazards_string = ""
    for j in range(len(data[i].get('hazardConditions').get('hazards'))):
        if j is not None:
            temp_hazards_string += (str(j) + ",")
    sql.insert_into_replace(data[i].get('locationName'), temp_hazards_string)

print(datetime.datetime.now())

result = sql.select_where('locationName', '高雄市')
for i in range(len(result)):
    print(result[i])
