package cn.ogsu.api.util;

/**
 * Created by yangwu on 2017/3/28 0028.
 */
public final class AppUtils {
    public static String AppPath = "";
    public static boolean isUTF8 = false;
    static{
        try {
            isUTF8 = "中国".getBytes().length == 6;
            AppPath = AppUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            AppPath = AppPath.replace('\\', '/');
            if(AppPath.indexOf(":") > 0 && AppPath.startsWith("/")) {
                AppPath = AppPath.substring(1, AppPath.length());
            }

            if(AppPath.endsWith("/")) {
                AppPath = AppPath.substring(0, AppPath.lastIndexOf("/"));
            }

//            if(AppPath.endsWith("/classes")) {
//                AppPath = AppPath.substring(0, AppPath.lastIndexOf("/"));
//            }

            if(AppPath.endsWith("/lib")) {
                AppPath = AppPath.substring(0, AppPath.lastIndexOf("/"));
            }

            if(AppPath.endsWith("/classes/jvc/util/AppUtils.class")) {
                AppPath = AppPath.substring(0, AppPath.lastIndexOf("/classes/jvc/util/AppUtils.class"));
            }

        } catch (Exception var1) {
            AppPath = null;
        }

    }
}
