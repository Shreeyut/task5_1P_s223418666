package sit707_week5;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;



public class WeatherControllerTest {

	@Test
	public void testStudentIdentity() {
		String studentId = "s223418666";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Shreeyut Shrestha";
		Assert.assertNotNull("Student name is null", studentName);
	}
	
	static List<Double> temps;
	static int nHours;
	static double minn;
	static double maxx;
	static double avgg;
	@BeforeClass
	public static void initOnce() {
		WeatherController wController = WeatherController.getInstance();
		nHours = wController.getTotalHours();
		minn = wController.getTemperatureMinFromCache();
		maxx = wController.getTemperatureMaxFromCache();
		avgg = wController.getTemperatureAverageFromCache();
		temps= new ArrayList<>();
		for (int i=0; i<nHours; i++) {
			temps.add(wController.getTemperatureForHour(i+1)); 			
		}
		wController.close();
	}
	@Test
	public void testTemperatureMin() {
		System.out.println("+++ testTemperatureMin +++");
		double minTemperature = 1000;
		for (int i=0; i<nHours; i++) {
			if (minTemperature > temps.get(i)) {
				minTemperature = temps.get(i);
			}
		}
		Assert.assertTrue( minn == minTemperature);
		
	}
	
	@Test
	public void testTemperatureMax() {
		System.out.println("+++ testTemperatureMax +++");
		double maxTemperature = -1;
		for (int i=0; i<nHours; i++) {
			// Hour range: 1 to nHours
			if (maxTemperature < temps.get(i)) {
				maxTemperature = temps.get(i);
			}
		}
		Assert.assertTrue(maxx == maxTemperature);
	}

	@Test
	public void testTemperatureAverage() {
		System.out.println("+++ testTemperatureAverage +++");
		double sumTemp = 0;
		for (int i=0; i<nHours; i++) {
			// Hour range: 1 to nHours
			sumTemp += temps.get(i);
		}
		double averageTemp = sumTemp / nHours;
		Assert.assertTrue( avgg == averageTemp);
		
	

	}
	
	@Test
	public void testTemperaturePersist() {
		/*
		 * Remove below comments ONLY for 5.3C task.
		 */
//		System.out.println("+++ testTemperaturePersist +++");
//		
//		// Initialise controller
//		WeatherController wController = WeatherController.getInstance();
//		
//		String persistTime = wController.persistTemperature(10, 19.5);
//		String now = new SimpleDateFormat("H:m:s").format(new Date());
//		System.out.println("Persist time: " + persistTime + ", now: " + now);
//		
//		Assert.assertTrue(persistTime.equals(now));
//		
//		wController.close();
	}
}
