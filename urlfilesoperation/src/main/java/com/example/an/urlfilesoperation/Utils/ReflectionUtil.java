package com.example.an.urlfilesoperation.Utils;

import android.util.Log;

import java.lang.reflect.Constructor;

/**
 * description: 反射类
 * author: WDSG
 * date: 2016/10/17
 */
public class ReflectionUtil {
    private static final String TAG = ReflectionUtil.class.getSimpleName();

    /**
     * 通过反射获取类的对象, 只通过public的构造方法获取
     * <p>
     * 通过循环的方式构造对象, 因为构造函数的数量还是非常小的, 不会增加多少开销
     * 无法通过传统的方式封装此方法, 因为需要传两个多变参数
     * ({@link Class#getConstructor(Class...)} 和 {@link Constructor#newInstance(Object...)}都需要)
     * </p>
     *
     * @param clz  目标类
     * @param args 构造参数
     * @param <T>  任意类型
     * @return
     */
    public static <T> T newInstance(Class<?> clz, Object... args) {
        if (clz == null) {
            return null;
        }

        T t = null;
        Constructor<?>[] cs = clz.getConstructors();
        for (int i = 0; i < cs.length; ++i) {
            try {
                t = (T) cs[i].newInstance(args);
                break;
            } catch (Exception e) {
                Log.e(TAG, e.toString());
                continue;
            }
        }
        return t;
    }
}
