from __future__ import print_function
import os
import io
import time
from googleapiclient.discovery import build
from googleapiclient.http import MediaFileUpload, MediaIoBaseDownload
from httplib2 import Http
from oauth2client import file, client, tools

# 權限必須
SCOPES = ['https://www.googleapis.com/auth/drive.file',
    'https://www.googleapis.com/auth/drive',
    'https://www.googleapis.com/auth/drive.file',
    'https://www.googleapis.com/auth/drive.metadata',
    'https://www.googleapis.com/auth/drive.readonly']

getDownloadFileIdList = []
getDownloadFileNameList = []
getDownloadFileMimeTypeList = []

googleFormatList = []
changeGoogleFormat = []

class Google_Driver_Download:

    def __init__(self):
        pass

# ----------------------------------------------------------------------------------------------
    def Delete_File(self,service, file_id):
        service.files().delete(fileId=file_id).execute()

# ----------------------------------------------------------------------------------------------
    def getGoogleDocFormatDict(self):
        """
        如果有透過Google Drive建立的檔案，如試算表, 表格, 文件, 投影片 下載時會去做轉換
        可以參考下方官方連結：
        https://developers.google.com/drive/api/v3/ref-export-formats
        主要看你想要轉成什麼可以從這裡做修改
        記得一個參數都不能少喔！最後一個是要有一個.XXX
        """
        googleDocFormat = {
            "application/vnd.google-apps.spreadsheet": [
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx"],
            "application/vnd.google-apps.presentation": [
                "application/vnd.openxmlformats-officedocument.presentationml.presentation", ".ppt"],
            "application/vnd.google-apps.document": [
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".doc"],
            # "application/vnd.google-apps.document": ["application/pdf", ".pdf"],
            "application/vnd.google-apps.drawing": ["image/p ng", ".png"],
            "application/vnd.google-apps.form": ["application/pdf", ".pdf"]
        }
        return googleDocFormat

# ----------------------------------------------------------------------------------------------
    def Search_Folder(self,service, Folder_Name=None):
        """
        如果雲端資料夾名稱相同，則只會選擇一個資料夾，請勿取名相同名稱
        :param service: 認證用
        :param Folder_Name: 取得指定資料夾的id，沒有的話回傳None，給錯也會回傳None
        """
        if Folder_Name is not None:  # 如果有給參數的話就會跑這裡，否則就直接回傳None
            response = service.files().list(fields="nextPageToken, files(id, name)", spaces='drive',  # 搜尋雲端上的資料夾是否有符合
                                            q="name = '" + Folder_Name + "' and mimeType = 'application/vnd.google-apps.folder' and trashed = false").execute()
            for file in response.get('files', []):  # 搜尋雲端上符合的資料夾名稱，有就會回傳id，沒有的話依樣回傳None
                print('雲端資料夾: %s (%s)' % (file.get('name'), file.get('id')))
                return file.get('id')
        return None

# ----------------------------------------------------------------------------------------------
    def Download_Floder_Files(self,service, File_Id_List, File_Name_List, File_Type_List, Download_Path,
                              Google_Format_Dict):

        """
        :param service: 認證用
        :param File_Id_List: 取得檔案的id list
        :param File_Name_List: 取得檔案的 name list
        :param File_Type_List: 取得檔案的 type
        :param Download_Path: 你要下載到哪個位置
        :param Google_Format_Dict: 取得google drive上的檔案格式
        """
        download_file_path_list = []  # 紀錄下載多少個檔案跟檔案路徑

        # 我們將剛剛的三個list轉成 dict 來做操作下面的流程
        downloadFileDict = dict(zip(File_Id_List, zip(File_Name_List, File_Type_List)))  # key 為 id, value 為 name, type

        if downloadFileDict == {}:
            print('你所提供的檔案名稱不存在或是輸入有錯，記得要加上副檔名')
        else:
            print('下載的檔案名稱以及Id: %s' % str(downloadFileDict))
            if File_Id_List is not None:
                for key, value in downloadFileDict.items():  # key 就是檔案的id, file_name_list = value[0] 是檔案名稱, file_type_list = value[1] 是檔案Type
                    if str(value[1]) in Google_Format_Dict.keys():  # 判斷說 是否是Google上所建立的檔案，是的話用這個方法上傳
                        if str(value[1]) != 'application/vnd.google-apps.form':  # 目前不支援表格，不曉得可以轉成什麼格式就先跳過不下載
                            request = service.files().export_media(fileId=key,
                                                                   mimeType=Google_Format_Dict[value[1]][
                                                                       0])  # fileId是檔案id, mimeTpye是下載轉成的檔案格式

                        local_download_path = Download_Path + str(value[0]) + Google_Format_Dict[value[1]][
                            1]  # 下載位置，最後一個是副檔名，因為google drive 好像沒提供，因此寫一個dict紀錄
                    else:
                        request = service.files().get_media(fileId=key)  # 單純下載之前上傳的檔案，只要給一個fileId就可以
                        local_download_path = Download_Path + str(value[0])  # 下載檔案到你的本地端的檔案會與雲端上名稱相同

                    fh = io.FileIO(local_download_path, 'wb')  # 指定下載檔案位置寫入
                    downloader = MediaIoBaseDownload(fh, request)  # 進行上傳檔案
                    print("下載檔案中....")
                    done = False
                    while done is False:
                        status, done = downloader.next_chunk()
                        print("Download %d%%." % int(status.progress() * 100))
                    # print("下載檔案位置為: ", str(local_download_path))
                    print("=====下載檔案 %s 完成=====" % str(value[0]))
                    download_file_path_list.append(local_download_path)
            else:
                print("=====下載檔案失敗，未找到檔案=====")
            print('雲端檔案下載儲存在你本地端的位置:\n%s' % '\n'.join(download_file_path_list))
            print('檔案下載數量為: %s' % len(download_file_path_list))

# ----------------------------------------------------------------------------------------------
    def Search_File(self,service, Download_Name, Delete=False):
        """
        本地端
        取得到雲端名稱，可透過下載時，取得file id 下載

        :param service: 認證用
        :param Download_Name: 要上傳到雲端的名稱
        :param Delete: 判斷是否需要刪除這個檔案名稱
        :return:
        """
        # Call the Drive v3 API
        results = service.files().list(fields="nextPageToken, files(id, name)", spaces='drive',
                                       q="name = '" + Download_Name + "' and trashed = false",
                                       ).execute()
        items = results.get('files', [])
        if not items:
            print('沒有發現你要找尋的 ' + Download_Name + ' 檔案.')
        else:
            print('搜尋的檔案: ')
            for item in items:
                times = 1
                print(u'{0} ({1})'.format(item['name'], item['id']))
                if Delete is True:
                    print("刪除檔案為:" + u'{0} ({1})'.format(item['name'], item['id']))
                    self.Delete_File(service, file_id=item['id'])

                if times == len(items):
                    return item['id']
                else:
                    times += 1

# ----------------------------------------------------------------------------------------------
    def Search_Folder_File(self,service, Download_Name, Folder_Id):
        """
        :param service: 認證用
        :param Download_Name: 你要下載的檔案如果給None 就是下載資料夾內的檔案，但是不包含裡面的資料夾
        :param Folder_Id: 取得到的資料夾 id
        """
        # 進行雲端搜尋 取得 parents, id, name, mimeType的參數值
        # 篩選方式 不要資料夾以及不要垃圾桶的資料
        results = service.files().list(fields="nextPageToken, files(parents, id, name, mimeType)", spaces='drive',
                                       q="trashed = false and mimeType != 'application/vnd.google-apps.folder'",
                                       ).execute()
        items = results.get('files', [])  # 篩選完的資料放置到items

        for item in items:
            # 因為有些檔案沒有提供 parents參數會導致崩潰，因此用try的方式進行
            try:
                getItemStr = ''.join(item['parents'])  # 取得每個檔案的資料夾 id 轉換成 str 判斷用～
                if getItemStr == Folder_Id:  # 查看每個資料夾id 是否符合我們之前找的資料夾id 有符合就可以找尋這個資料夾底下的參數了
                    if Download_Name is None:  # 如果沒有指定的話(None)，會抓資料夾底下的所有檔案，但不會抓取下一層的資料夾底下的檔案
                        getDownloadFileIdList.append(item['id'])  # 我們需要幾個參數 id, name ,mimeType
                        getDownloadFileNameList.append(item['name'])
                        getDownloadFileMimeTypeList.append(item['mimeType'])
                    else:  # 如果你有給資料夾底下指定的檔案名稱會跑這裡
                        if item['name'] == Download_Name:
                            getDownloadFileIdList.append(item['id'])  # 我們需要幾個參數 id, name ,mimeType
                            getDownloadFileNameList.append(item['name'])
                            getDownloadFileMimeTypeList.append(item['mimeType'])
            except Exception as e:
                pass

# ----------------------------------------------------------------------------------------------
    def DownLoad_File(self,service, File_Id, Download_Path, Download_Name):
        """
        雲端將資料下載到本地端用
        :param service: 認證用
        :param File_Id: 雲端檔案的id 可透過 update_file()取得
        :param download_file_path: 將雲端上的資料下載到本地端的位置
        :param download_drive_service_name:
        """
        if File_Id is not None:  # 如果有取得到檔案id就會跑這裡
            request = service.files().get_media(fileId=File_Id)  # 取得檔案id
            local_download_path = Download_Path + Download_Name  # 你存放在本地端的位置以及名稱
            fh = io.FileIO(local_download_path, 'wb')  # 指定要寫入的檔案位置以及名稱
            downloader = MediaIoBaseDownload(fh, request)  # 使用 下載的功能 Google Drive內建的
            print("下載檔案中....")
            done = False
            while done is False:
                status, done = downloader.next_chunk()
                print("Download %d%%." % int(status.progress() * 100))
            print("下載完成")
            print("下載檔案位置為: ", str(Download_Path + Download_Name))
            print("=====下載檔案完成=====")
        else:  # 沒找到 id 會跑這裏
            print("=====下載檔案失敗，未找到檔案=====")

# ----------------------------------------------------------------------------------------------
    def Start_Download(self,Download=False, Download_Name=None, Download_Path=None,
             Download_Folder=None):
        """
        Start_Download(Download=bool(True), Download_Name='aaa.txt' ,Download_Path=os.getcwd()+'/',Download_Folder=None)
        單個 有同名會失敗

        Start_Download(Download=bool(True), Download_Name=None,Download_Path=os.getcwd()+'/',Download_Folder='Google_Drive_Api')
        多個 有同名會失敗

        :param Download: 是否要開啟下載功能 預設為 False
        :param Download_Name: 你要下載的檔案名稱，如果是選擇資料夾內檔案下載請給None,如果不是請給檔案名稱包含副檔名
                                            假設是Google上建立的檔案就不需要給副檔名
        :param Download_Path: 你要下載到本地端的位置
        :param Download_Folder: 指定雲端上的資料夾名稱，如果沒有請給None，他會搜尋找個雲端上的檔案
                                        如果是在資料夾結構為test/test123/download/的話，你想指定 test123 資料夾
                                        參數可以這樣給 drive_service_folder_name='test123'
        """

        print("Download ? : %s " % Download)  # 確認是否使用 download 功能
        print("Download_Folder : %s " % Download_Folder)  # 選擇的資料夾名稱
        print("Download_Name: %s" % Download_Name)  # 選擇的下載名稱

        # 認證用
        store = file.Storage('token.json')
        creds = store.get()
        if not creds or creds.invalid:
            flow = client.flow_from_clientsecrets('credentials.json', SCOPES)
            creds = tools.run_flow(flow, store)
        service = build('drive', 'v3', http=creds.authorize(Http()))

        # 本地端 執行部分
        if Download is True:
            print("=====執行下載檔案=====")
            get_folder_id = self.Search_Folder(service=service,
                                          Folder_Name=Download_Folder)  # 取得資料夾的id，如果沒有會回傳None

            if get_folder_id is None:  # 判斷說 如果沒有資料夾時，會跑這塊搜尋找個雲端上的檔案 PS:這塊部分我沒有針對Google Drive上所建立的檔案去做下載動作，如果指定Google Drive的檔案會有問題
                drive_service_file_id = self.Search_File(service=service,
                                                    Download_Name=Download_Name)  # 取得要下載
                self.DownLoad_File(service=service, File_Id=drive_service_file_id, Download_Path=Download_Path,
                              Download_Name=Download_Name)  # 下載你指定的檔案名稱到本地端
            else:  # 如果有指定資料夾會跑這塊，進行搜尋指定資料夾底下的檔案
                self.Search_Folder_File(service=service, Download_Name=Download_Name,
                                    Folder_Id=get_folder_id)  # 透過取得的資料夾id 找尋裡面的所有檔案id
                self.Download_Floder_Files(service=service, File_Id_List=getDownloadFileIdList,
                                      Download_Path=Download_Path,  # 執行下載的動作
                                      File_Name_List=getDownloadFileNameList,
                                      File_Type_List=getDownloadFileMimeTypeList,
                                      Google_Format_Dict=self.getGoogleDocFormatDict())
