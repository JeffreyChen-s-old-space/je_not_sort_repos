import threading
import tkinter as tk


class Window_Multi_Tkinter(threading.Thread):

    def __init__(self, Title="JE-Chen-Game", Str_Height_Width='500x500', id="id", event=None):
        threading.Thread.__init__(self)
        self.window = tk.Tk()
        self.Canvas = None
        self.Alive = True
        self.Is_Star = False
        self.id = id
        self.Str_Height_Width = Str_Height_Width
        self.Title = Title
        self.window.title(self.Title)
        self.window.geometry(self.Str_Height_Width)
        self.window.resizable(False, False)
        if (event == None):
            self.window.protocol("WM_DELETE_WINDOW", self.Close)
        else:
            self.event = event

    def run(self):
        while self.Alive:
            if not self.Is_Star:
                try:
                    self.window.mainloop()
                    self.Is_Star = True
                except:
                    print(self.id + " 高危錯誤" + "\n" + "run")
            else:
                self.window.update()

    def Close(self):
        self.window.destroy()
        print(self.id + ' Closed')
        del self

    def Dead(self):
        self.Alive = False
        self.window.destroy()
        del self

    def Add_Widget_Label(self, text="Hello World", font=('Arial,12'), fill='x', side='left', anchor='center'):
        self.Label = tk.Label(self.window, text=text, font=font)
        self.Label.pack(fill=fill, side=side, anchor=anchor)

    def Add_Widget_Button(self, command, text="Hello World", font=('Arial,12'), fill='x', side='left', anchor='center'):
        self.Button = tk.Button(self.window, command=command, text=text, font=font)
        self.Button.pack(fill=fill, side=side, anchor=anchor)

    def Add_Widget_Canvas(self, bg='white', height=500, width=500):
        self.Canvas = tk.Canvas(self.window, bg=bg, height=height, width=width)
        self.Canvas.pack()

    def Return_Widget_Canvas(self):
        if (self.Canvas != None):
            return self.Canvas
        else:
            print("No Canvas")

    def Return_Widget_Window(self):
        if (self.window != None):
            return self.window
        else:
            print("No Window")
