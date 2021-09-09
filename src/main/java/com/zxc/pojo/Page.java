package com.zxc.pojo;

import java.util.List;

/**
 * @author zhu
 * @create 2021-08-25 15:31
 */
public class Page<T>{
    public static final int PAGE_SIZE=4;

    private String url;
    private int pageNo;
    private int pageTotal;
    private int pageTotalCount;
    private int pageSize=PAGE_SIZE;
    private List<T> items;

    public int getPageNo() {
        return pageNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
           pageNo=1;
        }
        if (pageNo>this.pageTotal){
            pageNo=this.pageTotal;
        }
        this.pageNo = pageNo;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal() {
        if(pageTotalCount%pageSize==0){
            this.pageTotal = pageTotalCount/pageSize;
        }else {
            this.pageTotal=pageTotalCount/pageSize+1;
        }
    }

    public int getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(int pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "url='" + url + '\'' +
                ", pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                '}';
    }
}
