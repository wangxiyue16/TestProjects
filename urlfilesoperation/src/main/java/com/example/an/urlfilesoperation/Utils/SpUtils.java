package com.example.an.urlfilesoperation.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.example.an.urlfilesoperation.App;
import com.example.an.urlfilesoperation.Params.ConstParmas;
import com.example.an.urlfilesoperation.bean.EnumsValue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * description:
 * author: WDSG
 * date: 2016/10/17
 */
public class SpUtils {

    private static final String TAG = SpUtils.class.getSimpleName();

    private static final String KKeyData = "data";
    private static final String KKeyClass = "class";

    /**
     * 根据类型自动匹配存储
     *
     * @param sp
     * @param key
     * @param value
     * @return
     */

    public static boolean save(SharedPreferences sp, String key, Object value) {
        if (value instanceof Integer) {
            return save(sp, key, (Integer) value);
        } else if (value instanceof String) {
            return save(sp, key, (String) value);
        } else if (value instanceof Long) {
            return save(sp, key, (Long) value);
        } else if (value instanceof Boolean) {
            return save(sp, key, (Boolean) value);
        } else if (value instanceof EnumsValue) {
            return saveEnumsValue(sp, key, (EnumsValue) value);
        } else if (value instanceof Serializable) {
            return save(sp, key, (Serializable) value);
        } else {
            Log.d(TAG, "未知类型 = " + value);
            return false;
        }
    }

    public static boolean save(String spName, String key, Object value) {
        SharedPreferences sp = App.ct().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return save(sp, key, value);
    }

    private static boolean save(SharedPreferences sp, String key, Integer value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    private static boolean save(SharedPreferences sp, String key, Long value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    private static boolean save(SharedPreferences sp, String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    private static boolean save(SharedPreferences sp, String key, Boolean value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    private static boolean save(SharedPreferences sp, String key, Serializable data) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(data);
            String dataString = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            save(sp, key, dataString);
            oos.close();
            baos.close();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return false;
        }
        return true;
    }

    /**
     * 保存某个 EnumsValue子类的数据, 可以兼容新老版本的数据不一样
     * PS: 如果直接使用保存序列化的模式, 修改新数据会导致数据不一样的崩溃
     *
     * @param sp
     * @param key
     * @param value
     * @return
     */
    private static boolean saveEnumsValue(SharedPreferences sp, String key, EnumsValue value) {
        String content = getJsonObject(value).toString();
        if (!TextUtils.isEmpty(key)) {
            return save(sp, key, content);
        } else {
            // FIXME: 比起保存 getSimpleName(), 使用 getName() 更为灵活, 暂时不修改因为老版本数据会无效
            return save(sp, value.getClass().getSimpleName(), content);
        }
    }

    /**
     * 拼接JsonObject
     *
     * @param value
     * @return
     */
    private static JSONObject getJsonObject(EnumsValue value) {
        Enum[] enums = value.getEnumFiled();

        JSONObject jsonObject = new JSONObject();
        try {
            for (int i = 0; i < enums.length; ++i) {
                Enum each = enums[i];
                Object object = value.getObject(each);
                if (object == null) {
                    continue;
                }

                JSONObject subJson = new JSONObject();
                subJson.put(KKeyClass, object.getClass().getName());

                if (object instanceof EnumsValue) {
                    subJson.put(KKeyData, getJsonObject((EnumsValue) object));
                } else if (object instanceof ArrayList) {
                    subJson.put(KKeyData, getJsonArray((ArrayList) object));
                } else {
                    subJson.put(KKeyData, value.getString(each));
                }

                jsonObject.put(each.name(), subJson);
            }

        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }

        return jsonObject;
    }

    /**
     * 拼接JsonArray
     *
     * @param list
     * @return
     * @throws JSONException
     */
    private static JSONArray getJsonArray(ArrayList list) throws JSONException {
        JSONArray array = new JSONArray();

        for (int j = 0; j < list.size(); ++j) {
            Object listObject = list.get(j);
            if (listObject instanceof EnumsValue) {
                EnumsValue value = (EnumsValue) listObject;

                JSONObject jsonObject = new JSONObject();
                jsonObject.put(KKeyClass, value.getClass().getName());
                jsonObject.put(KKeyData, getJsonObject(value));

                array.put(jsonObject);
            } else if (listObject instanceof ArrayList) {
                getJsonArray((ArrayList) listObject);
            } else {
                array.put(listObject);
            }
        }

        return array;
    }

    public static boolean contains(SharedPreferences sp, String key) {
        return sp.contains(key);
    }

    public static Integer getInt(SharedPreferences sp, String key) {
        return sp.getInt(key, ConstParmas.KErrNotFound);
    }

    public static Integer getInt(SharedPreferences sp, String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public static Integer getInt(String spName, String key, int defValue) {
        SharedPreferences sp = App.ct().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    /**
     * Get Long Value
     */
    public static long getLong(SharedPreferences sp, String key) {
        return sp.getLong(key, ConstParmas.KErrNotFound);
    }

    public static long getLong(SharedPreferences sp, String key, long defValue) {
        return sp.getLong(key, defValue);
    }

    /**
     * Get String Value
     */
    public static String getString(SharedPreferences sp, String key) {
        return sp.getString(key, ConstParmas.KEmptyValue);
    }

    public static String getString(SharedPreferences sp, String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public static String getString(String spName, String key, String defValue) {
        SharedPreferences sp = App.ct().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return getString(sp, key, defValue);
    }

    /**
     * get boolean value
     */
    public static Boolean getBoolean(SharedPreferences sp, String key) {
        return sp.getBoolean(key, false);
    }

    public static Boolean getBoolean(SharedPreferences sp, String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public static Boolean getBoolean(String spName, String key, boolean defValue) {
        SharedPreferences sp = App.ct().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return getBoolean(sp, key, defValue);
    }

    /**
     * clear all values
     */
    public static boolean clear(SharedPreferences sp) {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        return editor.commit();
    }

    public static boolean remove(SharedPreferences sp, String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        return editor.commit();
    }

    public static boolean removeKeys(SharedPreferences sp, String... keys) {
        SharedPreferences.Editor editor = sp.edit();
        for (int i = 0; i < keys.length; ++i) {
            editor.remove(keys[i]);
        }
        return editor.commit();
    }

    /**
     * @param sp
     * @param key
     * @return 没有值的时候返回null
     */
    public static Serializable getSerializable(SharedPreferences sp, String key) {
        String objBase64 = getString(sp, key);
        if (TextUtils.isEmpty(objBase64)) {
            return null;
        }
        byte[] base64Bytes = Base64.decode(objBase64.getBytes(), Base64.DEFAULT);

        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            Serializable ret = (Serializable) ois.readObject();
            ois.close();
            bais.close();
            return ret;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }

    /***********************************************
     * EnumsValue---start
     */

    public static boolean isEnumsValueExist(SharedPreferences sp, String key, Class<? extends EnumsValue> clz) {
        if (TextUtils.isEmpty(key)) {
            // 使用类名作为key
            key = clz.getSimpleName();
        }

        String text = getString(sp, key);
        if (TextUtils.isEmpty(text)) {
            // 没有保存
            return false;
        }

        EnumsValue value = getEnumsValueInstance(clz);
        if (value == null) {
            return false;
        }

        return true;
    }

    /**
     * 读取保存的EnumsValue数据
     *
     * @param sp
     * @param key 保存的关键字
     * @param clz
     * @return
     */
    public static EnumsValue getEnumsValue(SharedPreferences sp, String key, Class<? extends EnumsValue> clz) {
        if (TextUtils.isEmpty(key)) {
            // 使用类名作为key
            key = clz.getSimpleName();
        }

        String text = getString(sp, key);
        if (TextUtils.isEmpty(text)) {
            // 没有保存
            return null;
        }

        EnumsValue value = getEnumsValueInstance(clz);
        if (value == null) {
            return null;
        }

//        try {
//            setEnumsValue(value, text);
//        } catch (JSONException e) {
//            Log.e(TAG, e.toString());
//        }

        return value;
    }

//    private static void setEnumsValue(EnumsValue value, String text) throws JSONException {
//        if (value == null) {
//            return;
//        }
//
//        JSONObject jsonObject = new JSONObject(text);
//
//        Enum[] enums = value.getEnumFiled();
//        for (int i = 0; i < enums.length; ++i) {
//            Enum eachEnum = enums[i];
//
//            String content = JsonUtil.getString(jsonObject, eachEnum.name());
//            if (TextUtils.isEmpty(content)) {
//                // 无数据
//                continue;
//            }
//
//            JSONObject object = new JSONObject(content);
//
//            String className = JsonUtil.getString(object, KKeyClass);
//
//            Class clz = null;
//            try {
//                clz = Class.forName(className);
//            } catch (ClassNotFoundException e) {
//                Log.e(TAG, className, e);
//                continue;
//            }
//
//            String data = JsonUtil.getString(object, KKeyData);
//
//            if (clz.isAssignableFrom(Integer.class) //
//                    || clz.isAssignableFrom(Boolean.class) //
//                    || clz.isAssignableFrom(String.class) //
//                    || clz.isAssignableFrom(Long.class) //
//                    || clz.isAssignableFrom(Double.class)) {
//                // 基础类型, 直接赋值
//                value.save(eachEnum, data);
//            } else if (clz.isAssignableFrom(ArrayList.class)) {
//                // 数组特殊处理
//                JSONArray array = new JSONArray(data);
//                value.save(eachEnum, getArrayList(array));
//            } else {
//                // EnumsValue形式
//                value.save(eachEnum, getEnumsValue(clz, data));
//            }
//        }
//    }
//
//    /**
//     * 获取数组, 里面可以是任意类型的数据
//     *
//     * @param array
//     * @return
//     * @throws JSONException
//     */
//    private static ArrayList getArrayList(JSONArray array) throws JSONException {
//        ArrayList<Object> list = new ArrayList<Object>();
//
//        for (int i = 0; i < array.length(); ++i) {
//            JSONObject object = array.getJSONObject(i);
//
//            String className = JsonUtil.getString(object, KKeyClass);
//            Class clz = null;
//            try {
//                clz = Class.forName(className);
//            } catch (ClassNotFoundException e) {
//                Log.e(TAG, className, e);
//                continue;
//            }
//
//            String data = JsonUtil.getString(object, KKeyData);
//
//            if (clz.isAssignableFrom(Integer.class) //
//                    || clz.isAssignableFrom(Boolean.class) //
//                    || clz.isAssignableFrom(String.class) //
//                    || clz.isAssignableFrom(Long.class) //
//                    || clz.isAssignableFrom(Double.class)) {
//                // 基础类型, 直接赋值
//                list.add(data);
//            } else if (clz.isAssignableFrom(ArrayList.class)) {
//                JSONArray arrayNew = new JSONArray(data);
//                list.add(getArrayList(arrayNew));
//            } else {
//                // EnumsValue形式
//                list.add(getEnumsValue(clz, data));
//            }
//        }
//
//        return list;
//    }
//
//    /**
//     * 根据文本内容设置value的内容
//     *
//     * @param clz
//     * @param text
//     * @return
//     * @throws JSONException
//     */
//    private static EnumsValue getEnumsValue(Class<? extends EnumsValue> clz, String text) throws JSONException {
//        EnumsValue value = getEnumsValueInstance(clz);
//        setEnumsValue(value, text);
//        return value;
//    }

    /**
     * 获取EnumsValue的实例
     *
     * @param clz
     * @return
     */
    private static EnumsValue getEnumsValueInstance(Class<? extends EnumsValue> clz) {
        return ReflectionUtil.newInstance(clz);
    }

    /***********************************************
     * EnumsValue---end
     */
}
