package net.wss.rs.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import net.wss.rs.dao.BaseDao;
import net.wss.rs.dao.DoctorDao;

import net.wss.rs.entity.DoctorEntity;

public class DoctorDaoImpl implements DoctorDao {

	/*
	 * ��doctor���ݼ����ó��ж��ٸ�ҽ��
	 */
	// @Override
	public List<DoctorEntity> readDoctorTxtFile(String filePath) {

		List<DoctorEntity> allDoctor = new ArrayList<DoctorEntity>();
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
					BaseDao bd = new BaseDaoImpl();
					String[] array = bd.split(lineTxt);// ����ȡ���ַ����ָ����һ��������

					DoctorEntity doctorEntity = new DoctorEntity();// ʵ����һ��ҽ��ʵ��
					doctorEntity.setId(Integer.valueOf(array[0]));// ����ҽ����idΪ�ָ��ַ�����õ�����ĵ�һ��
					allDoctor.add(doctorEntity);// �����ʵ����ӵ�һ��ר�Ҽ���
					System.out.println(array[0]);

				}
				read.close();

			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {
				//
				e.printStackTrace();
			}
		}
		return allDoctor;
	}

}
