package com.example.hello.service;

import java.io.File;

public interface HelloFileService {

    void saveFile(File file) throws Exception;

    File downloadFile(Integer id);
}
