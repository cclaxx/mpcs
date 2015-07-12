package it.polimi.awt.mpcs.service;

import it.polimi.awt.mpcs.domain.MountainPhoto;

import java.util.Calendar;
import java.util.List;

public interface PicasaService {
	
	List<MountainPhoto> getPhotosFromService (String tag, Calendar lastSearch);

}
