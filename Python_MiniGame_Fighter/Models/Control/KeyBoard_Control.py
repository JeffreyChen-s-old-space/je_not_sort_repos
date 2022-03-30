import threading

from pynput import keyboard


# 類別 KeyBoard_Control 繼承自執行緒
class KeyBoard_Control(threading.Thread):

    # 類別初始化
    def __init__(self, id="Player_Keyboard"):
        # 執行緒初始化
        threading.Thread.__init__(self)
        # 判斷是否運行旗標
        self.Alive = True
        self.id = id
        self.Key_Value = 'None'

    def run(self):
        while self.Alive:
            with keyboard.Listener(on_press=self.on_press) as listener:
                listener.join()

    def Return_Key_Value(self):
        return self.Key_Value

    def Set_Key_Value(self, Value='None'):
        self.Key_Value = Value

    def on_press(self, key):
        self.Key_Value = key

    def Return_Alive(self):
        return self.Alive

    def Dead(self):
        self.Alive = False
        del self
