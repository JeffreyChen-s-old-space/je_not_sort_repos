import time


from VoiceIO_Core.VoiceIOCore import VoiceIOCore
a=VoiceIOCore()

a.Play_Sound.Play_MainThread(r'..\Test_Source\test.mp3')
time.sleep(40)

a.TextToSpeech.Speech_Save('你好世界')

a.TextToSpeech.Speech_Text("Hello World")
print(a.TextToSpeech.Get_Voice())
