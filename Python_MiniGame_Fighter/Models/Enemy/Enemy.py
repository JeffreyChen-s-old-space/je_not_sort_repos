import random
import threading
import time


# Enemy繼承自執行緒
class Enemy(threading.Thread):
    def __init__(self, Canvas, X=0, Y=0, Right_X=500, Bottom_Y=500, id="id", img=None, Height=50, Width=50, Speed=5):
        threading.Thread.__init__(self)
        self.Alive = True
        self.img = img
        self.id = id
        self.Speed = Speed
        self.Canvas = Canvas
        self.X = X
        self.Y = Y
        self.Height = Height
        self.Width = Width
        self.Right_X = Right_X
        self.Bottom_Y = Bottom_Y
        self.lock = threading.Lock()

    def __str__(self):
        return self.X + "self.X\n" + \
               self.Y + "self.Y\n" + \
               self.Right_X + "self.Right_X\n" + \
               self.Bottom_Y + "self.Bottom_Y\n" + \
               self.id + "self.id\n" + \
               self.img + "self.img"

    # 每次run都往下移動
    def run(self):
        # 隨機決定出生位置
        if (self.id.startswith("Enemy")):
            self.X = random.randint(20, 480)
            # 出生在螢幕上方
            self.Bottom_Y = -100
            while self.Alive:

                if (self.Bottom_Y < 600):
                    self.Bottom_Y += self.Speed

                if (self.Bottom_Y >= 600):
                    self.Dead()

                try:
                    # 每0.1秒重繪自己 並刪除舊的自己 以免OutOfMemory
                    self.Canvas.delete(self.id)
                    item = self.Canvas.create_image(self.X, self.Bottom_Y - 30, image=self.img, anchor="center")
                    self.Canvas.itemconfig(item, tag=self.id)
                    time.sleep(0.1)
                except AttributeError:
                    print(self.id + " No Canvas")
                    print(self.id + " Break")
                    break
                except:
                    print(self.id + " 高危錯誤")
                    print(self.id + " Break")
                    break
            if (not self.Alive):
                try:
                    self.Canvas.delete(self.id)
                except:
                    pass

    def Return_Alive(self):
        return self.Alive

    def Dead(self):
        self.Canvas.delete(self.id)
        print(self.id + " Dead")
        self.Alive = False
        del self
