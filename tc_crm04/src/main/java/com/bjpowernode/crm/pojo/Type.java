package com.bjpowernode.crm.pojo;

import lombok.*;

@Data // lombok:自动生成getter、setter、toString、equals等方法
//@Getter@Setter@ToString
public class Type {
    private String id;
    private String name;
    private String description;
}
