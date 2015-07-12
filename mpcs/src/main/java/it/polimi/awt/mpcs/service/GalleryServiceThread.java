package it.polimi.awt.mpcs.service;

import it.polimi.awt.mpcs.domain.MountainPhoto;
import it.polimi.awt.mpcs.domain.SeedQuery;
import it.polimi.awt.mpcs.repository.PhotoRepository;
import it.polimi.awt.mpcs.repository.SeedQueryRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author Claudio
 *
 */

@Service
public class GalleryServiceThread{


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


		/**
		 * ASync method that allow to start search in a different thread.
		 * It executes Freebase search for queries and Flickr, Panoramio, Picasa search for mountain photos
		 */
		@Async
		public void execute() {
	
			System.out.println("THREAD STARTS");
			
		List<MountainPhoto> mountains = new ArrayList<MountainPhoto>();
			
		//yesterday
		Calendar lastSearch = Calendar.getInstance();
		
		lastSearch.set(lastSearch.get(Calendar.YEAR),lastSearch.get(Calendar.MONTH),lastSearch.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		
		lastSearch.add(Calendar.DAY_OF_MONTH, -2);
		
		flickrService.init();
		
		sqr.saveSeedQueries(xmlReaderService.readSeedQueries());

		sqr.saveSeedQueries(freeBaseService.mountainRangeSearch(sqr.getAllSeedQueries()));

		int count = 0;
		System.out.println("finericercaseed");
		for (SeedQuery query : sqr.getAllSeedQueries()) {
			count++;
			String [] tags = new String [1];
			tags[0]=query.getName();
			
			mountains = flickrService.getPhotosFromService(query.getLat(), query.getLng(), tags, lastSearch);
			photoRepository.savePhotos(mountains);
			
			mountains = panoramioService.getPhotosFromService(query.getName(), query.getLat(), query.getLng());
			photoRepository.savePhotos(mountains);
		     System.out.println(count + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			
			//searchResults.addAll(picasaService.getPhotosFromService(query.getName(), lastSearch));
		}
		
	}


}