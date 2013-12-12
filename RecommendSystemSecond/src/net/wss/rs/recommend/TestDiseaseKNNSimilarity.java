package net.wss.rs.recommend;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.wss.rs.data.DataSetConfig;
import net.wss.rs.data.DoctorRecommendDataset;
import net.wss.rs.entity.DoctorEntity;
import net.wss.rs.entity.RatingEntity;


public class TestDiseaseKNNSimilarity {
	public static void main(String[] args) {
		
		testSort();//��һ��ҽ�����μ�������ν�������
//		testAllSort();//������ҽ�����μ�������ν�������
//		testSim();//��������ҽ�������ƶ�
//		testAllSim();//��������ҽ�������ƶ�
	}
	
	public static void testSim(){
//		DoctorRecommendDataset ds = new DoctorRecommendDataset();
		DoctorRecommendDataset ds = new DoctorRecommendDataset(DataSetConfig.AllDoctorPath,DataSetConfig.AllDiseasePath,DataSetConfig.AllRatingPath);
		DiseaseKNNSimilarity knn = new DiseaseKNNSimilarity(ds);
		knn.setAllRatingSort(100);
		
		DoctorEntity d1 = new DoctorEntity();
		d1.setId(2);
		DoctorEntity d2 = new DoctorEntity();
		d2.setId(3);
		int simVal = knn.getSimilarityByCommonRating(d1, d2);
		System.out.println("doc"+d1.getId()+"��doc"+d2.getId()+"���ƶȣ�"+simVal);
	}
	
	public static void testAllSim(){
//		DoctorRecommendDataset ds = new DoctorRecommendDataset();
		DoctorRecommendDataset ds = new DoctorRecommendDataset(DataSetConfig.AllDoctorPath,DataSetConfig.AllDiseasePath,DataSetConfig.AllRatingPath);
		
		DiseaseKNNSimilarity knn = new DiseaseKNNSimilarity(ds);
		knn.setAllRatingSort(100);
		
		int[][] doctorSimilarity = knn.getAllSimilarityByCommonRating();
	
		for (int i = 0; i < doctorSimilarity.length; i++) {
			for (int j = 0; j < doctorSimilarity[i].length; j++) {
				System.out.print(doctorSimilarity[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	
	public static void testSort(){
		DoctorRecommendDataset ds = new DoctorRecommendDataset(DataSetConfig.AllDoctorPath,DataSetConfig.AllDiseasePath,DataSetConfig.AllRatingPath);
		DiseaseKNNSimilarity knn = new DiseaseKNNSimilarity(ds);
		List<RatingEntity> list = knn.ratingSortByDocId(2,2);
		for(int i=0;i<list.size();i++){
			System.out.println("ҽ��"+list.get(i).getDoctorId()+"��"+list.get(i).getDiseaseId()+"������ǣ�"+list.get(i).getRating());
		}
	}
	
	public static void testAllSort(){
		DoctorRecommendDataset ds = new DoctorRecommendDataset(DataSetConfig.AllDoctorPath,DataSetConfig.AllDiseasePath,DataSetConfig.AllRatingPath);
		DiseaseKNNSimilarity knn = new DiseaseKNNSimilarity(ds);
		
		//��������Դ ��������
//		Map<Integer, List<RatingEntity>> sortedRatingsByDoctorId = new HashMap<Integer, List<RatingEntity>>();
//		knn.setAllRatingSort(2);
//		sortedRatingsByDoctorId = ds.getSortedRatingsByDoctorId();		
		//ֱ�ӻ�ȡ���
		Map<Integer, List<RatingEntity>> sortedRatingsByDoctorId = knn.allRatingSort(2);
		
		for (Entry<Integer, List<RatingEntity>> entry: sortedRatingsByDoctorId.entrySet()) {
			 List<RatingEntity>	sortedDocRatingList = entry.getValue();
			 for (int i = 0; i < sortedDocRatingList.size(); i++) {						 
				 System.out.print(sortedDocRatingList.get(i).getDiseaseId()+":"+sortedDocRatingList.get(i).getRating() + " " );	
			 }
			 System.out.println();	
			 }
		}
	}
