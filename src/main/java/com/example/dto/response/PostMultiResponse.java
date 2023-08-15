package com.example.dto.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PostMultiResponse<T> {
    private List<T> data;
    private PageInfo pageInfo;

    public PostMultiResponse(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber()+1, page.getSize(), (int) page.getTotalElements(), page.getTotalPages());
    }
}
