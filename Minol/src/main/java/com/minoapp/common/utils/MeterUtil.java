package com.minoapp.common.utils;

/**
 * Created by Devin on 2017/7/27.
 */

public class MeterUtil {
    public static String getHCAPositon(String p){
        String returns="";
        switch (p){
            case "WZ":
                returns="WZ客厅";
                break;
            case "SZ":
                returns="SZ卧室";
                break;
            case "AZ":
                returns="AZ书房";
                break;
            case "KI":
                returns="KI儿童房  ";
                break;
            case "KU":
                returns="KU厨房";
                break;
            case "BD":
                returns="BD卫生间";
                break;
            case "FL":
                returns="FL走廊";
                break;
            case "XX":
                returns="XX其他";
                break;
        }
        return returns;
    }
}
