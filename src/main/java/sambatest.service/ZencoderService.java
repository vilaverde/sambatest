package sambatest.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class ZencoderService {

  private final String apiEndPoint = "https://app.zencoder.com/api/v2/jobs";
  private final String apiKey      = System.getenv("ZENCODE_API_KEY");

	public ZencoderService() { }

  public URL ProcessVideo(URL originalVideoUrl) throws
    IOException, UnsupportedEncodingException, JSONException {

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httpPost              = new HttpPost(apiEndPoint);

    httpPost.setHeader("Zencoder-Api-Key", apiKey);
    httpPost.setHeader("Content-Type", "application/json; charset=utf-8");

    JSONObject postParams = new JSONObject();
    postParams.put("input", originalVideoUrl.toString());

    StringEntity strEntity = new StringEntity(postParams.toString(), ContentType.APPLICATION_JSON);
    httpPost.setEntity(strEntity);

    HttpResponse httpResponse = httpClient.execute(httpPost);
    HttpEntity httpEntity     = httpResponse.getEntity();
    JSONObject postResponse   = new JSONObject(EntityUtils.toString(httpEntity));

    URL decodedFileUrl = new URL(
        postResponse.getJSONArray("outputs").getJSONObject(0).getString("url"));

    return decodedFileUrl;
  }

}

