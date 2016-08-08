package geocoder;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.code.geocoder.model.LatLng;
import com.hhfactory.Application;
import com.hhfactory.geocode.GeoCoderService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class GeocoderTest {
	@Autowired
	private GeoCoderService geocoderService;
	
	@Test
	public void getLocationTest() {
		String address = "北海道中央区南4条西8丁目2番地";
		try {
			LatLng location = geocoderService.getLatLngByAddress(address);
			assertNotNull(location);			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
