package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	public RequestSpecification requestspecification() throws Exception
	{
		if(req==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder().setBaseUri(getGlobalvalue("baseUrl")).addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	public static String getGlobalvalue(String key) throws Exception
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return  prop.getProperty(key);
		
	}
	public static String  getJsonPath(Response response,String key)
	{
		String res=response.asString();
		JsonPath js=new JsonPath(res);
		return js.getString(key);
		
	}
	

}
