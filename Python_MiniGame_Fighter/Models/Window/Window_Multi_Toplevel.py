import threading
import tkinter as tk


# 用來產生新視窗用
class Window_Multi_Toplevel(threading.Thread):

    def __init__(self, window, Title="JE-Chen-Game", Str_Height_Width='500x500', id="id", event=None):
        threading.Thread.__init__(self)
        self.window = tk.Toplevel(window)
        self.Canvas = None
        self.Alive = True
        self.id = id
        self.Str_Height_Width = Str_Height_Width
        self.Title = Title
        # 設定標題
        self.window.title(self.Title)
        # 設定大小
        self.window.geometry(self.Str_Height_Width)
        # 設定不可縮放
        self.window.resizable(False, False)

        # 設定關閉要執行的事件
        if (event == None):
            self.window.protocol("WM_DELETE_WINDOW", self.Close)
        else:
            self.event = event
            self.window.protocol("WM_DELETE_WINDOW", self.event)
            self.Close()

    # 更新視窗
    def run(self):
        while self.Alive:
            self.window.update()

    def Dead(self):
        self.Alive = False
        self.window.destroy()
        del self

    # 關閉執行的事件
    def Close(self):
        self.window.destroy()
        print(self.id + ' Closed')
        del self

    # 添加label
    def Add_Widget_Label(self, text="Hello World", font=('Arial,12'), fill='x', side='left', anchor='center'):
        self.Label = tk.Label(self.window, text=text, font=font)
        self.Label.pack(fill=fill, side=side, anchor=anchor)

    # 添加button
    def Add_Widget_Button(self, command, text="Hello World", font=('Arial,12'), fill='x', side='left', anchor='center'):
        self.Button = tk.Button(self.window, command=command, text=text, font=font)
        self.Button.pack(fill=fill, side=side, anchor=anchor)

    # 添加畫布
    def Add_Widget_Canvas(self, bg='white', height=500, width=500):
        self.Canvas = tk.Canvas(self.window, bg=bg, height=height, width=width)
        self.Canvas.pack()

    # 返回畫布
    def Return_Widget_Canvas(self):
        if (self.Canvas != None):
            return self.Canvas
        else:
            print("No Canvas")

    #
    def Return_Widget_Window(self):
        if (self.Canvas != None):
            return self.window
        else:
            print("No window")
