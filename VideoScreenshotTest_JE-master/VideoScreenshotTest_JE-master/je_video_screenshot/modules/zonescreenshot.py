# -*- coding:utf-8 -*-
import ctypes
import os
import tkinter
import tkinter.filedialog
from time import sleep
from tkinter import StringVar

from PIL import ImageGrab

user32 = ctypes.windll.user32
user32.SetProcessDPIAware()


class ZoneScreenshot(object):

    def __init__(self, filename='temp.png', root=tkinter.Tk()):
        # 先存一張截圖 用來選取截圖區域
        self.text = StringVar()
        self.root = root
        self.buttonCapture = tkinter.Button(self.root, text='截圖', command=self.button_capture_click)
        self.filename = filename
        im = ImageGrab.grab()
        im.save(filename)
        im.close()
        # 創建tkinter主窗口
        self.root = root
        # 指定主窗口位置與大小
        root.geometry('200x100')
        # 不允許改變窗口大小
        root.resizable(False, False)
        # 變量X和Y用來記錄鼠標左鍵按下的位置
        self.X = tkinter.IntVar(value=0)
        self.Y = tkinter.IntVar(value=0)
        self.selectPosition = None
        # 屏幕尺寸
        screenWidth, screenHeight = [user32.GetSystemMetrics(0), user32.GetSystemMetrics(1)]
        # 創建頂級組件容器
        self.top = tkinter.Toplevel(root, width=screenWidth, height=screenHeight)

        self.canvas = tkinter.Canvas(self.top, bg='white', width=screenWidth, height=screenHeight)
        # 顯示全屏截圖，在全屏截圖上進行區域截圖
        self.image = tkinter.PhotoImage(file=filename)
        self.canvas.create_image(screenWidth // 2, screenHeight // 2, image=self.image)

        # 鼠標左鍵按下的位置
        def on_left_button_down(event):
            self.X.set(event.x)
            self.Y.set(event.y)
            # 開始截圖
            self.sel = True

        self.canvas.bind('<Button-1>', on_left_button_down)

        # 鼠標左鍵移動，顯示選取的區域
        def on_left_button_move(event):
            if not self.sel:
                return
            global lastDraw
            try:
                # 刪除剛畫完的圖形，要不然鼠標移動的時候是黑乎乎的一片矩形
                self.canvas.delete(lastDraw)
            except Exception as error:
                print(error)
            lastDraw = self.canvas.create_rectangle(self.X.get(), self.Y.get(), event.x, event.y, outline='black')

        self.canvas.bind('<B1-Motion>', on_left_button_move)

        # 獲取鼠標左鍵抬起的位置，保存區域截圖
        def on_left_button_up(event):
            self.sel = False
            try:
                self.canvas.delete(lastDraw)
            except Exception as error:
                print(error)
            sleep(0.1)
            # 考慮鼠標左鍵從右下方按下而從左上方抬起的截圖
            myleft, myright = sorted([self.X.get(), event.x])
            mytop, mybottom = sorted([self.Y.get(), event.y])
            self.selectPosition = (myleft, myright, mytop, mybottom)
            pic = ImageGrab.grab((myleft, mytop, myright, mybottom))
            # 彈出保存截圖對話框
            #
            self.fileName = tkinter.filedialog.asksaveasfilename(title='保存截图', filetypes=[('PNG files', '*.png')])
            #
            if self.fileName:
                #
                pic.save(self.fileName + '.png')
            # 關閉當前窗口
            # print(left, '  ', top,'  ',right,'  ',bottom)

            self.top.destroy()

        # 開始截圖
        self.canvas.bind('<ButtonRelease-1>', on_left_button_up)
        self.canvas.pack(fill=tkinter.BOTH, expand=tkinter.YES)

    def button_capture_click(self):
        # 顯示全屏幕截圖
        w = ZoneScreenshot(self.filename)
        self.buttonCapture.wait_window(w.top)
        self.text.set(str(w.selectPosition))
        # 截圖結束，恢復主窗口，並刪除臨時的全屏幕截圖文件
        self.root.state('normal')
        os.remove(self.filename)

    # 秀出Tkinter 視窗
    def start(self):
        self.text.set('請截圖')
        label = tkinter.Label(self.root, textvariable=self.text)
        label.place(x=10, y=30, width=160, height=20)
        label.config(text='New')
        self.buttonCapture.place(x=10, y=10, width=160, height=20)
        # 啟動循環
        # root.update()
        self.root.mainloop()
