package com.lihuking.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class GmallManageWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void textFileUpload() throws IOException, MyException {
        String file = this.getClass().getResource("/tracker.conf").getFile();
         System.err.println("我是。。"+file);
        ClientGlobal.init(file);
        TrackerClient trackerClient=new TrackerClient();
        //获取连接
        TrackerServer trackerServer=trackerClient.getTrackerServer();
        StorageClient storageClient=new StorageClient(trackerServer,null);
        String orginalFilename="C:\\Users\\Administrator.SC-202008030816\\Pictures\\Saved Pictures\\新建文件夹 (2)\\IMG_1635.JPG";
        //上传图片
        String[] upload_file = storageClient.upload_file(orginalFilename, "JPG", null);
        for (int i = 0; i < upload_file.length; i++) {
            String s = upload_file[i];
            System.out.println("s = " + s);
        }
    }

    @Test
    public void testUp(){
        File file=new File("D:\\maven\\电商\\资料\\day 04\\小米手机\\黑全.jpg");

    }


}
