package com.fake.Restaurant.domain.convert.Imp;

import com.fake.Restaurant.domain.convert.ConvertImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class ConvertImageImp implements ConvertImage {

    @Override
    public byte[] imageFileToByte(String path) throws IOException {
        File file=new File(path);
        BufferedImage bImage = ImageIO.read(new File(path));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();
        bos.close();
        return data;
    }

    @Override
    public void byteToImageFile(byte[] bytes,String file) throws IOException {
        ByteArrayInputStream byteArrayInputStream=
                new ByteArrayInputStream(bytes);
        BufferedImage bufferedImage=ImageIO.read(byteArrayInputStream);
        ImageIO.write(bufferedImage,"jpg,png,jpeg",new File(file));
        byteArrayInputStream.close();
    }
}
