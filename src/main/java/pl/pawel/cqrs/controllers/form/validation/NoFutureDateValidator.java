package pl.pawel.cqrs.controllers.form.validation;

import static java.time.LocalDate.now;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import pl.pawel.cqrs.controllers.form.PersonForm;
import pl.pawel.cqrs.controllers.form.validation.constraint.NoFutureDate;

public class NoFutureDateValidator implements ConstraintValidator<NoFutureDate, PersonForm> {

  @Override
  public void initialize(NoFutureDate constraintAnnotation) {

  }

  @Override
  public boolean isValid(PersonForm value, ConstraintValidatorContext context) {
    return value.getDateOfBirth().isBefore(now());
  }
}
