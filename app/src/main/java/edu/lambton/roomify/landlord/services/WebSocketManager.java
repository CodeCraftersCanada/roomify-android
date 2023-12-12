package edu.lambton.roomify.landlord.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketManager {

    private static final String SOCKET_URL = "wss://yourwebsocketurl.com/socket";
    private WebSocket webSocket;

    public void connectWebSocket() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(SOCKET_URL).build();
        WebSocketListener socketListener = new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, okhttp3.Response response) {
                // WebSocket connection opened
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                // Handle incoming WebSocket messages
                // Update your UI or trigger necessary actions
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                // WebSocket connection closed
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, okhttp3.Response response) {
                // Handle connection failure
            }
        };

        webSocket = client.newWebSocket(request, socketListener);
    }

    public void closeWebSocket() {
        if (webSocket != null) {
            webSocket.close(1000, "Goodbye, WebSocket!");
        }
    }
}
