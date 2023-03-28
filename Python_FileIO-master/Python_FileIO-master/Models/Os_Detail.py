import platform
import os

class Os_Detail():

    def __init__(self):
        pass

    def Os_Name(self):
        return os.name

    def Os_System(self):
        return platform.system()

    def Os_Machine(self):
        return platform.machine()

    def Os_Platform(self):
        return platform.platform()

    def Os_Uname(self):
        return platform.uname()

    def Os_Version(self):
        return platform.version()

    def Os_MacVer(self):
        return platform.mac_ver()

    def Os_All_Detial(self):
        print(f"system: {platform.system()}",
              f"machine: {platform.machine()}",
              f"platform: {platform.platform()}",
              f"uname: {platform.uname()}",
              f"version: {platform.version()}",
              f"mac_ver: {platform.mac_ver()}",sep='\n')

    def Python_Ver(self):
        return platform.python_version()