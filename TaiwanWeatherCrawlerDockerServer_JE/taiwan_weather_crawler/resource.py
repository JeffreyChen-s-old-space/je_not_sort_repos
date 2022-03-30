import configparser

from apscheduler.schedulers.background import BackgroundScheduler
from je_database import sqlite_core
from je_taiwan_open_data_core import GovernmentOpenDataCore

config = configparser.ConfigParser()
config.read('key.ini')
key = config['APIKEY']['GovernmentOpenDataKey']
sql_one_weak_weather = sqlite_core(r"One_Week_Weather.sqlite", table_name="One_Week_Weather", check_same_thread=False)
sql_hazard_weather = sqlite_core(r"Hazard_Weather.sqlite", table_name="Hazard_Weather", check_same_thread=False)
one_weak_weather = GovernmentOpenDataCore(key)
hazard_weather = GovernmentOpenDataCore(key)
scheduler = BackgroundScheduler()


def create_database():
    sql_hazard_weather.create_table("CREATE TABLE IF NOT EXISTS Hazard_Weather("
                                    "LocationName VARCHAR (10) PRIMARY KEY,"
                                    "Hazards VARCHAR (50))")

    sql_one_weak_weather.create_table("CREATE TABLE IF NOT EXISTS One_Week_Weather("
                                      "LocationName VARCHAR (10) PRIMARY KEY,"
                                      "Weathers_State VARCHAR (200),"
                                      "High_Temps_State VARCHAR (100),"
                                      "Low_Temps_State VARCHAR (100))")


def get_crawler_F_C0032_005_data():
    data_flag = "F-C0032-005"
    one_weak_weather.parse_url = f"https://opendata.cwb.gov.tw/fileapi/v1/opendataapi/{data_flag}?Authorization={key}&downloadType=WEB&format=JSON"
    data = one_weak_weather.parse_response_content()['cwbopendata'].get('dataset').get('location')
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
                    temp_weathers += (temp.get('parameterName') + ":")
                elif element_string == 'MaxT':
                    temp_high_temperatures += (
                            temp.get('parameterName') + temp.get('parameterUnit') + ":")
                else:
                    temp_low_temperatures += (
                            temp.get('parameterName') + temp.get('parameterUnit') + ":")

        sql_one_weak_weather.insert_into_replace(data[i].get('locationName'),
                                                 temp_weathers,
                                                 temp_high_temperatures,
                                                 temp_low_temperatures)


def get_crawler_W_C0033_001_data():
    data_flag = "W-C0033-001"
    hazard_weather.parse_url = f"https://opendata.cwb.gov.tw/api/v1/rest/datastore/{data_flag}?Authorization={key}"
    data = hazard_weather.parse_response_content()['records'].get('location')
    for i in range(len(data)):
        temp_hazards_string = ""
        for j in (data[i].get('hazardConditions').get('hazards')):
            if j is not None:
                temp_hazards_string = j.get("info").get("phenomena")
        sql_hazard_weather.insert_into_replace(data[i].get('locationName'), temp_hazards_string)
