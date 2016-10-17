package com.jonathan.intellij.API;

import com.google.gson.Gson;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.jonathan.intellij.DataModels.Translation;
import com.jonathan.intellij.UIUtils.UIHelper;
import okhttp3.*;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Jianyang on 10/16/16.
 */
public class TranslationAPI {

    private static final String HOST = "fanyi.youdao.com";
    private static final String PATH = "/openapi.do";

    private static final String PARAM_KEY_FROM = "keyfrom";
    //TODO...Use your own keyfrom
    private static final String KEY_FROM = "*****";

    private static final String PARAM_KEY = "key";
    //TODO..Use your own Key
    private static final String KEY = "******";

    private static final String PARAM_TYPE = "type";
    private static final String TYPE = "data";

    private static final String PARAM_DOC_TYPE = "doctype";
    private static final String DOC_TYPE = "json";

    private static final String PARAM_CALL_BACK = "callback";
    private static final String CALL_BACK = "show";

    private static final String PARAM_VERSION = "version";
    private static final String VERSION = "1.1";

    private static final String PARAM_QUERY = "q";

    public static void getTranslationOnBalloon(Editor editor, String query) throws URISyntaxException {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(HttpUrl.get(createTranslationURI(query)))
                .build();


        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                    Messages.showMessageDialog(
                            e.getMessage(),
                            "啊欧，崩溃了",
                            Messages.getInformationIcon()
                    );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //转化为Translation对象
                Gson gson = new Gson();
                Translation translation = gson.fromJson(response.body().string(), Translation.class);
                UIHelper.showPopupBallon(editor, translation.toString());

            }
        });
    }

    public static void getTranslation(String query) throws URISyntaxException {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(HttpUrl.get(createTranslationURI(query)))
                .build();


        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Messages.showMessageDialog(
                        e.getMessage(),
                        "啊欧，崩溃了",
                        Messages.getInformationIcon()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //转化为Translation对象
                Gson gson = new Gson();
                Translation translation = gson.fromJson(response.body().string(), Translation.class);
                UIHelper.showMessageBox(translation.toString());

            }
        });
    }

    /**
     * 生成URI
     *
     * @param query 查询内容
     * @return URI
     * @throws URISyntaxException
     */
    public static URI createTranslationURI(String query) throws URISyntaxException {

        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost(HOST)
                .setPath(PATH)
                .addParameter(PARAM_KEY_FROM, KEY_FROM)
                .addParameter(PARAM_KEY, KEY)
                .addParameter(PARAM_TYPE, TYPE)
                .addParameter(PARAM_VERSION, VERSION)
                .addParameter(PARAM_DOC_TYPE, DOC_TYPE)
                .addParameter(PARAM_CALL_BACK, CALL_BACK)
                .addParameter(PARAM_QUERY, query)
        ;
        return builder.build();
    }
}
