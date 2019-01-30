package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2018/07/04
 *     desc  : utils about rom
 * </pre>
 */
public final class RomUtils {

    public static final String ROM_HUAWEI  = "huawei";
    public static final String ROM_VIVO    = "vivo";
    public static final String ROM_XIAOMI  = "xiaomi";
    public static final String ROM_OPPO    = "oppo";
    public static final String ROM_LEECO   = "leeco";
    public static final String ROM_QIKU    = "qiku";
    public static final String ROM_ZTE     = "zte";
    public static final String ROM_ONEPLUS = "oneplus";
    public static final String ROM_NUBIA   = "nubia";
    public static final String ROM_SAMSUNG = "samsung";
    public static final String ROM_MEIZU   = "meizu";
    public static final String ROM_COOLPAD = "coolpad";
    public static final String ROM_LENOVO  = "lenovo";

    public static final  String VERSION_PROPERTY_HUAWEI  = "ro.build.version.emui";
    public static final  String VERSION_PROPERTY_VIVO    = "ro.vivo.os.build.display.id";
    public static final  String VERSION_PROPERTY_XIAOMI  = "ro.build.version.incremental";
    public static final  String VERSION_PROPERTY_OPPO    = "ro.build.version.opporom";
    public static final  String VERSION_PROPERTY_LEECO   = "ro.letv.release.version";
    public static final  String VERSION_PROPERTY_QIKU    = "ro.build.uiversion";
    public static final  String VERSION_PROPERTY_ZTE     = "ro.build.MiFavor_version";
    public static final  String VERSION_PROPERTY_ONEPLUS = "ro.rom.version";
    public static final  String VERSION_PROPERTY_NUBIA   = "ro.build.rom.id";
    private final static String UNKNOWN                  = "unknown";

    private static RomInfo bean = null;

    private RomUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Return whether the rom is made by huawei.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isHuawei() {
        return ROM_HUAWEI.equals(getRomInfo().name);
    }

    /**
     * Return whether the rom is made by vivo.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isVivo() {
        return ROM_VIVO.equals(getRomInfo().name);
    }

    /**
     * Return whether the rom is made by xiaomi.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isXiaomi() {
        return ROM_XIAOMI.equals(getRomInfo().name);
    }

    /**
     * Return whether the rom is made by oppo.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isOppo() {
        return ROM_OPPO.equals(getRomInfo().name);
    }

    /**
     * Return whether the rom is made by leeco.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isLeeco() {
        return ROM_LEECO.equals(getRomInfo().name);
    }

    /**
     * Return whether the rom is made by qiku.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isQiku() {
        return ROM_QIKU.equals(getRomInfo().name);
    }

    /**
     * Return whether the rom is made by zte.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isZte() {
        return ROM_ZTE.equals(getRomInfo().name);
    }

    /**
     * Return whether the rom is made by oneplus.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isOneplus() {
        return ROM_ONEPLUS.equals(getRomInfo().name);
    }

    /**
     * Return whether the rom is made by nubia.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isNubia() {
        return ROM_NUBIA.equals(getRomInfo().name);
    }

    /**
     * Return whether the rom is made by samsung.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isSamsung() {
        return ROM_SAMSUNG.equals(getRomInfo().name);
    }

    /**
     * Return whether the rom is made by meizu.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isMeizu() {
        return ROM_MEIZU.equals(getRomInfo().name);
    }

    /**
     * Return whether the rom is made by coolpad.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isCoolpad() {
        return ROM_COOLPAD.equals(getRomInfo().name);
    }

    /**
     * Return whether the rom is made by lenovo.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isLenovo() {
        return ROM_LENOVO.equals(getRomInfo().name);
    }

    /**
     * Return the rom's information.
     *
     * @return the rom's information
     */
    public static RomInfo getRomInfo() {
        if (bean != null) return bean;
        bean = new RomInfo();
        final String brandName = getBrand();
        final String manufacturer = getManufacturer();
        if (ROM_HUAWEI.equals(brandName) || ROM_HUAWEI.equals(manufacturer)) {
            bean.name = ROM_HUAWEI;
            String version = getRomVersion(VERSION_PROPERTY_HUAWEI);
            String[] temp = version.split("_");
            if (temp.length > 1) {
                bean.version = temp[1];
            } else {
                bean.version = version;
            }
        } else if (ROM_VIVO.equals(brandName) || ROM_VIVO.equals(manufacturer)) {
            bean.name = ROM_VIVO;
            bean.version = getRomVersion(VERSION_PROPERTY_VIVO);
        } else if (ROM_XIAOMI.equals(brandName) || ROM_XIAOMI.equals(manufacturer)) {
            bean.name = ROM_XIAOMI;
            bean.version = getRomVersion(VERSION_PROPERTY_XIAOMI);
        } else if (ROM_OPPO.equals(brandName) || ROM_OPPO.equals(manufacturer)) {
            bean.name = ROM_OPPO;
            bean.version = getRomVersion(VERSION_PROPERTY_OPPO);
        } else if (ROM_LEECO.equals(brandName) || ROM_LEECO.equals(manufacturer)) {
            bean.name = ROM_LEECO;
            bean.version = getRomVersion(VERSION_PROPERTY_LEECO);
        } else if (ROM_QIKU.equals(brandName) || ROM_QIKU.equals(manufacturer)) {
            bean.name = ROM_QIKU;
            bean.version = getRomVersion(VERSION_PROPERTY_QIKU);
        } else if (ROM_ZTE.equals(brandName) || ROM_ZTE.equals(manufacturer)) {
            bean.name = ROM_ZTE;
            bean.version = getRomVersion(VERSION_PROPERTY_ZTE);
        } else if (ROM_ONEPLUS.equals(brandName) || ROM_ONEPLUS.equals(manufacturer)) {
            bean.name = ROM_ONEPLUS;
            bean.version = getRomVersion(VERSION_PROPERTY_ONEPLUS);
        } else if (ROM_NUBIA.equals(brandName) || ROM_NUBIA.equals(manufacturer)) {
            bean.name = ROM_NUBIA;
            bean.version = getRomVersion(VERSION_PROPERTY_NUBIA);
        } else if (ROM_SAMSUNG.equals(brandName) || ROM_SAMSUNG.equals(manufacturer)) {
            bean.name = ROM_SAMSUNG;
            bean.version = getRomVersion("");
        } else if (ROM_MEIZU.equals(brandName) || ROM_MEIZU.equals(manufacturer)) {
            bean.name = ROM_MEIZU;
            bean.version = getRomVersion("");
        } else if (ROM_COOLPAD.equals(brandName) || ROM_COOLPAD.equals(manufacturer)) {
            bean.name = ROM_COOLPAD;
            bean.version = getRomVersion("");
        } else if (ROM_LENOVO.equals(brandName) || ROM_LENOVO.equals(manufacturer)) {
            bean.name = ROM_LENOVO;
            bean.version = getRomVersion("");
        } else {
            bean.name = manufacturer;
            bean.version = getRomVersion("");
        }
        return bean;
    }

    private static String getManufacturer() {
        try {
            String manufacturer = Build.MANUFACTURER;
            if (!TextUtils.isEmpty(manufacturer)) {
                return manufacturer.toLowerCase();
            }
        } catch (Throwable ignore) { /**/ }
        return UNKNOWN;
    }

    private static String getBrand() {
        try {
            String brand = Build.BRAND;
            if (!TextUtils.isEmpty(brand)) {
                return brand.toLowerCase();
            }
        } catch (Throwable ignore) { /**/ }
        return UNKNOWN;
    }

    private static String getRomVersion(final String propertyName) {
        String ret = "";
        if (!TextUtils.isEmpty(propertyName)) {
            ret = getSystemProperty(propertyName);
        }
        if (TextUtils.isEmpty(ret) || ret.equals(UNKNOWN)) {
            try {
                String display = Build.DISPLAY;
                if (!TextUtils.isEmpty(display)) {
                    ret = display.toLowerCase();
                }
            } catch (Throwable ignore) { /**/ }
        }
        if (TextUtils.isEmpty(ret)) {
            return UNKNOWN;
        }
        return ret;
    }

    private static String getSystemProperty(final String name) {
        String prop = getSystemPropertyByShell(name);
        if (!TextUtils.isEmpty(prop)) return prop;
        prop = getSystemPropertyByStream(name);
        if (!TextUtils.isEmpty(prop)) return prop;
        if (Build.VERSION.SDK_INT < 28) {
            return getSystemPropertyByReflect(name);
        }
        return prop;
    }

    private static String getSystemPropertyByShell(final String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            String ret = input.readLine();
            if (ret != null) {
                return ret;
            }
        } catch (IOException ignore) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ignore) { /**/ }
            }
        }
        return "";
    }

    private static String getSystemPropertyByStream(final String key) {
        try {
            Properties prop = new Properties();
            FileInputStream is = new FileInputStream(
                    new File(Environment.getRootDirectory(), "build.prop")
            );
            prop.load(is);
            return prop.getProperty(key, "");
        } catch (Exception ignore) { /**/ }
        return "";
    }

    private static String getSystemPropertyByReflect(String key) {
        try {
            @SuppressLint("PrivateApi")
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, "");
        } catch (Exception e) { /**/ }
        return "";
    }

    public static class RomInfo {
        private String name;
        private String version;

        public String getName() {
            return name;
        }

        public String getVersion() {
            return version;
        }

        @Override
        public String toString() {
            return "RomInfo{name: " + name +
                    "\nversion: " + version + "}";
        }
    }
}