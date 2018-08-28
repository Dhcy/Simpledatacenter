package com.good.plat.datacenter.common.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.good.plat.datacenter.common.base.PropertiesUtil;


public class FileHandleUtil {
	
	public static final String contractFilePath=PropertiesUtil.getValue("contractfilepath");
    /**
     * 保存文件到磁盘
     * @Title: saveFile
     * @Description: TODO
     * @param file
     * @param dir
     * @param realName
     * @throws IOException
     * void
     * @author hwj
     * @date 2017-5-12 下午01:49:51
     */
    public static void  saveFile(MultipartFile file,final String dir,String realName) throws IOException {
       String path=dir+File.separator+realName;
       File newFile=new File(path);
       File parentFile=newFile.getParentFile();
       if(!parentFile.exists()){
    	   parentFile.mkdirs();
       }
       if(!newFile.exists()){
    	   newFile.createNewFile();
       }
       //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
       file.transferTo(newFile);
   }
    
    /**
     * 删除单个文件
     * @Title: deleteFile
     * @Description: TODO
     * @param fileName
     * @return
     * boolean
     * @author hwj
     * @date 2017-5-12 下午02:03:45
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
  /**
   * 获取合同文件路径
   * @Title: getContractFilePath
   * @Description: TODO
   * @return
   * String
   * @author hwj
   * @date 2017-5-17 下午02:51:35
   */
    public static String getContractFilePath(){
    	return contractFilePath;
    }
}
