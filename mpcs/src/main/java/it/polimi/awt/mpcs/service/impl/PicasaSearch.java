package it.polimi.awt.mpcs.service.impl;

import com.google.gdata.client.Query;
import com.google.gdata.client.photos.*;
import com.google.gdata.data.*;
import com.google.gdata.data.photos.*;
import com.google.gdata.util.ServiceException;

import it.polimi.awt.mpcs.domain.MountainPhoto;
import it.polimi.awt.mpcs.service.PicasaService;

import java.awt.geom.Point2D;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PicasaSearch implements PicasaService {

	
	public List<MountainPhoto> getPhotosFromService(String tag, Calendar lastSearchDate) {

		List<MountainPhoto> _tempList = new ArrayList<MountainPhoto>();
		try {
			PicasawebService myService = new PicasawebService("exampleCo-exampleApp");
			URL baseSearchUrl = new URL("https://picasaweb.google.com/data/feed/api/all");

			Query myQuery = new Query(baseSearchUrl);
			myQuery.setStringCustomParameter("kind", "photo");
			myQuery.setMaxResults(200);
			myQuery.setFullTextQuery(tag);
            myQuery.setPublishedMin(new DateTime(lastSearchDate.getTime()));
			myQuery.setPublishedMax(new DateTime(Calendar.getInstance().getTime()));
			AlbumFeed searchResultsFeed = myService.query(myQuery,AlbumFeed.class);
			MountainPhoto mPhoto;
			System.out.println("picasa è partito" + tag);
			for (PhotoEntry photo : searchResultsFeed.getPhotoEntries()) {
				System.out.println("picasa ha trovato una foto");
				 mPhoto = new MountainPhoto();
				mPhoto.setDescription(photo.getDescription().getPlainText());
				mPhoto.setName(photo.getTitle().getPlainText());
				try{
				mPhoto.setGeoPoint((Point2D) photo.getGeoLocation());
				mPhoto.finalize();
				}
				catch(Exception ex){}
				mPhoto.setSource(photo.getMediaThumbnails().get(0).getUrl());
                if(mPhoto.getName() == null || mPhoto.getName().isEmpty()) mPhoto.setName("titolo sconosciuto");
    			if(mPhoto.getName().length() > 200) mPhoto.setName(StringUtils.abbreviate(mPhoto.getName(), 200));
    			
				_tempList.add(mPhoto);
			}
		} catch (IOException | ServiceException e) {
					e.printStackTrace();
		}

		return _tempList;

	}
}
