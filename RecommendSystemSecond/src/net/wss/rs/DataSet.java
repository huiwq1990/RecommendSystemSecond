package net.wss.rs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.wss.rs.entity.DiseaseEntity;
import net.wss.rs.entity.DoctorEntity;
import net.wss.rs.entity.RatingEntity;

public class DataSet {
	/*
	 * ���м��������
	 */
	private List<RatingEntity> allRating = new ArrayList<RatingEntity>();

	/*
	 * �õ����е�ҽ��.
	 */
	private Map<Integer, DoctorEntity> allDoctor = new HashMap<Integer, DoctorEntity>();

	/*
	 * �õ����еļ���
	 */
	private Map<Integer, DiseaseEntity> allDisease = new HashMap<Integer, DiseaseEntity>();

	/*
	 *ͨ��������id�õ����м��������
	 * 
	 */
	private Map<Integer, List<RatingEntity>> ratingsByDiseaseId = new HashMap<Integer, List<RatingEntity>>();
/*
 * ��ȡĳ���û���ĳ����������ϴ���
 */
	private Map<Integer,Integer> ratingByDiseaseIdDocId = new HashMap<Integer,Integer>();
	/*
	 *
	 * ͨ��ҽ����id�õ����м��������
	 */
	Map<Integer, List<RatingEntity>> ratingsByDoctorId = new HashMap<Integer, List<RatingEntity>>();
	/*
	 * ĳ��ҽ��˵���Ƶļ����ļ���
	 */
	private Map<Integer, List<Integer>> diseaseByDoctorId = new HashMap<Integer, List<Integer>>();

	
	
	
	public Map<Integer, Integer> getRatingByDiseaseIdDocId() {
		return ratingByDiseaseIdDocId;
	}

	public void setRatingByDiseaseIdDocId(
			Map<Integer, Integer> ratingByDiseaseIdDocId) {
		this.ratingByDiseaseIdDocId = ratingByDiseaseIdDocId;
	}

	public List<RatingEntity> getAllRating() {
		return allRating;
	}

	public void setAllRating(List<RatingEntity> allRating) {
		this.allRating = allRating;
	}

	public Map<Integer, DoctorEntity> getAllDoctor() {
		return allDoctor;
	}

	public void setAllDoctor(Map<Integer, DoctorEntity> allDoctor) {
		this.allDoctor = allDoctor;
	}

	public Map<Integer, DiseaseEntity> getAllDisease() {
		return allDisease;
	}

	public void setAllDisease(Map<Integer, DiseaseEntity> allDisease) {
		this.allDisease = allDisease;
	}

	public Map<Integer, List<RatingEntity>> getRatingsByDiseaseId() {
		return ratingsByDiseaseId;
	}

	public void setRatingsByDiseaseId(
			Map<Integer, List<RatingEntity>> ratingsByDiseaseId) {
		this.ratingsByDiseaseId = ratingsByDiseaseId;
	}

	public Map<Integer, List<RatingEntity>> getRatingsByDoctorId() {
		return ratingsByDoctorId;
	}

	public void setRatingsByDoctorId(
			Map<Integer, List<RatingEntity>> ratingsByDoctorId) {
		this.ratingsByDoctorId = ratingsByDoctorId;
	}

	public Map<Integer, List<Integer>> getDiseaseByDoctorId() {
		return diseaseByDoctorId;
	}

	public void setDiseaseByDoctorId(Map<Integer, List<Integer>> diseaseByDoctorId) {
		this.diseaseByDoctorId = diseaseByDoctorId;
	}


	
	
	/**
	 * �ж�ר�Һͼ����Ƿ���ڹ�ϵ
	 * 
	 * @param docId
	 * @param disId
	 * @return
	 */
	public boolean isExist(int docId, int disId) {
		List<Integer> disList = diseaseByDoctorId.get(docId);// ����һ�����ϣ����һ��ҽ�����ε����м���

		if (disList == null) {// ������Ϊ�յ�ʱ�򣬷���false
			return false;
		}

		for (int tempDisId : disList) {
			if (tempDisId == disId) {
				return true;
			}
		}
		return false;
	}
	/**
	 * ��ӡ����ҽ����key-value,key,value��ҽ����id
	 */
	public void printAllDoctor() {

		for (Entry<Integer, DoctorEntity> entry: allDoctor.entrySet()) {
			Integer key = entry.getKey();
			DoctorEntity  value = entry.getValue();
			
			System.out.println(value.getId());
		}
	}
	/**
	 * ��ӡ���м���key-value��key,value�Ǽ�����id
	 */
	public void printAllDisease() {

		for (Entry<Integer, DiseaseEntity> entry: allDisease.entrySet()) {
			Integer key = entry.getKey();
			DiseaseEntity  value = entry.getValue();
			
			System.out.println(value.getId());
		}
	}
	/**
	 * ��ӡ�������key-value��key,value�Ǽ�����id
	 */
	public void printAllRating() {

		for (Entry<Integer, RatingEntity> entry: allRating.entrySet()) {
			Integer key = entry.getKey();
			DiseaseEntity  value = entry.getValue();
			
			System.out.println(value.getId());
		}
	}

	/**
	 * ��ӡ�����е�ҽ�����������Ƶļ������������ʽ��ʾ�� 
	 * ���ж��Ƿ���ڹ�ϵ 
	 * ���ҽ������ĳ��������������Ϊ1 
	 * ���ҽ��������ĳ��������������Ϊ0
	 */
//	public void print() {
//		for (DoctorEntity de : allDoctor) {
//			for (DiseaseEntity dis : allDisease) {
//				int docId = de.getId();// �õ�ҽ����id
//				int disId = dis.getId();// �õ�������id
//				if (isExist(docId, disId)) {// �ж�ҽ���ͼ����Ƿ��й�ϵ
//					System.out.print("1" + " ");// �й�ϵ���1
//				} else {
//					System.out.print("0" + " ");// û�й�ϵ���0
//				}
//
//			}
//			System.out.println();
//		}
//	}

}
