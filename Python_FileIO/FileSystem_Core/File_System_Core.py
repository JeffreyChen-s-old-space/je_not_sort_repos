import datetime

from Models.Os_Dir import Os_Dir
from Models.Os_File import Os_File
from Models.Os_Detail import Os_Detail

class File_System_Core():

    def __init__(self):
        try:
            self.Os_Dir=Os_Dir()
            self.Os_File=Os_File()
            self.Os_Detail=Os_Detail()
        except Exception as Err:
            print(Err)
        print(datetime.datetime.now(),self.__class__,'Ready',sep=' ')