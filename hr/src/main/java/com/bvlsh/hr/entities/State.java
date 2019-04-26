/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bvlsh.hr.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lorela.shehu
 */
@Entity
@Table(name = "state")
@Getter @Setter
public class State implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODE")
    private String code;
    @Column(name = "CODE_ALPHA_3")
    private String codeAlpha3;
    @Column(name = "NAME")
    private String name;
    
}
