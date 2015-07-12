package it.polimi.awt.mpcs.repository;

import it.polimi.awt.mpcs.domain.MountainPhoto;

import java.util.Collection;
import java.util.List;

public interface PhotoRepository {

	void savePhoto(MountainPhoto photo);
	
	void deletePhoto();
	
	MountainPhoto getPhotoByID(int id);
	
	void savePhotos(List<MountainPhoto> mountains);

	List<MountainPhoto> findAll();
	
	List<MountainPhoto> findSaved();

	void updatePhoto(MountainPhoto photo);
}
