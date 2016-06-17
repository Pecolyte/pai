package me.pecolyte.pai.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <P>Title: HttpUtil</P>
 * <P>Description: ����HTTP����Ĺ�����</P>
 * <P>Copyright: Copyright(c) 2016</P>
 * <P>Company: pecolyte.me</P>
 * <P>Date:2016��6��16��-����2:23:54</P>
 * @author Pecolyte
 * @version 1.0.0
 */
public class HttpUtil {
    
    /**
     * Get��ʽ�����ַ�����Ӧ
     * @param url
     * @return 
     * @since 1.0.0
     * 2016��6��16��-����6:10:56
     */
    public static String getStringResp(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .disableRedirectHandling()
                .build();
        ResponseHandler<String> stringHandler = new ResponseHandler<String>() {
            public String handleResponse(HttpResponse response)
                    throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    System.out.println(response.getFirstHeader("Location").getValue());
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };
        HttpGet getRequest = new HttpGet(url);
        try {
            return httpClient.execute(getRequest, stringHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(httpClient != null)
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * Get��ʽ����ͼƬ��Ӧ
     * @param url
     * @return 
     * @since 1.0.0
     * 2016��6��16��-����6:11:16
     */
    public static BufferedImage getImageResp(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .disableRedirectHandling()
                .build();
        ResponseHandler<BufferedImage> imageHandler = new ResponseHandler<BufferedImage>() {
            public BufferedImage handleResponse(HttpResponse response)
                    throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    BufferedImage result = ImageIO.read(entity.getContent());
                    return result;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };
        HttpGet getRequest = new HttpGet(url);
        try {
            return httpClient.execute(getRequest, imageHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Post��ʽ����JSON����
     * @param url
     * @param post
     * @return 
     * @since 1.0.0
     * 2016��6��16��-����6:11:43
     */
    public static String postJSONObject(String url, String post) {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .disableRedirectHandling()
                .build();
        ResponseHandler<String> stringHandler = new ResponseHandler<String>() {
            public String handleResponse(HttpResponse response)
                    throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    HttpEntity entity = response.getEntity();
                    System.out.println(entity != null ? EntityUtils.toString(entity) : null);
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };
        try {
            HttpPost postRequest = new HttpPost(url);
            StringEntity requestEntity;
            postRequest.setHeader("Content-Type", "application/json; charset=utf-8");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", post);
            requestEntity = new StringEntity(jsonObject.toString(), "utf-8");
            postRequest.setEntity(requestEntity);
            return httpClient.execute(postRequest, stringHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * Post��ʽ�����ַ���
     * @param url
     * @param post
     * @return 
     * @since 1.0.0
     * 2016��6��1��-����6:01:47
     */
    public static String postString(String url, String post) {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .disableRedirectHandling()
                .build();
        ResponseHandler<String> stringHandler = new ResponseHandler<String>() {
            public String handleResponse(HttpResponse response)
                    throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };
        HttpPost postRequest = new HttpPost(url);
        StringEntity requestEntity;
        postRequest.setHeader("Content-Type", "text/plain; charset=utf-8");
        requestEntity = new StringEntity(post, "utf-8");
        postRequest.setEntity(requestEntity);
        try {
            return httpClient.execute(postRequest, stringHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
