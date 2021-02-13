package com.sistemas.edu.restClinica.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "citas")
public class Citas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "fecha_cita")
	@Temporal(TemporalType.DATE)
	private Date fechacita;
	@Column(name = "hora")
	private Date hora;
	@Column(name = "motivo")
	private String motivo;
	private String estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Paciente paciente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Medico medico;
	
	
	
	@PrePersist
	public void PreSave() {
		this.setEstado("pendiente");
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public void prePersist () {
		fechacita= new Date();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechacita() {
		return fechacita;
	}

	public void setFechacita(Date fechacita) {
		this.fechacita = fechacita;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Citas(int id, Date fechacita, Date hora, String motivo, String estado, Paciente paciente, Medico medico) {
		super();
		this.id = id;
		this.fechacita = fechacita;
		this.hora = hora;
		this.motivo = motivo;
		this.estado = estado;
		this.paciente = paciente;
		this.medico = medico;
	}

	public Citas() {
	}

}
