package xyz.iotcode.iadmin.common.validated.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import xyz.iotcode.iadmin.common.annotation.validated.IPV4;

/**
 * @author	孙金川
 * @since	2019年5月8日
 */
public class IPV4Validator implements ConstraintValidator<IPV4, String> {

	private boolean notNull;
	
	@Override
	public void initialize(IPV4 constraintAnnotation) {
		this.notNull = constraintAnnotation.notNull();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StrUtil.isNotBlank(value)) {
			return Validator.isIpv4(value);
		}
		
		if (notNull) {
			return false;
		}
		
		return true;
	}
	
}
