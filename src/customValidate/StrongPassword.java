package customValidate;

/**
 * @author Tri Phan
 *
 */

import java.lang.annotation.*;
import javax.validation.*;

@Documented
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {

	String message() default "{StrongPassword}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}