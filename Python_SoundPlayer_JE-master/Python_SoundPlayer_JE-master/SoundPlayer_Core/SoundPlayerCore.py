import datetime

from Models.TextToSpeech import TextToSpeech
from Models.Play_Sound import Play_Sound

class VoiceIOCore():

    def __init__(self):
        try:
            self.TextToSpeech=TextToSpeech()
            self.Play_Sound=Play_Sound()
        except Exception as Errr:
            print(Errr)
        print(datetime.datetime.now(),self.__class__,'Reday',sep=' ')
