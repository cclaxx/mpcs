package it.polimi.awt.mpcs.service;

import it.polimi.awt.mpcs.domain.MountainPhoto;

import java.util.List;

public interface GalleryService {
	
	MountainPhoto getPhoto(int id);
	
	List<MountainPhoto> search();

	List<MountainPhoto> getPhotos();

	void isWrong(int id);
}
