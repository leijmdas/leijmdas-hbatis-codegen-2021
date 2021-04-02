package com.jtest.utility;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.springframework.util.StringUtils.isEmpty;


public final class JTestUtil {

    //默认去掉“-”
    public static String getUUID(boolean flag) {
        String s = UUID.randomUUID().toString();
        if (flag) {
            return s.replace("-", "");
        }
        return s;
    }

    /**
     * 获取IP地址
     *
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ip;
    }

    public static String toJSONStringSkipNull(Object o){
        return  JSONObject.toJSONString(o, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat);

    }

    public static String toJSONString(Object o){
        return  JSONObject.toJSONString(o, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue);

    }

    public static String toJSONStringPretty(Object o){
        return  JSONObject.toJSONString(o, SerializerFeature.PrettyFormat,SerializerFeature.SkipTransientField,  SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue);

    }

    public static Object invokeMethod(String clsName, String mName, Class[] parameterTypes, Object... args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        Class cls = Class.forName(clsName);
        Object o = cls.newInstance();
        Method m = cls.getDeclaredMethod(mName, parameterTypes);
        return m.invoke(o, args);  // Type type = m.getGenericReturnType();
    }

    public static Object invokeMethod_try(String clsName, String mName, Class[] parameterTypes, Object... args) throws InvocationTargetException, NoSuchMethodException {
        try {
            return invokeMethod(clsName,mName,parameterTypes, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw e;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }


    public static long dateDiffDays(Date sdate, Date edate) {

        return (sdate.getTime() - edate.getTime()) / 24 / 1000 / 60 / 60;
    }

    public static Date dateAddDays(Date date, int days) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        //rightNow.add(Calendar.YEAR,-1);//日期减1年
        //rightNow.add(Calendar.MONTH,3);//日期加3个月
        rightNow.add(Calendar.DAY_OF_YEAR, days);//日期加
        return rightNow.getTime();
    }

    //http://ip.taobao.com/service/getIpInfo.php?ip=111.222.241.139
    /*input :ip
    output:
    {
	"code":0,
	"data":{
		"area":"",
		"country":"中国",
		"isp_id":"100076",
		"city":"深圳",
		"ip":"111.222.241.139",
		"isp":"天威",
		"county":"XX",
		"region_id":"440000",
		"area_id":"",
		"county_id":"xx",
		"region":"广东",
		"country_id":"CN",
		"city_id":"440300"
	}
    }   */


    //String url="http://admin.youtobon.com/rest/sysuser"; rest



}

