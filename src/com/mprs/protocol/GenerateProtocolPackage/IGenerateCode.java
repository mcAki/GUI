package com.mprs.protocol.GenerateProtocolPackage;

import com.mprs.protocol.FieldType;

/**
 * 可生成代码接口
 * @author Seraph.Yang
 *
 */
public interface IGenerateCode {
	
	public String getGenerateEncode();

	/**
	 * 设置编码
	 * @param generateEncode
	 */
	public void setGenerateEncode(String generateEncode);

	/**
	 * 生成代码
	 * @param configFile
	 * @param packageName
	 * @param protocolPfx 协议前缀（生成代码时候会乘100000）
	 * @param type
	 * @param dir
	 * @throws Exception
	 */
	public void GenerateCode(String configFile, String packageName,int protocolPfx, ProtocolSetting ps, String dir)throws Exception;
	
	/**
	 * 类型转换
	 * @param fieldType
	 * @return
	 */
	public String TranslateType(FieldType type);
}
