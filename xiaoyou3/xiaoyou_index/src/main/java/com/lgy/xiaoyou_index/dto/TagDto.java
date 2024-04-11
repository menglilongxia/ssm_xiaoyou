package com.lgy.xiaoyou_index.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TagDto {
    private String categoryname;
    private List<String> tags;

}
