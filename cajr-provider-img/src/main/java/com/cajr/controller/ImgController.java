package com.cajr.controller;

import com.cajr.service.ImgService;
import com.cajr.util.CommonParams;
import com.cajr.vo.ImageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author CAJR
 * @date 2020/4/3 11:18 上午
 */
@RestController
@RequestMapping("/img")
public class ImgController {

    @Autowired
    private ImgService imgService;

    @PostMapping("/news/upload")
    public ImageResult upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        if (multipartFile.isEmpty()){

        }
        return new ImageResult(this.imgService.uploadImg(multipartFile, request, CommonParams.NEWS_TYPE));
    }
}
