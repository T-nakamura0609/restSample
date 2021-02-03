package myexample.zipcodeclient.container;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import myexample.zipcodeclient.dto.ZipcodeRequestResource;
import myexample.zipcodeclient.dto.ZipcodeResponseResource;
import myexample.zipcodeclient.service.ZipCodeService;

@RequestMapping("/zipcode")
@Controller
public class ZipcodeRecieveController {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(ZipcodeRecieveController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ZipCodeService zpcService;

	public static final String URL="https://zipcloud.ibsnet.co.jp/api/search?zipcode=1640001";

	/**
	 * 入力フォーム
	 * @param model
	 * @return
	 */
	@RequestMapping(path = {"/", "input"})
	public String zipcodeInputForm(HttpSession session, Model model) {
		// confirm2の方でデータを受ける場合は
		// 入力データの格納用オブジェクトを生成して渡す必要がある
		model.addAttribute("zipcodeRequestResource", new ZipcodeRequestResource());
		return "ZipcodeRequestForm";
	}

	@RequestMapping(value="confirm1", method = RequestMethod.POST)
	public String zipcodeConfirm(HttpSession session, Model model, @RequestParam("zipcode") String zipcode) {

		// 入力チェック
		if(zipcode == null || zipcode.equals("")) {
			model.addAttribute("errorMessage", "郵便番号を入力してください");
			return zipcodeInputForm(session, model);
		}
		// 引数の値をserviceクラスへ渡す
		ZipcodeResponseResource result = zpcService.service(zipcode);
		// レスポンス結果をmodelへ渡す
		model.addAttribute("zipcodeList", result.getResults());

		// 結果表示用viewを指定
		return "zipcode-confirm";
	}

	@RequestMapping(value="confirm2", method = RequestMethod.POST)
	public String zipcodeConfirm(HttpSession session, Model model, @ModelAttribute ZipcodeRequestResource zipcodeRequestResource) {
		String zipcode = zipcodeRequestResource.getZipcode();
		// 入力チェック
		if(zipcode == null || zipcode.equals("")) {
			model.addAttribute("errorMessage", "郵便番号を入力してください");
			return zipcodeInputForm(session, model);
		}
		ZipcodeResponseResource result = zpcService.service(zipcode);
		model.addAttribute("zipcodeList", result.getResults());

		return "zipcode-confirm";
	}

	// これ以降のメソッドは
	@RequestMapping(value="/request1", method=RequestMethod.GET)
	public ModelAndView recieverClient(Model model) {
		ModelAndView mv = new ModelAndView("ZipcodeTestPrint");

		String tmp = restTemplate.getForObject(URL, String.class);
		log.info("rcv1 : " + tmp);

		mv.addObject("RcvZipcode", tmp);

		return mv;
	}

	@RequestMapping(value="/request2", method=RequestMethod.GET)
	public ModelAndView recieverClient(ModelAndView mav) {

		String tmp = restTemplate.getForObject(URL, String.class);
		log.info("rcv2 : " + tmp);

		// viewの表示先変数を指定
		mav.addObject("RcvZipcode", tmp);

		// viewの指定
		mav.setViewName("ZipcodeTestPrint");


		return mav;
	}

	@RequestMapping(value="/request3", method=RequestMethod.GET)
	public String getZipcode(Model model) {
		String tmp = restTemplate.getForObject(URL, String.class);

		log.info("rcv3 : " + tmp);

		model.addAttribute("RcvZipcode", tmp);

		// 戻り値でviewを指定
		return "ZipcodeTestPrint";
	}
}
