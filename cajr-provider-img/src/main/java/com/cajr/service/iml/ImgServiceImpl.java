package com.cajr.service.iml;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectRequest;
import com.cajr.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author CAJR
 * @date 2020/4/3 11:21 上午
 */
@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    private OSS oss;

    @Value("${oss.bucket.name}")
    private String bucketName;

    @Override
    public String uploadImg(MultipartFile multipartFile, HttpServletRequest request, String type) throws IOException {
            String imgFileName = multipartFile.getOriginalFilename();
            String returnUrl = "";
            String userId = request.getHeader("userId");

            try {
                if (!"".equals(imgFileName) && imgFileName != null){
                    String newsImgPath = type+"/img/"+ userId +"/";
                    String fileF = imgFileName.substring(imgFileName.lastIndexOf("."));
                    imgFileName = new Date().getTime() + fileF;

                    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newsImgPath+imgFileName, new ByteArrayInputStream(multipartFile.getBytes()));
                    this.oss.putObject(putObjectRequest);
                    returnUrl = newsImgPath+imgFileName;
                }
            }catch (Exception e){
                e.printStackTrace();
                this.oss.shutdown();
            }
            return returnUrl;
    }
}
