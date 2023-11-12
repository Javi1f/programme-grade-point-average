package co.edu.unbosque.beans;

import java.io.Serializable;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;

@ManagedBean
@SessionScoped
public class ButtonsBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2337775897589095969L;
	private String selectedComposition = "agregar.xhtml";

	public String getSelectedComposition() {
		return selectedComposition;
	}

	public void setSelectedComposition(String selectedComposition) {
		this.selectedComposition = selectedComposition;
	}

	public String showComposition1() {
		selectedComposition = "agregar.xhtml";
		return selectedComposition;
	}

	public String showComposition2() {
		selectedComposition = "mostrar.xhtml";
		return selectedComposition;
	}

}
