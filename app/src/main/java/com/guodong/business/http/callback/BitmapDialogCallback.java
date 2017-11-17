/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.guodong.business.http.callback;

import android.content.Context;
import android.graphics.Bitmap;

import com.guodong.widget.LoadingDialog;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.request.base.Request;
/**
 * Description:
 * Created by LSQ108 on 2017/10/28.
 */
public abstract class BitmapDialogCallback extends BitmapCallback {

    private LoadingDialog dialog;

    public BitmapDialogCallback(Context context) {
        super(1000, 1000);
        dialog = new LoadingDialog(context);
    }

    @Override
    public void onStart(Request<Bitmap, ? extends Request> request) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onFinish() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
