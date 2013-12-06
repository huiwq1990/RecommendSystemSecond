package net.wss.rs.data;

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
import java.util.Map.Entry;
import java.util.regex.Pattern;

import net.wss.rs.entity.DiseaseEntity;
import net.wss.rs.entity.DoctorEntity;
import net.wss.rs.entity.RatingEntity;
import net.wss.rs.jdbc.ReadDBTable;
import net.wss.rs.util.StringUtil;

public class DoctorRecommendDataset {

	public static String REDOCDISPATH = "data/redocdiscount.txt";
	public static String DOCTORPATH = "data/doctor.txt";
	public static String DISEASEPATH = "data/disease.txt";

	String docPath;
	String disPath;
	String ratingPath;
	/**
	 * ���м��������
	 */
	public List<RatingEntity> allRating = new ArrayList<RatingEntity>();

	/**
	 * �õ����е�ҽ��.
	 */
	public Map<Integer, DoctorEntity> allDoctor = new HashMap<Integer, DoctorEntity>();

	/**
	 * �õ����еļ���
	 */
	public Map<Integer, DiseaseEntity> allDisease = new HashMap<Integer, DiseaseEntity>();

	/**
	 * ͨ��������id�õ����м��������
	 * 
	 */
	public Map<Integer, List<RatingEntity>> ratingsByDiseaseId = new HashMap<Integer, List<RatingEntity>>();

	/**
	 * ͨ��ҽ����id�õ����м��������
	 */
	public Map<Integer, List<RatingEntity>> ratingsByDoctorId = new HashMap<Integer, List<RatingEntity>>();

	/**
	 * ��������Դ
	 */
	public DoctorRecommendDataset() {
		this.docPath=DOCTORPATH;
		this.disPath = DISEASEPATH;
		this.ratingPath = REDOCDISPATH;
		
		readDoctorTxtFile(DOCTORPATH);
		readDiseaseTxtFile(DISEASEPATH);
		loadRatings(REDOCDISPATH);

	}
	
	/**
	 * ���ݿ�����
	 * @param docPath
	 * @param disPath
	 * @param ratingPath
	 */
	public DoctorRecommendDataset(String docPath,String disPath,String ratingPath) {
		this.docPath=docPath;
		this.disPath = disPath;
		this.ratingPath = ratingPath;
		readDoctorTxtFile(docPath);
		readDiseaseTxtFile(disPath);
		loadRatings(ratingPath);

	}


	/**
	 * ��doctor���ݼ����ó��ж��ٸ�ҽ��
	 */
	public void readDoctorTxtFile(String filePath) {

		// Map<Integer,DoctorEntity> allDoctor = new
		// HashMap<Integer,DoctorEntity>();
		File file = new File(filePath);

		if (file.isFile() && file.exists()) {
			String encoding = "GBK";
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(lineTxt);
					if (lineTxt.length() == 0) {
						continue;
					}

					String[] array = split(lineTxt);// ����ȡ���ַ����ָ����һ��������

					DoctorEntity doctorEntity = new DoctorEntity();// ʵ����һ��ҽ��ʵ��

					doctorEntity.setId(Integer.valueOf(array[0]));// ����ҽ����idΪ�ָ��ַ�����õ�����ĵ�һ��
					if (array[1] != null) {
						doctorEntity.setName(array[1]);
					} else {
						doctorEntity.setName("");
					}
					allDoctor.put(doctorEntity.getId(), doctorEntity);// �����ʵ����ӵ�һ��ר�Ҽ���
					// System.out.println(doctorEntity.getId());

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
		} else {
			System.err.println(file.getName() + " not exist");
		}
	}

	/**
	 * ��disease���ݼ�����ȡ�ж����ֲ�
	 */
	public void readDiseaseTxtFile(String filePath) {

		File file = new File(filePath);

		if (file.isFile() && file.exists()) {
			String encoding = "GBK";
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(lineTxt);
					if (lineTxt.length() == 0) {
						continue;
					}

					String[] array = split(lineTxt);// ����ȡ���ַ����ָ����һ��������

					DiseaseEntity diseaseEntity = new DiseaseEntity();// ʵ����һ��ҽ��ʵ��
					diseaseEntity.setId(Integer.parseInt(array[0]));

					diseaseEntity.setId(Integer.valueOf(array[0]));// ����ҽ����idΪ�ָ��ַ�����õ�����ĵ�һ��
					allDisease.put(diseaseEntity.getId(), diseaseEntity);// �����ʵ����ӵ�һ��ר�Ҽ���
					// System.out.println(doctorEntity.getId());

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
		} else {
			System.err.println(file.getName() + " not exist");
		}

	}

	
	

	/**
	 * ��ȡ���е�rating
	 */
	public void loadRatings(String filePath) {

		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			String encoding = "GBK";
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(lineTxt);
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

		/* build maps that provide access to ratings by userId or itemId */
		for (RatingEntity rating : allRating) {
			addRatingToMap(ratingsByDiseaseId, rating.getDiseaseId(), rating);
			addRatingToMap(ratingsByDoctorId, rating.getDoctorId(), rating);
		}

	}

	/**
	 * �������ӵ�map������
	 */
	public void addRatingToMap(Map<Integer, List<RatingEntity>> map,
			Integer key, RatingEntity rating) {
		List<RatingEntity> ratingsForKey = map.get(key);
		if (ratingsForKey == null) {
			ratingsForKey = new ArrayList<RatingEntity>();
			map.put(key, ratingsForKey);
		}
		ratingsForKey.add(rating);
	}

	/**
	 * ��ӡ����ҽ����key-value,key,value��ҽ����id
	 */
	public void printAllDoctor() {

		for (Entry<Integer, DoctorEntity> entry : allDoctor.entrySet()) {
			Integer key = entry.getKey();
			DoctorEntity value = entry.getValue();

			System.out.println("doc id:" + value.getId() + "  doc name:"
					+ value.getName());
		}
	}

	/**
	 * ��ӡ���м���key-value��key,value�Ǽ�����id
	 */
	public void printAllDisease() {

		for (Entry<Integer, DiseaseEntity> entry : allDisease.entrySet()) {
			Integer disKey = entry.getKey();
			DiseaseEntity value = entry.getValue();

			System.out.println("dis id:" + value.getId());
		}
	}

	/**
	 * ��ӡ�������key-value��key,value�Ǽ�����id
	 */
	public void printAllRating() {

		for (Entry<Integer, DoctorEntity> entry : allDoctor.entrySet()) {
			Integer docKey = entry.getKey();
			System.out.print("doc id:" + docKey + "  ");
			List<RatingEntity> docRatingList = ratingsByDoctorId.get(docKey);
			if (docRatingList == null) {
				System.out.println();
				continue;
			}
			for (RatingEntity rating : docRatingList) {
				System.out.print(rating.getDiseaseId() + ","
						+ rating.getRating() + "  ");
			}
			System.out.println();
		}
	}

	/**
	 * ��ӡ������ҽ�������Ƹ���
	 * 
	 * @param allDoctor
	 * @param doctorSimilarity
	 */
	public void printAllDoctorSimilartiy(List<DoctorEntity> allDoctor,
			int[][] doctorSimilarity) {
		for (int i = 0; i < allDoctor.size(); i++) {

			for (int j = 0; j < allDoctor.size(); j++) {
				System.out.print(doctorSimilarity[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * ��ӡҽ���Լ���������
	 */
	public void printAllRatingMatrix() {
		int[][] ratingMatrix = new int[allDoctor.size()][allDisease.size()];
		for (Entry<Integer, DoctorEntity> doctorEntry : allDoctor.entrySet()) {
			for (Entry<Integer, DiseaseEntity> entry1 : allDisease.entrySet()) {
				Integer docKey = doctorEntry.getKey();
				// System.out.print( "doc id:"+key+"  ");
				// List<RatingEntity> docRatingList =
				// ratingsByDoctorId.get(key);
				Integer disKey = entry1.getKey();
				// System.out.print( "dis id:"+key1+"  ");
				List<RatingEntity> disRatingList = ratingsByDoctorId
						.get(docKey);
				if (disRatingList == null) {
					System.out.print(0 + " ");
					continue;
				}
				int r = 0;
				for (RatingEntity rating : disRatingList) {
					if (rating.getDiseaseId() == disKey) {
						r = rating.getRating();
					}
				}
				System.out.print(r + " ");
			}
			System.out.println();
		}
	}

	// int[][] ratingMatrix = new int[allDoctor.size()][allDisease.size()];
	// System.out.println("suoyouyonghu "+allDoctor.size());
	// System.out.println("suoyoujibing "+allDisease.size());
	// for (int i = 0; i < allDoctor.size(); i++) {
	// System.out.println("ratingsByDoctorId"+ratingsByDoctorId.get(i));
	// for (int j = 0; j < allDisease.size(); j++) {
	// System.out.println("ҽ���й�ϵ��"+ratingsByDoctorId.get(i).get(j));
	// if(ratingsByDoctorId.get(i).get(j)==null){
	//
	// ratingMatrix[i][j]=0;
	// System.out.println(ratingMatrix[i][j]+" ");
	//
	// }else{
	// ratingMatrix[i][j]=ratingsByDoctorId.get(i).get(j).getRating();
	// System.out.print(ratingMatrix[i][j] + " ");
	// }
	// }
	// System.out.println();
	// }

	/**
	 * ��һ���ַ����÷ֺŵ���ʽ�ָ�洢��һ�����鵱��
	 * 
	 * @param lineTxt
	 *            ����
	 * @return
	 */
	public static String[] split(String lineTxt) {
		// String[] array = lineTxt.split("\\s+");//�ո����ʽ�ָ�
		String[] array = lineTxt.split(";");
		// System.out.println(array[0]+" ,"+array[1]);
		return array;
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

}
