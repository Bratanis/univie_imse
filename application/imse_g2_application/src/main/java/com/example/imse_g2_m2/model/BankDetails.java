package com.example.imse_g2_m2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
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
	@Column (name="member_id")
	private int memberId;
	 
	@Column(name = "card_number", nullable = false)
    private String cardNumber; 

    @Column(name = "exp_date", nullable = false)
    private String expDate;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "member_id")
	@JsonBackReference // Breaks cyclic reference
    private Member member;
	
	public BankDetails(String cardNumber, String expDate) {
		this.cardNumber = cardNumber;
		this.expDate = expDate;
	}
}
