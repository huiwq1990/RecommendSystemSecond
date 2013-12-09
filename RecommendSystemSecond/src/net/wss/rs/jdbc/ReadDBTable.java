package net.wss.rs.jdbc;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.wss.rs.data.DataSetConfig;
import net.wss.rs.util.FileUtil;


public class ReadDBTable {
	DBConnection db;

	public ReadDBTable(DBConnection db) {
		this.db = db;
	}

	public ReadDBTable() {
		this.db = new DBConnection();
	}

	ResultSet rs = null;
	
	Statement stmt = null;
	Connection conn=null;
	
	
	public void getDoctorData() {
		
		try {
			// ���ݿ����ӵĻ�ȡ
			Connection conn = db.getConn();
			stmt = conn.createStatement();
			// ���ݿ�sql����ƴ��
			String sql = "select id,name,cure_china_disease from kb_doctor";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
//				String line = id+";"+name;
//				newFile("d:\\doctor.txt",line);
//				String cure_china_disease= rs.getString("cure_china_disease");
//				newFile("d:\\disease.txt",cure_china_disease);
				
				//�����һ�е�����ƴ���Էֺ����һ���ַ�����Ȼ��д��txt�ļ���
//				String line1 = id+";"+name+";"+cure_china_disease;
//				FileUtil.appendData(DataSetConfig.AllDataPath, lin1);
//				System.out.println(id+";"+name+";"+cure_china_disease);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		db.close(rs);
		db.close(stmt);
		db.close(conn);

	}
//	public static void newFile(String filePathAndName, String fileContent) {
//        try {
//            File myFilePath = new File(filePathAndName.toString());
//            if (!myFilePath.exists()) { // ������ļ�������,�򴴽�
//                myFilePath.createNewFile();
//            }
//            // FileWriter(myFilePath, true); ʵ�ֲ�����׷�ӵ��ļ���
//             //FileWriter(myFilePath); ���ǵ�ԭ��������
//            FileWriter resultFile = new FileWriter(myFilePath, true);
//            PrintWriter myFile = new PrintWriter(resultFile);
//            // ���ļ�����д����,ԭ���ĻḲ�ǵ�
//            myFile.println(fileContent);
//            resultFile.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

