package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.repository.RepoDataCart;
import com.fake.Restaurant.service.AnotherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AnotherServiceImp implements AnotherService {
    private double KICH_THUOC_SAO=30;



    @Autowired
    private RepoDataCart repoDataCart;


    @Override
    public void saveImage(byte[] image,String ten_anh,String vitri) throws IOException {
        Path path=Path.of("src/main/resources/static/photos/memory-"+vitri+"/"+ten_anh+".jpg");
        Files.write(path,image);
    }

    @Override
    public void deleteImage(String ten_anh,String vitri) throws IOException {
        Path path=Path.of("src/main/resources/static/photos/memory-"+vitri+"/"+ten_anh+".jpg");
        Files.delete(path);
    }



}
