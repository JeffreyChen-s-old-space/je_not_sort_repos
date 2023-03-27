import configparser
import unittest

from je_taiwan_open_data_core import GovernmentOpenDataCore


class testCrawlerFC_0032_005(unittest.TestCase):

    def setUp(self) -> None:
        config = configparser.ConfigParser()
        config.read('key.ini')
        self.key = config['APIKEY']['GovernmentOpenDataKey']
        self.GovernmentOpenDataCore = GovernmentOpenDataCore(self.key)

    def testCrawlerFC_0032_005(self):
        # 一般天氣預報 - 一週縣市天氣預報
        data_flag = "F-C0032-005"
        self.GovernmentOpenDataCore.parse_url = f"https://opendata.cwb.gov.tw/fileapi/v1/opendataapi/{data_flag}?Authorization={self.key}&downloadType=WEB&format=JSON"
        print()
        print("testCrawlerFC_0032_005")
        print("-----------------------------------------")
        data = self.GovernmentOpenDataCore.parse_response_content()['cwbopendata'].get('dataset').get('location')
        for i in range(len(data)):
            print(data[i])
        print("-----------------------------------------")
        print()


if __name__ == "__main__":
    suite = (unittest.TestLoader().loadTestsFromTestCase(testCrawlerFC_0032_005))
    unittest.TextTestRunner(verbosity=2).run(suite)
