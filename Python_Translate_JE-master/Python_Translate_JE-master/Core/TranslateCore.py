import sys
from Models.GoogleTransl import GoogleTransl
from Models.Translate import Translate

class TransCore():

    def __init__(self):
        try:
            self.GoogleTransl=GoogleTransl()
            self.Translate=Translate()
        except Exception as Err:
            print(Err)
            sys.exit()
