from OpenGL.GL import *
from OpenGL.GLUT import *


class GLUT_Window():

    def __init__(self):
        self.Draw_Function = self.Default_Draw

    @staticmethod
    def Default_Draw():
        glClearColor(0.64, 0.64, 0.64, 1)
        # 清除之前畫面
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
        glRotatef(0.1, 0, 5, 0)  # (角度,x,y,z)
        glutWireTeapot(0.5)
        glutSwapBuffers()
        # 刷新顯示
        # glFlush()

    def Set_Draw_Function(self, Draw_Function):
        self.Draw_Function = Draw_Function

    def Show_Window(self, Window_Name=b"Glut_Window", Position_X=0, Position_Y=0, Window_Width=500, Window_Height=500):
        # 使用glut初始化OpenGL
        glutInit()
        '''
        GLUT_RGB	指定RGB顏色模式的窗口
        GLUT_RGBA	指定RGBA 顏色模式的窗口
        GLUT_INDEX	指定顏色索引模式的窗口
        GLUT_SINGLE	指定單緩存窗口
        GLUT_DOUBLE	指定雙緩存窗口
        GLUT_ACCUM	窗口使用累加緩存
        GLUT_ALPHA	窗口的顏色分量包含alpha 值
        GLUT_DEPTH	窗口使用深度緩存
        GLUT_STENCIL	窗口使用模板緩存
        GLUT_MULTISAMPLE	指定支持多樣本功能的窗口
        GLUT_STEREO	指定立體窗口
        GLUT_LUMINANCE	窗口使用亮度顏色模型
        '''
        glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA | GLUT_DEPTH)

        # 窗口位置及大小-生成
        glutInitWindowPosition(Position_X, Position_Y)
        glutInitWindowSize(Window_Width, Window_Height)
        glutCreateWindow(Window_Name)
        # 調用函數繪製圖像
        glutDisplayFunc(self.Draw_Function)
        glutIdleFunc(self.Draw_Function)
        # 主循環
        glutMainLoop()
