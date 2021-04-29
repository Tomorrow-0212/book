package com.zhangft.bean;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

    // 当前是第几页，用户传过来的
    private int pageNo;
    // 总页数，通过总记录数/每页显示的个数 计算得出
    private int allPage;
    // 总记录数,通过获取数据库中数据的数量 得出
    private int allCount;
    // 每一页在数据库中开始查找的索引，通过当前的页数以及每页显示的数量计算得出
    // index = (pageNo - 1) * pageSize
    private int index;
    // 每页显示多少数据，用户传过来的，此处默认显示4个数据
    private int pageSize = 4;
    // 转发时动态的url，主要是用于分页功能的抽取使用，使跳转页面时有动态的链接
    private String url;
    // 每页显示的对象信息
    private List<T> pageList;
    // 是否有上一页
    private boolean hasPre;
    // 是否有下一页
    private boolean hasNext;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        pageNo = pageNo > 1 ? pageNo : 1;
        pageNo = pageNo < getAllPage() ? pageNo : getAllPage();
        this.pageNo = pageNo;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }

    public int getAllPage() {
        // 总页数=总记录数/每页显示的记录数；
        // 判断 总记录数%每页显示的记录数 是否等于0来判断是否需要加1
        allPage = allCount / pageSize;
        if (allCount % pageSize != 0){
            allPage++;
        }
        return allPage;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public int getIndex() {
        // 在数据库中开始的索引页=(当前页-1)*每页的数量
        int i = (pageNo - 1) * pageSize;
        if(i < 0){
            i = 0;
        }
        return i;
    }

    public int getpageSize() {
        return pageSize;
    }

    public void setpageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    public boolean isHasPre() {
        // 判断当前页是否大于第一页，大于第一页说明有上一页
        return pageNo > 1;
    }

    public boolean isHasNext() {
        // 判断当前页是否小于总页数，小于总页数说明有下一页
        return pageNo < getAllPage();
    }

    public Page() {
    }

    public Page(int pageNo, int allPage, int allCount, int index, int pageSize, String url, List<T> pageList, boolean hasPre, boolean hasNext) {
        this.pageNo = pageNo;
        this.allPage = allPage;
        this.allCount = allCount;
        this.index = index;
        this.pageSize = pageSize;
        this.url = url;
        this.pageList = pageList;
        this.hasPre = hasPre;
        this.hasNext = hasNext;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", allPage=" + allPage +
                ", allCount=" + allCount +
                ", index=" + index +
                ", pageSize=" + pageSize +
                ", url='" + url + '\'' +
                ", pageList=" + pageList +
                ", hasPre=" + hasPre +
                ", hasNext=" + hasNext +
                '}';
    }
}
