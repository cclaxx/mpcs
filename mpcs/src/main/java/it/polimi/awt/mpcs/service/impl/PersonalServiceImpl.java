package it.polimi.awt.mpcs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.polimi.awt.mpcs.domain.MountainPhoto;
import it.polimi.awt.mpcs.repository.PhotoRepository;
import it.polimi.awt.mpcs.service.PersonalService;

@Service
public class PersonalServiceImpl implements PersonalService{

	@Autowired
	private PhotoRepository photoRepository;
	
	public List<MountainPhoto> personalGallery() {
		
		return photoRepository.findSaved();
	}

	public void personalPhoto() {
		// TODO Auto-generated method stub
		
	}

	public void savePhoto(int id) {

		MountainPhoto photo = photoRepository.getPhotoByID(id);
		photo.setSaved(true);
		photoRepository.updatePhoto(photo);
		
	}

	public void deletePhoto(int id) {

		MountainPhoto photo = photoRepository.getPhotoByID(id);
		photo.setSaved(false);
		photoRepository.updatePhoto(photo);
		
	}

}
