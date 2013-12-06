package net.wss.rs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.wss.rs.entity.DiseaseEntity;
import net.wss.rs.entity.DoctorEntity;

public class DataSet {

	/*
	 * ���м����ļ���.
	 */
	private List<DiseaseEntity> allDisease = new ArrayList<DiseaseEntity>();
	/*
	 * ����ר�ҵļ���
	 */
	private List<DoctorEntity> allDoctor = new ArrayList<DoctorEntity>();
	/*
	 * ĳ��ҽ��˵���Ƶļ����ļ���
	 */
	private Map<Integer, List<Integer>> diseaseByDoctorId = new HashMap<Integer, List<Integer>>();

	public List<DiseaseEntity> getAllDisease() {
		return allDisease;
	}

	public void setAllDisease(List<DiseaseEntity> allDisease) {
		this.allDisease = allDisease;
	}

	public List<DoctorEntity> getAllDoctor() {
		return allDoctor;
	}

	public void setAllDoctor(List<DoctorEntity> allDoctor) {
		this.allDoctor = allDoctor;
	}

	public Map<Integer, List<Integer>> getDiseaseByDoctorId() {
		return diseaseByDoctorId;
	}

	public void setDiseaseByDoctorId(
			Map<Integer, List<Integer>> diseaseByDoctorId) {
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
	 * ��ӡ�����е�ҽ�����������Ƶļ������������ʽ��ʾ�� 
	 * ���ж��Ƿ���ڹ�ϵ 
	 * ���ҽ������ĳ��������������Ϊ1 
	 * ���ҽ��������ĳ��������������Ϊ0
	 */
	public void print() {
		for (DoctorEntity de : allDoctor) {
			for (DiseaseEntity dis : allDisease) {
				int docId = de.getId();// �õ�ҽ����id
				int disId = dis.getId();// �õ�������id
				if (isExist(docId, disId)) {// �ж�ҽ���ͼ����Ƿ��й�ϵ
					System.out.print("1" + " ");// �й�ϵ���1
				} else {
					System.out.print("0" + " ");// û�й�ϵ���0
				}

			}
			System.out.println();
		}
	}

}
