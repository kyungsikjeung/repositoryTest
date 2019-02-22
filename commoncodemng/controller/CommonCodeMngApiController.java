/**
 *
 */
package com.tollgateadmin.biz.system.commoncodemng.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import framework.exception.AbleRuntimeException;
import framework.exception.AbleValidationException;
import framework.spring.web.rest.AbleResponseEntity;
import framework.spring.web.rest.AbleResponseEntityBuilder;
import framework.vo.SearchMap;
import com.tollgateadmin.biz.system.commoncodemng.service.CommonCodeMngService;
import com.tollgateadmin.biz.system.commoncodemng.vo.CommonCodeMngVO;
import com.tollgateadmin.biz.system.commonmaincodemng.service.CommonMainCodeMngService;
import com.tollgateadmin.biz.system.commonmaincodemng.vo.CommonMainCodeMngVO;
import com.tollgateadmin.common.support.ApiControllerSupport;
import com.tollgateadmin.common.vo.CommonCodeDT;
import com.tollgateadmin.common.vo.CommonMainCodeDT;
import com.tollgateadmin.security.annotation.AppLoginUser;
import com.tollgateadmin.security.vo.AppUserDetails;

/**
 * @author impjs
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_CODE_MNG')")
@RequestMapping(value = "/system/commoncodemng/api")
public class CommonCodeMngApiController extends ApiControllerSupport {

	@Autowired CommonMainCodeMngService commonMainCodeMngService;
	@Autowired CommonCodeMngService commonCodeMngService;
	@Autowired CommonMainCodeMngService commonMainCodeService;

	/** 목록 조회 */
	@JsonView(CommonCodeDT.BaseView.class)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public AbleResponseEntity<List<CommonCodeDT>> getListAll(
			HttpServletRequest request,
			@SortDefault(sort = {"mainCode","displayOrder"}, direction=Direction.ASC) Sort sort,
			@AppLoginUser AppUserDetails me, Model model) {
		SearchMap searchMap = SearchMap.buildFrom(request);
		List<CommonCodeDT> list = commonCodeMngService.getListAll(sort, searchMap);
		return AbleResponseEntityBuilder.success(list);
	}

	/** 페이징 조회 */
	@JsonView(CommonCodeDT.BaseView.class)
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public AbleResponseEntity<Page<CommonCodeDT>> getList(
			HttpServletRequest request,
			@PageableDefault(sort = {"mainCode","displayOrder"}, direction=Direction.ASC) Pageable pageable,
			@AppLoginUser AppUserDetails me, Model model) {
		SearchMap searchMap = SearchMap.buildFrom(request);
		Page<CommonCodeDT> page = commonCodeMngService.getList(pageable, searchMap);
		return AbleResponseEntityBuilder.success(page);
	}

	/** layer popup - 페이징 조회 */
	@JsonView(CommonMainCodeMngVO.BaseView.class)
	@RequestMapping(value = "/mainCodepage", method = RequestMethod.GET)
	public AbleResponseEntity<Page<CommonMainCodeMngVO>> getMainCodeList(
			HttpServletRequest request,
			@PageableDefault(sort = "regDate", direction=Direction.ASC) Pageable pageable,
			@AppLoginUser AppUserDetails me, Model model) {
		SearchMap searchMap = SearchMap.buildFrom(request);
		Page<CommonMainCodeMngVO> page = commonMainCodeMngService.getVOList(pageable, searchMap);
		return AbleResponseEntityBuilder.success(page);
	}

	/** 상세 */
	@RequestMapping(value = "/details/{mainCode}/{subCode}", method = RequestMethod.GET)
	public AbleResponseEntity<CommonCodeMngVO> detail(
			@PathVariable("mainCode") String mainCode,
			@PathVariable("subCode") String subCode,
			@AppLoginUser AppUserDetails me ) {
		logger.debug("mainCode: {}, subCode: {}", mainCode, subCode);

		CommonCodeMngVO vo = commonCodeMngService.getCommonCodeVO(mainCode, subCode);

		return AbleResponseEntityBuilder.success(vo);
	}

	/** 메인코드 조회 */
	@JsonView(CommonMainCodeDT.BaseView.class)
	@RequestMapping(value = "/mainCodelist", method = RequestMethod.GET)
	public AbleResponseEntity<List<CommonMainCodeDT>> getMainCodeListAll(
			HttpServletRequest request,
			@SortDefault(sort = "regDate", direction=Direction.ASC) Sort sort,
			@AppLoginUser AppUserDetails me, Model model) {
		SearchMap searchMap = SearchMap.buildFrom(request);
		List<CommonMainCodeDT> list = commonMainCodeService.getListAll(sort, searchMap);
		return AbleResponseEntityBuilder.success(list);
	}

	/** 등록 */
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public AbleResponseEntity<Object> insert(
			/* 클라이언트 => 서버로 전송되는 데이터를 DT에 담을 거구요*/ @RequestBody @JsonView({CommonCodeDT.CreateAction.class}) @Validated({CommonCodeDT.CreateAction.class}) CommonCodeDT actionVO, BindingResult error,
			@AppLoginUser AppUserDetails me ) {
		logger.debug("vo: {}", actionVO);
		if(error.hasErrors()) {
			logger.trace("error: {}", error);
			throw new AbleValidationException(error);
		}
		//메인코드가 공통코드마스터 테이블에 존재하는지 체크
		if ( !this.validateMainCodeExistance(actionVO.getMainCode()) ){
			throw new AbleRuntimeException("등록하려는 코드의 메인코드가 존재하지 않습니다.");
		}
		//공통코드가 존재하는지 체크
		if ( this.validateCommonCodeExistance(actionVO.getMainCode(), actionVO.getSubCode()) ) {
			throw new AbleRuntimeException("메인코드와 서브코드에 해당하는 데이터가 이미 존재합니다.");
		}
		commonCodeMngService.insert(actionVO);

		insertAdminAudit("공통코드생성", actionVO, me);
		return AbleResponseEntityBuilder.success(null);
	}

	/** 수정 */
	@RequestMapping(value = "/details/{mainCode}/{subCode}", method = RequestMethod.PUT)
	public AbleResponseEntity<Object> update(
			@PathVariable("mainCode") String mainCode,
			@PathVariable("subCode") String subCode,
			@RequestBody @JsonView({CommonCodeDT.UpdateAction.class}) @Validated({CommonCodeDT.UpdateAction.class}) CommonCodeDT actionVO, BindingResult error,
			@AppLoginUser AppUserDetails me ) {
		logger.debug("mainCode: {}, subCode: {}, vo: {}", mainCode, subCode, actionVO);
		if(error.hasErrors()) {
			logger.trace("error: {}", error);
			throw new AbleValidationException(error);
		}
		if( StringUtils.isBlank(mainCode) ){
			throw new AbleRuntimeException("메인코드는 필수 입니다.");
		}
		//메인코드가 공통코드마스터 테이블에 존재하는지 체크
		if ( !this.validateMainCodeExistance(mainCode) ){
			throw new AbleRuntimeException("등록하려는 코드의 메인코드가 존재하지 않습니다.");
		}
		//공통코드가 존재하는지 체크
		if ( !this.validateCommonCodeExistance(mainCode, subCode) ){
			throw new AbleRuntimeException("정보를 변경할 데이터(메인코드·서브코드 기준)가 존재하지 않습니다.");
		}
		actionVO.setMainCode(mainCode);
		actionVO.setSubCode(subCode);
		commonCodeMngService.update(actionVO);

		CommonCodeDT updatedCodeDT = commonCodeMngService.getCommonCode(mainCode, subCode);
		insertAdminAudit("공통코드수정", updatedCodeDT, me);
		return AbleResponseEntityBuilder.success(null);
	}

	/** 삭제 */
	@RequestMapping(value = "/details/{mainCode}/{subCode}", method = RequestMethod.DELETE)
	public AbleResponseEntity<Object> delete(
			@PathVariable("mainCode") String mainCode,
			@PathVariable("subCode") String subCode,
			@AppLoginUser AppUserDetails me ) {
		logger.debug("mainCode: {}, subCode: {}", mainCode, subCode);

		CommonCodeDT targetDt = commonCodeMngService.getCommonCode(mainCode, subCode);
		if ( targetDt != null ){
			commonCodeMngService.delete(mainCode, subCode);
			insertAdminAudit("공통코드삭제", targetDt, me);
		}

		return AbleResponseEntityBuilder.success(null);
	}

	/** 그리드 순번 변경 event */
	@RequestMapping(value = "/changedisplayorder/{mainCode}/{subCode}/{direction}", method = RequestMethod.PUT)
	public AbleResponseEntity<Object> changeDisplayOrder(
			@PathVariable("mainCode") String mainCode,
			@PathVariable("subCode") String subCode,
			@PathVariable("direction") String direction,
			@AppLoginUser AppUserDetails me ) {
		logger.debug("mainCode: {}, subCode: {}, direction: {}", mainCode, subCode, direction);
		logger.debug("그리드 순번 변경 event");

		CommonCodeDT dtAtDb = commonCodeMngService.getCommonCode(mainCode, subCode);
		if( null == dtAtDb ) {
			throw new AbleRuntimeException("대상 공통코드를 찾을 수 없습니다.");
		}
		int maxDisplayOrder = commonCodeMngService.getMaxDisplayOrder(mainCode);
		int displayOrder = dtAtDb.getDisplayOrder();
		switch(direction.toUpperCase()){
			case "UP":
				if( displayOrder > 1 ) {
					displayOrder--;
					commonCodeMngService.swapDisplayOrder(mainCode, subCode, displayOrder);
				}
				break;
			case "DOWN":
				if( displayOrder < maxDisplayOrder ) {
					displayOrder++;
					commonCodeMngService.swapDisplayOrder(mainCode, subCode, displayOrder);
				}
				break;
			default:
			//no action
			break;
		}
		return AbleResponseEntityBuilder.success(null);
	}

	/** 수정, 삭제 공통 체크 - 공통코드마스터가 존재하는지 체크 */
	private boolean validateMainCodeExistance(String mainCode){
		return (commonCodeMngService.getCommonCodeVOByMainCode(mainCode) != null);
	}

	/** 수정, 삭제 공통 체크 - 공통코드가 존재하는지 체크 */
	private boolean validateCommonCodeExistance(String mainCode, String subCode){
		return (commonCodeMngService.getCommonCode(mainCode, subCode) != null);
	}

	/** 관리자감사로그 */
	private void insertAdminAudit(String detailsTitle, CommonCodeDT actionVO, AppUserDetails me) {
		insertAdminAudit("공통코드관리", detailsTitle + "-" + actionVO.toStringAdminAuditDetails(), me.getAdminId(),
				null, null, null, null, null);
	}
}
