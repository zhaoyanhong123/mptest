package com.test.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.test.oss.service.OssService;
import com.test.oss.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile multipartFile) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCEESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件输入流。
            InputStream inputStream = multipartFile.getInputStream();

            //1.在文件名称里面添加随机唯一的值
            String uuid= UUID.randomUUID().toString().replaceAll("-","");
            //2.把文件按照日期进行分类
            SimpleDateFormat s=new SimpleDateFormat("yyyy/MM/dd");
            String dataPath=s.format(new Date());
            //获取文件名称
            String fileName=dataPath+"/"+uuid+multipartFile.getOriginalFilename();
            //第一个参数bucketName
            //第二个参数 上传到oss文件路径和文件名称
            //第三个参数 上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            String url="https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }
}
