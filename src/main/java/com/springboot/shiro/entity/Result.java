package com.springboot.shiro.entity;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by heyou on 2019/5/14 0014.
 * @Desc:返回体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Result {
    private boolean success;

    private String code;//状态码

    private String msg;//返回信息

    private Object data;//返回体数据

    private ResPage page;//


    public Result() {
        this.success = false;
    }
    public Result(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
    public Result setInfo(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.setData(data);
        return this;
    }

    public void setData(Object data) {
        if(data instanceof PageInfo<?>) {
            PageInfo<?> pageInfo = (PageInfo<?>)data;
            this.page = new ResPage(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getTotal());
            this.data = pageInfo.getList();
        }else {
            this.data = data;
        }
    }
}
