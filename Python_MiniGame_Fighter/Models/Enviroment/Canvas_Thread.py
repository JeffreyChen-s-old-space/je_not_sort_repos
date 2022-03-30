import threading
import time
# import pyttsx3
from datetime import datetime
from tkinter import messagebox

from PIL import Image, ImageTk

from Models.Enemy.Enemy import Enemy
from Models.Enviroment.BackGround import BackGround
from Models.Music.Music_Thread import Music_Thread
from Models.Objects.Bullet import Bullet
from Models.Player.Player import Player
from Models.Rule.Collision import Collision


# 類別 Canvas_Thread 繼承自執行緒
class Canvas_Thread(threading.Thread):

    # 類別初始化 創立類別時執行
    def __init__(self, window, Canvas, X=0, Y=0, Right_X=500, Bottom_Y=500, id="id", img=None):
        # 執行緒初始化
        threading.Thread.__init__(self)
        # 判斷是否運行旗標
        self.Alive = True
        # 圖片
        self.img = img
        # ID
        self.id = id
        # 視窗
        self.window = window
        # 畫布
        self.Canvas = Canvas
        # 擺放的左 X
        self.X = X
        # 擺放的上 Y
        self.Y = Y
        # 擺放的右X
        self.Right_X = Right_X
        # 擺放的下 Y
        self.Bottom_Y = Bottom_Y
        # 碰撞偵測用
        self.Collision = Collision()
        self.Bullet_Collision = Collision()
        '''
        #語音合成引擎
        self.engine = pyttsx3.init()
        #取得聲音的可用列表
        voices = self.engine.getProperty('voices')
        #設置聲音
        self.engine.setProperty('voice', voices[1].id)
        #設置說話速率
        self.engine.setProperty('rate', 125)
        '''
        # 取得現在時間 用以改難度 (活越久越難)
        self.Time = datetime.now()
        # 設置難度用 最大敵人數量
        self.Hard = 5
        # 設置難度用 最大敵人速度
        self.Speed = 10

        # 玩家用執行緒
        self.Player_Born = None
        # 背景用執行緒
        self.BG = None

        # 用來幫敵人編號
        self.Enemy_Count = 0
        # 敵人列表
        self.Enemy_List = []
        # 子彈列表
        self.Bullet_List = []

        # 背景圖片
        self.Image_Open = Image.open("../Source/Picture/back_ground.png")
        # 更改圖片大小
        self.Image_Open = self.Image_Open.resize((500, 500), Image.ANTIALIAS)
        # 取得圖片
        self.Background_Png = ImageTk.PhotoImage(self.Image_Open)

        # 主角圖片
        self.Image_Open = Image.open("../Source/Picture/air_01_blue.png")
        # 更改圖片大小
        self.Image_Open = self.Image_Open.resize((50, 50), Image.ANTIALIAS)
        # 取得圖片
        self.Player_Png = ImageTk.PhotoImage(self.Image_Open)

        # 敵人圖片
        self.Image_Open = Image.open("../Source/Picture/enemy_type_1.png")
        # 更改圖片大小
        self.Image_Open = self.Image_Open.resize((50, 50), Image.ANTIALIAS)
        # 取得圖片
        self.Enemy_Png = ImageTk.PhotoImage(self.Image_Open)

        # 子彈圖片
        self.Image_Open = Image.open("../Source/Picture/bullet_length.png")
        # 更改圖片大小
        self.Image_Open = self.Image_Open.resize((10, 40), Image.ANTIALIAS)
        # 取得圖片
        self.Player_Bullet_Png = ImageTk.PhotoImage(self.Image_Open)

        # 用以撥放音樂執行緒
        self.Music = Music_Thread(r"..\Source\Music\Battle-Legendary.mp3", id="Start_Music")
        # 執行緒舊術用函數
        self.Thread_Ready(self.Music)

        # 用以計算分數
        self.Score_Time = datetime.now()

    # 當對類別使用str()返回
    def __str__(self):
        return self.X + "self.X\n" + \
               self.Y + "self.Y\n" + \
               self.Right_X + "self.Right_X\n" + \
               self.Bottom_Y + "self.Bottom_Y\n" + \
               self.id + "self.id\n" + \
               self.img + "self.img"

    # 執行緒初始完後會呼叫run
    def run(self):

        # 當狀態為遊戲開始
        if (self.id == "Game_Start"):
            # 背景用執行緒
            self.BG = BackGround(self.Canvas, self.X, self.Y, self.Right_X, self.Bottom_Y, id="BackGround",
                                 img=self.Background_Png)
            self.Thread_Ready(self.BG)
            # 主角用執行緒
            self.Player_Born = Player(self.Canvas, self.X, self.Y, self.Right_X, self.Bottom_Y, id="Player",
                                      img=self.Player_Png)
            self.Thread_Ready(self.Player_Born)
            '''
            # 語音合成 並說出 Game Start
            self.engine.say('Game Start')
            self.engine.runAndWait()
            '''

        # 當此類別活著 (旗標Alive 為True) 和 此個執行緒為遊戲開始執行緒
        while self.Alive and self.id == "Game_Start":
            # 每次執行都停0.1秒
            time.sleep(0.1)
            # 從列表清除死去敵人
            self.clean_enemy()
            # 從列表清除死去子彈
            self.clean_bullet()

            # 當敵人數量小於難度 產生難度隻
            if (len(self.Enemy_List) < self.Hard):
                Enemy_Born = Enemy(self.Canvas, self.X, self.Y, self.Right_X, self.Bottom_Y,
                                   id="Enemy" + str(self.Enemy_Count), img=self.Enemy_Png, Speed=self.Speed)
                # 編號+1
                self.Enemy_Count += 1
                # 產生敵人
                self.Thread_Ready(Enemy_Born)
                # 加入列表
                self.Enemy_List.append(Enemy_Born)

            # 遍尋敵人列表
            for enemy in range(len(self.Enemy_List)):

                # 如果敵人還活著
                if (self.Enemy_List[enemy].Alive == True):

                    # 判斷是否跟玩家碰撞
                    if (self.Collision.Is_Collision(Object_X=self.Enemy_List[enemy].X,
                                                    Object_Y=self.Enemy_List[enemy].Bottom_Y,
                                                    Object_Width=self.Enemy_List[enemy].Width,
                                                    Object_Height=self.Enemy_List[enemy].Height,
                                                    Collision_X=self.Player_Born.X,
                                                    Collision_Y=self.Player_Born.Bottom_Y,
                                                    Collision_Width=self.Player_Born.Width,
                                                    Collision_Heihgt=self.Player_Born.Height)):
                        # 設回False以備下一次碰撞
                        self.Collision.Now_Collision = False
                        # 跟敵人碰撞直接Game Over
                        self.Game_Over()

                    # 遍尋子彈列表
                    for bullet in range(len(self.Bullet_List)):
                        # 創建新碰撞
                        # 如果子彈還活著 判斷是否撞上敵人
                        if (self.Bullet_Collision.Is_Collision(Object_X=self.Bullet_List[bullet].X,
                                                               Object_Y=self.Bullet_List[bullet].Bottom_Y,
                                                               Object_Width=self.Bullet_List[bullet].Width,
                                                               Object_Height=self.Bullet_List[bullet].Height,
                                                               Collision_X=self.Enemy_List[enemy].X,
                                                               Collision_Y=self.Enemy_List[enemy].Bottom_Y,
                                                               Collision_Width=self.Enemy_List[enemy].Width,
                                                               Collision_Heihgt=self.Enemy_List[enemy].Height)):
                            # 如果敵人撞到子彈 敵人死亡
                            self.Enemy_List[enemy].Dead()
                            # 撞到敵人的子彈也死亡
                            self.Bullet_List[bullet].Dead()
                            # 設回False以備下一次碰撞
                            self.Bullet_Collision.Now_Collision = False

            # 如果玩家準備發射狀態為True
            if (self.Player_Born.Shoot == True):
                # 產生子彈
                Bullet_Born = Bullet(self.Canvas, self.Player_Born.X,
                                     (self.Player_Born.Bottom_Y - (
                                         self.Player_Born.Height) * 2) + self.Player_Born.Height, self.Right_X,
                                     self.Bottom_Y - self.Player_Born.Height, id="Player_Bullet",
                                     img=self.Player_Bullet_Png)
                # 開始子彈執行緒
                self.Thread_Ready(Bullet_Born)
                # 加進子彈列表
                self.Bullet_List.append(Bullet_Born)
                # 玩家準備發射狀態為False
                self.Player_Born.Shoot = False

            # 增加難度用時間
            Last_Time = datetime.now()
            # 每10秒增加一次難度
            if ((Last_Time - self.Time).seconds > 10):
                if (self.Hard < 20):
                    self.Hard += 1
                else:
                    print("Hard Over")
                if (self.Speed < 40):
                    self.Speed += 0.5
                else:
                    print("Speed Over")
                self.Time = datetime.now()

    # 執行緒準備用
    def Thread_Ready(self, Thread):
        Thread.setDaemon(True)
        Thread.start()

    # 清掉死掉的子彈
    def clean_bullet(self):
        try:
            for bullet in range(len(self.Bullet_List)):
                if (self.Bullet_List[bullet].Alive == False):
                    self.Bullet_List.remove(self.Bullet_List[bullet])
        except:
            self.clean_bullet()

    # 清掉死掉的敵人
    def clean_enemy(self):
        try:
            for enemy in range(len(self.Enemy_List)):
                if (self.Enemy_List[enemy].Alive == False):
                    self.Enemy_List.remove(self.Enemy_List[enemy])
        except:
            self.clean_enemy()

    # 傳回是否活著
    def Return_Alive(self):
        return self.Alive

    # 死亡
    def Dead(self):
        self.Canvas.delete("all")
        print(self.id + " Dead")
        self.Alive = False
        del self

    # GameOver事件
    def Game_Over(self):
        self.Alive = False
        # 清空所有列表 背景 主角
        for Dead_All in range(len(self.Enemy_List)):
            self.Enemy_List[Dead_All].Dead()
        for Dead_All in range(len(self.Bullet_List)):
            self.Bullet_List[Dead_All].Dead()
        self.BG.Dead()
        self.Player_Born.Dead()
        # 畫布清空
        self.Canvas.delete("all")
        # 關閉音樂
        self.Music.Dead()
        '''
        #間隔0.3秒後唸出 GameOver 並顯示分數
        time.sleep(0.3)
        self.engine.say('Game Over')
        self.engine.runAndWait()
        '''
        # 計算分數用
        Last_Time = datetime.now()
        print("Score: ", (Last_Time - self.Score_Time).seconds)
        # 秀出分數
        if (messagebox.askyesno("Your Score", "Score: " + str((Last_Time - self.Score_Time).seconds))):
            self.window.destroy()
        else:
            self.window.destroy()
