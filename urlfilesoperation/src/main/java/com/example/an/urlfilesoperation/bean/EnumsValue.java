package com.example.an.urlfilesoperation.bean;


import com.example.an.urlfilesoperation.Params.ConstParmas;
import com.example.an.urlfilesoperation.Utils.ReflectionUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * description: 使用枚举的hash map, Key是任意枚举
 *
 * @param <E> 任意枚举
 * @author yuansui
 *         author: WDSG
 *         date: 2016/10/17
 */
@SuppressWarnings({"unchecked", "rawtypes", "serial"})
abstract public class EnumsValue<E extends Enum<E>> implements Serializable, Cloneable {

    protected String TAG = getClass().getSimpleName();

    private Map<E, Object> mMap = null;

    public EnumsValue() {
        mMap = new HashMap<E, Object>();
    }

    public final void save(E key, Object value) {
        if (value == null) {
            return;
        }

        mMap.put(key, value);
    }

    public final void clear() {
        mMap.clear();
    }

    public final Integer getInt(E key) {
        return getInt(key, ConstParmas.KErrNotFound);
    }

    public final Integer getInt(E key, int defaultValue) {
        Integer value = defaultValue;
        Object object = getObject(key);
        if (object == null) {
            return value;
        }

        // 判断顺序很重要, string的需要放到最后
        try {
            if (object instanceof Integer) {
                value = (Integer) object;
            } else if (object instanceof Long) {
                value = ((Long) object).intValue();
            } else if (object instanceof Float) {
                value = ((Float) object).intValue();
            } else if (object instanceof Double) {
                value = ((Double) object).intValue();
            } else if (object instanceof String) {
                String str = (String) object;
                value = Float.valueOf(str).intValue();
            }
        } catch (NumberFormatException e) {
            // LogMgr.e(TAG, e);
        }

        return value;
    }

    public final String getString(E key) {
        return getString(key, ConstParmas.KEmptyValue);
    }

    public final String getString(E key, String defaultValue) {
        String value = defaultValue;
        Object object = getObject(key);
        if (object == null) {
            return value;
        }

        try {
            // 适用类型太多, 不用区分类型
            value = String.valueOf(object);
        } catch (NumberFormatException e) {
            // LogMgr.e(TAG, e);
        }

        return value;
    }

    public final Long getLong(E key) {
        return getLong(key, ConstParmas.KErrNotFound);
    }

    public final Long getLong(E key, long defaultValue) {
        Long value = defaultValue;
        Object object = getObject(key);
        if (object == null) {
            return value;
        }

        try {
            if (object instanceof Long) {
                value = (Long) object;
            } else if (object instanceof Integer) {
                value = Long.valueOf((Integer) object);
            } else if (object instanceof String) {
                value = Long.valueOf((String) object);
            }
        } catch (NumberFormatException e) {
            // LogMgr.e(TAG, e);
        }

        return value;
    }

    public final Boolean getBoolean(E key) {
        return getBoolean(key, false);
    }

    public final Boolean getBoolean(E key, boolean defaultValue) {
        Boolean value = defaultValue;
        Object object = getObject(key);
        if (object == null) {
            return value;
        }

        try {
            if (object instanceof Boolean) {
                value = (Boolean) object;
            } else if (object instanceof Integer) {
                // 数字规则, 0为false, 1为true
                Integer i = (Integer) object;
                if (i == 0) {
                    value = false;
                } else if (i == 1) {
                    value = true;
                } else {
                    value = false;
                }
            } else if (object instanceof String) {
                String str = String.valueOf(object);
                int i = Integer.valueOf(str);
                if (i == 0) {
                    value = false;
                } else if (i == 1) {
                    value = true;
                } else {
                    value = false;
                }
            }
        } catch (NumberFormatException e) {
            // LogMgr.e(TAG, e);
        }

        return value;
    }

    public final Float getFloat(E key) {
        return getFloat(key, ConstParmas.KErrNotFound);
    }

    public final Float getFloat(E key, float defaultValue) {
        Float value = defaultValue;
        Object object = getObject(key);
        if (object == null) {
            return value;
        }

        try {
            if (object instanceof Float) {
                value = (Float) object;
            } else if (object instanceof String) {
                value = Float.valueOf(String.valueOf(object));
            } else if (object instanceof Integer) {
                String s = String.valueOf(object);
                float f = Integer.valueOf(s);
                value = f;
            }
        } catch (NumberFormatException e) {
            // LogMgr.e(TAG, e);
        }

        return value;
    }

    public Double getDouble(E key) {
        return getDouble(key, ConstParmas.KErrNotFound);
    }

    public Double getDouble(E key, double defaultValue) {
        Double value = defaultValue;
        Object object = getObject(key);
        if (object == null) {
            return value;
        }

        try {
            if (object instanceof Double) {
                value = (Double) object;
            } else if (object instanceof String) {
                value = Double.valueOf((String) object);
            } else if (object instanceof Integer) {
                String s = String.valueOf(object);
                double d = Integer.valueOf(s);
                value = d;
            } else if (object instanceof Float) {
                value = (Double) object;
            }
        } catch (NumberFormatException e) {
            // LogMgr.e(TAG, e);
        }

        return value;
    }

    public Serializable getSerializable(E key) {
        Object object = getObject(key);
        if (object == null) {
            return null;
        }
        try {
            return (Serializable) object;
        } catch (ClassCastException e) {
            // LogMgr.e(TAG, e);
            return null;
        }
    }

    public final Object getObject(E key) {
        return mMap.get(key);
    }

    /**
     * 获取所有已赋值的枚举的合集, 如有字段未赋值, 不在此集合里
     *
     * @return
     * @see EnumsValue#getEnumFiled()
     */
    public ArrayList<E> getValidEnumField() {
        ArrayList<E> keyNames = new ArrayList<E>();

        Iterator iter = mMap.keySet().iterator();
        while (iter.hasNext()) {
            E key = (E) iter.next();
            keyNames.add(key);
        }

        return keyNames;
    }

    /**
     * 获取枚举里所有属性的集合
     *
     * @return
     */
    public E[] getEnumFiled() {
        Class<E> entityClass = getEntityClz();
        return entityClass.getEnumConstants();
    }

    private Class<E> getEntityClz() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    protected void finalize() throws Throwable {
        clear();
    }

    @Override
    public EnumsValue clone() throws CloneNotSupportedException {
        EnumsValue value = ReflectionUtil.newInstance(getClass());

        Enum[] enums = getEnumFiled();
        for (int i = 0; i < enums.length; ++i) {
            value.save(enums[i], getString((E) enums[i]));
        }

        return value;
    }

    /**
     * 根据外部数据更新
     *
     * @param source
     */
    public void update(EnumsValue<E> source) {
        Enum[] enums = getEnumFiled();
        for (int i = 0; i < enums.length; ++i) {
            E e = (E) enums[i];
            save(e, source.getObject(e));
        }
    }
}
