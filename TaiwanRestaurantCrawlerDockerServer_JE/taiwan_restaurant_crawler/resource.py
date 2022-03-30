from apscheduler.schedulers.background import BackgroundScheduler
from je_database import sqlite_core
from je_taiwan_open_data_core import GovernmentOpenDataCore

sql = sqlite_core(r"Restaurant.sqlite", table_name="Restaurant", check_same_thread=False)
core = GovernmentOpenDataCore("Not Need Key")
core.parse_url = "https://gis.taiwan.net.tw/XMLReleaseALL_public/restaurant_C_f.json"
scheduler = BackgroundScheduler()


def create_database():
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


def get_crawler_data():
    data = core.parse_response_content(is_utf8_sig=True)["XML_Head"].get("Infos").get("Info")
    for i in range(len(data)):
        sql.insert_into_replace(data[i].get("Id"),
                                data[i].get("Name"),
                                str(data[i].get("Description")).replace(",", ";"),
                                data[i].get("Region"),
                                data[i].get("Town"),
                                data[i].get("Add").replace(",", ";"),
                                str(data[i].get("Tel")).replace(",", ";"),
                                str(data[i].get("Opentime")).replace(",", ";"),
                                data[i].get("Website"),
                                data[i].get("Picture1"),
                                str(data[i].get("Picdescribe1")).replace(",", ";"),
                                data[i].get('Picture2'),
                                str(data[i].get('Picdescribe2')).replace(",", ";"),
                                data[i].get('Picture3'),
                                str(data[i].get('Picdescribe3')).replace(",", ";"),
                                data[i].get('Px'),
                                data[i].get('Py'),
                                str(data[i].get('Parkinginfo')).replace(",", ";"))
