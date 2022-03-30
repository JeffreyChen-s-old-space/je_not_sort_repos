import threading
import time


# 類別 Bullet 繼承自執行緒
class Bullet(threading.Thread):
    # 類別初始化 創立類別時執行
    def __init__(self, Canvas, X=0, Y=0, Right_X=500, Bottom_Y=500, id="id", Bullet_id="Player1", img=None, Height=50,
                 Width=50):
        # 執行緒初始化
        threading.Thread.__init__(self)
        # 判斷是否運行旗標
        self.Alive = True
        # 圖片
        self.img = img
        # 此執行緒id
        self.id = id
        # 子彈id
        self.Bullet_id = Bullet_id
        # 畫布
        self.Canvas = Canvas
        # 左x
        self.X = X
        # 上 Y
        self.Y = Y
        # 高度
        self.Height = Height
        # 寬度
        self.Width = Width
        # 右X
        self.Right_X = Right_X
        # 下Y
        self.Bottom_Y = Bottom_Y

    # 執行緒初始完後會呼叫run
    def run(self):
        # 當活著
        while self.Alive:
            # 如果由敵人射出 往下移動
            if (self.id.startswith("Enemy")):
                self.Y += 5

                if (self.X >= 500):
                    self.Dead()
                if (self.X <= 0):
                    self.Dead()
                if (self.Y >= 500):
                    self.Dead()
                if (self.Y <= 0):
                    self.Dead()

            # 如果由玩家射出 往上移動
            elif (self.id.startswith("Player")):

                self.Y -= 5

                if (self.X >= 500):
                    self.Dead()
                if (self.X <= 0):
                    self.Dead()
                if (self.Y >= 500):
                    self.Dead()
                if (self.Y <= 0):
                    self.Dead()
            try:
                # 每隔0.2秒重繪一次
                time.sleep(0.05)
                self.Canvas.delete(self.id)
                item = self.Canvas.create_image(self.X, self.Y, image=self.img)
                self.Canvas.itemconfig(item, tag=self.id)
            except AttributeError:
                print(self.id + " No Canvas")
                print(self.id + " Break")
                break
            except:
                print(self.id + " 高危錯誤")
                print(self.id + " Break")
                break
        # 死了就抹除
        if (not self.Alive):
            self.Canvas.delete(self.id)

    def Return_Alive(self):
        return self.Alive

    def Dead(self):
        try:
            self.Canvas.delete(self.id)
            print(self.id + " Dead")
            self.Alive = False
            del self
        except:
            print(self.id + " Canvas Error")
