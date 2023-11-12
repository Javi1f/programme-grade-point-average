package co.edu.unbosque.model;

import java.io.Serializable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Notes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7081645583739080202L;
	private long id;
	private String name;
	private double note1;
	private double note2;
	private double note3;
	private double promedio;

	public Notes() {
		// TODO Auto-generated constructor stub
	}

	public Notes(long id, String name, double note1, double note2, double note3, double promedio) {
		super();
		this.id = id;
		this.name = name;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.promedio = promedio;
	}
	public Notes( String name, double note1, double note2, double note3, double promedio) {
		super();
		this.name = name;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.promedio = promedio;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getNote1() {
		return note1;
	}

	public void setNote1(double note1) {
		this.note1 = note1;
	}

	public double getNote2() {
		return note2;
	}

	public void setNote2(double note2) {
		this.note2 = note2;
	}

	public double getNote3() {
		return note3;
	}

	public void setNote3(double note3) {
		this.note3 = note3;
	}

	public double getPromedio() {
		return promedio;
	}

	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	public void setPromedio(float promedio) {
		this.promedio = promedio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
