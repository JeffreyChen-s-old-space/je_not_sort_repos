import configparser
import datetime

from je_database import sqlite_core
from je_taiwan_open_data_core import GovernmentOpenDataCore

config = configparser.ConfigParser()
config.read('key.ini')
key = config['APIKEY']['GovernmentOpenDataKey']
core = GovernmentOpenDataCore(key)

print(datetime.datetime.now())

# 一般天氣預報 - 一週縣市天氣預報
data_flag = "F-C0032-005"
core.parse_url = f"https://opendata.cwb.gov.tw/fileapi/v1/opendataapi/{data_flag}?Authorization={key}&downloadType=WEB&format=JSON"
data = core.parse_response_content()['cwbopendata'].get('dataset').get('location')

sql = sqlite_core(r"One_Week_Weather.sqlite", table_name="One_Week_Weather")

sql.create_table("CREATE TABLE IF NOT EXISTS One_Week_Weather("
                 "LocationName VARCHAR (10) PRIMARY KEY,"
                 "Weathers_State VARCHAR (200),"
                 "High_Temps_State VARCHAR (100),"
                 "Low_Temps_State VARCHAR (100))")

for i in range(len(data)):
    temp_weathers = ""
    temp_high_temperatures = ""
    temp_low_temperatures = ""
    temperature_data = data[i].get('weatherElement')
    for j in range(len(temperature_data)):
        temperature_data_time = temperature_data[j].get('time')
        for k in range(len(temperature_data_time)):
            temperature_data_time_parameters = temperature_data_time[k]
            temp = temperature_data_time_parameters.get('parameter')
            element_string = temperature_data[j].get('elementName')
            if element_string == 'Wx':
                temp_weathers += (temp.get('parameterName') + ",")
            elif element_string == 'MaxT':
                temp_high_temperatures += (
                        temp.get('parameterName') + temp.get('parameterUnit') + ",")
            else:
                temp_low_temperatures += (
                        temp.get('parameterName') + temp.get('parameterUnit') + ",")

    sql.insert_into_replace(data[i].get('locationName'), temp_weathers, temp_high_temperatures, temp_low_temperatures)

print(datetime.datetime.now())

result = sql.select_where('locationName', '高雄市')
for i in range(len(result)):
    print(result[i])
