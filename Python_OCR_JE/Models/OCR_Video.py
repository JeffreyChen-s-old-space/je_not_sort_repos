import cv2
from pytesseract import pytesseract


class OCR_Video:

    @staticmethod
    def video_ocr(Video_Path, Video_Name='Video'):
        """
        :param Video_Path: video file path
        :param Video_Name:  video file name
        """
        Total = []
        Cap = cv2.VideoCapture(Video_Path)
        while True:
            ret, frame = Cap.read()
            if ret:
                cv2.imshow(Video_Name, frame)
                Text = pytesseract.image_to_string(frame)
                print(Text)
                Total.append(Text)
                if cv2.waitKey(1) & 0xFF == ord('q'):
                    break
            else:
                Cap.release()
                cv2.destroyAllWindows()
