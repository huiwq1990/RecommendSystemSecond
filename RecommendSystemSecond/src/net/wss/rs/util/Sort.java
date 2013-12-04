package net.wss.rs.util;

public class Sort {

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
}
