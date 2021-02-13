package com.sistemas.edu.restClinica.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="pacientes")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double talla;
	private double peso;
	
	@Column(name="tipo_sangre")
	private String tipoSangre;
	
	private String alergias;
	
	@OneToMany(mappedBy = "paciente",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Citas> citas;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="persona", referencedColumnName = "id")
	private Persona persona;
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Paciente() {
		citas = new ArrayList<Citas>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTalla() {
		return talla;
	}
	public void setTalla(double talla) {
		this.talla = talla;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getTipoSangre() {
		return tipoSangre;
	}
	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}
	public String getAlergias() {
		return alergias;
	}
	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}
	public List<Citas> getCitas() {
		return citas;
	}
	public void setCitas(List<Citas> citas) {
		this.citas = citas;
	}
	
	public void addCita(Citas cita) {
		citas.add(cita);
	}
	public Paciente(int id, double talla, double peso, String tipoSangre, String alergias, List<Citas> citas,
			Persona persona) {
		super();
		this.id = id;
		this.talla = talla;
		this.peso = peso;
		this.tipoSangre = tipoSangre;
		this.alergias = alergias;
		this.citas = citas;
		this.persona = persona;
	}
	
	
	

}
