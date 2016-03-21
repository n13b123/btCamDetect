package com.example.victor.btcamdetect.network;

import android.os.Build;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by victor.shkanov on 21.03.2016.
 */
public class VicWebSocket {
    static WebSocketClient mWebSocketClient;
    static String server = "ws://10.48.90.17:8888";

    public static void connectWebSocket() {
        Log.i("IO", "start connect webserver server");

        URI uri;
        try {
            uri = new URI(server);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                this.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        TextView textView = (TextView) findViewById(R.id.socketDbg);
//                        textView.setText(textView.getText() + "\n" + message);
//                    }
//                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }

    public static void sendWebSocket(String msg) {
        Log.i("IO", "send msg");
        mWebSocketClient.send(msg);
    }
}
