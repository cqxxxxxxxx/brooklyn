package com.cqx.local.storage;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MessageWriter implements Writer {
    private Writer writer;


    public MessageWriter(Writer writer) {
        this.writer = writer;
    }


    @Override
    public void write(ByteBuffer data) throws IOException {


        writer.write(data);
    }
}
