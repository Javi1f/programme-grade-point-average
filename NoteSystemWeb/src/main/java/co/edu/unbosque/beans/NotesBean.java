package co.edu.unbosque.beans;

import java.io.Serializable;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.Notes;
import co.edu.unbosque.util.AESUtil;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class NotesBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8062883103499673722L;
	private Notes notes = new Notes();

	public Notes getNotes() {
		return notes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;
	}

	public void calcularPromedio() {
		double nota1 = notes.getNote1();
		double nota2 = notes.getNote2();
		double nota3 = notes.getNote3();

		double promedio = (nota1 * 0.3 + nota2 * 0.3 + nota3 * 0.4);

		promedio = Math.round(promedio * 100.0) / 100.0;

		notes.setPromedio(promedio);

		showSticky(promedio);

		HttpClientSynchronous.doPost("http://localhost:8081/user/createnoteregisterjson",
				"{" + "  \"nameUser\": \"" + AESUtil.encrypt(notes.getName()) + "\"," + "  \"note1\": \""
						+ AESUtil.encrypt(Double.toString(notes.getNote1())) + "\"," + "  \"note2\": \""
						+ AESUtil.encrypt(Double.toString(notes.getNote2())) + "\"," + "  \"note3\": \""
						+ AESUtil.encrypt(Double.toString(notes.getNote3())) + "\"," + "  \"average\": \""
						+ AESUtil.encrypt(Double.toString(notes.getPromedio())) + "\"" + "}");

	}

	public void showSticky(double average) {

		if (average >= 3.1) {

			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Your notes doesnt have problem"));
		} else if (average < 3.1 && average >= 3) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING", "Your average is 3, becareful"));
		} else if (average < 3) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "DANGER", "You are losing the semester"));
		} else {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sticky Message", "Something is wrong"));
		}
	}

}
