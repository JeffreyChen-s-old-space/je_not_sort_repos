import configparser
import unittest

from je_taiwan_open_data_core import GovernmentOpenDataCore


class testCrawlerW_C0033_001(unittest.TestCase):

    def setUp(self) -> None:
        config = configparser.ConfigParser()
        config.read('key.ini')
        self.key = config['APIKEY']['GovernmentOpenDataKey']
        self.GovernmentOpenDataCore = GovernmentOpenDataCore(self.key)

    def testCrawlerW_C0033_001(self):
        # 天氣特報 - 各別縣市地區目前之天氣警特報情形
        data_flag = "W-C0033-001"
        self.GovernmentOpenDataCore.parse_url = f"https://opendata.cwb.gov.tw/api/v1/rest/datastore/{data_flag}?Authorization={self.key}"
        print()
        print("testCrawlerW_C0033_001")
        print("-----------------------------------------")
        print(self.GovernmentOpenDataCore.parse_response_content()['records'].get('location'))
        print("-----------------------------------------")
        print()


if __name__ == "__main__":
    suite = (unittest.TestLoader().loadTestsFromTestCase(testCrawlerW_C0033_001))
    unittest.TextTestRunner(verbosity=2).run(suite)
