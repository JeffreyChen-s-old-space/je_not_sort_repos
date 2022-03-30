import glfw
import time
from OpenGL.GL import *
from PIL import Image


class GLFW_Window():

    def Default_Size_Change(self, Window, Width, Height):
        print(Window, 'Width: {} Height: {}'.format(Width, Height))
        glViewport(0, 0, Width, Height)

    def Set_Size_Change_Function(self, Function):
        self.Default_Size_Function = Function

    def Default_Close(self, Window):
        print(Window, ' Press title bar X Closed')

    def Set_Close_Function(self, Function):
        self.Default_Close_Function = Function

    def Clear_Color(self, R=0.24, G=0.22, B=0.22, A=1):
        glClearColor(R, G, B, A)

    def Default_Press(self, Window):
        if glfw.get_key(Window, glfw.KEY_ESCAPE) == glfw.PRESS:
            glfw.set_window_should_close(Window, True)
            print(Window, ' Press Escape Closed')

    def Set_Press_Function(self, Function):
        self.Default_Press = Function

    def Set_Icon(self, Icon):
        Image_Icon = Image.open(Icon)
        glfw.set_window_icon(self.Window, 1, Image_Icon)

    def Set_Frame_Time(self, Frame_Time):
        self.FrameTime = Frame_Time

    def __init__(self, Width=500, Height=500, Window_Name="GLFW_Window", X_Pos=200, Y_Pos=200,
                 Icon=r'..\Source\Picture\air_01_blue.png'):

        self.Default_Size_Function = self.Default_Size_Change
        self.Default_Close_Function = self.Default_Close
        self.Default_Press = self.Default_Press

        self.FrameTime = 0.01

        if not glfw.init():
            raise Exception("Check GLFW")

        glfw.window_hint(glfw.CONTEXT_VERSION_MAJOR, 3)
        glfw.window_hint(glfw.CONTEXT_VERSION_MINOR, 3)

        self.Window = glfw.create_window(Width, Height, Window_Name, None, None)

        if not self.Window:
            glfw.terminate()
            raise Exception("Window Create Failure")

        glfw.set_window_pos(self.Window, X_Pos, Y_Pos)

        glfw.make_context_current(self.Window)

        glViewport(X_Pos, Y_Pos, Width, Height)

        Image_Icon = Image.open(Icon)
        glfw.set_window_icon(self.Window, 1, Image_Icon)

        glfw.set_window_close_callback(self.Window, self.Default_Close_Function)

        glfw.set_framebuffer_size_callback(self.Window, self.Default_Size_Function)

        self.Clear_Color()

    def Show_Window(self):

        while not glfw.window_should_close(self.Window):
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

            self.Default_Press(self.Window)

            glfw.swap_buffers(self.Window)

            glfw.poll_events()

            time.sleep(self.FrameTime)

        glfw.terminate()
