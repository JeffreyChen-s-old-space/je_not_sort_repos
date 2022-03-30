import tkinter as tk
from tkinter import messagebox

from Models.Enviroment.Canvas_Thread import Canvas_Thread
from Models.Music.Music_Thread import Music_Thread

# 用以音樂撥放
Menu_Music = Music_Thread(r"..\Source\Music\Astral_-_Astral_Travel.mp3", id="Menu_Music")


# 按下Play按鈕
def game_start_button():
    # 銷毀Play按鈕
    Start_Button.destroy()
    # 銷毀Explain按鈕
    Explain_Button.destroy()
    # 添加Canvas
    Canvas = tk.Canvas()
    # Canvas 占滿整個螢幕
    Canvas.place(x=0, y=0, relwidth=1, relheight=1)
    # 開啟執行緒 開始遊戲
    thread_ready(Canvas_Thread(window=window, Canvas=Canvas, id="Game_Start"))
    # 更換標題
    window.title("Game-Playing")
    # 清除舊音樂
    Menu_Music.Dead()


# 解釋用按鈕
def explain_button():
    tk.messagebox.showinfo(title="How to Play", message="上下左右鍵操控玩家閃躲來襲的敵機\n按下空白鍵可射擊子彈擊落敵軍\n當被敵軍碰撞即失敗\n請開聲音有2首主題曲")


# 初始設定
window = tk.Tk()
# 設定視窗大小 500x500
window.geometry("500x500")
# 設定視窗背景為黑
window.configure(background='black')
# 設定不可縮放
window.resizable(0, 0)
# 設定標題
window.title("JE-Chen-Game-Start-Scene")
# 讀取背景圖片
BackGround_Photo = tk.PhotoImage(file="../Source/Picture/back_ground.png")
# label 放置背景
BackGround = tk.Label(window, image=BackGround_Photo)
# 占滿整個螢幕
BackGround.place(x=0, y=0, relwidth=1, relheight=1)
# 放置Play按鈕 與上方widget 間隔為 5
Start_Button = tk.Button(text="Play", font=('Arial,12'), command=game_start_button)
Start_Button.pack(anchor='center', pady=5)
# 放置Explain按鈕 與上方widget 間隔為 5
Explain_Button = tk.Button(text="Explain", font=('Arial,12'), command=explain_button)
Explain_Button.pack(anchor='center', pady=5)


# 設置執行緒用函數
def thread_ready(Thread):
    # 設置守護線程 確保主程式關閉執行緒也關閉
    Thread.setDaemon(True)
    # 開始執行緒
    Thread.start()


# 非import一定會執行到
def main():
    # 開始撥放音樂
    thread_ready(Menu_Music)
    # 秀出說明
    tk.messagebox.showinfo(title="How to Play", message="上下左右鍵操控玩家閃躲來襲的敵機\n按下空白鍵可射擊子彈擊落敵軍\n當被敵軍碰撞即失敗\n請開聲音有2首主題曲")


# 直接執行才呼叫
if __name__ == "__main__":
    main()
    window.mainloop()
