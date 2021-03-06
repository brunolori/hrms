package com.bvlsh.hr.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BankAccountForm implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer bankId;
    private String personNid;
    private String iban;

}
