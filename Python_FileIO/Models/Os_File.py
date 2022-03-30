import os
import pathlib
import glob

class Os_File():

    def __init__(self):
        self.WorkPath=os.getcwd()+'/'

# ----------------------------------------------------------------------------------------------
    #刪除檔案
    def Delete_File(self, File):
        try:
            os.remove(File)
        except OSError as Err:
            print(Err)
        else:
            print(File, "File is deleted successfully", sep=' ')

# ----------------------------------------------------------------------------------------------
    #檢查路徑處文件是否是檔案
    def Check_File(self,Dir_Path):
        Dir_Path = Dir_Path
        File = pathlib.Path(Dir_Path)
        return File.is_file()
# ----------------------------------------------------------------------------------------------
    #用UTF-8寫出
    def Write_UTF_8(self,Name='Note.txt',Mode='w+',Encoding='utf-8',Text='Text'):
        with open(Name,Mode,encoding=Encoding) as File:
            File.write(Text)
            File.flush()

    #用UTF-8寫出陣列形式
    def Write_UTF_8_Lines(self,Name='Note.txt',Mode='w+',Encoding='utf-8',Text_Seq=['Text']):
        with open(Name,Mode,encoding=Encoding) as File:
            File.writelines(Text_Seq)
            File.flush()

# ----------------------------------------------------------------------------------------------
    #寫出 普通
    def Write_UTF_Note(self,Output=open('Note.txt','w+',encoding='utf-8'),Text='Text'):
        Output.write(Text)
        Output.flush()
        Output.close()

    # 寫出 Print
    def Write_UTF_Note_Print(self,Text,Output=open('Note.txt','w+',encoding='utf-8')):
        print(Text,file=Output)
        Output.close()
# ----------------------------------------------------------------------------------------------
    #用UTF-8讀入
    def Read_UTF_8(self,Name='Note.txt',Mode='r+',Encoding='utf-8'):
        with open(Name,Mode,encoding=Encoding) as File:
            File.flush()
            return File.read()

    #用UTF-8讀入陣列 慢慢讀
    def Read_UTF_8_Line(self,Name='Note.txt',Mode='r+',Encoding='utf-8'):
        with open(Name,Mode,encoding=Encoding) as File:
            File_List=[]
            for line in File.readline():
                File.flush()
                File_List.append(line)
            return File_List

    #用UTF-8讀入陣列
    def Read_UTF_8_Lines(self,Name='Note.txt',Mode='r+',Encoding='utf-8'):
        with open(Name,Mode,encoding=Encoding) as File:
            return File.readlines()
# ----------------------------------------------------------------------------------------------
    #不包含子目錄 取得包含特定附檔名的檔案
    def Check_Extension(self,Check_Path):
        #取得該目錄特定附檔名的檔案
        return glob.glob(Check_Path)

    #取得包含子目錄的所有具特定副檔名檔案
    def Check_Extension_All(self,Check_Dir_Path):
        return glob.glob(Check_Dir_Path)

    #不包含子目錄 取得包含特定附檔名的檔案
    def Check_Extension_Lib(self,Dir_Path,File_Extension):
        return list(str(pathlib.Path(Dir_Path).glob(File_Extension)))

# ----------------------------------------------------------------------------------------------
    #取得該目錄的具特定副檔名的檔案列表
    def List_Extension(self,Dir_Path,File_Extension):
        return [_ for _ in os.listdir(Dir_Path) if _.endswith(File_Extension)]

    #取得該目錄具特定附檔名的完整路徑
    def List_Extension_Whole_Path(self, Dir_Path, File_Extension):
        return [os.path.join(Dir_Path, _) for _ in os.listdir(Dir_Path) if _.endswith(File_Extension)]

    #取得該目錄具特定附檔名的完整路徑
    def List_Extension_Whole_Path_Lib(self, Dir_Path, File_Extension):
        return list(str(pathlib.Path(Dir_Path).glob(File_Extension)))

# ----------------------------------------------------------------------------------------------