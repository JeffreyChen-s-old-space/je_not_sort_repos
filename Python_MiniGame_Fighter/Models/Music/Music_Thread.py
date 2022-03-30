import threading
import time

import pygame as Pygame_MP3


# 類別 Music_Thread 繼承自執行緒
class Music_Thread(threading.Thread):

    def __init__(self, Music=r"..\Source\Music\Battle-Legendary.mp3", id="id"):
        threading.Thread.__init__(self)
        self.Alive = True
        self.Music = Music
        self.id = id
        # pygame mixer 初始化
        Pygame_MP3.mixer.init()
        print("Pygame has init ", Pygame_MP3.mixer.get_init())
        # pygame mixer 載入音樂
        Pygame_MP3.mixer.music.load(self.Music)
        # pygame mixer 播放音樂
        Pygame_MP3.mixer.music.play()

    def __str__(self):
        return self.Music

    # 一直撥放 除非停止
    def run(self):
        try:
            if (not Pygame_MP3.mixer.music.get_busy() and self.Alive):
                self.Play()
            while self.Alive:
                if (not Pygame_MP3.mixer.music.get_busy() and self.Alive):
                    self.Play()
                if (not Pygame_MP3.mixer.music.get_busy() and self.Alive):
                    time.sleep(1)
        except Exception as horror:
            print(self.id + " Music Play Failed Maybe Window is closed")
            print(horror)

    # 撥放
    def Play(self):
        Pygame_MP3.mixer.init()
        Pygame_MP3.mixer.music.load(self.Music)
        Pygame_MP3.mixer.music.play()

    # 停止撥放
    def Stop_Play(self):
        Pygame_MP3.mixer.music.stop()

    # 是否撥放中
    def Is_Playing(self):
        return Pygame_MP3.mixer.get_busy()

    def Return_Alive(self):
        return self.Alive

    def Dead(self):
        self.Alive = False
        self.Stop_Play()
