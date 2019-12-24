package com.tedu.pojo;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageObject<T> implements Serializable {

    private static final long serialVersionUID = 5664338045237026158L;
    private Integer pageSize;
    private Integer pageCurrent;
    private Integer pageCount;
    private Long rowCount;
    private List<T> records;

    public PageObject(Page<T> page){
        pageSize = page.getPageSize();
        pageCurrent = page.getPageNum();
        pageCount = page.getPages();
        rowCount = page.getTotal();
        records = page.getResult();
    }
}
