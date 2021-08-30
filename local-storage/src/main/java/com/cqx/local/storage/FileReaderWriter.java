package com.cqx.local.storage;

import java.io.*;
import java.nio.ByteBuffer;

public class FileReaderWriter implements Writer, Reader {

    private final OutputStream outputStream;
    private final FileInputStream inputStream;

    public FileReaderWriter(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        this.outputStream = new FileOutputStream(file);
        this.inputStream = new FileInputStream(file);
    }


    @Override
    public void write(ByteBuffer data) throws IOException {
        outputStream.write(data.array());
    }


    @Override
    public ByteBuffer read(long offset, long size) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1);
        return byteBuffer;
    }
}
