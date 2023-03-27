import configparser

from je_taiwan_open_data_core import GovernmentOpenDataCore

config = configparser.ConfigParser()
config.read('key.ini')
key = config['APIKEY']['GovernmentOpenDataKey']
core = GovernmentOpenDataCore(key)
# 一般天氣預報 - 七天天氣預報
data_flag = "F-C0032-003"
core.parse_url = f"https://opendata.cwb.gov.tw/fileapi/v1/opendataapi/{data_flag}?Authorization={key}&downloadType=WEB&format=JSON"
print(core.parse_response_content())
