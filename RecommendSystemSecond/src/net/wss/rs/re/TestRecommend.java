package net.wss.rs.re;

import net.wss.rs.entity.DoctorEntity;
import net.wss.rs.text.DoctorRecommendDataset;

public class TestRecommend {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DoctorRecommendDataset ds = new DoctorRecommendDataset();
//		ds.printAllRating();
		ds.printAllRatingMatrix();
		RecommendDoctor re =new RecommendDoctor(ds);
		
		DoctorEntity d1 = new DoctorEntity();
		d1.setId(1);
		DoctorEntity d2 = new DoctorEntity();
		d2.setId(2);
		
		int count = re.getSimilarityByCommonRating(d1, d2);
		System.out.println("�û�"+d1.getId()+"���û�"+d2.getId()+"���ƶ�"+count);
		
		int count1 = re.getSimilarityBySumCommonRating(d1, d2);
		System.out.println("�û�"+d1.getId()+"���û�"+d2.getId()+"���ƶ�"+count1);
		
		
		
	}
}
