import datetime

from Models.OCR import OCR
from Models.OCR_Video import OCR_Video


class OCR_Core:

    def __init__(self):
        try:
            self.OCR = OCR()
            self.OCR_Video = OCR_Video
        except Exception as error:
            print(error)
        print(datetime.datetime.now(), self.__class__, 'Ready', sep=' ')
