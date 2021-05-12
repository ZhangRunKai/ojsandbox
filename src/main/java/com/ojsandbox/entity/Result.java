package com.ojsandbox.entity;

import lombok.Data;

/**
 * @author zhang run kai
 * @version 1.0
 * @date 2021/5/12 22:38
 */
@Data
public class Result {
    private Integer submitId;

    private Integer runTime;

    private Integer rumMemory;

    private Integer submitResult;

    private String message;

    public Result(Integer submitId, Integer runTime, Integer rumMemory, Integer submitResult, String message) {
        this.submitId = submitId;
        this.runTime = runTime;
        this.rumMemory = rumMemory;
        this.submitResult = submitResult;
        this.message = message;
    }

    public static Result accept(Integer runId, Integer runTime, Integer rumMemory){
        return new Result(runId, runTime,rumMemory, 0,null);
    }

    public static Result RE(Integer runId, Integer runTime, Integer rumMemory){
        return new Result(runId, runTime,rumMemory, 1,null);
    }
    public static Result PE(Integer runId,String message){
        return new Result(runId, 0,0, 1,message);
    }
}
