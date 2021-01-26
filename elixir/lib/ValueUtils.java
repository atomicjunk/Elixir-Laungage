package com.atomteam.elixir.lib;

import com.atomteam.elixir.exceptions.TypeException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ValueUtils {

    private ValueUtils() { }

    public static Object toObject(Value val) {
        switch (val.type()) {
            case Types.ARRAY:
                return toObject((ArrayValue) val);
            case Types.MAP:
                return toObject((MapValue) val);
            case Types.NUMBER:
                return val.raw();
            case Types.STRING:
                return val.asString();
            default:
                return 0;
        }
    }





    public static Value toValue(Object obj) {
        if (obj instanceof String) {
            return new StringValue((String) obj);
        }
        if (obj instanceof Number) {
            return NumberValue.of(((Number) obj));
        }
        if (obj instanceof Boolean) {
            return NumberValue.fromBoolean((Boolean) obj);
        }
        // NULL or other
        return NumberValue.ZERO;
    }



    public static Number getNumber(Value value) {
        if (value.type() == Types.NUMBER) return ((NumberValue) value).raw();
        return value.asInt();
    }

    public static float getFloatNumber(Value value) {
        if (value.type() == Types.NUMBER) return ((NumberValue) value).raw().floatValue();
        return (float) value.asNumber();
    }

    public static byte[] toByteArray(ArrayValue array) {
        final int size = array.size();
        final byte[] result = new byte[size];
        for (int i = 0; i < size; i++) {
            result[i] = (byte) array.get(i).asInt();
        }
        return result;
    }

    public static Function consumeFunction(Value value, int argumentNumber) {
        return consumeFunction(value, " at argument " + (argumentNumber + 1));
    }

    public static Function consumeFunction(Value value, String errorMessage) {
        final int type = value.type();
        if (type != Types.FUNCTION) {
            throw new TypeException("Function expected" + errorMessage
                    + ", but found " + Types.typeToString(type));
        }
        return ((FunctionValue) value).getValue();
    }

    public static <T extends Number> MapValue collectNumberConstants(Class<?> clazz, Class<T> type) {
        MapValue result = new MapValue(20);
        for (Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) continue;
            if (!field.getType().equals(type)) continue;
            try {
                result.set(field.getName(), NumberValue.of((T) field.get(type)));
            } catch (IllegalAccessException ignore) {
            }
        }
        return result;
    }
}