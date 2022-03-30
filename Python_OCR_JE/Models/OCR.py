from PIL import Image
from pytesseract import pytesseract


class OCR:

    def __init__(self):
        self.pyTesserAct = pytesseract

    # 開啟圖片並辨識
    def OpenImage_And_OCR(self, Image_Name):
        """
        :param Image_Name: need to ocr image
        :return: OCR detect string
        """
        OCRImage = Image.open(Image_Name)
        OCRImage = OCRImage.convert('L')
        return self.pyTesserAct.image_to_string(OCRImage)

    # 英文 'eng'、簡體中文 'chi_sim'、繁體中文 'chi_tra' 可以用+的疊加
    def OpenImage_And_OCR_Lang(self, Image_Name, Lang='eng'):
        """
        :param Image_Name: need to ocr image
        :param Lang: OCR detect language
        :return: OCR detect string
        """
        OCRImage = Image.open(Image_Name)
        OCRImage = OCRImage.convert('L')
        return self.pyTesserAct.image_to_string(OCRImage, lang=Lang)
