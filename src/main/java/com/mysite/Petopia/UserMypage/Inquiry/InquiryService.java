package com.mysite.Petopia.UserMypage.Inquiry;

import java.util.List;

public interface InquiryService {
	void insertinquiry(InquiryDTO inquiryDTO);
	
	List<InquiryDTO> inquiryList(String user);

	public void inquiryDelete(InquiryDTO inquiryDTO);

	public InquiryDTO inquiryModify(InquiryDTO inquiryDTO);

	List<InquiryDTO> inquiryListAll();

	public InquiryDTO insertAnswer(InquiryDTO inquiryDTO);
	
	public InquiryDTO updateAnswer(InquiryDTO inquiryDTO);
	
	public InquiryDTO inquiryAnswerDelete(InquiryDTO inquiryDTO);
	
	int countByUserEmail(String UserEmail);
}
