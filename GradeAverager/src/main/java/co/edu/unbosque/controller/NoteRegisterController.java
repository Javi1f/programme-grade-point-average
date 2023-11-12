package co.edu.unbosque.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.NoteRegister;
import co.edu.unbosque.services.NoteRegisterService;
import co.edu.unbosque.util.AESUtil;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "*" })
@Transactional
public class NoteRegisterController {

	@Autowired
	private NoteRegisterService noteServ;

	public NoteRegisterController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping(path = "/createnoteregisterjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewNoteRegisterWithJson(@RequestBody NoteRegister newRegister) {
		int status = noteServ.create(newRegister);

		if (status == 0) {
			return new ResponseEntity<String>("Register created succesfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error creating the Register", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(path = "getallnoteregister")
	public ResponseEntity<List<NoteRegister>> getAll() {
		List<NoteRegister> plates = noteServ.getAll();
		List<NoteRegister> aux = new ArrayList<NoteRegister>();

		for (int i = 0; i < plates.size(); i++) {
			NoteRegister newRegister = new NoteRegister(plates.get(i).getId(),
					AESUtil.encryptForFront(AESUtil.decrypt(plates.get(i).getNameUser())),
					AESUtil.encryptForFront(AESUtil.decrypt(plates.get(i).getNote1())),
					AESUtil.encryptForFront(AESUtil.decrypt(plates.get(i).getNote2())),
					AESUtil.encryptForFront(AESUtil.decrypt(plates.get(i).getNote3())),
					AESUtil.encryptForFront(AESUtil.decrypt(plates.get(i).getAverage())));

			aux.add(newRegister);
		}

		if (aux.isEmpty()) {
			return new ResponseEntity<List<NoteRegister>>(aux, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<NoteRegister>>(aux, HttpStatus.ACCEPTED);
		}
	}
}
