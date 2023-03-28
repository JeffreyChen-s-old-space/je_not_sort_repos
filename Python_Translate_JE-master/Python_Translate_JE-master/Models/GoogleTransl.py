from googletrans import Translator

class GoogleTransl():

    def __init__(self):
        self.TextTranslate=Translator()

    #翻譯至繁體中文
    def Translate(self,Text):
        return self.TextTranslate.translate(Text,dest='zh-tw').text