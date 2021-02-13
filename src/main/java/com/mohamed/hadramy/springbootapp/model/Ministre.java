package com.mohamed.hadramy.springbootapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="minister")
@NoArgsConstructor
@Getter
@Setter
public class Ministre {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="full_name")
	private String full_name;
	
	@Column(name="job_type")
	private String job_type;
	
	@Column(name="mot_du_ministre")
	private String mot_du_ministre;
	
	@Column(name="full_name_ar")
	private String full_name_ar;
	
	@Column(name="job_type_ar")
	private String job_type_ar;
	
	@Column(name="mot_du_ministre_ar")
	private String mot_du_ministre_ar;

    public Ministre(long id, String full_name, String job_type, String mot_du_ministre, String full_name_ar,
			String job_type_ar, String mot_du_ministre_ar) {
		this.id = id;
		this.full_name = full_name;
		this.job_type = job_type;
		this.mot_du_ministre = mot_du_ministre;
		this.full_name_ar = full_name_ar;
		this.job_type_ar = job_type_ar;
		this.mot_du_ministre_ar = mot_du_ministre_ar;
	}

}
