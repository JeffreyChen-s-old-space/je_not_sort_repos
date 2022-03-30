import configparser

from je_taiwan_open_data_core import GovernmentOpenDataCore

config = configparser.ConfigParser()
config.read('key.ini')
key = config['APIKEY']['GovernmentOpenDataKey']
core = GovernmentOpenDataCore(key)

# 天氣特報 - 各別縣市地區目前之天氣警特報情形
data_flag = "W-C0033-001"
core.parse_url = f"https://opendata.cwb.gov.tw/api/v1/rest/datastore/{data_flag}?Authorization={key}"
data = core.parse_response_content()['records'].get('location')
for i in range(len(data)):
    print(data[i].get('locationName'))
    print(data[i].get('geocode'))
    for j in range(len(data[i].get('hazardConditions').get('hazards'))):
        print(j)
