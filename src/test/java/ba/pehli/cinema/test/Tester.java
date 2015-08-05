package ba.pehli.cinema.test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ba.pehli.cinema.domain.WSOmdbMovie;

public class Tester {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
		
		String url = "http://www.omdbapi.com/?i=tt0118615";
		WSOmdbMovie result = restTemplate.getForObject(url, WSOmdbMovie.class);
		System.out.println(result);
		try {
			URL posterUrl = new URL(result.getPoster());
			byte[] bytes = IOUtils.toByteArray(posterUrl);
			System.out.println(bytes.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result.getPoster());

	}

}
