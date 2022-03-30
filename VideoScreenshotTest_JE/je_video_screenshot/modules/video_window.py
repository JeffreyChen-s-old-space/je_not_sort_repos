import ctypes
import threading

import cv2
import numpy as np
from PIL import ImageGrab

user32 = ctypes.windll.user32
user32.SetProcessDPIAware()
width, height = [user32.GetSystemMetrics(0), user32.GetSystemMetrics(1)]


class Video_Window(threading.Thread):

    def __init__(self):
        super().__init__()
        self.top_list, self.windows_list = [], []
        self.setDaemon(True)
        self.record = True

    def run(self) -> None:
        print('Start record')
        fourcc = cv2.VideoWriter_fourcc(*'XVID')
        video = cv2.VideoWriter('test.avi', fourcc, 25, (width, height))
        while self.record:
            img_rgb = ImageGrab.grab()
            img_bgr = cv2.cvtColor(np.array(img_rgb), cv2.COLOR_RGB2BGR)
            video.write(img_bgr)
            cv2.imshow("record", img_bgr)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break
        video.release()
        cv2.destroyAllWindows()
