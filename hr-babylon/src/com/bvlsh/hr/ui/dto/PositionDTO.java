/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bvlsh.hr.ui.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lorela.shehu
 */
@Getter @Setter
public class PositionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String tag;
    private boolean status;
   
}
