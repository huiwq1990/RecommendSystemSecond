package net.wss.rs.re;

import java.util.List;
import java.util.Map;

import net.wss.rs.entity.DoctorEntity;
import net.wss.rs.entity.RatingEntity;
import net.wss.rs.text.DoctorRecommendDataset;

public class RecommendDoctor {
	DoctorRecommendDataset ds ;
	public RecommendDoctor(DoctorRecommendDataset ds) {
		this.ds=ds;
	}
	
	/**
	 * ���������û������ƶ�
	 * ������Σ�ֻ���㹲ͬ���Ĳ��ĸ���
	 */
	public int getSimilarityByCommonRating(DoctorEntity doctor1, DoctorEntity doctor2) {
		List<RatingEntity> dis1 = ds.getRatingsByDoctorId().get(doctor1.getId());// ��ȡĳһ���û��������ļ���
		List<RatingEntity> dis2 = ds.getRatingsByDoctorId().get(doctor2.getId());
		
		// �������������������һ��Ϊ�գ���˵�������û������ƣ�����0
		int count = 0;
		if (dis1 == null || dis2 == null) {
			System.err.println("�û�������");
			return 0;
			
		}

		
		// �ж��������������Ƿ��н������������count+1
	
		for(int i =0;i<dis1.size();i++){
			for(int j =0;j<dis2.size();j++){
				if(dis1.get(i).getDiseaseId()==dis2.get(j).getDiseaseId()){
					count++;				
				}
			}
		}
		return count;
	}
	
	
	/**
	 * ���������û������ƶ�
	 * ������Σ�ֻ���㹲ͬ���Ĳ��ĸ���
	 */
	public int getSimilarityBySumCommonRating(DoctorEntity doctor1, DoctorEntity doctor2) {
		List<RatingEntity> dis1 = ds.getRatingsByDoctorId().get(doctor1.getId());// ��ȡĳһ���û��������ļ���
		List<RatingEntity> dis2 = ds.getRatingsByDoctorId().get(doctor2.getId());
		
		// �������������������һ��Ϊ�գ���˵�������û������ƣ�����0
		if (dis1 == null || dis2 == null) {
			System.err.println("�û�������");
			return 0;
			
		}

		
		// �ж��������������Ƿ��н������������count+1
		int min=0;
		for(int i =0;i<dis1.size();i++){
			for(int j =0;j<dis2.size();j++){
				if(dis1.get(i).getDiseaseId()==dis2.get(j).getDiseaseId()){
					int dis1Rating = dis1.get(i).getRating();
					int dis2Rating = dis2.get(j).getRating();
					if(dis1Rating>dis2Rating){
					  min = min+dis2Rating;						
					}else{
						min = min+dis1Rating;
					}			
				}
			}
		}
		return min;
	}
	
	/**
	 * �õ�����ҽ��������ͬ�ļ����ĸ������Ƚϣ�
	 * 
	 * @param allDisease
	 * @param allDoctor
	 * @param diseaseByDoctorId
	 * 
	 */
//	public void getAllDoctorSimilartiy(
//			List<DiseaseEntity> allDisease, List<DoctorEntity> allDoctor,
//			Map<Integer, List<Integer>> diseaseByDoctorId) {
//
//		int[][] doctorSimilarity = new int[allDoctor.size()][allDoctor.size()];
//		for (int i = 0; i < allDoctor.size(); i++) {
//			DoctorEntity doc1 = allDoctor.get(i);
//			for (int j = 0; j < allDoctor.size(); j++) {
//				DoctorEntity doc2 = allDoctor.get(j);
//				if (i > j) {
//					continue;// ��������ѭ����ִ���´�ѭ��
//				}
//				int count = getSimilarity(doc1, doc2, diseaseByDoctorId);// �ҳ����Ƶĸ���
//				doctorSimilarity[i][j] = count;
//				doctorSimilarity[j][i] = count;// ��Ϊ�Ǹ��ԳƵ�
//			}
//		}
//	}
	
	
	
	
}
