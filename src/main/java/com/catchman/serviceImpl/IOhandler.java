package com.catchman.serviceImpl;

import com.catchman.model.Constant;
import com.catchman.model.Userinfo;
import com.google.gson.Gson;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IOhandler {

    private static final String userfileName = Constant.USERINFO;
    private static final String recordfileName = Constant.TESTRECORD;

    public static boolean addUserInfo(Userinfo info) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userfileName, true), "UTF-8"));
            //  FileWriter writer = new FileWriter(userfileName, true);
            writer.write(new Gson().toJson(info) + "\r\n");
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Userinfo getUserInfo(String name, String password, String mac) {
        Userinfo ui = null;
        ArrayList<Userinfo> ulist = new ArrayList<>();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(userfileName), "UTF-8"); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); //建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            Gson gson = new Gson();


            while ((line = br.readLine()) != null) {
                Userinfo u = gson.fromJson(line, Userinfo.class);
                if (u.getName().equals(name) && u.getPassword().equals(password)) {
                    if (u.getPclist() != null && u.getPclist().size() < Integer.parseInt(u.getPcnum())) {
                        ui = u;
                        ArrayList<String> list = ui.getPclist();
                        list.add(mac);
                        ui.setPclist(list);
                    } else {
                        ui = new Userinfo();
                        ui.setPcnum("0");
                    }
                    continue;
                }
                ulist.add(u);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (ui != null && !ui.getPcnum().equals("0")) { //如果不是用户名密码不正确或者mac超过限制
            ulist.add(ui);
            reWriteUserInfo(ulist);//登录后pc数+1
        }
        return ui;
    }


    public static boolean update(Userinfo ui) {
        ArrayList<Userinfo> list = new ArrayList<>();
        Userinfo right_info = null;
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(userfileName), "UTF-8");// 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); //建立一个对象，它把文件内容转成计算机能读懂的语言
            String line;
            Gson gson = new Gson();
            while ((line = br.readLine()) != null) {
                Userinfo u = gson.fromJson(line, Userinfo.class);
                if (u.getName().equals(ui.getName()) && u.getPassword().equals(ui.getPassword())) {
                    right_info = u;
                    continue;
                }
                list.add(u);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        if (right_info != null) {
            if (right_info.getCatchnum() != null && !right_info.getCatchnum().equals("无限制"))
                right_info.setAreadynum(ui.getAreadynum());
            right_info.setCurrentmac(ui.getCurrentmac());
            ArrayList<String> arrayList = right_info.getPclist();
            arrayList.remove(ui.getCurrentmac());
            right_info.setPclist(arrayList);
            list.add(right_info);

        }
        reWriteUserInfo(list);

        return true;
    }

    private static void reWriteUserInfo(ArrayList<Userinfo> userinfos) {
        try {

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userfileName, false), "UTF-8"));
            for (Userinfo userinfo : userinfos) {
                //    System.out.println(new Gson().toJson(userinfo));
                writer.write(new Gson().toJson(userinfo) + "\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否存在测试记录
     *
     * @param mac
     * @return
     */
    public static Boolean isExsitTestRecord(String mac) {
        boolean isExit = false;
        ArrayList<String> recordlist = new ArrayList<>();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(recordfileName), "UTF-8"); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); //建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.split(" ")[0].equals(mac) ){
                    isExit=true;
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return isExit;
    }


    /**
     * 保存测试的mac地址
     *
     * @param mac
     * @return
     */
    public static Boolean saveTestRecord(String mac) {

            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(recordfileName, true), "UTF-8"));
                writer.write(mac + " " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\r\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        return true;
    }

}