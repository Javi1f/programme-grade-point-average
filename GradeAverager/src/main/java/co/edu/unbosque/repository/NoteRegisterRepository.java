package co.edu.unbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.model.NoteRegister;

public interface NoteRegisterRepository extends JpaRepository<NoteRegister, Long> {

	public Optional<NoteRegister> findByNameUser(String name);

	public void deleteByNameUser(String name);

}
