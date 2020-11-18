package com.chenwuqiang.oa.controller;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private FastFileStorageClient fc;

    @RequestMapping("/upload")
    public void testUpload(MultipartFile filename) throws Exception{
        // 元数据
        Set<MetaData> metaDataSet = new HashSet<MetaData>();
        metaDataSet.add(new MetaData("Author", "qiangge"));
        metaDataSet.add(new MetaData("CreateDate", "2020-11-17"));

        try {
            StorePath uploadFile = null;
            FastFile fastFile = new FastFile(filename.getInputStream(), filename.getSize(), FilenameUtils.getExtension(filename.getOriginalFilename()), metaDataSet);

            uploadFile = fc.uploadFile(fastFile);
            System.out.println(uploadFile.getFullPath());

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping("/down")
    @ResponseBody
    public ResponseEntity<byte[]> down(HttpServletResponse resp) {
        DownloadByteArray cb = new DownloadByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "aaa.jpg");
        byte[] bs = fc.downloadFile("group1", "M00/00/00/wKg6gF-ziuyAFwoeAAOtk9YKJog913.jpg", cb);

        return new ResponseEntity<>(bs,headers, HttpStatus.OK);
    }
}
