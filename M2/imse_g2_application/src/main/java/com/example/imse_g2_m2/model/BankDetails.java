package com.example.imse_g2_m2.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * The BankDetails data is always displayed in the member
 */
public class BankDetails {

	@Id
	private int memberId;
	 
	private int cardNumber;
	
	private Date expirationDate;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "member_id")
    private Member member;
}
