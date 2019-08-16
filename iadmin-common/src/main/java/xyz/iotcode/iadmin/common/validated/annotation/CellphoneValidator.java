package xyz.iotcode.iadmin.common.validated.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;

/**
 * @author	孙金川
 * @since	2019年5月8日
 */
public class CellphoneValidator implements ConstraintValidator<Cellphone, String> {

	private boolean notNull;
	
	@Override
	public void initialize(Cellphone constraintAnnotation) {
		this.notNull = constraintAnnotation.notNull();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StrUtil.isNotBlank(value)) {
			return Validator.isMobile(value);
		}
		
		if (notNull) {
			return false;
		}
		
		return true;
	}
	
}
