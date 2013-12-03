package net.wss.rs.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.wss.rs.DataSet;
import net.wss.rs.dao.BaseDao;
import net.wss.rs.dao.impl.BaseDaoImpl;
import net.wss.rs.entity.DiseaseEntity;
import net.wss.rs.entity.DoctorEntity;

public class Recommend {

	// private static String PATH = "data.txt";
	// private static String DOCTORPATH = "doctor.txt";
	// private static String DISEASEPATH = "disease.txt";
	//
	// public static DataSet loadDataSet() {
	// DataSet ds = new DataSet();
	// ds.setAllDisease(readDiseaseTxtFile(DISEASEPATH));
	// ds.setAllDoctor(readDoctorTxtFile(DOCTORPATH));
	// ds.setDiseaseByDoctorId(getDiseaseByDoctorId(PATH));
	// return ds;
	//
	// }
	//
	// /*
	// * ��doctor���ݼ����ó��ж��ٸ�ҽ��
	// */
	// public static List<DoctorEntity> readDoctorTxtFile(String filePath) {
	//
	// List<DoctorEntity> allDoctor = new ArrayList<DoctorEntity>();
	// File file = new File(filePath);
	// if (file.isFile() && file.exists()) {
	// String encoding = "GBK";
	// try {
	// InputStreamReader read = new InputStreamReader(
	// new FileInputStream(file), encoding);
	// BufferedReader bufferedReader = new BufferedReader(read);
	// String lineTxt = null;
	// while ((lineTxt = bufferedReader.readLine()) != null) {
	// if (lineTxt.length() == 0) {
	// continue;
	// }
	//
	// String[] array = split(lineTxt);// ����ȡ���ַ����ָ����һ��������
	//
	// DoctorEntity doctorEntity = new DoctorEntity();// ʵ����һ��ҽ��ʵ��
	// doctorEntity.setId(Integer.valueOf(array[0]));// ����ҽ����idΪ�ָ��ַ�����õ�����ĵ�һ��
	// allDoctor.add(doctorEntity);// �����ʵ����ӵ�һ��ר�Ҽ���
	// System.out.println(array[0]);
	//
	// }
	// read.close();
	//
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return allDoctor;
	// }
	//
	// /*
	// * ��disease���ݼ�����ȡ�ж����ֲ�
	// */
	// public static List<DiseaseEntity> readDiseaseTxtFile(String filePath) {
	// DataSet ds = new DataSet();
	// List<DiseaseEntity> allDisease = new ArrayList<DiseaseEntity>();
	// File file = new File(filePath);
	// if (file.isFile() && file.exists()) {
	// String encoding = "GBK";
	// try {
	// InputStreamReader read = new InputStreamReader(
	// new FileInputStream(file), encoding);
	// BufferedReader bufferedReader = new BufferedReader(read);
	// String lineTxt = null;
	// while ((lineTxt = bufferedReader.readLine()) != null) {
	// if (lineTxt.length() == 0) {
	// continue;
	// }
	// String[] array = split(lineTxt);
	//
	// DiseaseEntity diseaseEntity = new DiseaseEntity();
	// diseaseEntity.setId(Integer.valueOf(array[0]));
	// allDisease.add(diseaseEntity);
	// System.out.println(array[0]);
	//
	// }
	// read.close();
	//
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return allDisease;
	// }
	//
	// /*
	// * ��data(ר���뼲���Ĺ�ϵ)���ݼ�
	// */
	// public static Map<Integer, List<Integer>> getDiseaseByDoctorId(
	// String filePath) {
	//
	// Map<Integer, List<Integer>> diseaseByDoctorId = new HashMap<Integer,
	// List<Integer>>();
	//
	// File file = new File(filePath);
	// if (file.isFile() && file.exists()) {
	// String encoding = "GBK";
	// try {
	// InputStreamReader read = new InputStreamReader(
	// new FileInputStream(file), encoding);
	// BufferedReader bufferedReader = new BufferedReader(read);
	// String lineTxt = null;
	// while ((lineTxt = bufferedReader.readLine()) != null) {
	// if (lineTxt.length() == 0) {
	// continue;
	// }
	//
	// String[] array = split(lineTxt);
	//
	// Integer docId = Integer.parseInt(array[0]);
	// Integer disId = Integer.parseInt(array[1]);
	// // DiseaseEntity dis = new DiseaseEntity();
	// // dis.setId(Integer.parseInt(array[1]));
	//
	//
	// if (diseaseByDoctorId.containsKey(docId)) {
	// diseaseByDoctorId.get(docId).add(disId);
	// } else {
	// List<Integer> list = new ArrayList<Integer>();
	// list.add(disId);
	// diseaseByDoctorId.put(docId, list);
	// }
	//
	// }
	// read.close();
	//
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// return diseaseByDoctorId;
	// }
	//
	// /*
	// * public static Map<Integer, List<DiseaseEntity>>
	// * getDiseaseByDoctorId(String filePath) {
	// *
	// * Map<Integer, List<DiseaseEntity>> diseaseByDoctorId =new
	// HashMap<Integer,
	// * List<DiseaseEntity>>();
	// *
	// *
	// * File file = new File(filePath); if (file.isFile() && file.exists()) {
	// * String encoding = "GBK"; try { InputStreamReader read = new
	// * InputStreamReader( new FileInputStream(file), encoding); BufferedReader
	// * bufferedReader = new BufferedReader(read); String lineTxt = null; while
	// * ((lineTxt = bufferedReader.readLine()) != null) { if (lineTxt.length()
	// ==
	// * 0) { continue; }
	// *
	// *
	// * String[] array = split(lineTxt); Integer docId =
	// * Integer.parseInt(array[0]); DiseaseEntity dis = new DiseaseEntity();
	// * dis.setId(Integer.parseInt(array[1]));
	// *
	// *
	// * if(diseaseByDoctorId.containsKey(docId)){
	// * diseaseByDoctorId.get(docId).add(dis); }else{ List<DiseaseEntity> list
	// =
	// * new ArrayList<DiseaseEntity>(); list.add(dis);
	// * diseaseByDoctorId.put(docId, list); }
	// *
	// *
	// * } read.close();
	// *
	// * } catch (UnsupportedEncodingException e) { // TODO Auto-generated catch
	// * block e.printStackTrace(); } catch (FileNotFoundException e) { // TODO
	// * Auto-generated catch block e.printStackTrace(); } catch (IOException e)
	// {
	// * // TODO Auto-generated catch block e.printStackTrace(); } }
	// *
	// * return diseaseByDoctorId; }
	// */
	//
	// /*
	// * ��data(ר���뼲���Ĺ�ϵ)���ݼ�
	// */
	// public static Map<Integer, Integer> readTxtFile(String filePath) {
	//
	// Map<Integer, Integer> userMap = new HashMap<Integer, Integer>();
	// // List<DiseaseEntity> allDisease = new ArrayList<DiseaseEntity>();
	// // List<DoctorEntity> allDoctor = new ArrayList<DoctorEntity>();
	// // Map<Integer, List<DiseaseEntity>> diseaseByDoctorId =new
	// // HashMap<Integer, List<DiseaseEntity>>();
	//
	// File file = new File(filePath);
	// if (file.isFile() && file.exists()) {
	// String encoding = "GBK";
	// try {
	// InputStreamReader read = new InputStreamReader(
	// new FileInputStream(file), encoding);
	// BufferedReader bufferedReader = new BufferedReader(read);
	// String lineTxt = null;
	// while ((lineTxt = bufferedReader.readLine()) != null) {
	// if (lineTxt.length() == 0) {
	// continue;
	// }
	// String[] array = split(lineTxt);
	// userMap.put(1, Integer.parseInt(array[0]));
	// userMap.put(2, Integer.parseInt(array[1]));
	// System.out.println(array[0]);
	//
	// }
	// read.close();
	//
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// return userMap;
	// }
	//
	// /*
	// * ��һ���ַ����ÿո����ʽ�ָ�洢��һ�����鵱��
	// */
	// public static String[] split(String lineTxt) {
	// String[] array = lineTxt.split("\\s+");
	// // System.out.println(array[0]+" ,"+array[1]);
	// return array;
	// }

	/**
	 * 
	 * ���������û����ͼ�������
	 * ��������ҽ��������ͬ����������
	 * 
	 */
	public static int getSimilarity(DoctorEntity doctor1, DoctorEntity doctor2,
			Map<Integer, List<Integer>> diseaseByDoctorId) {
		List<Integer> dis1 = diseaseByDoctorId.get(doctor1.getId());// ��ȡĳһ���û��������ļ���
		List<Integer> dis2 = diseaseByDoctorId.get(doctor2.getId());
		// �������������������һ��Ϊ�գ���˵�������û������ƣ�����0
		if (dis1 == null || dis2 == null) {
			return 0;
		}

		int count = 0;
		// �ж��������������Ƿ��н������������count+1
		for (int d1 : dis1) {
			for (int d2 : dis2) {
				if (d1 == d2) {
					// System.out.print(d1);
					count++;
					continue;// ��������ѭ��
				}
			}
		}
		return count;
	}

	/**
	 * �õ�����ҽ��������ͬ�ļ����ĸ������Ƚϣ�
	 * @param allDisease
	 * @param allDoctor
	 * @param diseaseByDoctorId
	 * @return
	 */
	public static int[][] getAllDoctorSimilartiy(List<DiseaseEntity> allDisease,List<DoctorEntity> allDoctor,Map<Integer, List<Integer>> diseaseByDoctorId){

		int[][] doctorSimilarity = new int[allDoctor.size()][allDoctor.size()];
		for (int i = 0; i < allDoctor.size(); i++) {
			DoctorEntity doc1 = allDoctor.get(i);
			for (int j = 0; j < allDoctor.size(); j++) {
				DoctorEntity doc2 = allDoctor.get(j);
				if (i > j) {
					continue;//��������ѭ����ִ���´�ѭ��
				}
				int count = getSimilarity(doc1, doc2, diseaseByDoctorId);//�ҳ����Ƶĸ���
				doctorSimilarity[i][j] = count;
				doctorSimilarity[j][i] = count;//��Ϊ�Ǹ��ԳƵ�
			}
		}

	
		return doctorSimilarity;
	} 
	/**
	 * ��ӡ����ҽ�������Ƶĸ�����
	 * �Խ�����ҽ�����Ƶļ�������
	 * ��������ҽ��������ҽ��������ͬ��������Ŀ
	 * 3 1 1 1 1 0 0 0 
	   1 3 1 0 2 0 0 0 
	   1 1 3 1 2 0 0 0 
	   1 0 1 2 0 0 0 0 
	   1 2 2 0 3 0 0 0 
	   0 0 0 0 0 0 0 0 
	   0 0 0 0 0 0 0 0 
	   0 0 0 0 0 0 0 0 
	 * @param allDoctor
	 * @param doctorSimilarity
	 */
	public static void printAllDoctorSimilartiy(List<DoctorEntity> allDoctor,int[][] doctorSimilarity){
		for (int i = 0; i < allDoctor.size(); i++) {

			for (int j = 0; j < allDoctor.size(); j++) {
				System.out.print(doctorSimilarity[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	
	public static void main(String[] args) {

		BaseDao bd = new BaseDaoImpl();
		DataSet ds = bd.loadDataSet();
//		int[][] doctorSimilarity = getAllDoctorSimilartiy(ds.getAllDisease(),ds.getAllDoctor(),ds.getDiseaseByDoctorId());
//		printAllDoctorSimilartiy(ds.getAllDoctor(),doctorSimilarity);
		// readDoctorTxtFile(DOCTORPATH);
		// readDiseaseTxtFile(DISEASEPATH);
		// readTxtFile(PATH);
//		BaseDao bd = new BaseDaoImpl();
//		DataSet ds = bd.loadDataSet();
//		System.out.println("��ȡ�ɹ�");
//		List<DiseaseEntity> allDisease = ds.getAllDisease();
//
//		List<DoctorEntity> allDoctor = ds.getAllDoctor();
//
//		Map<Integer, List<Integer>> diseaseByDoctorId = ds
//				.getDiseaseByDoctorId();

		// DoctorEntity doc1 = allDoctor.get(0);
		// DoctorEntity doc2 = allDoctor.get(1);
		// int count = getSimilarity(doc1,doc2,diseaseByDoctorId);
		// System.out.println(count);

		// �����û��Ƚ�
//		int[][] doctorSimilarity = new int[allDoctor.size()][allDoctor.size()];
//		for (int i = 0; i < allDoctor.size(); i++) {
//			DoctorEntity doc1 = allDoctor.get(i);
//			for (int j = 0; j < allDoctor.size(); j++) {
//				DoctorEntity doc2 = allDoctor.get(j);
//				if (i > j) {
//					continue;
//				}
//				int count = getSimilarity(doc1, doc2, diseaseByDoctorId);
//				doctorSimilarity[i][j] = count;
//				doctorSimilarity[j][i] = count;
//			}
//		}
//
//		for (int i = 0; i < allDoctor.size(); i++) {
//
//			for (int j = 0; j < allDoctor.size(); j++) {
//				System.out.print(doctorSimilarity[i][j] + " ");
//			}
//			System.out.println();
//		}
//
//		ds.print();
//	}  
}
}
