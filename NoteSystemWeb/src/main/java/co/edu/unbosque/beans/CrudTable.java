package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.primefaces.PrimeFaces;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.Notes;
import co.edu.unbosque.util.AESUtil;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@ManagedBean
@RequestScoped
@Named
public class CrudTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7240570880396360558L;
	private String text;
	private Notes selectedNote;
	private ArrayList<String[]> notes;

	public CrudTable() {
		selectedNote = new Notes();
		notes = users();
		text = decryptedText();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Notes getSelectedNote() {
		return selectedNote;
	}

	public void setSelectedNote(Notes selectedNote) {
		this.selectedNote = selectedNote;
	}

	public ArrayList<String[]> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<String[]> notes) {
		this.notes = notes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Notes getselectedNote() {
		return selectedNote;
	}

	public void setselectedNote(Notes selectedNote) {
		this.selectedNote = selectedNote;
	}

	public void openNew() {
		selectedNote = new Notes();
	}

	public void saveNotes() {

		long id = selectedNote.getId();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Planet Updated Succesfully"));

		PrimeFaces.current().executeScript("PF('managePlanetDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-Notes");

	}

	public void deleteNotes() {

		long id = selectedNote.getId();

		this.selectedNote = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Note Removed Succesfully"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-Notes");
	}

	public ArrayList<String[]> users() {
		String response = HttpClientSynchronous.doGet("http://localhost:8081/user/getallnoteregister");
		if (response.isEmpty())
			return new ArrayList<String[]>();
		String json = response.replaceAll("[{\n ]", "");
		json = json.replaceAll("[" + Pattern.quote("[") + Pattern.quote("]") + "]", "");
		json = json.substring(0, json.length() - 1);
		String[] rows = json.split("\\}");
		ArrayList<String[]> users = new ArrayList<>();
		for (String aux : rows) {
			if (aux.charAt(0) == ',')
				aux = aux.substring(1);
			String[] attributes = aux.split(",");
			String[] all = new String[attributes.length];
			for (int i = 0; i < attributes.length; i++) {
				if (i == 0) {
					all[i] = attributes[i].substring(attributes[i].indexOf(":") + 1);
					continue;
				} else if (i == attributes.length - 1) {
					attributes[i] = attributes[i].replace("\"", "");
					all[i] = attributes[i].substring(attributes[i].indexOf(":") + 1);
					continue;
				}
				attributes[i] = attributes[i].replace("\"", "");
				all[i] = AESUtil.decrypt(attributes[i].substring(attributes[i].indexOf(":") + 1));
			}
			users.add(all);
		}
		return users;
	}

	private String decryptedText() {
		StringBuilder sb = new StringBuilder();

		for (String[] s : users()) {

			sb.append("id: " + s[0] + "\n");
			sb.append("Name: " + s[1] + "\n");
			sb.append("Note 1: " + s[2] + "\n");
			sb.append("Note 2: " + s[3] + "\n");
			sb.append("Note 3: " + s[4] + "\n");
			sb.append("Average: " + AESUtil.decrypt(s[5]) + "\n\n");

		}

		return sb.toString();

	}

}
