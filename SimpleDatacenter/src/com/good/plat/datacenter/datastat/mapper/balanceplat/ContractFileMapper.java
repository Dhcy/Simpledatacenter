package com.good.plat.datacenter.datastat.mapper.balanceplat;

import java.util.List;

import com.good.plat.datacenter.datastat.entity.balanceplat.ContractFile;



/**
 * 合同扫描文件
 * @ClassName: ContractFileMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-5-11 上午10:47:40
 */
public interface ContractFileMapper {
	
	/**
	 * 添加合同扫描文件
	 * @Title: insertContractFile
	 * @Description: TODO
	 * @param contractFile
	 * @return
	 * @throws Exception
	 * List<ContractFile>
	 * @author hwj
	 * @date 2017-5-11 上午10:49:25
	 */
	int insertContractFile(ContractFile contractFile)throws Exception;
	/**
	 * 获取合同文件通过id
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * ContractFile
	 * @author hwj
	 * @date 2017-5-11 上午10:51:18
	 */
	ContractFile selectByPrimaryKey(Integer id)throws Exception;
	/**
	 * 查询合同文件列表
	 * @Title: selectContractFileList
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * List<ContractFile>
	 * @author hwj
	 * @date 2017-5-11 上午10:53:02
	 */
	List<ContractFile> selectContractFileList(ContractFile contractFile)throws Exception;
	/**
	 * 根据文件路径查询合同文件
	 * @Title: selectByChannelId
	 * @Description: TODO
	 * @param contractFile
	 * @return
	 * @throws Exception
	 * ContractFile
	 * @author hwj
	 * @date 2017-5-11 上午11:16:16
	 */
	ContractFile selectByFilePath(ContractFile contractFile)throws Exception;
	
	/**
	 * 删除合同文件
	 * @Title: delectByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * int
	 * @author hwj
	 * @date 2017-5-12 下午02:07:56
	 */
	int delectByPrimaryKey(Integer id)throws Exception;
}
