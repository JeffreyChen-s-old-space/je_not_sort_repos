import pyttsx3
from gtts import gTTS

class TextToSpeech():

    def __init__(self):
        self.Engine=pyttsx3.init()
        self.Engine.setProperty('rate',100)

    def Speech_Save(self,Text='Hello World',Language='zh-tw',File_Name='hello.mp3'):
        TTS=gTTS(Text,lang=Language,slow=False)
        TTS.save(File_Name)

    def Speech_Text(self,Text):
        self.Engine.say(Text)
        self.Engine.runAndWait()

    def Set_Rate(self,Rate=100):
        self.Engine.setProperty('rate',Rate)

    def Get_Voice(self):
        return self.Engine.getProperty('voice')