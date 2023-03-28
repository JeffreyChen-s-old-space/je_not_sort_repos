from translate import Translator
class Translate():

    def __init__(self):
        self.TextTransl=Translator(from_lang='english',to_lang='zh-tw')

    #更改自中文翻譯至英文
    def Change_To_Eng_Mode(self):
        self.TextTransl = Translator(from_lang='zh-tw',to_lang='english')

    #翻譯傳進來的文字
    def Translate(self,Text):
        return self.TextTransl.translate(Text)

