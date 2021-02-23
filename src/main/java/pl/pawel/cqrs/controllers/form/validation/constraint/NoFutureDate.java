package pl.pawel.cqrs.controllers.form.validation.constraint;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import pl.pawel.cqrs.controllers.form.validation.NoFutureDateValidator;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = {NoFutureDateValidator.class})
public @interface NoFutureDate {

  String message() default "Date can't be in the future";
  
  Class<?>[] groups () default {};
  
  Class<? extends Payload> [] payload () default {};
}
