package com.ybn.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class IssueDto {
    private String content;
    private String authorName;
    private String title;
    private String[] groups;
    private String email;
}
