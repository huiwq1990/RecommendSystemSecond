package net.wss.rs.evaluation;

import java.io.File;
import java.util.List;
import java.util.Map.Entry;

import net.wss.rs.data.DataSetConfig;
import net.wss.rs.data.DoctorRecommendDataset;
import net.wss.rs.entity.DoctorEntity;
import net.wss.rs.entity.RatingEntity;
import net.wss.rs.util.FileUtil;

public class DoctorEvaluation {
	DoctorRecommendDataset ds;

	public DoctorEvaluation(DoctorRecommendDataset ds) {
		this.ds = ds;
	}
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
		System.out.println(sim);
		if(commDiseases>0){
//			sim=1.0d - Math.tanh(sim);
			sim=1.0/(sim+1);
		}
		
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
		
		File file = new File(DataSetConfig.SimiResultPath);
		if (file.exists()) {		
			file.delete();
		} 
		
		for (int i = 0; i < doctorSimilarity.length; i++) {
			String line="";
			for (int j = 0; j < doctorSimilarity[i].length; j++) {
				line +=String.format("%.4f",doctorSimilarity[i][j]) + " ";
//				System.out.print(String.format("%.4f",doctorSimilarity[i][j]) + " ");
			}
			System.out.println();
			FileUtil.appendData(DataSetConfig.SimiResultPath, line);
		}
	
		return doctorSimilarity;
	}
}