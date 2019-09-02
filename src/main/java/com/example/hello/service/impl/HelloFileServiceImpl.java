package com.example.hello.service.impl;

import com.example.hello.model.HelloFile;
import com.example.hello.repository.HelloFileRepository;
import com.example.hello.service.HelloFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class HelloFileServiceImpl implements HelloFileService {

    @Autowired
    private HelloFileRepository helloFileRepository;

    @Override
    public void saveFile(File file) throws Exception {
        HelloFile helloFile = new HelloFile();
        helloFile.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
        helloFile.setFile(file2Byte(file));
        helloFileRepository.save(helloFile);
    }

    @Override
    public File downloadFile(Integer id) {
        HelloFile helloFile = helloFileRepository.getOne(id);
        String fileName = HelloFileServiceImpl.class.getResource("/").getPath() + "/result." + helloFile.getType();
        byte[] file = helloFile.getFile();
        return byteToFile(file, fileName);
    }

    private static File byteToFile(byte[] bytes, String fileName) {
        File file = null;
        try {
            file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream os = new FileOutputStream(file);
            os.write(bytes);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private static byte[] file2Byte(File file) {
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[is.available()];
            while (is.read(bytes) != -1) {
                bos.write(bytes);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
