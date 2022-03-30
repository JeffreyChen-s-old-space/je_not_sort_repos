import threading


# 背景類別 繼承自執行緒
class BackGround(threading.Thread):

    def __init__(self, Canvas, X=0, Y=0, Right_X=500, Bottom_Y=500, id="id", img=None):
        threading.Thread.__init__(self)
        self.Alive = True
        self.img = img
        self.id = id
        self.Canvas = Canvas
        self.X = X
        self.Y = Y
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

    def run(self):
        # 畫上背景
        if (self.id == "BackGround"):
            try:
                self.Canvas.delete(self.id)
                item = self.Canvas.create_image(self.X, self.Y, anchor='nw', image=self.img)
                self.Canvas.itemconfig(item, tag=self.id)
            except AttributeError:
                print(self.id + " No Canvas")
            except:
                print(self.id + " Canvas Error")

        if (not self.Alive):
            self.Canvas.delete(self.id)

    def Return_Alive(self):
        return self.Alive

    def Dead(self):
        print(self.id + " Dead")
        self.Alive = False
        del self
