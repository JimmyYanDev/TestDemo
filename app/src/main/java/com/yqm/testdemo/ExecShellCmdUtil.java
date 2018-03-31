package com.yqm.testdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 执行shell命令的工具类
 *
 * @author michealyan
 * email yanqinming@hymost.com
 * created at 2018/3/31 10:35
 */
public class ExecShellCmdUtil {

    private static Runtime mRuntime = Runtime.getRuntime();


    public static String execShellCmd(String cmd) {
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String result = "";
        BufferedReader bufferedReader = null;
        try {
            Process process = mRuntime.exec(cmd);
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (null != (line = bufferedReader.readLine())) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            result += stringBuilder.toString();
        } catch (IOException e) {
            stringBuilder.append(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
