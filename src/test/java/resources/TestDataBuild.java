package resources;
import java.time.LocalDate;
import java.util.Random;
import com.github.javafaker.Faker;

import Pojo.AuthPojo;
import Pojo.BookingDates;
import Pojo.BookingDetailsDTO;

public class TestDataBuild {
	
	public static String fName;
	public static String lName;
	public static int tPrice;
	public static boolean paid;
	public static String checkin;
	public static String checkOut;
	public static String extraDetails;
	
	public static AuthPojo addAuthCredeantials(String username, String password) {

		AuthPojo auth = new AuthPojo();
		auth.setUsername(username);
		auth.setPassword(password);

		return auth;
	}

	public static BookingDetailsDTO createBookRequestPayload() {
		Faker faker = new Faker();
		Random rd = new Random();
		BookingDetailsDTO bookingDetails = new BookingDetailsDTO();
		String firstName = faker.name().firstName();
		bookingDetails.setFirstname(firstName);
		fName = bookingDetails.getFirstname();
		String lastName = faker.name().lastName();
		bookingDetails.setLastname(lastName);
		lName = bookingDetails.getLastname();
		int totalPrice = rd.nextInt(10000);
		bookingDetails.setTotalprice(totalPrice);
		tPrice = bookingDetails.getTotalprice();
		bookingDetails.setDepositpaid(rd.nextBoolean());
		paid = bookingDetails.getDepositpaid();
		BookingDates bookingDates = new BookingDates();
		LocalDate todayDate = LocalDate.now();
		LocalDate previousDate = todayDate.minusDays(5); 
		String strchekinDate = previousDate.toString();
		bookingDates.setCheckin(strchekinDate);
		checkin = bookingDates.getCheckin();
		bookingDetails.setBookingdates(bookingDates);
		LocalDate futureDate = todayDate.plusDays(10);
		String strcheckoutDate = futureDate.toString();
		bookingDates.setCheckout(strcheckoutDate);
		checkOut= bookingDates.getCheckout();
		bookingDetails.setBookingdates(bookingDates);
		String additionalNeeds = faker.lorem().word();
		bookingDetails.setAdditionalneeds(additionalNeeds);
		extraDetails = bookingDetails.getAdditionalneeds();
	
		return bookingDetails;
	}

}
