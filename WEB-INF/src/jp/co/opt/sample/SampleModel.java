package jp.co.opt.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SampleModel {
	private int groupId = 0;
	private String itemId = "";
	private String columnValue = "";
	
	/**
	 * @return groupId
	 */
	public int getGroupId() {
		return groupId;
	}
	
	/**
	 * @param groupId セットする groupId
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * @return itemId
	 */
	public String getItemId() {
		return itemId;
	}
	
	/**
	 * @param itemId セットする itemId
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * @return columnValue
	 */
	public String getColumnValue() {
		return columnValue;
	}
	
	/**
	 * @param columnValue セットする columnValue
	 */
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
	
	/**
	 * メインのロジックです。
	 * コードマスターの中身を全文検索します。
	 * 
	 * @param arg 検索文字列
	 */
	public static List<SampleModel> mainLogic(String arg) throws Exception {
		// Oracle JDBC Driverのロード
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// Oracle8iに接続
		Connection conn =
				DriverManager.getConnection
						("jdbc:oracle:thin:@vssdb.c3qujzuklxao.ap-northeast-1.rds.amazonaws.com:1521:VSSDB", "VSSAPP", "VSSAPPVSSAPP");
		// ステートメントを作成
		Statement stmt = conn.createStatement();
		// 問合せの実行
		ResultSet rset = stmt.executeQuery("select group_id	,item_id,column_value as cnt from code_master where column_value like '%" + arg + "%'");
		
		List<SampleModel> list = new ArrayList<SampleModel>();
		// 問合せ結果の表示
		while (rset.next()) {
			SampleModel sampleModel = new SampleModel();
			
			// 列番号による指定
			sampleModel.setGroupId(rset.getInt(1));
			sampleModel.setItemId(rset.getString(2));
			sampleModel.setColumnValue(rset.getString(3));
			
			list.add(sampleModel);
		}
		
		System.out.println("count:" + list.size());
		// 結果セットをクローズ
		rset.close();
		// ステートメントをクローズ
		stmt.close();
		// 接続をクローズ
		conn.close();
		
		return list;
	}
	
}
