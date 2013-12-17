package net.wss.rs.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.wss.rs.entity.DoctorEntity;
import net.wss.rs.entity.RatingEntity;
import net.wss.rs.util.Sort;

public class DealDataSet {
	
	DoctorRecommendDataset ds;
	
	
	
	public DealDataSet(DoctorRecommendDataset ds) {
		super();
		this.ds = ds;
	}


	/**
	 * ��һ��ҽ�����μ����������ֽ�������,ȡ��ǰk��
	 * 
	 * k = 0 ȫ��
	 * k > 0 ȡǰK��
	 * k < 0 ��
	 * @param doctor1
	 * @return
	 */
	public List<RatingEntity> ratingSortByDocId(int docId,int k){
		if(k < 0){
			return new ArrayList<RatingEntity>();
		}
		
		List<RatingEntity> docRatingList =ds.ratingsByDoctorId.get(docId);
		if(docRatingList==null){
			System.out.println("doctor:"+docId+"û�п���");
			return new ArrayList<RatingEntity>();
		}
		//��doc���Ĳ�list����ת����array
//		RatingEntity[] docRatingArray = new  RatingEntity[docRatingList.size()];
//		docRatingList.toArray(docRatingArray);		
		//ȡ��ҽ�������֣���������
//		int[] docRatingValueArray = new int[docRatingArray.length];
//		for (int i=0; i<docRatingArray.length;i++) {
//			docRatingValueArray[i] = docRatingArray[i].getRating();
//		}

		//ȡ��ҽ�������֣���������
		int[] docRatingValueArray = new int[docRatingList.size()];
		for (int i=0; i<docRatingValueArray.length;i++) {
			docRatingValueArray[i] = docRatingList.get(i).getRating();
		}
		//���������򣬷�������
		int[] docRatingIndexArray  = Sort.similaritySort(docRatingValueArray);
		
		//�����±���list���ҳ���Ӧ��ʵ��
		List<RatingEntity> sortedDocRatingList =new ArrayList<RatingEntity>();
		int listLen = 0;
		if(k > 0 && docRatingIndexArray.length > k){
			listLen = k;
		}else{
			listLen = docRatingIndexArray.length;
		}
//		System.out.println(listLen);
		for (int i = 0; i < listLen; i++) {
			int docIdIndex = docRatingIndexArray[i];
			RatingEntity sortedRating = docRatingList.get(docIdIndex);
			sortedDocRatingList.add(sortedRating);	
		}
		
//		for (int i = 0; i < sortedDocRatingList.size(); i++) {		
//			System.out.print(sortedDocRatingList.get(i).getDiseaseId()+":"+sortedDocRatingList.get(i).getRating() + " " );
//		}		
//		System.out.println();
		
		return sortedDocRatingList;
	}
	
	
	/**
	 * ������ҽ���������ֽ�������
	 * ��һ�ַ������޷���ֵ
	 * @param k  ȡǰk��
	 * @return
	 */
	public void setAllRatingSort(int k){
//		Map<Integer, List<RatingEntity>> sortedRatingsByDoctorId;
		
		for (Entry<Integer, DoctorEntity> entry: ds.allDoctor.entrySet()) {
			Integer docKey = entry.getKey();//��ȡһ��ҽ����id
//			System.out.print( "doc id:"+docKey+"  ");
			 ds.sortedRatingsByDoctorId.put(docKey, ratingSortByDocId(docKey,k));
		}		
		
	}
	/**
	 * ������ҽ���������ֽ������У�ȡ��ǰk������
	 * �ڶ��ַ������з���ֵ
	 * @param k
	 * @return
	 */
	public Map<Integer, List<RatingEntity>> allRatingSort(int k){
		Map<Integer, List<RatingEntity>> sortedRatingsByDoctorId = new HashMap<Integer, List<RatingEntity>>();
		
		for (Entry<Integer, DoctorEntity> entry: ds.allDoctor.entrySet()) {
			Integer docKey = entry.getKey();//��ȡһ��ҽ����id
//			System.out.print( "doc id:"+docKey+"  ");
			sortedRatingsByDoctorId.put(docKey, ratingSortByDocId(docKey,k));
		}
		
		return sortedRatingsByDoctorId;
	}
	
}
