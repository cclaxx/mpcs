package it.polimi.awt.mpcs.service;

import it.polimi.awt.mpcs.domain.MountainPhoto;

import java.util.List;

public interface PersonalService {

	List<MountainPhoto> personalGallery();
	
	void personalPhoto();
	
	void savePhoto(int id);
	
	void deletePhoto(int id);
}
