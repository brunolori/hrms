package com.bvlsh.hr.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MediaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    String content;
    String type;
    String suffix;
    
    

}
