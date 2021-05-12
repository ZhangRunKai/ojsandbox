package com.ojsandbox.entity;

import lombok.Data;

/**
 * @author zhang run kai
 * @version 1.0
 * @date 2021/5/12 20:20
 */
@Data
public class Submit {

    private Integer problemId;
    /**
     * 1:C
     * 2:C++
     * 3:Java
     */
    private Integer submitType;

    /**
     * 运行Id
     */
    private Integer submitId;

    /**
     * 运行代码
     */
    private String submitCode;

    /**
     * 发送给沙箱时表示：时间空间的限制
     * 返回时表示：运行所用的时间及空间
     */
    private Integer runTime;

    private Integer rumMemory;

    /**
     * 运行结果
     */
    private Integer submitResult;

}
