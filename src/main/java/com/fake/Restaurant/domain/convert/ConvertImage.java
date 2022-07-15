package com.fake.Restaurant.domain.convert;

import java.io.IOException;

public interface ConvertImage {
    byte[] imageFileToByte(String path) throws IOException;
    void byteToImageFile(byte[] bytes,String file) throws IOException;
}
