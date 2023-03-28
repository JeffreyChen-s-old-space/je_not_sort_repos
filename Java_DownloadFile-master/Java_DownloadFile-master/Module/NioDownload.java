package com.je_chen.Module;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class NioDownload {

    public void Download(URL fileURL,String fileName) throws IOException {
        ReadableByteChannel readableByteChannel = Channels.newChannel(fileURL.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        fileOutputStream.getChannel().transferFrom(readableByteChannel,0,Long.MAX_VALUE);
        readableByteChannel.close();
        fileOutputStream.close();
    }
}
