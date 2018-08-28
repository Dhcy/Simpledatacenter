package com.good.plat.datacenter.datastat.entity.balanceplat;

import java.util.List;

public class CheckBillResult {
	
	public static String CHECK_RESULT_3PART_MISSING = "第三方缺失";
	public static String CHECK_RESULT_MISSING = "我方缺失";
	public static String CHECK_RESULT_MONEY_MISMATCH = "金额差异";
	
	private Integer myBillItemCount;
	private Integer dsBillItemCount;
	private Integer matchItemCount;
	
	List<CheckBill> diffBillList;

	

	public Integer getMyBillItemCount() {
		return myBillItemCount;
	}

	public void setMyBillItemCount(Integer myBillItemCount) {
		this.myBillItemCount = myBillItemCount;
	}

	public Integer getDsBillItemCount() {
		return dsBillItemCount;
	}

	public void setDsBillItemCount(Integer dsBillItemCount) {
		this.dsBillItemCount = dsBillItemCount;
	}

	public Integer getMatchItemCount() {
		return matchItemCount;
	}

	public void setMatchItemCount(Integer matchItemCount) {
		this.matchItemCount = matchItemCount;
	}

	public List<CheckBill> getDiffBillList() {
		return diffBillList;
	}

	public void setDiffBillList(List<CheckBill> diffBillList) {
		this.diffBillList = diffBillList;
	}
	
	
}
