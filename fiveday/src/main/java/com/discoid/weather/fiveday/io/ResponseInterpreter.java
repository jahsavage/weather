package com.discoid.weather.fiveday.io;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Categorizes errors and delivers then in a standard form
 */

public class ResponseInterpreter {

    private static final String TAG = ResponseInterpreter.class.getSimpleName();

    public void filterErrors(Response response) throws ErrorCategoryException, IOException {
        int responseCode = response.code();

        if (HttpResponseCodeChecker.isAnHttpError(responseCode)) {
            ResponseBody responseBody = response.errorBody();

            if (responseBody != null) {
                String msg = responseBody.string();
                Timber.v(TAG, String.format("Response error code %d, %s", responseCode, msg));
                throw new ErrorCategoryException(ErrorCategory.translate(responseCode), msg);
            } else {
                Timber.v(TAG, String.format("Response error code %d, no error body", responseCode));
                throw new ErrorCategoryException(ErrorCategory.translate(responseCode));
            }
        }
    }

}
