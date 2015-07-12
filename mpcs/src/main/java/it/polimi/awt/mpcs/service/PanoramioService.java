package it.polimi.awt.mpcs.service;

import it.polimi.awt.mpcs.domain.MountainPhoto;

import java.util.List;

public interface PanoramioService {
	
	List<MountainPhoto> getPhotosFromService (String tag, String Lat, String Long);

}
