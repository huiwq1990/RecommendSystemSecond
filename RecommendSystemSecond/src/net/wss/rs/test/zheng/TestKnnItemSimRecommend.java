package net.wss.rs.test.zheng;

import java.util.HashMap;
import java.util.Map.Entry;

import net.wss.rs.data.zheng.ZhengDataSetConfig;
import net.wss.rs.data.zheng.ZhengDealDataSet;
import net.wss.rs.data.zheng.ZhengDoctorRecommendDataset;
import net.wss.rs.recommend.Recommender;
import net.wss.rs.similarity.zheng.KnnItemSimilarity;

public class TestKnnItemSimRecommend {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		�����ϵ����ļ�������
		int zhengCalCount = 30;
//		�Ƽ�������ҽ������
		int recommendDocNum = 10;
//		ZhengDoctorRecommendDataset ds = new ZhengDoctorRecommendDataset();
		ZhengDoctorRecommendDataset ds = new ZhengDoctorRecommendDataset(ZhengDataSetConfig.AllDoctorPath,ZhengDataSetConfig.AllZhengPath,ZhengDataSetConfig.AllRatingPath);
//		��������Դ
		ZhengDealDataSet dds = new ZhengDealDataSet(ds);
//		��ds�е�sortedRatingsByDoctorId��ֵ
		dds.setAllRatingSort(zhengCalCount);
		
//		����doc-doc���ƾ���
		KnnItemSimilarity knnsim = new KnnItemSimilarity();
		int[][] doctorSimilarity = knnsim.getAllSimilarityByCommonRating(ds);
		
//		�������ƶ��Ƽ�
		Recommender recom = new Recommender();
		HashMap<Integer,String> sortedAllDocSim = recom.recommendSimDoc(doctorSimilarity, recommendDocNum);
		
		for (Entry<Integer, String> entry: sortedAllDocSim.entrySet()) {
			System.out.println("doc id="+entry.getKey()+"  "+entry.getValue());
		}
	}

}
