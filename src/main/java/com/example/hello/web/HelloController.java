package com.example.hello.web;

import com.example.hello.model.User;
import com.example.hello.service.HelloFileService;
import com.example.hello.service.UserService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Controller
public class HelloController {

    @Autowired
    UserService userService;

    @Autowired
    private HelloFileService helloFileService;

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String index() {

//        User user = userService.findUser();
//        return "Hello World " + user.getName() + "!";
        return "index";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    /**
     * 上传
     * @param request
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public void uploadFile(MultipartHttpServletRequest request) {

        try {
            Iterator<String> iterator = request.getFileNames();
            File file = null;
            while (iterator.hasNext()) {
                String name = iterator.next();
                MultipartFile multipartFile = request.getFile(name);
                String fileName = multipartFile.getOriginalFilename();
                file = new File(Class.class.getResource("/").getPath() + fileName);
                if (file.exists()) {
                    file.delete();
                }
                multipartFile.transferTo(file);
                helloFileService.saveFile(file);
                file.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping(value = "/download")
    @ResponseBody
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        File file = helloFileService.downloadFile(id);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());
        try {
            OutputStream os = response.getOutputStream();
            InputStream is = new FileInputStream(file);
            byte[] bytes = new byte[is.available()];
            while (is.read(bytes) != -1) {
                os.write(bytes);
            }
            os.flush();
            os.close();
            is.close();
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/downloadDoc")
    @ResponseBody
    public void downloadDoc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = userService.findAll();
        // 创建doc文档
        XWPFDocument doc = new XWPFDocument();
        // 创建段落
        XWPFParagraph p = null;
        XWPFRun r = null;
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            p = doc.createParagraph();
            r = p.createRun();//创建段落文本
            r.setText(i + 1 + "." + user.getName());
            r.setBold(true);
            p = doc.createParagraph();
            r = p.createRun();
            r.addTab();
            r.setText(user.getCode());
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + UUID.randomUUID() + ".doc");
        OutputStream os = response.getOutputStream();
        doc.write(os);
        os.close();
    }

    public void transferToFile(InputStream is, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            byte[] buffer = new byte[8000];
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
            os.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFiles(File... files) {
        for(File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }


}
