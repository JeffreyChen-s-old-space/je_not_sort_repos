import tkinter
from tkinter import ttk

# Implement the default Matplotlib key bindings.
from matplotlib.backends.backend_tkagg import (
    FigureCanvasTkAgg)
import matplotlib.pyplot as plt
import numpy as np


def open_new_window_with_figure(toplevel_window):
    new_window = tkinter.Toplevel(toplevel_window)
    new_window.title("New Window")
    set_embed_matplotlib(new_window)


def set_embed_matplotlib(new_window):
    figure, ax = plt.subplots()
    people = ('Tom', 'Dick', 'Harry', 'Slim', 'Jim')
    y_pos = np.arange(len(people))
    performance = [10, 20, 30, 40, 50]
    ax.barh(y_pos, performance, align='center')
    ax.set_yticks(y_pos, labels=people)
    ax.invert_yaxis()  # labels read top-to-bottom
    ax.set_xlabel('Performance')
    ax.set_title('How fast do you want to go today?')
    canvas = FigureCanvasTkAgg(figure, master=new_window)  # A tk.DrawingArea.
    canvas.draw()
    button_quit = tkinter.Button(master=new_window, text="Quit", command=new_window.quit)
    button_quit.pack(side=tkinter.BOTTOM)
    canvas.get_tk_widget().pack(side=tkinter.TOP, fill=tkinter.BOTH, expand=1)


main_window = tkinter.Tk()

main_window.geometry()

main_window_label = ttk.Label(main_window, text="This is the test window")

main_window_label.pack(pady=5)

"""
new_window_button = ttk.Button(main_window, text=" Click to open a new window",
                               command=lambda: open_new_window_with_figure(main_window))

new_window_button.pack(pady=5)
"""
set_embed_matplotlib(main_window)

main_window.mainloop()
