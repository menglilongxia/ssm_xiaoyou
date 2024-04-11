package com.lgy.xiaoyou_index.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommentCreateDto {
    private int parent_id;
    private int type;
    private String content;

}
