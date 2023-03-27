import json
import sys

import requests


class GovernmentOpenDataCore:

    def __init__(self, open_data_key):
        self.open_data_key = open_data_key
        self.parse_url = ""

    def parse_response_content(self, is_file_type_json=True, is_utf8_sig=False):
        if self.parse_url is "":
            try:
                raise KeyError
            except KeyError:
                print("parseURL is None", file=sys.stderr)
            return
        request = requests.get(self.parse_url)
        if is_utf8_sig:
            response_content = request.content.decode('utf-8-sig')
        else:
            response_content = request.content.decode('utf-8')
        if is_file_type_json:
            response_content = json.loads(response_content)
        return response_content
