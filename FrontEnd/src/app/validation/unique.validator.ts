import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function uniqueValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    return control.value.hasError('notunique') ? {notUnique: true} : null;
  }
}
