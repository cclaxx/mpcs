package it.polimi.awt.mpcs.service.impl;

	import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import it.polimi.awt.mpcs.domain.MountainPhoto;
import it.polimi.awt.mpcs.service.PanoramioService;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

	/**
	 * This class is responsible for downloading and parsing the search results for
	 * a particular area. All of the work is done on a separate thread, and progress
	 * is reported back through the DataSetObserver set in
	 * {@link #addObserver(DataSetObserver). State is held in memory by in memory
	 * maintained by a single instance of the ImageManager class.
	 */
	@Service
	public class PanoramioSearch implements PanoramioService {
		
	    /**
	     * Base URL for Panoramio's web API
	     */
	    private static final String THUMBNAIL_URL = "//www.panoramio.com/map/get_panoramas.php?set=public&from=0&to=2&minx=%s&miny=%s&maxx=%s&maxy=%s&size=medium&mapfilter=true";

	    /**
	     * Holds the images and related data that have been downloaded
	     */
	    private List<MountainPhoto> mImages = new ArrayList<MountainPhoto>();
	    


	    /**
	     * @return The number of items displayed so far
	     */
	    public int size() {
	        return mImages.size();
	    }

	    /**
	     * Gets the item at the specified position
	     */
	    public MountainPhoto get(int position) {
	        return mImages.get(position);
	    }

	    
	    /**
	     * Load a new set of search results for the specified area.

	     */
	    public  List<MountainPhoto> getPhotosFromService(String tag , String Lat, String Long) {
	
	        String url = THUMBNAIL_URL;
	        Float minLat = Float.parseFloat(Lat) - 0.05F;
	        Float minLong = Float.parseFloat(Long) -0.05F;
	        Float maxLat = Float.parseFloat(Lat) +0.05F;
	        Float maxLong= Float.parseFloat(Long) +0.05F;
	        		
            url = String.format(url, minLong , minLat , maxLong.toString(), maxLat.toString());
            try {
                URI uri = new URI("http", url, null);
                HttpGet get = new HttpGet(uri);        
                CloseableHttpClient client = HttpClients.createDefault();
                //HttpClient client = new DefaultHttpClient();
                HttpResponse response = client.execute(get);
                HttpEntity entity = response.getEntity();
                String str = convertStreamToString(entity.getContent());
                JSONObject json = new JSONObject(str);
                System.out.printf(tag + "   " + minLat +"   " +  minLong);
                parse(json);
                client.close();
            } catch (Exception e) {
            	System.out.printf(e.toString());
            }
            
	        return mImages;
	    }
	    

	        private void parse (JSONObject json) {
	            try {
	            	MountainPhoto item;
	            	
	                JSONArray array = json.getJSONArray("photos");
	                int count = array.length();
	                for (int i = 0; i < count; i++) {
	                    JSONObject obj = array.getJSONObject(i);
	                    String title = obj.getString("photo_title");
	                    title = title.replaceAll("\\P{Print}", "");
	                    title = StringUtils.remove(title, '"');
	                    String photoUrl = obj.getString("photo_file_url");
	                    double latitude = obj.getDouble("latitude");
	                    double longitude = obj.getDouble("longitude");
	                    
	                  /*  if (title == null) {
	                        title = "TitoloSconosciuto";
	                    }*/
	                    
	                    item = new MountainPhoto(title, "", photoUrl , new Point2D.Double(latitude, longitude),"");
	                    item.finalize();
	                    if(item.getName() == null || item.getName().isEmpty()) item.setName("titolo sconosciuto");
	        			if(item.getName().length() > 200) item.setName(StringUtils.abbreviate(item.getName(), 200));
	        			
	                    System.out.println(title + photoUrl);
	                    
	                      mImages.add(item);
	                                        
	                }
	            } catch (JSONException e) {
	            	System.out.printf(e.toString());
	            }
	        }

	        private String convertStreamToString(InputStream is) {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8*1024);
	            StringBuilder sb = new StringBuilder();
	     
	            String line = null;
	            try {
	                while ((line = reader.readLine()) != null) {
	                    sb.append(line + "\n");
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    is.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	     
	            return sb.toString();
	        }

	    }
	
