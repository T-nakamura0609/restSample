package myexample.zipcodeclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import myexample.zipcodeclient.dto.ZipcodeResponseResource;

@Service
public class ZipCodeService {

	@Autowired
	private RestTemplate restTemplate;

	// リクエストURL
//	private static final String URL = "http://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";
	@Value("${url}")
	private String url;

	public ZipcodeResponseResource service(String zipcode) {
		// 通信ついでに
		// レスポンスのJSONデータをオブジェクトにイイカンジに変換して返してくれる
		return restTemplate.getForObject(url, ZipcodeResponseResource.class,zipcode);
	}
}
