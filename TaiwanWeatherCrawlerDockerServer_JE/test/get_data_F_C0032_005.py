import configparser
import datetime

from je_taiwan_open_data_core import GovernmentOpenDataCore

config = configparser.ConfigParser()
config.read('key.ini')
key = config['APIKEY']['GovernmentOpenDataKey']
core = GovernmentOpenDataCore(key)

# 一般天氣預報 - 一週縣市天氣預報
data_flag = "F-C0032-005"
core.parse_url = f"https://opendata.cwb.gov.tw/fileapi/v1/opendataapi/{data_flag}?Authorization={key}&downloadType=WEB&format=JSON"
data = core.parse_response_content()['cwbopendata'].get('dataset').get('location')

print(datetime.datetime.now())

for i in range(len(data)):
    print(data[i].get('locationName'))
    tempData = data[i].get('weatherElement')
    for j in range(len(tempData)):
        tempDataTime = tempData[j].get('time')
        for k in range(len(tempDataTime)):
            tempDataTimeParameters = tempDataTime[k]
            temp = tempDataTimeParameters.get('parameter')
            element_string = tempData[j].get('elementName')
            if element_string == 'Wx':
                print(temp.get('parameterName'))
            elif element_string == 'MaxT':
                print(k, '最高溫', temp.get('parameterName'), temp.get('parameterUnit'))
            else:
                print(k, '最低溫', temp.get('parameterName'), temp.get('parameterUnit'))

print(datetime.datetime.now())
