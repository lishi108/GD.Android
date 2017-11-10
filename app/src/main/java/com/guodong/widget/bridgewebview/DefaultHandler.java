package com.guodong.widget.bridgewebview;

public class DefaultHandler implements BridgeHandler {

	String TAG = "DefaultHandler";
	
	@Override
	public void handler(String name, String data, CallBackFunction function) {
		if(function != null){
			function.onCallBack("DefaultHandler response data");
		}
	}

}
