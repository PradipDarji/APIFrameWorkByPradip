package resources;

public enum APIResources {
	
	createAuthToken("/auth"),
	GetBookingDetailsById("/booking/"),
	createBooking("/booking");

private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
