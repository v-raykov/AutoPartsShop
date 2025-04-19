package com.viktor.oop.car.parts.shop.web.service.helper;

import com.viktor.oop.car.parts.shop.config.exception.EntityUpdateException;

import java.lang.reflect.Field;

public class Utilities {
    public static void updateEntity(Object source, Object target) {
        var sourceFields = source.getClass().getDeclaredFields();
        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            try {
                var value = sourceField.get(source);
                if (value != null) {
                    var targetField = target.getClass().getDeclaredField(sourceField.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new EntityUpdateException(e);
            }
        }
    }
}
