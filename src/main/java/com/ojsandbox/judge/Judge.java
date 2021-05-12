package com.ojsandbox.judge;

import com.ojsandbox.entity.Result;
import com.ojsandbox.entity.Submit;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @author zhang run kai
 * @version 1.0
 * @date 2021/5/12 20:27
 */
public class Judge implements Callable<Result> {

    private Submit submit;
    private static String codePath = "/home/oj/sandbox/";
    private static String err = "/home/oj/sandbox/err";
    private static String cppCmd = "docker run -d -v /home/oj/sandbox:/home judge /bin/sh -c \"g++ code.cpp 2>err;./a.out <input >output\" >dockerId";

    public Judge(Submit submit){
        this.submit = submit;
    }

    @Override
    public Result call() throws Exception {
        Integer codeType = submit.getSubmitType();
        String code = submit.getSubmitCode();
        if (codeType == null) {

        }
        else if (codeType == 1) {
            writeFile(codePath+"code.c",code);

        }
        else if (codeType == 2){
            exec("rm input err output",null, null);
            writeFile(codePath+"code.cpp",code);
            exec(cppCmd,null,null);
            Thread.sleep(submit.getRunTime());
            exec("docker stop $(< dockerId)",null, null);
//            exec("docker logs $(<dockerId) > codeOut",null,null);
        }
        else if (codeType == 2){
            writeFile(codePath+"code.java",code);
        }
        File errFile = new File(err);
        if (errFile.length() > 0){
            Result.PE(submit.getSubmitId(),readFile(errFile));
        }
        return null;
    }

    private ArrayList<String> exec(String cmd, String[] evnp, File dir) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd, evnp, dir);
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        ArrayList<String> result = new ArrayList<>();
        String line = "";
        while ((line = input.readLine()) != null) {
            result.add(line);
        }
        input.close();
        return result;
    }
    private void writeFile(String fileName,String fileData){
        File file = new File(fileName);
        FileWriter fileWriter=null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(fileData);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(2);
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                System.out.println(3);
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }
    private String readFile(File file){
        try {
            byte [] b = new byte[10000];
            FileInputStream fileInputStream = new FileInputStream(file);
            int read = fileInputStream.read(b);
            for(int i=0;i<read;i++){
                System.out.print(b[i]);
            }
            System.out.println();
            return String.valueOf(b);
        } catch (FileNotFoundException e) {
            System.out.println(4);
            System.out.println(e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(5);
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

}
