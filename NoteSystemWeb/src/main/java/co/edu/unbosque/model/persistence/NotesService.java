package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.Random;

import co.edu.unbosque.model.Notes;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class NotesService {

	private ArrayList<Notes> notes;

	@PostConstruct
	public void init() {
		ArrayList<Notes> NotesTable = new ArrayList<>();
		for (Notes Notes : NotesTable) {
			NotesTable.add(new Notes());
		}

	}

	public ArrayList<Notes> getNotes() {
		return new ArrayList<>(notes);
	}

	public ArrayList<Notes> getNotes(int size) {

		if (size > notes.size()) {
			Random rand = new Random();

			ArrayList<Notes> randomList = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				int randomIndex = rand.nextInt(notes.size());
				randomList.add(notes.get(randomIndex));
			}

			return randomList;
		}

		else {
			return new ArrayList<>(notes.subList(0, size));
		}

	}

}
