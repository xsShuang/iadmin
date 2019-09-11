package xyz.iotcode.iadmin.common.validated.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import xyz.iotcode.iadmin.common.annotation.validated.PlateNumber;

/**
 * @author	孙金川
 * @since	2019年5月8日
 */
public class PlateNumberValidator implements ConstraintValidator<PlateNumber, String> {

	private boolean notNull;
	
	@Override
	public void initialize(PlateNumber constraintAnnotation) {
		this.notNull = constraintAnnotation.notNull();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StrUtil.isNotBlank(value)) {
			return Validator.isPlateNumber(value);
		}
		
		if (notNull) {
			return false;
		}
		
		return true;
	}
	
}
