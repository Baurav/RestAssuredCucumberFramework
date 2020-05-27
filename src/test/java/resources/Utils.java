package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {

		if(req==null) {
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		
		req = new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}
	
	public static String getGlobalProperty(String Key) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Baurav Singh\\Automation_Framework\\RestAssuredFramework\\src\\test\\java\\resources\\global.properties");
	
		prop.load(fis);
		return prop.getProperty(Key);
	}
	
	public String getJsonPath(Response response,String key) {
		String resp = response.asString();
		System.out.println(resp);
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
