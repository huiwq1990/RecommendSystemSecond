package net.wss.rs.similarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.wss.rs.DataSet;
import net.wss.rs.entity.DiseaseEntity;
import net.wss.rs.entity.DoctorEntity;
import net.wss.rs.entity.RatingEntity;

public class Data {
	private static String REDOCDISPATH = "data/redocdiscount.txt";
	private static String DOCTORPATH = "data/doctor.txt";
	private static String DISEASEPATH = "data/disease.txt";

	/**
	 * �������ݼ�
	 * 
	 * @return
	 */
	public static DataSet loadDataSet() {
		
		DataSet ds = new DataSet();
		ds.setAllDisease(readDiseaseTxtFile(DISEASEPATH));
		ds.setAllDoctor(readDoctorTxtFile(DOCTORPATH));
		ds.setDiseaseByDoctorId(getDiseaseByDoctorId(REDOCDISPATH));
		return ds;

	}

	/*
	 * ��doctor���ݼ����ó��ж��ٸ�ҽ��
	 */
	public static Map<Integer,DoctorEntity> readDoctorTxtFile(String filePath) {

		Map<Integer,DoctorEntity> allDoctor = new HashMap<Integer,DoctorEntity>();
		File file = new File(filePath);
		
		if (file.isFile() && file.exists()) {
			String encoding = "GBK";
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
//					System.out.println(lineTxt);
					if (lineTxt.length() == 0) {
						continue;
					}

					String[] array = split(lineTxt);// ����ȡ���ַ����ָ����һ��������

					DoctorEntity doctorEntity = new DoctorEntity();// ʵ����һ��ҽ��ʵ��
					doctorEntity.setId(Integer.parseInt(array[0]));
					
					doctorEntity.setId(Integer.valueOf(array[0]));// ����ҽ����idΪ�ָ��ַ�����õ�����ĵ�һ��
					allDoctor.put(doctorEntity.getId(), doctorEntity);// �����ʵ����ӵ�һ��ר�Ҽ���
//					System.out.println(doctorEntity.getId());

				}
				read.close();

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.err.println(file.getName()+" not exist");
		}
		return allDoctor;
	}

	/*
	 * ��disease���ݼ�����ȡ�ж����ֲ�
	 */
	public static Map<Integer,DiseaseEntity> readDiseaseTxtFile(String filePath) {
		Map<Integer,DiseaseEntity> allDisease = new HashMap<Integer,DiseaseEntity>();
		File file = new File(filePath);
		
		if (file.isFile() && file.exists()) {
			String encoding = "GBK";
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
//					System.out.println(lineTxt);
					if (lineTxt.length() == 0) {
						continue;
					}

					String[] array = split(lineTxt);// ����ȡ���ַ����ָ����һ��������

					DiseaseEntity diseaseEntity = new DiseaseEntity();// ʵ����һ��ҽ��ʵ��
					diseaseEntity.setId(Integer.parseInt(array[0]));
					
					diseaseEntity.setId(Integer.valueOf(array[0]));// ����ҽ����idΪ�ָ��ַ�����õ�����ĵ�һ��
					allDisease.put(diseaseEntity.getId(), diseaseEntity);// �����ʵ����ӵ�һ��ר�Ҽ���
//					System.out.println(doctorEntity.getId());

				}
				read.close();

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.err.println(file.getName()+" not exist");
		}
		return allDisease;
	}

	/*
	 * ��ȡ���е�rating
	 */
	public static List<RatingEntity> loadRatings(String filePath) {
		List<RatingEntity> allRating = new ArrayList<RatingEntity>();
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			String encoding = "GBK";
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
//					System.out.println(lineTxt);
					if (lineTxt.length() == 0) {
						continue;
					}

					String[] array = split(lineTxt);// ����ȡ���ַ����ָ����һ��������
				int docId = Integer.parseInt(array[0]);
				int disId = Integer.parseInt(array[1]);
				int rating = Integer.parseInt(array[2]);
				allRating.add(new RatingEntity(docId, disId, rating));
				}
				read.close();

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

			
		return allRating;
	}

	/*
	 * ��data(ר���뼲���Ĺ�ϵ)���ݼ�
	 */
	public static Map<Integer, List<Integer>> getDiseaseByDoctorId(
			String filePath) {

		Map<Integer, List<Integer>> diseaseByDoctorId = new HashMap<Integer, List<Integer>>();

		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			String encoding = "GBK";
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (lineTxt.length() == 0) {
						continue;
					}

					String[] array = split(lineTxt);

					Integer docId = Integer.parseInt(array[0]);//ҽ���ı��
					Integer disId = Integer.parseInt(array[1]);//�����ı��
					Integer treatmentTimes = Integer.parseInt(array[2]);//���ƴ���
					// DiseaseEntity dis = new DiseaseEntity();
					// dis.setId(Integer.parseInt(array[1]));

					if (diseaseByDoctorId.containsKey(docId)) {
						diseaseByDoctorId.get(docId).add(disId);
					} else {
						List<Integer> list = new ArrayList<Integer>();
						list.add(disId);
						diseaseByDoctorId.put(docId, list);
					}

				}
				read.close();

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return diseaseByDoctorId;
	}

	
	
	/*
	 * public static Map<Integer, List<DiseaseEntity>>
	 * getDiseaseByDoctorId(String filePath) {
	 * 
	 * Map<Integer, List<DiseaseEntity>> diseaseByDoctorId =new HashMap<Integer,
	 * List<DiseaseEntity>>();
	 * 
	 * 
	 * File file = new File(filePath); if (file.isFile() && file.exists()) {
	 * String encoding = "GBK"; try { InputStreamReader read = new
	 * InputStreamReader( new FileInputStream(file), encoding); BufferedReader
	 * bufferedReader = new BufferedReader(read); String lineTxt = null; while
	 * ((lineTxt = bufferedReader.readLine()) != null) { if (lineTxt.length() ==
	 * 0) { continue; }
	 * 
	 * 
	 * String[] array = split(lineTxt); Integer docId =
	 * Integer.parseInt(array[0]); DiseaseEntity dis = new DiseaseEntity();
	 * dis.setId(Integer.parseInt(array[1]));
	 * 
	 * 
	 * if(diseaseByDoctorId.containsKey(docId)){
	 * diseaseByDoctorId.get(docId).add(dis); }else{ List<DiseaseEntity> list =
	 * new ArrayList<DiseaseEntity>(); list.add(dis);
	 * diseaseByDoctorId.put(docId, list); }
	 * 
	 * 
	 * } read.close();
	 * 
	 * } catch (UnsupportedEncodingException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } catch (FileNotFoundException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } catch (IOException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * return diseaseByDoctorId; }
	 */
	
	 private DoctorEntity getDoctor(Integer docId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ��data(ר���뼲���Ĺ�ϵ)���ݼ�
	 */
	public static Map<Integer, Integer> readTxtFile(String filePath) {

		Map<Integer, Integer> userMap = new HashMap<Integer, Integer>();

		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			String encoding = "GBK";
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (lineTxt.length() == 0) {
						continue;
					}
					String[] array = split(lineTxt);
					userMap.put(1, Integer.parseInt(array[0]));
					userMap.put(2, Integer.parseInt(array[1]));
					System.out.println(array[0]);

				}
				read.close();

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return userMap;
	}

	/*
	 * ��һ���ַ����ÿո����ʽ�ָ�洢��һ�����鵱��
	 */
	public static String[] split(String lineTxt) {
		String[] array = lineTxt.split("\\s+");
		// System.out.println(array[0]+" ,"+array[1]);
		return array;
	}
/*
 * �������ӵ�map������
 */
	public void addRatingToMap(Map<Integer, List<RatingEntity>> map, Integer key,
			RatingEntity rating) {
		List<RatingEntity> ratingsForKey = map.get(key);
		if (ratingsForKey == null) {
			ratingsForKey = new ArrayList<RatingEntity>();
			map.put(key, ratingsForKey);
		}
		ratingsForKey.add(rating);
	}
	
	private void loadData(List<RatingEntity> ratings) {
		List<RatingEntity> allRating = new ArrayList<RatingEntity>();
		DataSet ds = new DataSet();
		
			/* Load all available ratings */
			if (ratings == null) {
				allRating = loadRatings(REDOCDISPATH);
			} else {
				allRating = ratings;
			}
			/* build maps that provide access to ratings by userId or itemId */
			for (RatingEntity rating : allRating) {
				addRatingToMap(ds.getRatingsByDiseaseId(), rating.getDiseaseId(), rating);
				addRatingToMap(ds.getRatingsByDoctorId(), rating.getDoctorId(), rating);
			}
	}
	/**
	 * 
	 * ���������û����ͼ������� ��������ҽ��������ͬ����������
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
					continue;// ������ǰѭ��
				}
			}
		}
		return count;
	}

	/**
	 * �õ�����ҽ��������ͬ�ļ����ĸ������Ƚϣ�
	 * 
	 * @param allDisease
	 * @param allDoctor
	 * @param diseaseByDoctorId
	 * @return
	 */
	public static int[][] getAllDoctorSimilartiy(
			List<DiseaseEntity> allDisease, List<DoctorEntity> allDoctor,
			Map<Integer, List<Integer>> diseaseByDoctorId) {

		int[][] doctorSimilarity = new int[allDoctor.size()][allDoctor.size()];
		for (int i = 0; i < allDoctor.size(); i++) {
			DoctorEntity doc1 = allDoctor.get(i);
			for (int j = 0; j < allDoctor.size(); j++) {
				DoctorEntity doc2 = allDoctor.get(j);
				if (i > j) {
					continue;// ��������ѭ����ִ���´�ѭ��
				}
				int count = getSimilarity(doc1, doc2, diseaseByDoctorId);// �ҳ����Ƶĸ���
				doctorSimilarity[i][j] = count;
				doctorSimilarity[j][i] = count;// ��Ϊ�Ǹ��ԳƵ�
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
	public static void printAllDoctorSimilartiy(List<DoctorEntity> allDoctor,
			int[][] doctorSimilarity) {
		for (int i = 0; i < allDoctor.size(); i++) {

			for (int j = 0; j < allDoctor.size(); j++) {
				System.out.print(doctorSimilarity[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * ��ĳһ��ҽ�������ƽ�����򣬰��ս���ķ�ʽ
	 * value�������ҽ������������
	 * index�����±꣬Ŀ��ʹ����ǰ����±걣��һ��
	 * ���±귵��
	 * @param doctorSimilarity
	 * @return
	 */
	public static int[]  similaritySort(int[] doctorSimilarity){
		int[] value = doctorSimilarity;		
		int[] index = new int[value.length]; 
		for(int i=0;i < value.length;i++){
			index[i]=i;//�ȸ��±긳ֵ
		}
		
		for (int i = 0; i < value.length - 1; i++) {
			for (int j = i + 1; j < value.length; j++) {
				if (value[i] < value[j]) {// ��С��С�����>
					int tempc = value[i];
					value[i] = value[j];
					value[j] = tempc;

					int tempb = index[i];
					index[i] = index[j];
					index[j] = tempb;
				}
			}
		}
		return index;
	}
	/**
	 * ��ӡ�����Ľ��������������±�Ľ��
	 * @param value
	 * @param index
	 */
	public static void printSortedSimilarity(int[] value,int[] index){
		for (int i = 0; i < index.length; i++) {
			System.out.print(index[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < value.length; i++) {
			System.out.print(value[i] + " ");
		}
		System.out.println();
	}
	/**
	 * ��ӡ�������±�
	 * @param index
	 */
	public static void printSortedSimilarity(int[] index){
		for (int i = 0; i < index.length; i++) {
			System.out.print(index[i] + " ");
		}
		System.out.println();
		
	}
	/**
	 * ������ҽ���������Խ�������
	 * @param doctorSimilarity
	 * @return
	 */
	public static int[][] similaritySort(int[][] doctorSimilarity){
			
		int[][] sortedindex = new int[doctorSimilarity.length][doctorSimilarity[0].length];
		
		for(int i = 0;i<sortedindex.length;i++){

				int[] index = similaritySort(doctorSimilarity[i]);
				sortedindex[i] =index;
		}
		return sortedindex;
	}
	
	
	public static void main(String[] args) {
		DataSet ds = loadDataSet();
//		System.out.println("dajiahao");
		/*
		 * ��ȡ�����û��Ĳ���
		 */
//		Map<Integer,DoctorEntity> allDoctor = ds.getAllDoctor();
//		System.out.println("Doctor������"+allDoctor.size());
//		System.out.println("Doctor��id:");
//		ds.printAllDoctor();
		
		/*
		 * ��ȡ���м����Ĳ���
		 */
//		Map<Integer,DiseaseEntity> allDisease = ds.getAllDisease();
//		System.out.println("Disease������"+allDisease.size());
//		System.out.println("Disease��id:");
//		ds.printAllDisease();
      /*
       * ����ĳ���û����Ƶļ��������
       */


//		int[][] doctorSimilarity = getAllDoctorSimilartiy(ds.getAllDisease(),
//				ds.getAllDoctor(), ds.getDiseaseByDoctorId());
//		printAllDoctorSimilartiy(ds.getAllDoctor(), doctorSimilarity);
//		
//		int[][] allIndex = similaritySort(doctorSimilarity);
//		
//		for (int i = 0; i < allIndex.length - 1; i++) {
//			for (int j = 0; j < allIndex[i].length; j++) {
//				System.out.print(allIndex[i][j] + " ");
//			}
//			System.out.println();
//		}
		
//		int[] index = similaritySort(doctorSimilarity[1]);
//		printSortedSimilarity(index);
		
	}
}
