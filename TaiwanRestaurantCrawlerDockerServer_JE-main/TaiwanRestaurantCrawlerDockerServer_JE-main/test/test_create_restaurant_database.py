import datetime

from je_database import sqlite_core
from je_taiwan_open_data_core import GovernmentOpenDataCore

print(datetime.datetime.now())

sql = sqlite_core(r"Restaurant.sqlite", table_name="Restaurant")
sql.create_table("CREATE TABLE IF NOT EXISTS Restaurant("
                 "Id VARCHAR (20) PRIMARY KEY,"
                 "Restaurant_Name VARCHAR (20),"
                 "Description VARCHAR (500),"
                 "Region VARCHAR (10),"
                 "Town VARCHAR (10),"
                 "Address VARCHAR (20),"
                 "Tel VARCHAR(15),"
                 "Open_Time VARCHAR (20),"
                 "Website VARCHAR (100),"
                 "Picture1 VARCHAR (30),"
                 "Picdescribe1 VARCHAR (50),"
                 "Picture2 VARCHAR (50),"
                 "Picdescribe2 VARCHAR (50),"
                 "Picture3 VARCHAR (50),"
                 "Picdescribe3 VARCHAR (50),"
                 "Px VARCHAR (15),"
                 "Py VARCHAR (15),"
                 "Parkinginfo VARCHAR (30))")

core = GovernmentOpenDataCore("Not Need Key")
core.parse_url = "https://gis.taiwan.net.tw/XMLReleaseALL_public/restaurant_C_f.json"
data = core.parse_response_content(is_utf8_sig=True)["XML_Head"].get("Infos").get("Info")
for i in range(len(data)):
    sql.insert_into_replace(data[i].get("Id"),
                            data[i].get("Name"),
                            data[i].get("Description"),
                            data[i].get("Region"),
                            data[i].get("Town"),
                            data[i].get("Add"),
                            data[i].get("Tel"),
                            data[i].get("Opentime"),
                            data[i].get("Website"),
                            data[i].get("Picture1"),
                            data[i].get("Picdescribe1"),
                            data[i].get('Picture2'),
                            data[i].get('Picdescribe2'),
                            data[i].get('Picture3'),
                            data[i].get('Picdescribe3'),
                            data[i].get('Px'),
                            data[i].get('Py'),
                            data[i].get('Parkinginfo'))
print(datetime.datetime.now())


result = sql.select_where('Region', '高雄市')
for i in range(len(result)):
    print(result[i])
