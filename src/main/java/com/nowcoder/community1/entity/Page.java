package com.nowcoder.community1.entity;

import static java.lang.Math.max;
import static java.lang.Math.min;

/*
    封装分页相关的信息
 */
public class Page {
    // 当前页码
    private int current = 1;
    // 显示上限
    private int limit = 10;
    // 数据总数 (用于计算总页数)
    private int rows;
    // 查询路径(用于复用分页链接)
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1) this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100) this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行
     *
     * @return
     */
    public int getOffset() {
        return (current - 1) * limit;
    }

    /**
     * 获取总的页数
     *
     * @return
     */
    public int getTotal() {
        return (rows + limit - 1) / limit;
    }

    /**
     * 获取起始页码
     *
     * @return
     */
    public int getFrom () {
         return max(current - 2, 1);
    }

    /**
     * 获取结束页码
     *
     * @return
     */
    public int getTo() {
        return min(current + 2, getTotal());
    }

}
