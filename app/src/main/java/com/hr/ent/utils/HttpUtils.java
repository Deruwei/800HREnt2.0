package com.hr.ent.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EncodingUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPInputStream;

/**
 * http请求工具类
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-3-26
 */
public abstract class HttpUtils {
    private static final String TAG = "HttpUtils";
    // 标识网络请求方式的常量（注：外部网络请求调用时，如果是需要以GET方式请求则必须增加此参数，如果不填则默认为POST方式请求）
    public static final String METHOD = "http_method";
    public static final String METHOD_POST = "post";
    public static final String METHOD_GET = "get";

    private static HttpParams httpParams;
    private static ClientConnectionManager connectionManager;
    private static String myString;

    static {
        httpParams = new BasicHttpParams();
        // 设置最大连接数
        ConnManagerParams.setMaxTotalConnections(httpParams, 100);
        // 设置获取连接的最大等待时间
        ConnManagerParams.setTimeout(httpParams, 30 * 1000);
        // 设置每个路由最大连接数
        ConnPerRouteBean connPerRoute = new ConnPerRouteBean(50);
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams, connPerRoute);
        // 设置连接超时时间
        HttpConnectionParams.setConnectionTimeout(httpParams, 30 * 1000);
        // 设置读取超时时间
        HttpConnectionParams.setSoTimeout(httpParams, 30 * 1000);
        // 设置连接Socket 缓存大小及agent
        HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
        HttpProtocolParams.setUserAgent(httpParams,
                System.getProperty("http.agent"));

        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));
        registry.register(new Scheme("https", SSLSocketFactory
                .getSocketFactory(), 443));

        connectionManager = new ThreadSafeClientConnManager(httpParams,
                registry);
    }

    private static HttpClient createHttpClient() {
        return new DefaultHttpClient(connectionManager, httpParams);
    }

    private static String getContentCharSet(final HttpEntity entity)
            throws ParseException {
        if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
        String charset = null;
        if (entity.getContentType() != null) {
            HeaderElement values[] = entity.getContentType().getElements();;
            if (values.length > 0) {
                NameValuePair param = values[0].getParameterByName("charset");
                if (param != null) {
                    charset = param.getValue();
                }
            }
        }
        return charset;
    }

    private static String toString(HttpResponse response)
            throws IllegalStateException, IOException {
        HttpEntity entity = response.getEntity();

        InputStream is = entity.getContent();
        if (is == null) {
            return "";
        }

        int i = (int) entity.getContentLength();
        if (i < 0) {
            i = 4096;
        }

        String charset = getContentCharSet(entity);
        if (charset == null) {
            charset = "UTF-8";
        }

        Header header = response.getLastHeader("Content-Encoding");
        if (header != null && header.getValue().equalsIgnoreCase("gzip")) {
            is = new GZIPInputStream(is);
        }

        Reader reader = new InputStreamReader(is, charset);
        CharArrayBuffer buffer = new CharArrayBuffer(i);
        try {
            char[] tmp = new char[1024];
            int l;
            while ((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    private static byte[] toByteArray(final HttpEntity entity)
            throws IOException {
        InputStream is = entity.getContent();
        if (is == null) {
            return null;
        }
        try {
            int i = (int) entity.getContentLength();
            if (i < 0) {
                i = 4096;
            }
            ByteArrayBuffer buffer = new ByteArrayBuffer(i);
            byte[] tmp = new byte[4096];
            int l;
            while ((l = is.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            return buffer.toByteArray();
        } finally {
            is.close();
        }
    }

    /**
     * 访问http，获得字符串，字符编码由网站决定
     *
     * @param url
     * @return
     * @throws Exception
     */
    private static String getString(String url, Map<String, Object> params)
            throws Exception {
        if (params != null) {
            StringBuffer buf = new StringBuffer();

            for (Map.Entry<String, Object> entry : params.entrySet()) {
                buf.append("&")
                        .append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue().toString(),
                                "UTF-8"));
            }
            if (url.indexOf("?") != -1)// 已经有参数
            {
                url += buf.toString();
            } else {
                if (buf.length() != 0)
                    url += "?" + buf.substring(1);
            }
        }
        Log.d(TAG, "getString,url:" + url);
        HttpClient client = createHttpClient();
        HttpGet get = new HttpGet(url);
        try {
            get.addHeader("Accept-Encoding", "gzip");
            HttpResponse response = client.execute(get);
            String content = toString(response);
            Log.d(TAG, "getString,content:" + content);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            client.getConnectionManager().closeExpiredConnections();
        }
    }

    public static String sendData(String url, Map<String, Object> params)
            throws Exception {
        if (params.containsKey(METHOD) && params.get(METHOD).toString() != null
                && params.get(METHOD).toString().equals(METHOD_GET)) {
            params.remove(METHOD);
            return getString(url, params);
        } else {
            // 如果外部调用者没有传method参数则默认是post请求
            params.remove(METHOD);
            return postString(url, EncryptUtils.encrypParams(params));
        }
    }

    /**
     * 使用http的post方法发送请求，返回字符串
     *
     * @param url    访问的地址
     * @param params 要提交的参数的集合，注意key是提交的每个参数的名字，value是数据。如果value是文件，则代表其中存在上传的文件
     * @return
     */
    public static String postString(String url, Map<String, Object> params)
            throws Exception {
        Log.d(TAG, "postAccessory,url:" + url);
        HttpClient client = createHttpClient();
        HttpPost post = new HttpPost(url);
        MultipartEntity reqEntity = new MultipartEntity(
                HttpMultipartMode.BROWSER_COMPATIBLE);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            final Object value = entry.getValue();
            if (value instanceof File) {
                InputStream in = new BufferedInputStream(new FileInputStream(
                        (File) value));
                InputStreamBody isb = new InputStreamBody(in,
                        ((File) value).getName()) {
                    public long getContentLength() {
                        return ((File) value).length();
                    }
                };
                reqEntity.addPart(entry.getKey(), isb);
            } else {
                reqEntity.addPart(
                        entry.getKey(),
                        new StringBody(value.toString(), Charset
                                .forName("UTF-8")));
            }

            Log.e(entry.getKey(), value + "");
        }

        post.setEntity(reqEntity);
        post.addHeader("Accept-Encoding", "gzip");
        try {
            HttpResponse response = client.execute(post);
            String content = toString(response);

            Log.d(TAG, "postAccessory,content:" + content);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            client.getConnectionManager().closeExpiredConnections();
        }
    }

    /**
     * 直接提交一个字符串
     *
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static String postString(String url, String param) throws Exception {
        Log.d(TAG, "postString,url:" + url + " 参数: " + param);
        HttpClient client = createHttpClient();

        try {
            HttpPost post = new HttpPost(url);
            post.addHeader("Accept-Encoding", "gzip");
            post.addHeader("Charset", "utf-8");
            post.addHeader("Content-Type", "application/x-www-form-urlencoded");
            post.addHeader("Connection", "Keep-alive");
            StringEntity se = new StringEntity(param, Const.ENCODE);
            post.setEntity(se);

            String content = toString(client.execute(post));

            Log.d(TAG, "postString,content:" + content);

            return content;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            client.getConnectionManager().closeExpiredConnections();
        }
    }

    /**
     * 通过get方法获得字节数组 调用此方法时，要估计到返回内容到大小，以免出现系统内存溢出。
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static byte[] getContent(String url) throws Exception {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "getContent,url:" + url);
        }

        HttpClient client = createHttpClient();
        HttpGet get = new HttpGet(url);

        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            return toByteArray(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            client.getConnectionManager().closeExpiredConnections();
        }
    }

    /**
     * 通过get方法，将请求到结果保存为临时文件，返回参数为临时文件全路径。
     *
     * @param url      要下载的文件的路径
     * @param handler  下载过程的消息处理器
     * @param message  下载过程中已经下载字节数的消息编号， arg1参数是当前已经下载的大小, arg2是整个文件的大小（服务器支持）
     * @param isStoped 下载过程中停止的标志
     * @return
     * @throws Exception
     */
    public static String getCachedFile(Context context, String url,
                                       Handler handler, int message, AtomicBoolean isStoped)
            throws Exception {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "getTempFile,url:" + url);
        }

        HttpClient client = createHttpClient();
        HttpGet get = new HttpGet(url);
        InputStream ios = null;
        FileOutputStream fos = null;
        try {
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            String tempFilePath = context.getCacheDir().getAbsolutePath() + "/" + fileName;
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            ios = entity.getContent();
            long fileLength = entity.getContentLength();
            File file = new File(tempFilePath);
            fos = new FileOutputStream(file);
            byte buf[] = new byte[512];
            // 收到的总字节数和当前字节数
            int totalSize = 0, size;
            int b = 0;
            while ((size = ios.read(buf)) != -1) {
                if (isStoped != null && isStoped.get()) {
                    return null;
                }
                fos.write(buf, 0, size);
                totalSize += size;
                int a = (int) totalSize * 100 / (int) fileLength;
                if (handler != null && a - b >= 1 || totalSize == fileLength) {
                    b = a;
                    Message msg = new Message();
                    msg.arg1 = totalSize;
                    msg.arg2 = (int) fileLength;
                    msg.what = message;
                    handler.sendMessage(msg);
                }
            }
            return tempFilePath;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                ios.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getCachedFile(Context context, String url,
                                       Handler handler, int message) throws Exception {
        return getCachedFile(context, url, null, 0, null);
    }

    public static String getCachedFile(Context context, String url)
            throws Exception {
        return getCachedFile(context, url, null, 0);
    }


    /**
     * 更新 city、job、lingyu 文件信息m
     * @param context
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void updateArrayFile(Context context, String url, String filename) throws IOException, FileNotFoundException {
        SharedPreferencedUtils sb = new SharedPreferencedUtils(context);
        try {
            URL uri = new URL(url);//注意，这里的URL地址必须为网络地址，
            //URL uri = new URL("http://localhost:8080/my/poem.txt");
            //本地地址http://localhost:8080/my/poem.txt会报Connection Refused的异常k
            URLConnection ucon = uri.openConnection();
            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(100);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            sb.setStringValue(filename, myString = new String(baf.toByteArray(), "UTF-8"));
            //myString = EncodingUtils.getString(baf.toByteArray(), "GBK");
            //myString = new String(baf.toByteArray());这个出现乱码，要在txt文件保存时选中utf-8
        } catch (Exception e) {
            sb.setStringValue(filename, e.getMessage());
        }
    }
    //读文件
    public static String readSDFile(String fileName) throws IOException {
        String res = "";
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        int length = fis.available();
        byte[] buffer = new byte[length];
        fis.read(buffer);
        res = EncodingUtils.getString(buffer, "UTF-8");
        fis.close();
        return res;
    }
}
