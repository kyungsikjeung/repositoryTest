package com.tollgateadmin.biz.system.commoncodemng.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import com.tollgateadmin.biz.system.commoncodemng.service.CommonCodeMngService;
import com.tollgateadmin.biz.system.commonmaincodemng.service.CommonMainCodeMngService;
import com.tollgateadmin.common.support.ControllerSupport;
import com.tollgateadmin.common.vo.CommonCodeDT;
import com.tollgateadmin.security.annotation.AppLoginUser;
import com.tollgateadmin.security.vo.AppUserDetails;

import framework.spring.web.view.AbleExcelCommand;
import framework.spring.web.view.AbleExcelMergeMode;
import framework.spring.web.view.AbleExcelView;
import framework.util.AbleUtil;
import framework.vo.SearchMap;

/**
 * @author impjs
 *
 */
@Controller
@PreAuthorize("hasAuthority('ROLE_CODE_MNG')")
@RequestMapping(value = "/system/commoncodemng")
public class CommonCodeMngController extends ControllerSupport {

	@Autowired AbleExcelView ableExcelView;
	@Autowired CommonMainCodeMngService commonMainCodeMngService;
	@Autowired CommonCodeMngService commonCodeMngService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, @AppLoginUser AppUserDetails admin) {
		model.addAttribute("COMMON_MAIN_CODE_LIST", AbleUtil.toJson( commonMainCodeMngService.getVOListAll() ));
		return "system/commoncodemng/commoncodemng";
	}

	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public View excelDownload(
			HttpServletRequest request,
			@SortDefault(sort = {"mainCode","displayOrder"}, direction=Direction.ASC) Sort sort,
			@RequestParam(required=false, defaultValue="false") boolean mergeExcelCell,
			Model model,
			@AppLoginUser AppUserDetails admin) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		SearchMap search = new SearchMap(request);

		//데이터 목록 획득
		List<CommonCodeDT> list = commonCodeMngService.getListAll(sort, search);

		//엑셀 출력 명령 준비
		AbleExcelCommand command = new AbleExcelCommand(list, CommonCodeDT.class, messageSource);

		command.setFilename("공통코드관리");
		command.setTitle("공통코드관리");
		command.setSheetName("공통코드관리");

		if(mergeExcelCell) {
			command.setMergeMode(AbleExcelMergeMode.MERGE_VERTICAL_HIERARCHY);
		}

		model.addAttribute(AbleExcelCommand.MODEL_KEY, command);

		return ableExcelView;
	}
}
