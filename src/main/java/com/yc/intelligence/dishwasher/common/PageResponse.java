package com.yc.intelligence.dishwasher.common;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
@Data
public class PageResponse<T> implements Serializable {
    private static final long serialVersionUID = -7028670775696201103L;
    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private int pageSize;
    private int pageOfElements;
    private List<T> content;
    private boolean hasContent;
    private boolean isFirst;
    private boolean isLast;

    public PageResponse(Page page){
        this.content = page.getContent();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.pageOfElements = page.getNumberOfElements();
        this.hasContent = page.getSize() != 0;
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
    }

    public PageResponse(Page page,List<T> content){
        this.content = content;
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.pageOfElements = page.getNumberOfElements();
        this.hasContent = page.getSize() != 0;
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
    }
}
