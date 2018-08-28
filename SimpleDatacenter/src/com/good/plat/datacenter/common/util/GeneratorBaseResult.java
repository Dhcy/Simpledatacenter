package com.good.plat.datacenter.common.util;

import java.lang.reflect.Field;

import com.good.plat.datacenter.datastat.entity.revenue.Behavior;
import com.good.plat.datacenter.datastat.entity.revenue.Conversion;
import com.good.plat.datacenter.datastat.entity.virtual.ItemExpense;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualCurrency;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualItems;
import com.good.plat.datacenter.datastat.entity.whales.Whales;


public class GeneratorBaseResult {

	public static void main(String[] args) throws ClassNotFoundException {
		reflect(new Whales());
	}

	public static void reflect(Object obj) {
		
		Field[] fis = obj.getClass().getSuperclass().getDeclaredFields();
		
		Field[] fields = obj.getClass().getDeclaredFields();
		
		for (int j = 0; j < fis.length; j++) {
			fis[j].setAccessible(true);
			String name = fis[j].getName();
			String str = "<result column=\""+name+"\" property=\""+name+"\"/>";
			System.out.println(str);
			// 字段名
			//System.out.print(fis[j].getName() + ",");
		}
		
		
		for (int j = 0; j < fields.length; j++) {
			fields[j].setAccessible(true);
			String name = fields[j].getName();
			String str = "<result column=\""+name+"\" property=\""+name+"\"/>";
			System.out.println(str);
			// 字段名
			//System.out.print(fields[j].getName() + ",");
		}
	}

}
