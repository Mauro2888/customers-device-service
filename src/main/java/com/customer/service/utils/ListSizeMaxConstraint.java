package com.customer.service.utils;

import com.customer.service.model.entity.Device;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * This class is used to validate the list size of the devices
 * Return BadRequest if the list size is greater than 2
 */
public class ListSizeMaxConstraint implements ConstraintValidator<ListSizeMax, List<Device>> {

    @Override
    public boolean isValid(List<Device> deviceList, ConstraintValidatorContext constraintValidatorContext) {
        return deviceList.size() <= 2;
    }
}
