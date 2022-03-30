import numpy as np
import os
import random

from keras.applications.vgg16 import VGG16
from keras.preprocessing import image
from keras.applications.vgg16 import preprocess_input


def Similarity(Rating):
    Similarity = Rating.dot(Rating.T)
    if not isinstance(Similarity, np.ndarray):
        Similarity = Similarity.toarray()
    norms = np.array([np.sqrt(np.diagonal(Similarity))])
    return (Similarity / norms / norms.T)


def main():
    Image_Path = []
    Image_Shapes = []
    for img_path in os.listdir(os.path.join("../Image")):
        if img_path.endswith(".png"):
            print(os.path.abspath("..")+"/Image/"+img_path)
            Image = image.load_img(os.path.abspath("..")+"/Image/"+img_path, target_size=(224, 224))
            Image_Path.append(img_path)
            Image_Shape = image.img_to_array(Image)
            Image_Shape = np.expand_dims(Image_Shape, axis=0)
            if len(Image_Shapes) > 0:
                Image_Shapes = np.concatenate((Image_Shapes, Image_Shape))
            else:
                Image_Shapes = Image_Shape

    x_test = preprocess_input(Image_Shapes)
    model = VGG16(weights='imagenet', include_top=False)
    features = model.predict(x_test)
    features_compress = features.reshape(len(Image_Path), 7 * 7 * 512)
    Similar = Similarity(features_compress)
    Count = random.randint(0,len(os.listdir(os.path.join("../Image"))))
    print("Choice Count " + str(Count))
    top = np.argsort(-Similar[Count], axis=0)[0:2]

    Recommend = [Image_Path[i] for i in top]
    print(Recommend)


if __name__ == "__main__":
    main()