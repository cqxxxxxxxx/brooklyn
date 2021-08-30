package com.cqx.local.storage;

import java.nio.ByteBuffer;

public interface Reader {

    ByteBuffer read(long offset, long size);
}
