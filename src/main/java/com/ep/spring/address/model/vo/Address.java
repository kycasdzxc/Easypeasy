package com.ep.spring.address.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {
	
	private int addNo;
	private String empNo;
	private String addType;
	private String addName;
	private String email;
	private String phone;
	private String addEmp;
	private String addDept;
	private String addJob;
	private String groupNo;
	private String empPhone;
	private String empAddress;
	private String memo;
	private String createDate;
	private String modifyDate;
	private String deleteDate;
	private String addBin;
	private String editable;
	
	private String addProfile;
	private AddGroup group;
	
	private String editNo;
	
	private List<AddEdit> editList;
	

}
