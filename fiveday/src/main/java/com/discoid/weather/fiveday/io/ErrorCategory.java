/*
 * Copyright (c) 2017 Fleetmatics Ireland Limited. All Rights Reserved.
 */

package com.discoid.weather.fiveday.io;

import java.io.IOException;

import retrofit2.HttpException;
import timber.log.Timber;

/**
 * Created by paul.savage on 08/02/2017.
 */

public enum ErrorCategory {
  CONNECTION_ERROR,
  AUTHENTICATION_ERROR,
  SERVER_ERROR,
  GENERIC_ERROR,
  NOT_FOUND,
  BAD_REQUEST,
  CONFLICT;

  private static final String TAG = "ErrorCategory";

  ErrorCategory() {
  }

  public boolean isConnectionError() {
    return this == CONNECTION_ERROR;
  }

  public boolean isAuthenticationError() {
    return this == AUTHENTICATION_ERROR;
  }

  public boolean isNotFound() {
    return this == NOT_FOUND;
  }

  public boolean isBadRequest() {
    return this == BAD_REQUEST;
  }

  public static ErrorCategory translate(int httpErrorCode) {
    ErrorCategory errorCategory = null;

    if (HttpResponseCodeChecker.isHttpUnauthorized(httpErrorCode)) {
      errorCategory = AUTHENTICATION_ERROR;
    } else if (HttpResponseCodeChecker.isHttpServerError(httpErrorCode)) {
      errorCategory = SERVER_ERROR;
    } else if (HttpResponseCodeChecker.isHttpNotFound(httpErrorCode)) {
      errorCategory = NOT_FOUND;
    } else if (HttpResponseCodeChecker.isHttpBadRequest(httpErrorCode)) {
      errorCategory = BAD_REQUEST;
    } else if (HttpResponseCodeChecker.isHttpAConflict(httpErrorCode)) {
      errorCategory = CONFLICT;
    } else {
      errorCategory = GENERIC_ERROR;
    }

    Timber.v(TAG,
            String.format("Error code %s category %s", httpErrorCode, errorCategory.name()));

    return errorCategory;
  }

  public static ErrorCategory translate(Throwable e) {
    if (e == null) {
      return ErrorCategory.GENERIC_ERROR;
    } else if (e instanceof ErrorCategoryException) {
      ErrorCategoryException errorCategoryException = (ErrorCategoryException) e;
      return errorCategoryException.getErrorCategory();
    } else if (e instanceof HttpException) {
      HttpException httpException = (HttpException) e;
      return translate(httpException.code());
    } else if (e instanceof IOException) {
      return ErrorCategory.CONNECTION_ERROR;
    }
    return ErrorCategory.GENERIC_ERROR;
  }

  public static String getDebugMessage(Throwable e, String requestName) {
    if (e == null) {
      return String.format("%s: UnExpected null exception?", requestName);
    } else if (e instanceof HttpException) {
      return getMsgForHttpException((HttpException) e, requestName);
    } else {
      if (e instanceof IOException) {
        return String.format("%s: Connection error, '%s'", requestName, e.getMessage());
      } else {
        return String.format("%s: Exception class %s msg '%s'", requestName,
            e.getClass().getSimpleName(), e.getMessage());
      }
    }
  }

  private static String getMsgForHttpException(HttpException httpException, String requestName) {
        return httpException.getMessage();
  }
}
