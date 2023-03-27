import ctypes
import os
import time

import cv2


def change_bg(image_path):
    SPI_SETDESKWALLPAPER = 20
    ctypes.windll.user32.SystemParametersInfoW(SPI_SETDESKWALLPAPER, 0, image_path, 3)


def show_video_bg(video):
    Cap = cv2.VideoCapture(video)
    while True:
        ret, frame = Cap.read()
        if ret is True:
            print(time.time())
            cv2.imwrite("test.jpg", frame)
            print(time.time())
            change_bg(os.getcwd() + "\\test.jpg")
            print(time.time())
        else:
            Cap.release()
            cv2.destroyAllWindows()


show_video_bg("never gonna give you up.mp4")
