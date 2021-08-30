package com.cqx.local.storage;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface Writer {

    void write(ByteBuffer data) throws IOException;
}
