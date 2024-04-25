package com.example.focuschildapp.com.example.focuschildapp.WebSockets

import org.json.JSONObject
import android.app.usage.UsageStatsManager
import android.content.Context
import android.provider.Settings
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.example.websocket.RoomDB.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.WebSocketListener
import okio.ByteString

class WebSocketManager(private val context: Context) : WebSocketListener() {

    private val _messages: MutableState<List<String>> = mutableStateOf(emptyList())
    private val messages: MutableState<List<String>> = _messages
    private var appDatabase: AppDatabase = AppDatabase.getDatabase(context.applicationContext)
    private var usersViewModel: UsersViewModel = UsersViewModel(appDatabase.userDao())


    private fun getMessages(): MutableState<List<String>> {
        return messages
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        //webSocket.send("Parent connected")
        println("WebSocket connection established.")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)

        val jsonObject = JSONObject(text)

        if(jsonObject.has("addUserToDatabase")){
            val jsonArray = jsonObject.getJSONArray("addUserToDatabase")
            val packageEntityList = mutableListOf<PackageEntity>()
            val timeSpentList = mutableListOf<Long>()
            var userToInsert : String = ""
            var email : String = ""
            var deviceType : String = ""
            for (i in 0 until jsonArray.length()) {
                val appData = jsonArray.getJSONObject(i)
                userToInsert = appData.getString("userId")
                email = appData.getString("email")
                deviceType = appData.getString("deviceType")
                val appName = appData.getString("appName")
                val packageName = appData.getString("packageName")
                val icon = appData.getString("icon")
                val timeSpent = appData.getLong("timeSpent")
                timeSpentList.add(timeSpent)
                packageEntityList.add(
                    PackageEntity(
                        packageName = packageName,
                        appName = appName,
                        icon = icon,
                    )
                )
            }

            GlobalScope.launch(Dispatchers.Default) {
              usersViewModel.insertUserAndPackages(
                  UserEntity(userToInsert, email, deviceType = deviceType),
                  packageEntityList,
                  timeSpentList
              )
            }
        }


    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)

        println("Message received: ${bytes.hex()}")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        webSocket.send("Closing connection...")
        webSocket?.close(code, reason)
        println("WebSocket connection closed.")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        webSocket.send("Connection closed")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        println("WebSocket connection failed: ${t.message}")
    }


}