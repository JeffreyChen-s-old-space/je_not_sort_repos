import threading
import time

from pynput.keyboard import Key

from Models.Control.KeyBoard_Control import KeyBoard_Control


class Player(threading.Thread):

    def __init__(self, Canvas, X=0, Y=0, Right_X=500, Bottom_Y=500, id="id", img=None, Height=50, Width=50,
                 Nuke_Count=3):
        threading.Thread.__init__(self)
        self.Alive = True
        self.img = img
        self.id = id
        self.Canvas = Canvas
        self.X = X
        self.Y = Y
        self.Height = Height
        self.Width = Width
        self.Right_X = Right_X
        self.Nuke_Count = Nuke_Count
        self.Bottom_Y = Bottom_Y
        self.Shoot = False
        self.lock = threading.Lock()

    def __str__(self):
        return self.X + "self.X\n" + \
               self.Y + "self.Y\n" + \
               self.Right_X + "self.Right_X\n" + \
               self.Bottom_Y + "self.Bottom_Y\n" + \
               self.id + "self.id\n" + \
               self.img + "self.img"

    def run(self):
        # 取得鍵盤輸入用
        if (self.id == "Player"):
            self.X = 250
            Control1 = KeyBoard_Control(id="Player_Keyboard_UP")
            self.Thread_Ready(Control1)
            Control2 = KeyBoard_Control(id="Player_Keyboard_DOWN")
            self.Thread_Ready(Control2)
            Control3 = KeyBoard_Control(id="Player_Keyboard_LEFT")
            self.Thread_Ready(Control3)
            Control4 = KeyBoard_Control(id="Player_Keyboard_RIGHT")
            self.Thread_Ready(Control4)
            Control5 = KeyBoard_Control(id="Player_Keyboard_SPACE")
            self.Thread_Ready(Control5)
            # 依鍵盤輸入對應執行
            while (self.Alive):

                if (Control1.Return_Key_Value() == Key.up):
                    Control1.Set_Key_Value()
                    self.Move_UP()

                if (Control2.Return_Key_Value() == Key.down):
                    Control2.Set_Key_Value()
                    self.Move_Down()

                if (Control3.Return_Key_Value() == Key.left):
                    Control3.Set_Key_Value()
                    self.Move_Left()

                if (Control4.Return_Key_Value() == Key.right):
                    Control4.Set_Key_Value()
                    self.Move_Right()

                if (Control5.Return_Key_Value() == Key.space):
                    Control5.Set_Key_Value()
                    self.Bullet()

                try:
                    self.Canvas.delete(self.id)
                    item = self.Canvas.create_image(self.X, self.Bottom_Y - 30, image=self.img, anchor="center")
                    self.Canvas.itemconfig(item, tag=self.id)
                    time.sleep(0.02)
                except AttributeError:
                    print(self.id + " No Canvas")
                    print(self.id + " Break")
                    break
                except:
                    print(self.id + " 高危錯誤")
                    print(self.id + " Break")
                    break
            if (not self.Alive):
                self.Canvas.delete(self.id)

    def Thread_Ready(self, Thread):
        Thread.setDaemon(True)
        Thread.start()

    # 往上移動
    def Move_UP(self):
        if (self.Bottom_Y < 70):
            self.Bottom_Y = 70
        else:
            self.Bottom_Y -= 10

    # 往左移動
    def Move_Left(self):
        if (self.X < 30):
            self.X = 30
        else:
            self.X -= 10

    # 往下移動
    def Move_Down(self):
        if (self.Bottom_Y > 490):
            self.Bottom_Y = 490
        else:
            self.Bottom_Y += 10

    # 往右移動
    def Move_Right(self):
        if (self.X > 470):
            self.X = 470
        else:
            self.X += 10

    # 設置開火準備為True
    def Bullet(self):
        self.Shoot = True

    def Return_Alive(self):
        return self.Alive

    def Dead(self):
        self.Canvas.delete(self.id)
        print(self.id + " Dead")
        self.Alive = False
        del self
