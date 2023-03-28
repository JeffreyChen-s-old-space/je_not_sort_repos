import datetime

from JECryptography.Module.Decryption import Decryption
from JECryptography.Module.Encryption import Encryption
from JECryptography.Module.Hash import Hash


class CryptographyCore:

    def __init__(self):
        try:
            self.Hash = Hash()
            self.Encryption = Encryption()
            self.Decryption = Decryption()

        except Exception as error:
            raise error
        print(datetime.datetime.now(), self.__class__, 'Ready', sep=' ')
