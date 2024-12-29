package com.example.imse_g2_m2.model;

import java.net.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "video_tutorial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "tutorial_id")
public class VideoTutorial extends Tutorial{
	
	private int length;
	private URL url;
}
