package it.polimi.awt.mpcs.service.impl;

import it.polimi.awt.mpcs.domain.MountainPhoto;
import it.polimi.awt.mpcs.service.FlickrService;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;

@Service
public class FlickrSearch implements FlickrService  {

/*	String minimum_longitude;
	String minimum_latitude;
	String maximum_longitude;
	String maximum_latitude;h
	String latitude;
	String longitude;*/
	SearchParameters searchParam;
	Flickr flickr;
	//String[] tags;
	PhotoList<Photo> _photoList;
	String apiKey = "7d0093c8145ff34dd02f7f46edd2ce11";
	String sharedSecret = "2932ff314f096473";
	
	public void init(){
		flickr = new Flickr(apiKey, sharedSecret, new REST());
	}
	
	public void flickrSetUp(String latitude, String longitude,String[] tags, Calendar lastSearchDate ){
	

	
    // Set the wanted search parameters (I'm not using real variables in the example)
    searchParam = new SearchParameters();
    //searchParam.setBBox(minimum_longitude, minimum_latitude,   maximum_longitude,  maximum_latitude);
    searchParam.setExtras(com.flickr4java.flickr.photos.Extras.ALL_EXTRAS);
    searchParam.setTags(tags);
    try {
		searchParam.setMedia("photos");
	} catch (FlickrException e1) {	
		e1.printStackTrace();
	}
    searchParam.setLatitude(latitude);
    searchParam.setLongitude(longitude);
    searchParam.setMinUploadDate(lastSearchDate.getTime());
    searchParam.setMaxUploadDate(Calendar.getInstance().getTime());
    
    try {
		_photoList = flickr.getPhotosInterface().search(searchParam, 400, 0);
	} catch (FlickrException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
  
	
	public List<MountainPhoto> getPhotosFromService(String latitude, String longitude,String[] tags,Calendar lastSearchDate) {
		
		flickrSetUp(latitude, longitude,tags, lastSearchDate );
		
		List<MountainPhoto> _tempList = new ArrayList<MountainPhoto>();

		for (Photo photo : _photoList){
         
			String title = photo.getTitle();
			
			title = StringUtils.remove(title, '"');
			
			MountainPhoto myMountain = new MountainPhoto();
			myMountain.setName(title);
			System.out.println(myMountain.getName());
			
			myMountain.setDescription(photo.getDescription());
			System.out.println(myMountain.getDescription());
			
			String urlPhoto = String.format("https://farm"+photo.getFarm()+".staticflickr.com/"+photo.getServer()+"/"+photo.getId()+"_"+photo.getSecret()+".jpg");
			
			myMountain.setSource(urlPhoto);
			
			Point2D xy = new Point2D.Double();
			
			try{
			xy.setLocation(photo.getGeoData().getLatitude(), photo.getGeoData().getLongitude());
			myMountain.setGeoPoint( xy );
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			myMountain.finalize();
			}
			catch (Exception ex){} 
			try{
			myMountain.setGeoArea(photo.getRegion().getName());
			System.out.println(myMountain.getGeoArea());
			}
			catch (Exception ex){} 
			
			if(myMountain.getName() == null || myMountain.getName().isEmpty()) myMountain.setName("titolo sconosciuto");
			if(myMountain.getName().length() > 200) myMountain.setName(StringUtils.abbreviate(myMountain.getName(), 200));
			
			_tempList.add(myMountain);
			
		}
		
		
		
		return _tempList;
	}
}
