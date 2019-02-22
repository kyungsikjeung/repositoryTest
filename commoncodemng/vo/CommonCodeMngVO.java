/**
 *
 */
package com.tollgateadmin.biz.system.commoncodemng.vo;

import com.tollgateadmin.common.vo.CommonCodeDT;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author impjs
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CommonCodeMngVO extends CommonCodeDT {

	private int lastDisplayOrder;

//	public int getLastDisplayOrder() {
//		return lastDisplayOrder;
//	}
//
//	public void setLastDisplayOrder(int lastDisplayOrder) {
//		this.lastDisplayOrder = lastDisplayOrder;
//	}

	@Override
	public String toString() {
		return "CommonCodeMngVO [lastDisplayOrder=" + lastDisplayOrder + "]";
	}
}
