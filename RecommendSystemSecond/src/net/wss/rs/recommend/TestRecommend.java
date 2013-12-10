package net.wss.rs.recommend;

import net.wss.rs.data.DataSetConfig;
import net.wss.rs.data.DoctorRecommendDataset;
import net.wss.rs.entity.DoctorEntity;
import net.wss.rs.util.Sort;

public class TestRecommend {
	public static void main(String[] args) {

//		DoctorRecommendDataset ds = new DoctorRecommendDataset();
		DoctorRecommendDataset ds = new DoctorRecommendDataset(DataSetConfig.AllDoctorPath,DataSetConfig.AllDiseasePath,DataSetConfig.AllRatingPath);
//		System.out.println("��ӡ���е�����");
//		ds.printAllRating();
//		System.out.println("��ӡ���е����־���");
//		ds.printAllRatingMatrix();
		RecommendDoctor re =new RecommendDoctor(ds);
//		
//		DoctorEntity d1 = new DoctorEntity();
//		d1.setId(1);
//		DoctorEntity d2 = new DoctorEntity();
//		d2.setId(2);
//		
//		int count = re.getSimilarityByCommonRating(d1, d2);
//		System.out.println("ҽ��"+d1.getId()+"��ҽ��"+d2.getId()+"���ƶ�"+count);
//		
//		int count1 = re.getSimilarityBySumCommonRating(d1, d2);
//		System.out.println("ҽ��"+d1.getId()+"��ҽ��"+d2.getId()+"���ƶ�"+count1);
		
		System.out.println("����ҽ����ͬ���Ĳ��ĸ������Խ��������ҽ��һ���������ٲ���");
		//0��ʾ������޹�
		//1��ʾ������й�
		int[][] allDoctorSimilarity = re.getAllDoctorSimilarity(1);
		
		for(int docId=1; docId< allDoctorSimilarity.length;docId++){
			int[] doctorSim = allDoctorSimilarity[docId];//�������count
			int[] doctorSimIndexAsc=Sort.similaritySort(doctorSim);//���±��������û���id
			
			System.out.println("��ҽ��"+docId+"���ƵĽ������������");
			for(int i=0; i< doctorSimIndexAsc.length;i++){
				System.out.print(doctorSimIndexAsc[i] + " ");
			}
			System.out.println();
			System.out.println("��ҽ��"+docId+"�����Ƶ�ǰ4��ҽ��");
			int kSim =10+1;
			for(int i=0; i<kSim ;i++){
				
				System.out.print(doctorSimIndexAsc[i] + " ");
			}
			System.out.println();
		}
	}
}
