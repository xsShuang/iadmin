package xyz.iotcode.iadmin.common.validated.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;

/**
 * @author	孙金川
 * @since	2019年5月8日
 */
public class ChineseValidator implements ConstraintValidator<Chinese, String> {

	private boolean notNull;
	
	@Override
	public void initialize(Chinese constraintAnnotation) {
		this.notNull = constraintAnnotation.notNull();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StrUtil.isNotBlank(value)) {
			return Validator.isChinese(value);
		}
		
		if (notNull) {
			return false;
		}
		
		return true;
	}
	
}
