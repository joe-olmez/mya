package com.olmez.mya.model.validation;

import java.util.function.Function;

import com.olmez.mya.model.Employee;

/**
 * This takes an employee as a parameter then returns ValidationEnum because
 * this interface inherite the Function
 */
public interface ValidatorForEmployee extends Function<Employee, ValidatorForEmployee.ValidationEnum> {

    enum ValidationEnum {
        SUCCESS,
        NAME_NOT_VALID,
        EMAIL_NOT_VALID,
        DOB_NOT_VALID,
    }

    static ValidatorForEmployee isNameValid() {
        return emp -> emp.getName().length() > 3 ? ValidationEnum.SUCCESS
                : ValidationEnum.NAME_NOT_VALID;
    }

    static ValidatorForEmployee isEmailValid() {
        return emp -> emp.getEmail().contains("@") ? ValidationEnum.SUCCESS
                : ValidationEnum.EMAIL_NOT_VALID;
    }

    static ValidatorForEmployee isDobValid() {
        return emp -> emp.getDob() != null ? ValidationEnum.SUCCESS
                : ValidationEnum.DOB_NOT_VALID;
    }

    default ValidatorForEmployee and(ValidatorForEmployee other) {
        return emp -> {
            ValidationEnum result = this.apply(emp);
            return result.equals(ValidationEnum.SUCCESS) ? other.apply(emp) : result;
        };
    }

}
