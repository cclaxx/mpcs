package it.polimi.awt.mpcs.service;

import it.polimi.awt.mpcs.domain.MountainPhoto;

import java.util.Calendar;
import java.util.List;

public interface FlickrService {
	
	List<MountainPhoto> getPhotosFromService (String lat, String lng, String[] tag, Calendar lastSearch);

	void init();
}
