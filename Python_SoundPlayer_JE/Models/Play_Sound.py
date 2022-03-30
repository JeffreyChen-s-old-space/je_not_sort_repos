from playsound import playsound

class Play_Sound():

    def __init__(self):
        pass

    #play in main thread
    def Play_MainThread(self,File_Name):
        playsound(File_Name)

    #play in new thread
    def Play_NewThread(self,File_Name):
        import pygame
        pygame.mixer.init()
        pygame.mixer_music.load(File_Name)
        pygame.mixer_music.play()

