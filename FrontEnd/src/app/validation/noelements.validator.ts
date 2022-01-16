import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function noElementsValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    return control.value.hasError('noelements') ? {noElements: true} : null;
  }
}
