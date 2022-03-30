import ctypes
import ctypes.wintypes

import win32gui
from PIL import ImageGrab


def window_enum_handler(hwnd, resultList):
    if win32gui.IsWindowVisible(hwnd) and win32gui.GetWindowText(hwnd) != '':
        resultList.append((hwnd, win32gui.GetWindowText(hwnd)))


# 取得當前所有視窗名

def get_app_list(handles=None):
    if handles is None:
        handles = []
    window_list = []
    win32gui.EnumWindows(window_enum_handler, handles)
    for handle in handles:
        window_list.append(handle)
    return window_list


def get_window_rect(hwnd):
    try:
        f = ctypes.windll.dwmapi.DwmGetWindowAttribute
    except WindowsError:
        f = None
    if f:
        rect = ctypes.wintypes.RECT()
        DWMWA_EXTENDED_FRAME_BOUNDS = 9
        f(ctypes.wintypes.HWND(hwnd),
          ctypes.wintypes.DWORD(DWMWA_EXTENDED_FRAME_BOUNDS),
          ctypes.byref(rect),
          ctypes.sizeof(rect)
          )
        return rect.left, rect.top, rect.right, rect.bottom


class Screenshot_Window(object):

    def __init__(self):
        self.top_list, self.window_list = [], []

    def enum_cb(self, hwnd, results):
        self.window_list.append((hwnd, win32gui.GetWindowText(hwnd)))

    def enum_windows(self, window_name):
        win32gui.EnumWindows(self.enum_cb, self.top_list)
        window = [(hwnd, title) for hwnd, title in self.window_list if window_name in title.lower()]
        window = window[0]
        hwnd = window[0]
        return hwnd

    # 實際截圖並顯示
    def show_image(self, window_name):
        hwnd = self.enum_windows(window_name)
        win32gui.SetForegroundWindow(hwnd)
        bbox = get_window_rect(hwnd)
        img = ImageGrab.grab(bbox)
        img.show()

    # 取得當前視窗截圖
    def get_now_window_title_picture(self):
        tempWindowName = win32gui.GetWindowText(win32gui.GetForegroundWindow())
        if tempWindowName == win32gui.GetWindowText(win32gui.GetForegroundWindow()):
            x = list(tempWindowName.split())
            if x is None or x == []:
                pass
            else:
                x = str(x[len(x) - 1]).lower()
                hwnd = self.enum_windows(x)
                win32gui.SetForegroundWindow(hwnd)
                bbox = get_window_rect(hwnd)
                img = ImageGrab.grab(bbox)
                img.show()
        else:
            tempWindowName = win32gui.GetWindowText(win32gui.GetForegroundWindow())
            # do what you want
