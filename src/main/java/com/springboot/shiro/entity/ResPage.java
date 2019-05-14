package com.springboot.shiro.entity;

/**
 * Created by heyou on 2019/5/14 0014.
 */
public class ResPage {
    //当前页码
    private Integer pageIndex = 1; //默认页码 1

    //当前每页条数
    private Integer pageSize = 10; //默认每页条数

    private Integer totalPage = 1; //默认为1

    private Long totalNum = 1L; //默认1


    //排序字段
    private String properties;

    public ResPage() {

    }

    public ResPage(Integer pageIndex,Integer pageSize,Integer totalPage,Long totalNum) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalNum = totalNum;
    }

}
