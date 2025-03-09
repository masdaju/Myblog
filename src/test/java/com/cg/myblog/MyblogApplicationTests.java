//package com.cg.myblog;
//import com.cg.myblog.mapper.UsersMapper;
//import com.cg.myblog.pojo.Users;
//import com.cg.myblog.utils.FileHandler;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.io.File;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//
//@SpringBootTest
//class MyblogApplicationTests {
//
//    @Autowired
//    UsersMapper usersMapper;
//
//    @Test
//    void contextLoads() {
//        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
//        System.out.println(bCryptPasswordEncoder.encode("123456"));
//    }
//    @Test
//    void sss(){     // 示例文件 (例如，通过打开一个文件)
//        File file = new File("D:\\HDP\\1.jpg");
//
//        // 指定存储文件夹
//        String storageFolder = "C:\\Users\\89394\\Desktop\\实验四";
//
//        // 使用FileHandler类保存文件并获取生成的文件名
//        try {
//            String generatedFileName = FileHandler.saveFile(file, storageFolder);
//            System.out.println("Generated file name: " + generatedFileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//@Test
//    void sadD(){
//    List<Users> users = usersMapper.selectList(null);
//    System.out.println(users);
//}
//}
