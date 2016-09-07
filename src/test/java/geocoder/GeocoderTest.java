package geocoder;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import com.google.code.geocoder.model.LatLng;
import com.hhfactory.Application;
import com.hhfactory.geocode.GeoCoderService;

@RunWith(Theories.class)
@ContextConfiguration(classes = Application.class)
@SpringApplicationConfiguration(classes = Application.class)
public class GeocoderTest {
	@Autowired
	private GeoCoderService geocoderService;

	@DataPoints
	public static String[] ARGS = {
		"北海道札幌市中央区南4条西8丁目2番地",
		"中央区南4条西8丁目"
	};
	
	@Before
	public void setUp() throws Exception{
		new TestContextManager(getClass()).prepareTestInstance(this);
	}
	
	@Theory
	@Test
	public void getLocationTest(String arg) {
		LatLng location = geocoderService.getLatLngByAddress(arg);
		System.out.println(location.getLat());
		System.out.println(location.getLng());
	}
	
}
