package com.cajr.controller.img;

import com.cajr.service.INewsClientService;
import com.cajr.service.IUserClientService;
import com.cajr.vo.ImageResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author CAJR
 * @date 2020/5/7 6:19 下午
 */
@RestController
@Validated
@RequestMapping("/img")
@Api(tags = "图片上传接口",value = "图片上传 rest接口")
public class ConsumerImgController {

    @Autowired
    private INewsClientService iNewsClientService;

    @Autowired
    private IUserClientService iUserClientService;

    @PostMapping("/news/upload")
    public ImageResult uploadNewsImg(@RequestParam("file") @NotNull MultipartFile multipartFile){
        return this.iNewsClientService.uploadNewsImg(multipartFile);
    }

    @PostMapping("/avatar/upload")
    public ImageResult uploadAvatarImg(@RequestParam("file") @NotNull MultipartFile multipartFile){
        return this.iUserClientService.uploadAvatarImg(multipartFile);
    }
}
