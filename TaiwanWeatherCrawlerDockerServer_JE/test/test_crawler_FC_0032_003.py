import configparser
import unittest

from je_taiwan_open_data_core import GovernmentOpenDataCore


class testCrawlerFC_0032_003(unittest.TestCase):

    def setUp(self) -> None:
        config = configparser.ConfigParser()
        config.read('key.ini')
        self.key = config['APIKEY']['GovernmentOpenDataKey']
        self.GovernmentOpenDataCore = GovernmentOpenDataCore(self.key)

    def testCrawlerFC_0032_003(self):
        # 一般天氣預報 - 七天天氣預報
        data_flag = "F-C0032-003"
        self.GovernmentOpenDataCore.parse_url = f"https://opendata.cwb.gov.tw/fileapi/v1/opendataapi/{data_flag}?Authorization={self.key}&downloadType=WEB&format=JSON"
        print()
        print("testCrawlerFC_0032_003")
        print("-----------------------------------------")
        print(self.GovernmentOpenDataCore.parse_response_content())
        print("-----------------------------------------")
        print()


if __name__ == "__main__":
    suite = (unittest.TestLoader().loadTestsFromTestCase(testCrawlerFC_0032_003))
    unittest.TextTestRunner(verbosity=2).run(suite)
