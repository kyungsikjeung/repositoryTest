/**
 *
 */
package com.tollgateadmin.biz.system.commoncodemng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import framework.exception.AbleRuntimeException;
import com.tollgateadmin.biz.system.commoncodemng.dao.CommonCodeMngDAO;
import com.tollgateadmin.biz.system.commoncodemng.vo.CommonCodeMngVO;
import com.tollgateadmin.common.service.CommonCodeService;
import com.tollgateadmin.common.vo.CommonCodeDT;

/**
 * @author impjs
 *
 */
@Service
public class CommonCodeMngService extends CommonCodeService {

	@Autowired CommonCodeMngDAO commonCodeMngDAO;

	/** 단건조회 */
	public CommonCodeMngVO getCommonCodeVO(String mainCode, String subCode) {
		return commonCodeMngDAO.getCommonCodeVO(mainCode, subCode);
	}

	/** 메인코드로 조회 */
	public List<CommonCodeMngVO> getCommonCodeVOByMainCode(String mainCode) {
		return commonCodeMngDAO.getCommonCodeVOByMainCode(mainCode);
	}

	/** 단건조회 */
	public CommonCodeDT getCommonCodeByDisplayOrder(String mainCode, int displayOrder) {
		return commonCodeMngDAO.getCommonCodeByDisplayOrder(mainCode, displayOrder);
	}

	/** 단건조회 */
	public int getMaxDisplayOrder(String mainCode) {
		return commonCodeMngDAO.getMaxDisplayOrder(mainCode);
	}

	/**
	 * 등록
	 */
	@Transactional
	public int insert(CommonCodeDT dt) {
		String mainCode = dt.getMainCode();
		String subCode = dt.getSubCode();
		CommonCodeDT dtAtDB = getCommonCode(mainCode, subCode);
		if( null != dtAtDB ) {
			throw new AbleRuntimeException("이미 등록된 공통코드 입니다.");
		}

		int maxDisplayOrder = getMaxDisplayOrder(mainCode) + 1;
		int displayOrder = dt.getDisplayOrder();
		if( displayOrder > maxDisplayOrder ) {
			dt.setDisplayOrder(maxDisplayOrder);
		} else if( displayOrder < maxDisplayOrder ) {
			updateOrderByInsertedOrder(mainCode, displayOrder);
		}
		return commonCodeMngDAO.insert(dt);
	}

	/**
	 * 수정
	 */
	@Transactional
	public int update(CommonCodeDT dt) {
		String mainCode = dt.getMainCode();
		String subCode = dt.getSubCode();
		CommonCodeDT dtAtDB = getCommonCode(mainCode, subCode);
		if( null == dtAtDB ) {
			throw new AbleRuntimeException("공통코드를 찾을 수 없습니다.");
		}

		int maxDisplayOrder = getMaxDisplayOrder(mainCode) + 1;
		int displayOrder = dt.getDisplayOrder();
		if( displayOrder > maxDisplayOrder ) {
			dt.setDisplayOrder(maxDisplayOrder);
		}
		int oldDisplayOrder = dtAtDB.getDisplayOrder();
		if( oldDisplayOrder < displayOrder ) {
			updateOrderByDeletedOrder(mainCode, oldDisplayOrder);
			updateOrderByInsertedOrder(mainCode, displayOrder);
		} else if( oldDisplayOrder > displayOrder ) {
			updateOrderByInsertedOrder(mainCode, displayOrder);
			updateOrderByDeletedOrder(mainCode, oldDisplayOrder);
		}
		return commonCodeMngDAO.update(dt);
	}

	/**
	 * 삭제
	 * 1. 기존에 존재하는 데이터의 displayOrder 업데이트(새로 등록되거나 수정되는 displayOrder가 마지막번호가 아닐경우 이후에 존재하던 displayOrder를 -1)
	 * 2. CommonCodeDT 정보 MOBILE_COMMON_CODE	테이블(delete)
	 */
	@Transactional
	public int delete(String mainCode, String subCode) {
		CommonCodeDT dt = getCommonCode(mainCode, subCode);
		if( null == dt ) {
			throw new AbleRuntimeException("공통코드를 찾을 수 없습니다.");
		}

		int maxDisplayOrder = getMaxDisplayOrder(mainCode) + 1;
		int displayOrder = dt.getDisplayOrder();

		if( displayOrder < maxDisplayOrder ) {
			updateOrderByDeletedOrder(mainCode, displayOrder);
		}
		return commonCodeMngDAO.delete(mainCode, subCode);
	}

	/**
	 * 순번 변경
	 */
	public int updateDisplayOrder(String mainCode, String subCode, int displayOrder){
		return commonCodeMngDAO.updateDisplayOrder(mainCode, subCode, displayOrder);
	}

	/**
	 * 순번 변경
	 */
	public int updateOrderByInsertedOrder(String mainCode, int displayOrder){
		return commonCodeMngDAO.updateOrderByInsertedOrder(mainCode, displayOrder);
	}

	/**
	 * 순번 변경
	 */
	public int updateOrderByDeletedOrder(String mainCode, int displayOrder){
		return commonCodeMngDAO.updateOrderByDeletedOrder(mainCode, displayOrder);
	}

	/**
	 * @param mainCode
	 * @param subCode
	 * @param order
	 */
	public void swapDisplayOrder(String mainCode, String subCode, int displayOrder) {
		int maxDisplayOrder = getMaxDisplayOrder(mainCode);

		CommonCodeDT newDt = getCommonCode(mainCode, subCode);
		CommonCodeDT oldDt = getCommonCodeByDisplayOrder(mainCode, displayOrder);
		if( null != oldDt ) {
			updateDisplayOrder( oldDt.getMainCode(), oldDt.getSubCode(), newDt.getDisplayOrder() );
		}
		int displayOrderToApply = displayOrder;
		if( displayOrder > maxDisplayOrder ) {
			displayOrderToApply = maxDisplayOrder;
		}
		updateDisplayOrder( newDt.getMainCode(), newDt.getSubCode(), displayOrderToApply );
	}

}
