package com.example.focusparentapp.WebSockets

import android.content.Context
import com.example.focuschildapp.com.example.focuschildapp.WebSockets.WebSocketManager
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

object WebSocketConnector {
    private var webSocket: WebSocket? = null
    private val client = OkHttpClient()

    fun connectWebSocket(context: Context, endPoint: String) {
        //val request = Request.Builder().url("ws://192.168.0.139:8200/ws/$endPoint").build()
        val request = Request.Builder().url("wss://fastapi-project-zgaflnvvcq-ey.a.run.app/ws/$endPoint").build()
        val listener = WebSocketManager(context)
        webSocket = client.newWebSocket(request, listener)
        webSocket?.send("CONNECTED_PARENT")
    }

    fun disconnectWebSocket() {
        webSocket?.close(1000, "Closing the connection")
    }

    fun reconnectWebSocket(context: Context, newEndPoint: String) {
        disconnectWebSocket()
        connectWebSocket(context, newEndPoint)
    }

    fun getWebSocket(): WebSocket? {
        return webSocket
    }
}