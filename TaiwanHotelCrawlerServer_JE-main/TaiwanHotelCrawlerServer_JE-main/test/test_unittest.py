import unittest

from je_taiwan_open_data_core import GovernmentOpenDataCore


class TestGetHotel(unittest.TestCase):

    def setUp(self) -> None:
        self.GovernmentOpenDataCore = GovernmentOpenDataCore("Not need")

    def testCrawler(self):
        self.GovernmentOpenDataCore.parse_url = "https://gis.taiwan.net.tw/XMLReleaseALL_public/hotel_C_f.json"
        data = self.GovernmentOpenDataCore.parse_response_content(is_utf8_sig=True)['XML_Head'].get('Infos').get('Info')
        for i in range(len(data)):
            print(data[i])


if __name__ == '__main__':
    suite = (unittest.TestLoader().loadTestsFromTestCase(TestGetHotel))
    unittest.TextTestRunner(verbosity=2).run(suite)
