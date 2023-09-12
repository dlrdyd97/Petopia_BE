package com.mysite.Petopia.UserMypage.Inquiry;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InquiryController {

	private InquiryServiceImpl inquiryServiceImpl;


	public InquiryController(InquiryServiceImpl inquiryServiceImpl) {
		this.inquiryServiceImpl = inquiryServiceImpl;
	}

	@RequestMapping("/inquirywrite")
	public void insertinquiry(@RequestBody InquiryDTO inquiryDTO) {
		inquiryServiceImpl.insertinquiry(inquiryDTO);
	}
	
	@RequestMapping("/inquirylist")
	public List<InquiryDTO> getinquirylist(@RequestParam String user) {
		return inquiryServiceImpl.inquiryList(user);
	}

	@RequestMapping("/inquirydelete")
	public void deleteInquiry(@RequestBody InquiryDTO inquiryDTO) {
		inquiryServiceImpl.inquiryDelete(inquiryDTO);
	}

	@RequestMapping("/inquiryupdate")
	public InquiryDTO updateInquiry(@RequestBody InquiryDTO inquiryDTO) {
		return inquiryServiceImpl.inquiryModify(inquiryDTO);
	}

	@RequestMapping("/inquiryadminlist")
	public List<InquiryDTO> inquirylistall() {
		return inquiryServiceImpl.inquiryListAll();
	}

	@RequestMapping("/inquiryanswer")
	public InquiryDTO insertAnswer(@RequestBody InquiryDTO inquiryDTO) {
		return inquiryServiceImpl.insertAnswer(inquiryDTO);
	}

	@RequestMapping("/inquiryUpdateAnswer")
	public InquiryDTO updateAnswer(@RequestBody InquiryDTO inquiryDTO) {
		return inquiryServiceImpl.updateAnswer(inquiryDTO);
	}

	@RequestMapping("/inquiryAnswerDelete")
	public InquiryDTO inquiryAnswerDelete(@RequestBody InquiryDTO inquiryDTO) {
		return inquiryServiceImpl.inquiryAnswerDelete(inquiryDTO);
	}
	
	@RequestMapping("/inquiryCount")
	public int inquiryCountByWriterEmail(@RequestParam("email") String email) {
		return inquiryServiceImpl.countByUserEmail(email);
	}

}
