package com.catchman.serviceImpl;

import com.catchman.model.Userinfo;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOhandler {

    private static final String fileName = "./user.txt";

    public static void writeInfo(String info) {

        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(info);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Userinfo getUserInfo(String name, String password) {
        Userinfo ui = null;
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(new File(fileName))); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); //建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            Gson gson = new Gson();

            line = br.readLine();
            while (line != null) {
                Userinfo u = gson.fromJson(line, Userinfo.class);
                if (u.getName().equals(name) && u.getPassword().equals(password)) {
                    ui=u;
                    break;
                }
                line = br.readLine(); // 一次读入一行数据
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ui;
    }

    public static void main(String[] args) {

    }
}