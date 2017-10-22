package com.dryseed.ds.net.rx;

import android.content.Context;

import com.dryseed.ds.net.HttpMethod;
import com.dryseed.ds.net.RestClientBuilder;
import com.dryseed.ds.net.RestCreator;
import com.dryseed.ds.net.RestService;
import com.dryseed.ds.net.callback.IError;
import com.dryseed.ds.net.callback.IFailure;
import com.dryseed.ds.net.callback.IRequest;
import com.dryseed.ds.net.callback.ISuccess;
import com.dryseed.ds.net.callback.RequestCallbacks;
import com.dryseed.ds.net.download.DownloadHandler;
import com.dryseed.ds.ui.loader.DsLoader;
import com.dryseed.ds.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by User on 2017/10/21.
 */
public class RxRestClient {

    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final RequestBody BODY;
    private final File FILE;            //文件上传
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYPE;

    public RxRestClient(String url,
                        Map<String, Object> params,
                        RequestBody body,
                        File file,
                        Context context,
                        LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYPE = loaderStyle;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        final RxRestService rxRestService = RestCreator.getRxRestService();

        Observable<String> observable = null;

        if (LOADER_STYPE != null) {
            DsLoader.showLoading(CONTEXT, LOADER_STYPE);
        }

        switch (method) {
            case GET:
                observable = rxRestService.get(URL, PARAMS);
                break;
            case POST:
                observable = rxRestService.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = rxRestService.postRaw(URL, BODY);
                break;
            case PUT:
                observable = rxRestService.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = rxRestService.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = rxRestService.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = rxRestService.upload(URL, body);
                break;
            default:
                break;
        }

        return observable;
    }

    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<ResponseBody> download() {
        return RestCreator.getRxRestService().download(URL, PARAMS);
    }
}
