package co.edu.unbosque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.NoteRegister;
import co.edu.unbosque.repository.NoteRegisterRepository;
import co.edu.unbosque.util.AESUtil;

@Service
public class NoteRegisterService implements CRUDOperations<NoteRegister> {
	@Autowired
	private NoteRegisterRepository dessertRepo;

	public NoteRegisterService() {

	}

	public boolean findNameAlreadyTaken(NoteRegister newNoteRegister) {

		Optional<NoteRegister> found = dessertRepo.findByNameUser(AESUtil.encrypt(newNoteRegister.getNameUser()));

		return found.isPresent() ? true : false;
	}

	@Override
	public int create(NoteRegister data) {
		if (findNameAlreadyTaken(data)) {

			return 1;
		} else {
			try {
				data.setNameUser(AESUtil.encrypt(AESUtil.decryptFromFront(data.getNameUser())));
				data.setNote1(AESUtil.encrypt(AESUtil.decryptFromFront(data.getNote1())));
				data.setNote2(AESUtil.encrypt(AESUtil.decryptFromFront(data.getNote2())));
				data.setNote3(AESUtil.encrypt(AESUtil.decryptFromFront(data.getNote3())));
				data.setAverage(AESUtil.encrypt(AESUtil.decryptFromFront(data.getAverage())));

				dessertRepo.save(data);
			} catch (Exception e) {
				return 1;
			}
			return 0;
		}
	}

	@Override
	public List<NoteRegister> getAll() {

		return dessertRepo.findAll();
	}

	@Override
	public int deleteById(Long id) {
		Optional<NoteRegister> found = dessertRepo.findById(id);

		if (found.isPresent()) {
			dessertRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}

	}

	@Override
	public int updateById(Long id, NoteRegister newData) {

		Optional<NoteRegister> found = dessertRepo.findById(id);
		Optional<NoteRegister> newFound = dessertRepo.findByNameUser(AESUtil.encrypt(newData.getNameUser()));

		if (found.isPresent() && !newFound.isPresent()) {
			NoteRegister temp = found.get();
			temp.setNameUser(AESUtil.encrypt(newData.getNameUser()));
			dessertRepo.save(temp);
			return 0;
		} else if (found.isPresent() && newFound.isPresent()) {
			return 1;
		} else if (!found.isPresent()) {
			return 2;
		} else {
			return 3;
		}

	}

	@Override
	public long count() {
		return dessertRepo.count();
	}

	@Override
	public boolean exists(Long id) {
		return dessertRepo.existsById(id) ? true : false;
	}

}
