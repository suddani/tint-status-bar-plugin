package com.sudi.plugins.tintstatusbar;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.Color;
import android.R;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

public class TintStatusBarPlugin extends CordovaPlugin {
  public static final String ACTION_SET_STATUS_BAR_COLOR = "setStatusBarColor";

  @Override
  public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {

    if (android.os.Build.VERSION.SDK_INT >= 21) {
      this.cordova.getActivity().runOnUiThread(new Runnable() {
        public void run() {
          try {
            if (ACTION_SET_STATUS_BAR_COLOR.equals(action)) {

              TintStatusBarPlugin.this.cordova.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
              TintStatusBarPlugin.this.cordova.getActivity().getWindow().setStatusBarColor(Color.parseColor(args.getString(0)));

              callbackContext.success();
              // return true;
            }
            callbackContext.error("Invalid action");
            // return false;
          } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            // return false;
          }
        }
      });
    } else {
      callbackContext.error("Not supported in this Android Version");
      return false;
    }
    return true;
  }
}
