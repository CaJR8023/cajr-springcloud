package com.cajr.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author CAJR
 * @date 2020/4/3 11:21 上午
 */
public interface ImgService {

    String uploadImg(MultipartFile multipartFile, String type) throws IOException;
}
