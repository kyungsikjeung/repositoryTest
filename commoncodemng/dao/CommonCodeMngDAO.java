/**
 * 
 */
package com.tollgateadmin.biz.system.commoncodemng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import framework.util.AbleUtil;
import com.tollgateadmin.biz.system.commoncodemng.vo.CommonCodeMngVO;
import com.tollgateadmin.common.support.DAOSupport;
import com.tollgateadmin.common.vo.CommonCodeDT;

/**
 * @author impjs
 *
 */
@Repository
public class CommonCodeMngDAO extends DAOSupport {

	/** 단건조회 */
	public CommonCodeMngVO getCommonCodeVO(String mainCode, String subCode) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mainCode", mainCode);
		param.put("subCode", subCode);
		return sqlSession.selectOne(mapperNamespace + AbleUtil.getCurrentMethodName(), param);
	}
	
	/** MainCode로 조회 */
	public List<CommonCodeMngVO> getCommonCodeVOByMainCode(String mainCode) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mainCode", mainCode);
		return sqlSession.selectList(mapperNamespace + AbleUtil.getCurrentMethodName(), param);
	}
	
	/** 단건조회 */
	public CommonCodeDT getCommonCodeByDisplayOrder(String mainCode, int displayOrder) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mainCode", mainCode);
		param.put("displayOrder", displayOrder);
		return sqlSession.selectOne(mapperNamespace + AbleUtil.getCurrentMethodName(), param);
	}
	
	/** 최대 노출순서 */
	public int getMaxDisplayOrder(String mainCode) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mainCode", mainCode);
		return sqlSession.selectOne(mapperNamespace + AbleUtil.getCurrentMethodName(), param);
	}
	
	/** 등록 */
	public int insert(CommonCodeDT dt) {
		return sqlSession.insert(mapperNamespace + AbleUtil.getCurrentMethodName(), dt);
	}
	
	/** 수정 */
	public int update(CommonCodeDT dt) {
		return sqlSession.update(mapperNamespace + AbleUtil.getCurrentMethodName(), dt);
	}

	/** 삭제 */ 
	public int delete(String mainCode, String subCode) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mainCode", mainCode);
		param.put("subCode", subCode);
		return sqlSession.delete(mapperNamespace + AbleUtil.getCurrentMethodName(), param);
	}
	
	/** 
	 * 순번 변경
 	 */
	public int updateDisplayOrder(String mainCode, String subCode, int displayOrder){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mainCode", mainCode);
		param.put("subCode", subCode);
		param.put("displayOrder", displayOrder);
		return sqlSession.update(mapperNamespace + AbleUtil.getCurrentMethodName(), param);
	}
	
	/** 
	 * 순번 변경 (등록되는 displayOrder 이후의 displayOrder를 1씩 증가)
 	 */
	public int updateOrderByInsertedOrder(String mainCode, int displayOrder){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mainCode", mainCode);
		param.put("displayOrder", displayOrder);
		return sqlSession.update(mapperNamespace + AbleUtil.getCurrentMethodName(), param);
	}
	
	/** 
	 * 순번 변경 (등록되는 displayOrder 이후의 displayOrder를 1씩 감소)
 	 */
	public int updateOrderByDeletedOrder(String mainCode, int displayOrder){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mainCode", mainCode);
		param.put("displayOrder", displayOrder);
		return sqlSession.update(mapperNamespace + AbleUtil.getCurrentMethodName(), param);
	}
}
