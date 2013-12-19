package net.wss.rs.compare;

import java.util.HashMap;
import java.util.Map.Entry;

import net.wss.rs.data.disease.DataSetConfig;
import net.wss.rs.data.disease.DealDataSet;
import net.wss.rs.data.disease.DoctorRecommendDataset;
import net.wss.rs.data.zheng.ZhengDataSetConfig;
import net.wss.rs.data.zheng.ZhengDealDataSet;
import net.wss.rs.data.zheng.ZhengDoctorRecommendDataset;
import net.wss.rs.recommend.Recommender;
import net.wss.rs.recommend.ZhengRecommend;
import net.wss.rs.similarity.disease.KnnItemSimilarity;
import net.wss.rs.similarity.zheng.ZhengKnnItemSimilarity;

public class DiseaseUnionZheng {
	
public static void main(String[] args) {

//		�����ϵ����ļ�������
		int zhengCalCount = 12;
//		�Ƽ�������ҽ������
		int recommendDocNum = 10;
//		ZhengDoctorRecommendDataset ds = new ZhengDoctorRecommendDataset();
		DoctorRecommendDataset ds = new DoctorRecommendDataset(DataSetConfig.AllDoctorPath,DataSetConfig.AllDiseasePath,DataSetConfig.AllRatingPath);
		ZhengDoctorRecommendDataset ds1 = new ZhengDoctorRecommendDataset(ZhengDataSetConfig.AllDoctorPath,ZhengDataSetConfig.AllZhengPath,ZhengDataSetConfig.AllRatingPath);
//		��������Դ
		DealDataSet dds = new DealDataSet(ds);
		ZhengDealDataSet dds1 = new ZhengDealDataSet(ds1);
//		��ds�е�sortedRatingsByDoctorId��ֵ
		dds.setAllRatingSort(zhengCalCount);
		dds1.setAllRatingSort(zhengCalCount);
		
//		����doc-doc���ƾ���
		KnnItemSimilarity knnsim = new KnnItemSimilarity();
		ZhengKnnItemSimilarity knnsim1 = new ZhengKnnItemSimilarity();
		int[][] doctorSimilarity = knnsim.getAllSimilarityByCommonRating(ds);
		int[][] doctorSimilarity1 = knnsim1.getAllSimilarityByCommonRating(ds1);
		
//		�������ƶ��Ƽ�
		Recommender recom = new Recommender();
		ZhengRecommend recom1 = new ZhengRecommend();
		HashMap<Integer,String> sortedAllDocSim = recom.recommendSimDoc(doctorSimilarity, recommendDocNum);
		HashMap<Integer,String> sortedAllDocSim1 = recom1.recommendSimDoc(doctorSimilarity1, recommendDocNum);
		
		for (Entry<Integer, String> entry: sortedAllDocSim.entrySet()) {
			String line = entry.getKey()+DataSetConfig.AttrSplit+entry.getValue();
			
			String[] array = line.split(";");
			if(array[0].length()>0&&array[1].length()>0){
			int docId = Integer.parseInt(array[0]);
	
			String simline = array[1];
			
			 String[] simArray=simline.split(",");
			
			 for(int i =0;i<simArray.length;i++){
				 System.out.println(simArray[i]);
			 }
			 }
			System.out.println("doc id="+entry.getKey()+"  "+entry.getValue());
		}
	}
	
}
