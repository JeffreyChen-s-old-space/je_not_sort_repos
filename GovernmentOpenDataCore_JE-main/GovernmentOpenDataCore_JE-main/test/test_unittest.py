import unittest

from je_taiwan_open_data_core import GovernmentOpenDataCore


class TestGovernmentOpenDataCore(unittest.TestCase):

    def setUp(self) -> None:
        self.GovernmentOpenDataCore = GovernmentOpenDataCore("Not need")

    def test_crawler(self):
        # this should be print in sys error parseURL is None
        self.GovernmentOpenDataCore.parse_response_content()
        self.GovernmentOpenDataCore.parse_url = "https://gis.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json"
        print(self.GovernmentOpenDataCore.parse_response_content(is_utf8_sig=True))


if __name__ == '__main__':
    suite = (unittest.TestLoader().loadTestsFromTestCase(TestGovernmentOpenDataCore))
    unittest.TextTestRunner(verbosity=2).run(suite)
