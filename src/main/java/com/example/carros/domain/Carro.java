package com.example.carros.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Table(name="carro")
public class Carro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="nome")
	private String nome;

	@Column(name="tipo")
	private String tipo;

	@Column(name="descricao")
	private String descricao;

	@Column(name="url_foto")
	private String urlFoto;

	@Column(name="url_video")
	private String urlVideo;

	@Column(name="latitude")
	private String latitude;

	@Column(name="longitude")
	private String longitude;

}
