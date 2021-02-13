package com.sistemas.edu.restClinica.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medicos")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "medico", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Citas> citas;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn( name="persona",referencedColumnName = "id")
	private Persona persona;

	@ManyToOne(fetch = FetchType.LAZY)
	private Especialidad especialidad;
	
	
	
	
	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public void setCita(Citas cita) {
		this.citas.add(cita);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Citas> getCitas() {
		return citas;
	}

	public void setCitas(List<Citas> citas) {
		this.citas = citas;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Medico(int id, List<Citas> citas, Persona persona) {
		super();
		this.id = id;
		this.citas = citas;
		this.persona = persona;
	}
	
	public Medico() {}

}
