package it.polimi.awt.mpcs.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import it.polimi.awt.mpcs.domain.MountainPhoto;
import it.polimi.awt.mpcs.repository.PhotoRepository;
import it.polimi.awt.mpcs.repository.SeedQueryRepository;
import it.polimi.awt.mpcs.service.FlickrService;
import it.polimi.awt.mpcs.service.FreeBaseService;
import it.polimi.awt.mpcs.service.GalleryService;
import it.polimi.awt.mpcs.service.PanoramioService;
import it.polimi.awt.mpcs.service.PicasaService;
import it.polimi.awt.mpcs.service.XMLReaderService;
import it.polimi.awt.mpcs.service.GalleryServiceThread;

@Service
public class GalleryServiceImpl implements GalleryService 
{ 

	@Autowired
	private FlickrService flickrService;
	
	@Autowired
	private PanoramioService panoramioService;

	@Autowired
	private PicasaService picasaService;
	
	@Autowired
	private FreeBaseService freeBaseService;
	
	@Autowired
	private XMLReaderService xmlReaderService;

	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private SeedQueryRepository sqr;

	@Autowired
	private GalleryServiceThread task;
	
   //funzione utilizzata solo per impostare lo scheduling all'avvio
	@Scheduled(cron = "0 0/120 0-22 * * *")
	public void startSearchThread(){
		task.execute();
	}
	
	public List<MountainPhoto> search() {

		task.execute();
		
		return photoRepository.findAll();
	}
	
	/*public List<MountainPhoto> search() {

		List<MountainPhoto> mountains  = new ArrayList<MountainPhoto>();

		//yesterday
		Calendar lastSearch = Calendar.getInstance();
		
		lastSearch.set(lastSearch.get(Calendar.YEAR),
				lastSearch.get(Calendar.MONTH),
				lastSearch.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		
		lastSearch.add(Calendar.DAY_OF_MONTH, -2);
		
		flickrService.init();
		
		sqr.saveSeedQueries(xmlReaderService.readSeedQueries());
		
		sqr.saveSeedQueries(freeBaseService.mountainRangeSearch(sqr.getAllSeedQueries()));

		for (SeedQuery query : sqr.getAllSeedQueries()) {
			
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBB");
			
			String [] tags = new String [1];
			tags[0]=query.getName();
			
			mountains = flickrService.getPhotosFromService(query.getLat(), query.getLng(), tags, lastSearch);
			photoRepository.savePhotos(mountains);
			
			mountains = panoramioService.getPhotosFromService(query.getName(), query.getLat(), query.getLng());
			photoRepository.savePhotos(mountains);
		    
			
			//searchResults.addAll(picasaService.getPhotosFromService(query.getName(), lastSearch));
			
		}

		return photoRepository.findAll();

	}*/

	public MountainPhoto getPhoto(int id) {
		return photoRepository.getPhotoByID(id);
	}

	public List<MountainPhoto> getPhotos() {
		return photoRepository.findAll();
	}

	
	public void isWrong(int id) {
		MountainPhoto photo = photoRepository.getPhotoByID(id);
		photo.setWrong(true);
		photoRepository.updatePhoto(photo);
	}
	
	
}
