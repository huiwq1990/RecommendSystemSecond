package net.wss.rs.evaluation;

import java.util.List;
import java.util.Map.Entry;

import net.wss.rs.data.DoctorRecommendDataset;
import net.wss.rs.entity.DoctorEntity;
import net.wss.rs.entity.RatingEntity;

public class DoctorEvaluation {
	DoctorRecommendDataset ds;

	public DoctorEvaluation(DoctorRecommendDataset ds) {
		this.ds = ds;
	}
//	/**
//	 * ���������Ĳ�ֵ
//	 * @param a
//	 * @param b
//	 * @return
//	 */
//	public int calculatePoor(int a,int b){
//		int poor=0;
//		poor=a-b;
//		return poor;
//	}
//	/**
//	 * �����ֵ��ƽ��
//	 * @param poor
//	 * @return
//	 */
//	public int calculateSquare(int poor){
//		int square =0;
//		square=poor*poor;
//		return square;
//	}
//	/**
//	 * ����ƽ����
//	 * @param square
//	 * @return
//	 */
//	public int calculateSquareAnd(int square){
//		int squareAnd=0;
//		squareAnd=squareAnd+square;
//		return squareAnd;
//	}
//	/**
//	 * ��ƽ������ƽ����
//	 * @param squareAnd
//	 * @return
//	 */
//	public double calculateSquareRoot(int squareAnd){
//		double squareAndRoot=0.0;
//		squareAndRoot=Math.sqrt(squareAnd);
//		return squareAndRoot;
//	}
//	/**
//	 * ȡ�ü����Ľ��
//	 * @param a
//	 * @param b
//	 * @return
//	 */
//	public double calculateResult(int a,int b){
//		double result =0.0;
//		int poor=calculatePoor(a,b);
//		int square=calculateSquare(poor);
//		int squareAnd=calculateSquareAnd(square);
//		result=calculateSquareRoot(squareAnd);
//		return result;
//	}
	/**
	 * ���������û������ƶ�
	 * @param doctor1
	 * @param doctor2
	 * @return
	 */
	
	public double getSimilarityByCommonRating(DoctorEntity doctor1,
			DoctorEntity doctor2) {
		List<RatingEntity> dis1 = ds.getRatingsByDoctorId()
				.get(doctor1.getId());// ��ȡĳһ��ҽ���������ļ���
		List<RatingEntity> dis2 = ds.getRatingsByDoctorId()
				.get(doctor2.getId());
		
		int commDiseases=0;
		if (dis1 == null || dis2 == null) {
//			System.err.println("ҽ��û���ι���");
			return 0;
		}
		
		
//		for (int i = 0; i < dis1.size(); i++) {
//			System.out.print(dis1.get(i).getDiseaseId()+":"+dis1.get(i).getRating()+" ");			
//		}
//		System.out.println();
//		for (int i = 0; i < dis2.size(); i++) {
//			System.out.print(dis2.get(i).getDiseaseId()+":"+dis2.get(i).getRating()+" ");			
//		}
//		System.out.println();
//		

		// �ж��������������Ƿ��н���
		double sim=0.0;
		
		for (int i = 0; i < dis1.size(); i++) {
			for (int j = 0; j < dis2.size(); j++) {
				if (dis1.get(i).getDiseaseId() == dis2.get(j).getDiseaseId()) {
					commDiseases++;
					int dis1Rating = dis1.get(i).getRating();
					int dis2Rating = dis2.get(j).getRating();
//					System.out.println("common dis id:"+dis1.get(i).getDiseaseId()+" "+dis1Rating+" "+dis2Rating);
					sim += Math.pow((dis1Rating-dis2Rating),2);
				}
			}
		}
//		System.out.println(sim);
//		if(commDiseases>0){
//			sim=1.0d - Math.tanh(sim);
//		}
		
		return sim;
	}
	/**
	 * ��������ҽ�������ƶ�
	 */
	public double[][] getAllDoctorSimilarity() {
		int allDoctorSize=ds.getAllDoctor().size();
//		System.out.println("allDoctorSize"+allDoctorSize);
		
		double[][] doctorSimilarity = new double[allDoctorSize+1][allDoctorSize+1];
	
		for (Entry<Integer, DoctorEntity> entry: ds.getAllDoctor().entrySet()) {
			DoctorEntity doc1 = entry.getValue();
			int i= doc1.getId();
			for (Entry<Integer, DoctorEntity> entry2: ds.getAllDoctor().entrySet()) {
				
				DoctorEntity doc2 = entry2.getValue();
				int j= doc2.getId();
				if (doc2.getId() > doc1.getId()) {
					continue;// ��������ѭ����ִ���´�ѭ��
				}
//				System.out.println(doc1.getId());
//				System.out.println(doc2.getId());
				double count=0;
				count = getSimilarityByCommonRating(doc1, doc2);// �ҳ����Ƶĸ���
				doctorSimilarity[i][j] = count;
				doctorSimilarity[j][i] = count;// ��Ϊ�Ǹ��ԳƵ�
			}
		}
		
		for (int i = 0; i < doctorSimilarity.length; i++) {
			for (int j = 0; j < doctorSimilarity[i].length; j++) {
				System.out.print(doctorSimilarity[i][j] + " ");
			}
			System.out.println();
		}
		
		return doctorSimilarity;
	}
}
