package guomanwang.exceptionValidate;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class ValidationImpl implements InitializingBean{

	private Validator validator;
	
	public ValidateResult validate(Object bean) {
		ValidateResult validateResult = new ValidateResult();
		Set<ConstraintViolation<Object>> set = validator.validate(bean, Default.class);
		if(set.size() > 0) {
			validateResult.setHasError(true);
			Iterator<ConstraintViolation<Object>> iterator = set.iterator();
			while(iterator.hasNext()) {
				ConstraintViolation<Object> constraintViolation = iterator.next();
				String errMsg = constraintViolation.getMessage();
				String property = constraintViolation.getPropertyPath().toString();
				validateResult.getErrMsgMap().put(property, errMsg);
			}
		}
		return validateResult;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	
}
