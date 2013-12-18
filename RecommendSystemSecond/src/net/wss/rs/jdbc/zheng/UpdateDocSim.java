package net.wss.rs.jdbc.zheng;

import java.util.HashMap;
import java.util.Map.Entry;

import net.wss.rs.data.zheng.ZhengDataSetConfig;
import net.wss.rs.data.zheng.ZhengDealDataSet;
import net.wss.rs.data.zheng.ZhengDoctorRecommendDataset;
import net.wss.rs.recommend.ZhengRecommend;
import net.wss.rs.similarity.zheng.KnnItemSimilarity;

public class UpdateDocSim {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		�����ϵ����ļ�������
		int zhengCalCount = 10;
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
		ZhengRecommend recom = new ZhengRecommend();
		HashMap<Integer,String> sortedAllDocSim = recom.recommendSimDoc(doctorSimilarity, recommendDocNum);
		KbDoctorService kbservice = new KbDoctorService();
		for (Entry<Integer, String> entry: sortedAllDocSim.entrySet()) {
			kbservice.updateDocSim(entry.getKey(), entry.getValue());
			System.out.println("doc id="+entry.getKey()+"  "+entry.getValue());
		}
	}

}
