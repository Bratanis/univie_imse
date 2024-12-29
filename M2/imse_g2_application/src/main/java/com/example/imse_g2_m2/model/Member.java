package com.example.imse_g2_m2.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * The member object as stored in the NoSQL database.
 * This is all the information that will be retrieved from the db 
 * and displayed on the front-end. When retrieving from the 
 * normalized sql db, special annotations are needed!
 */
public class Member {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private int memberId;
	
	private String name;
	
	private int age;
	
	private String password;
	
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Breaks cyclic reference
	private BankDetails bankDetails;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
	private Location location;
	
	@ManyToMany
    @JoinTable(
        name = "saved",
        joinColumns = @JoinColumn(name = "member_id"),
        inverseJoinColumns = @JoinColumn(name = "tutorial_id")
    )
	private List<Tutorial> saved_tutorials;
}
