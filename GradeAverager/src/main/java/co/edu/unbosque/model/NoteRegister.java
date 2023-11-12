package co.edu.unbosque.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notesrecords")
public class NoteRegister {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(unique = true)
	private String nameUser;
	private String note1;
	private String note2;
	private String note3;
	private String average;

	public NoteRegister() {
		// TODO Auto-generated constructor stub
	}

	public NoteRegister(Long id, String nameUser, String note1, String note2, String note3, String average) {
		super();
		this.id = id;
		this.nameUser = nameUser;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.average = average;
	}

	public NoteRegister(String nameUser, float note1, float note2, float note3) {
		super();
		this.nameUser = nameUser;
		this.note1 = note1 + "";
		this.note2 = note2 + "";
		this.note3 = note3 + "";
		float aux = ((note1 + note2 + note3) / 3);
		this.average = aux + "";
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(average, id, note1, note2, note3);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoteRegister other = (NoteRegister) obj;
		return average == other.average && Objects.equals(id, other.id) && note1 == other.note1 && note2 == other.note2
				&& note3 == other.note3;
	}

	@Override
	public String toString() {
		return "NoteRegister [note1=" + note1 + ", note2=" + note2 + ", note3=" + note3 + ", average=" + average + "]";
	}

}
