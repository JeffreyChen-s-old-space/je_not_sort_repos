from __future__ import print_function

import os
import time
from os.path import join
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
class Google_Driver_Upload():

    def __init__(self):
        self.Store = file.Storage('token.json')
        self.Creds = self.Store.get()
        if not self.Creds or self.Creds.invalid:
            flow = client.flow_from_clientsecrets('client_secret.json', SCOPES)
            creds = tools.run_flow(flow, self.Store)
        self.Service = build('drive', 'v3', http=self.Creds.authorize(Http()))

# ----------------------------------------------------------------------------------------------
    def Get_Service(self):
        return self.Service
# ----------------------------------------------------------------------------------------------
    def Delete_File(self,service, file_id):
        service.files().delete(fileId=file_id).execute()
# ----------------------------------------------------------------------------------------------

    def Trashed_File(self,service, is_delete_trashed_file=False):
        """
        抓取到雲端上垃圾桶內的全部檔案，進行刪除
        :param service: 認證用
        :param is_delete_trashed_file: 是否要刪除垃圾桶資料
        :return:
        """
        results = service.files().list(fields="nextPageToken, files(id, name)", spaces='drive', q="trashed = true",
                                       ).execute()
        items = results.get('files', [])
        if not items:
            print('垃圾桶無任何資料.')
        else:
            print('垃圾桶檔案: ')
            for item in items:
                print(u'{0} ({1})'.format(item['name'], item['id']))
                if is_delete_trashed_file is True:
                    print("刪除檔案為:" + u'{0} ({1})'.format(item['name'], item['id']))
                    self.Delete_File(service, file_id=item['id'])
# ----------------------------------------------------------------------------------------------

    def Search_Folder(self,service, Floder_Name=None):
        """
        如果雲端資料夾名稱相同，則只會選擇一個資料夾上傳，請勿取名相同名稱
        :param service: 認證用
        :param Floder_Name: 取得指定資料夾的id，沒有的話回傳None，給錯也會回傳None
        :return:
        """
        get_folder_id_list = []
        print(len(get_folder_id_list))
        if Floder_Name is not None:
            response = service.files().list(fields="nextPageToken, files(id, name)", spaces='drive',
                                            q="name = '" + Floder_Name + "' and mimeType = 'application/vnd.google-apps.folder' and trashed = false").execute()
            for file in response.get('files', []):
                # Process change
                print('Found file: %s (%s)' % (file.get('name'), file.get('id')))
                get_folder_id_list.append(file.get('id'))
            if len(get_folder_id_list) == 0:
                print("你給的資料夾名稱沒有在你的雲端上！，因此檔案會上傳至雲端根目錄")
                return None
            else:
                return get_folder_id_list
        return None

# ----------------------------------------------------------------------------------------------
    def Search_File(self,service, Update_Name, Delete=False):
        """
        本地端
        取得到雲端名稱，可透過下載時，取得file id 下載
        :param service: 認證用
        :param Update_Name: 要上傳到雲端的名稱
        :param Delete: 判斷是否需要刪除這個檔案名稱
        :return:
        """
        # Call the Drive v3 API
        results = service.files().list(fields="nextPageToken, files(id, name)", spaces='drive',
                                       q="name = '" + str(Update_Name) + "' and trashed = false",
                                       ).execute()
        items = results.get('files', [])
        if not items:
            print('沒有發現你要找尋的 ' + Update_Name + ' 檔案.')
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
    def Get_Path_List(self,Update_Path):
        """
        將上傳檔案的資料夾內的路徑以及名稱，全部放到list內
        :param update_files_path: 要上傳檔案的資料夾
        """
        UploadFilesPathList = []
        UploadFilesNameList = []
        for root, dirs, files in os.walk(Update_Path):
            for f in files:
                fullPath = join(root, f)
                UploadFilesPathList.append(fullPath)
                UploadFilesNameList.append(f)
        print("取得要上傳的所有檔案路徑: \n%s" % '\n'.join(UploadFilesPathList))
        return UploadFilesNameList, UploadFilesPathList

# ----------------------------------------------------------------------------------------------
    '''
    功能型 不直接用
    '''
    def Update_In_Folder(self,service, File_Name, File_Path, Floder_ID,Sharable=True):
        """
        將本地端的檔案傳到雲端上
        :param Floder_ID: 判斷是否有 Folder id 沒有的話，會上到雲端的目錄
        :param service: 認證用
        :param File_Path: 本地端的位置
        :param File_Name: 本地端的檔案名稱
        """
        print("正在上傳檔案...")
        if Floder_ID is None:
            file_metadata = {'name': File_Name}
        else:
            print(Floder_ID)
            file_metadata = {'name': File_Name,
                             'parents': Floder_ID}

        media = MediaFileUpload(File_Path, )
        file_metadata_size = media.size()
        start = time.time()
        file_id = service.files().create(body=file_metadata, media_body=media, fields='id').execute()
        end = time.time()
        print("上傳檔案成功！")
        print('雲端檔案名稱為: ' + str(file_metadata['name']))
        print('雲端檔案ID為: ' + str(file_id['id']))
        print('檔案大小為: ' + str(file_metadata_size) + ' byte')
        print("上傳時間為: " + str(end - start))

        return file_metadata['name'], file_id['id']

# ----------------------------------------------------------------------------------------------
    '''
    功能型 不直接用
    '''
    def Upload(self, service, Update_Name, File_Path,Sharable=True):
        """
        將本地端的檔案傳到雲端上
        :param service: 認證用
        :param Update_Name: 存到 雲端上的名稱
        :param File_Path: 本地端的位置
        :param Sharable: 是否分享
        """

        print('------------------------------------------------------------')
        print("正在上傳檔案...")
        file_metadata = {'name': Update_Name}
        media = MediaFileUpload(File_Path, )
        file_metadata_size = media.size()
        start = time.time()
        file_id = service.files().create(body=file_metadata, media_body=media, fields='id').execute()
        end = time.time()
        print("上傳檔案成功！")
        print('------------------------------------------------------------')
        print('雲端檔案名稱為: ' + str(file_metadata['name']))
        print('雲端檔案ID為: ' + str(file_id['id']))
        print('檔案大小為: ' + str(file_metadata_size) + ' byte')
        print("上傳時間為: " + str(end - start))
        print('------------------------------------------------------------')

        return file_metadata['name'], file_id['id']

# ----------------------------------------------------------------------------------------------
    #設置公開
    def Set_Shareable(self,File_Id):
        self.Service.permissions().create(body={"role": "reader", "type": "anyone"}, fileId=File_Id).execute()

    #返回公開的網址
    def Get_Shareable_File_URL(self,File_Id):
        return 'https://drive.google.com/open?id='+str(File_Id)

# ----------------------------------------------------------------------------------------------
    '''
    傳送檔案到根目錄
    '''
    def Update_In_Root(self,Update=False, Update_File_Name=None, Update_File_Path=None,Sharable=True):

        """
        :param Update: 判斷是否執行上傳的功能
        :param Update_File_Name: 要上傳到雲端上的檔案名稱
        :param Update_File_Path: 要上傳檔案的位置以及名稱
        """

        store = file.Storage('token.json')
        creds = store.get()
        if not creds or creds.invalid:
            flow = client.flow_from_clientsecrets('client_secret.json', SCOPES)
            creds = tools.run_flow(flow, store)
        service = build('drive', 'v3', http=creds.authorize(Http()))


        if Update is True:
            print("路徑: "+Update_File_Path +'\t'+'檔案名: '+ Update_File_Name)
            print("=====執行上傳檔案=====")
            # 清空 雲端垃圾桶檔案
            # trashed_file(service=service, is_delete_trashed_file=True)

            # 搜尋要上傳的檔案名稱是否有在雲端上並且刪除
            self.Search_File(service=service, Update_Name=Update_File_Name,
                        Delete=True)
            # 檔案上傳到雲端上
            self.Upload(service=service, Update_Name=Update_File_Name,
                        File_Path='aaa.txt')
            print("=====上傳檔案完成=====")
# ----------------------------------------------------------------------------------------------
    '''
    多個上傳 或單個上傳 至資料夾

    a.Multi_Upload(True,'Google_Drive_Api','aaa.txt',os.getcwd() + '/UploadFiles/') 單個檔案

    a.Multi_Upload(True,'Google_Drive_Api',None,os.getcwd() + '/UploadFiles/') 整個資料夾檔案
    '''

    def Multi_Upload(self, Update=False, Update_Folder=None, File_Name=None,
                     File_Path=None,Sharable=True):
        """
        :param Update_Folder: 給要上傳檔案到雲端的資料夾名稱，預設則是上傳至雲端目錄
        :param Update: 判斷是否執行上傳的功能
        :param File_Name: 要上傳到雲端上的檔案名稱
        :param File_Path: 要上傳檔案的位置以及名稱
        """

        store = file.Storage('token.json')
        creds = store.get()
        if not creds or creds.invalid:
            flow = client.flow_from_clientsecrets('credentials.json', SCOPES)
            creds = tools.run_flow(flow, store)
        service = build('drive', 'v3', http=creds.authorize(Http()))

        if Update is True:

            if File_Name is None:  # 上傳資料夾內的所有檔案會跑這裡
                print("上傳資料夾內，所有檔案")
                UploadFilesName, UploadFilesPath = self.Get_Path_List(Update_Path=File_Path)
                print("=====執行上傳檔案=====")
                get_folder_id = self.Search_Folder(service=service, Floder_Name=Update_Folder)
                print(UploadFilesPath)
                for UploadFileName in UploadFilesName:
                    #  搜尋要上傳的檔案名稱是否有在雲端上並且刪除
                    self.Search_File(service=service, Update_Name=UploadFileName, Delete=True)
                # # 檔案上傳到雲端上
                for i in range(len(UploadFilesPath)):
                    self.Update_In_Folder(service=service, File_Name=UploadFilesName[i],
                                          File_Path=UploadFilesPath[i], Floder_ID=get_folder_id)
                print("=====上傳檔案完成=====")

            else:  # 單純上傳一個檔案會跑這裡
                print(File_Path + '\t' + str(File_Name))
                print("=====執行上傳檔案=====")
                # 清空 雲端垃圾桶檔案
                # trashed_file(service=service, is_delete_trashed_file=True)
                get_folder_id = self.Search_Folder(service=service, Floder_Name=Update_Folder)
                # 搜尋要上傳的檔案名稱是否有在雲端上並且刪除
                self.Search_File(service=service, Update_Name=File_Name,
                                 Delete=True)
                # 檔案上傳到雲端上
                self.Update_In_Folder(service=service, File_Name=File_Name,
                                      File_Path=File_Path + File_Name,
                                      Floder_ID=get_folder_id)
                print("=====上傳檔案完成=====")