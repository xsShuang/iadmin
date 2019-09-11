package xyz.iotcode.iadmin.common.validated.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import xyz.iotcode.iadmin.common.annotation.validated.Birthday;

/**
 * @author	孙金川
 * @since	2019年5月8日
 */
public class BirthdayValidator implements ConstraintValidator<Birthday, String> {

	private boolean notNull;
	
	@Override
	public void initialize(Birthday constraintAnnotation) {
		this.notNull = constraintAnnotation.notNull();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StrUtil.isNotBlank(value)) {
			return Validator.isBirthday(value);
		}
		
		if (notNull) {
			return false;
		}
		
		return true;
	}
	
}
