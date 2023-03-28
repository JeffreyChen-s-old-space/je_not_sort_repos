using System;
using System.Drawing;
using System.IO;

namespace Base64.Module
{
    public class Base64Conversion
    {
        public static Image Base64ToImage(string base64String)
        {
            // Convert base 64 string to byte[]
            var imageBytes = Convert.FromBase64String(base64String);
            // Convert byte[] to Image
            using (var ms = new MemoryStream(imageBytes, 0, imageBytes.Length))
            {
                var image = Image.FromStream(ms, true);
                return image;
            }
        }
        
        public static string ImageToBase64(Image image,System.Drawing.Imaging.ImageFormat format)
        {
            using (var ms = new MemoryStream())
            {
                // Convert Image to byte[]
                image.Save(ms, format);
                var imageBytes = ms.ToArray();

                // Convert byte[] to base 64 string
                var base64String = Convert.ToBase64String(imageBytes);
                return base64String;
            }
        }
    }
}