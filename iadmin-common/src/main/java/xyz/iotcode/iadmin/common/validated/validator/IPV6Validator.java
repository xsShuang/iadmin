package xyz.iotcode.iadmin.common.validated.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import xyz.iotcode.iadmin.common.annotation.validated.IPV6;

/**
 * @author	孙金川
 * @since	2019年5月8日
 */
public class IPV6Validator implements ConstraintValidator<IPV6, String> {

	private boolean notNull;
	
	@Override
	public void initialize(IPV6 constraintAnnotation) {
		this.notNull = constraintAnnotation.notNull();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StrUtil.isNotBlank(value)) {
			return Validator.isIpv6(value);
		}
		
		if (notNull) {
			return false;
		}
		
		return true;
	}
	
}
