package com.good.plat.datacenter.datastat.entity.balanceplat;

import java.math.BigDecimal;
import java.util.Date;

public class CheckBill extends UscActorRecharge{
	
	private String diffDesc;
	private String acctID;
	
	public String getAcctID() {
		return acctID;
	}
	public void setAcctID(String acctID) {
		this.acctID = acctID;
	}
	public String getDiffDesc() {
		return diffDesc;
	}
	public void setDiffDesc(String diffDesc) {
		this.diffDesc = diffDesc;
	}
	public CheckBill(String diffDesc, String acctID) {
		super();
		this.diffDesc = diffDesc;
		this.acctID = acctID;
	}
	public CheckBill() {
		super();
	}
	public CheckBill(Integer id, Integer gameid, Date logdate, Date localdate, Date clientdate, Integer ucgameid,
			Integer ucplatid, Integer channel, Integer subchannel, Integer paychannel, BigDecimal money,
			String serverip, Integer currency, String orderid, String dstorderid) {
		super(id, gameid, logdate, localdate, clientdate, ucgameid, ucplatid, channel, subchannel, paychannel, money, serverip,
				currency, orderid, dstorderid);
	}
	public CheckBill(UscActorRecharge e) {
		super(e.getId(),e.getGameid(),e.getLogdate(),e.getLocaldate(),e.getClientdate(),e.getUcgameid(),
				e.getUcplatid(),e.getChannel(),e.getSubchannel(),e.getPaychannel(),e.getMoney(),
				e.getServerip(),e.getCurrency(),e.getOrderid(),e.getDstorderid());
	}
}
