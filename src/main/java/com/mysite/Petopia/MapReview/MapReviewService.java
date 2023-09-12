package com.mysite.Petopia.MapReview;

import java.util.List;

public interface MapReviewService {
	
	public Long insertReview(MapReviewDTO mapReviewDTO);
	
	public void reviewimgupload(String uploadfiles,Long num);
	
	public List<MapReviewDTO> reviewList(Long id,int num);

	public List<ReviewImgDTO> reviewImgList(Long id);
	
	public List<ReviewImgDTO> reviewReportImg(Long id);
	
	int countByWriterEmail(String writerEmail);
	
	void incrementReportStatus(Long reviewId);
}