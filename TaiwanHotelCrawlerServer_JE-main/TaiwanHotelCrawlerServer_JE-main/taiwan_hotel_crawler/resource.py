from apscheduler.schedulers.background import BackgroundScheduler
from je_database import sqlite_core
from je_taiwan_open_data_core import GovernmentOpenDataCore

sql = sqlite_core(r"Hotel.sqlite", table_name="Hotel", check_same_thread=False)
scheduler = BackgroundScheduler()
core = GovernmentOpenDataCore("Not Need Key")
core.parse_url = "https://gis.taiwan.net.tw/XMLReleaseALL_public/hotel_C_f.json"


def create_database():
    sql.create_table("CREATE TABLE IF NOT EXISTS Hotel("
                     "Id VARCHAR (20) PRIMARY KEY,"
                     "Hotel_Name VARCHAR (20),"
                     "Description VARCHAR (500),"
                     "Grade VARCHAR (10),"
                     "Address VARCHAR (10),"
                     "Region VARCHAR (20),"
                     "Town VARCHAR(15),"
                     "Tel VARCHAR (20),"
                     "Website VARCHAR (100),"
                     "Picture1 VARCHAR (30),"
                     "Picdescribe1 VARCHAR (50),"
                     "Picture2 VARCHAR (50),"
                     "Picdescribe2 VARCHAR (50),"
                     "Picture3 VARCHAR (50),"
                     "Picdescribe3 VARCHAR (50),"
                     "Px VARCHAR (15),"
                     "Py VARCHAR (15),"
                     "Serviceinfo VARCHAR (100),"
                     "Parkinginfo VARCHAR (30),"
                     "TotalNumberofRooms VARCHAR (5),"
                     "LowestPrice VARCHAR (6),"
                     "CeilingPrice VARCHAR (6),"
                     "IndustryEmail VARCHAR (50),"
                     "TotalNumberofPeople VARCHAR (5),"
                     "AccessibilityRooms VARCHAR (5),"
                     "PublicToilets VARCHAR (5),"
                     "LiftingEquipment VARCHAR (20),"
                     "ParkingSpace VARCHAR (20))")


def get_crawler_data():
    data = core.parse_response_content(is_utf8_sig=True)['XML_Head'].get('Infos').get('Info')
    for i in range(len(data)):
        sql.insert_into_replace(data[i].get("Id"),
                                data[i].get("Name"),
                                str(data[i].get("Description")).replace(",", ";"),
                                data[i].get("Grade"),
                                str(data[i].get("Add")).replace(",", ";"),
                                data[i].get("Region"),
                                data[i].get("Town"),
                                str(data[i].get("Tel")).replace(",", ";"),
                                data[i].get("Website"),
                                data[i].get("Picture1"),
                                str(data[i].get("Picdescribe1")).replace(",", ";"),
                                data[i].get('Picture2'),
                                str(data[i].get('Picdescribe2')).replace(",", ";"),
                                data[i].get('Picture3'),
                                str(data[i].get('Picdescribe3')).replace(",", ";"),
                                data[i].get('Px'),
                                data[i].get('Py'),
                                str(data[i].get('Serviceinfo')).replace(",", ";"),
                                str(data[i].get('Parkinginfo')).replace(",", ";"),
                                data[i].get('TotalNumberofRooms'),
                                data[i].get('LowestPrice'),
                                data[i].get('CeilingPrice'),
                                data[i].get('IndustryEmail'),
                                data[i].get('TotalNumberofPeople'),
                                data[i].get('AccessibilityRooms'),
                                data[i].get('PublicToilets'),
                                data[i].get('LiftingEquipment'),
                                str(data[i].get('ParkingSpace')).replace(",", ";"))
