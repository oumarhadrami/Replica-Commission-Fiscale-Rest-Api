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
@Table(name="commissiondescription")
@NoArgsConstructor
@Getter
@Setter
public class CommissionDescription {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="description_ar")
	private String description_ar;
	
	@Column(name="description_fr")
	private String description_fr;

    public CommissionDescription(long id, String description_ar, String description_fr) {
        this.id = id;
        this.description_ar = description_ar;
        this.description_fr = description_fr;
    }
}