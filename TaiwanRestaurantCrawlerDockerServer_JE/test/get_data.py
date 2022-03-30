from je_taiwan_open_data_core import GovernmentOpenDataCore


core = GovernmentOpenDataCore("Not Need Key")
core.parse_url = "https://gis.taiwan.net.tw/XMLReleaseALL_public/restaurant_C_f.json"
data = core.parse_response_content(is_utf8_sig=True)["XML_Head"].get("Infos").get("Info")
for i in range(len(data)):
    print("Name: \t", data[i].get("Name"))
    print("Description: \t", data[i].get("Description"))
    print("Region: \t", data[i].get("Region"))
    print("Town: \t", data[i].get("Town"))
    print("Add: \t", data[i].get("Add"))
    print("Tel: \t", data[i].get("Tel"))
    print("Opentime: \t", data[i].get("Opentime"))
    print("Website: \t", data[i].get("Website"))
    print("Picture1: \t", data[i].get("Picture1"))
    print("Picdescribe1: \t", data[i].get("Picdescribe1"))
    print("Picture2: \t", data[i].get('Picture2'))
    print("Picdescribe2: \t", data[i].get('Picdescribe2'))
    print("Picture3: \t", data[i].get('Picture3'))
    print("Picdescribe3: \t", data[i].get('Picdescribe3'))
    print("Px: \t", data[i].get('Px'))
    print("Py: \t", data[i].get('Py'))
    print("Parkinginfo: \t", data[i].get('Parkinginfo'))
