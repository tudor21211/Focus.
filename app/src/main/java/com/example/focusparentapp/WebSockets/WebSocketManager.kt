package com.example.focuschildapp.com.example.focuschildapp.WebSockets

import org.json.JSONObject
import android.app.usage.UsageStatsManager
import android.content.Context
import android.provider.Settings
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import com.example.focusparentapp.RoomDB.Entities.LocationCoordinatesEntity
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.PackageStatsEntity
import com.example.focusparentapp.RoomDB.Entities.ScreenTimeTrackerEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.RoomDB.ViewModels.PackagesViewModel
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.example.websocket.RoomDB.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.WebSocketListener
import okio.ByteString
import org.json.JSONException

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
        var jsonObject = JSONObject()
        try{
            jsonObject = JSONObject(text)
        }catch (e : JSONException){
            println(e.message)
        }

    when {

        jsonObject.has("addUserToDatabase") -> {
            val jsonArray = jsonObject.getJSONArray("addUserToDatabase")
            val packageEntityList = mutableListOf<PackageEntity>()
            val timeSpentList = mutableListOf<Long>()
            var userToInsert: String = ""
            var email: String = ""
            var deviceType: String = ""
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

        jsonObject.has("UPDATE_LOCATION_COORDINATES")->{
            val jsonArray = jsonObject.getJSONArray("UPDATE_LOCATION_COORDINATES")
            val userId = jsonArray.getString(0)
            val longitude = jsonArray.getDouble(1)
            val latitude = jsonArray.getDouble(2)
            val timestamp = jsonArray.getString(3)
            GlobalScope.launch(Dispatchers.Default) {
               try {
                   usersViewModel.insertLocationCoordinates(
                       LocationCoordinatesEntity(
                           userId = userId,
                           longitude = longitude,
                           latitude = latitude,
                           timestamp = timestamp
                       )
                   )
               }
               catch (e:Exception){
                   println("NU POT ADAUGA ${e.message}")
               }
            }
        }

        jsonObject.has("ADD_STATISTICS_DETAILS") -> {
            val jsonArray = jsonObject.getJSONArray("ADD_STATISTICS_DETAILS")
            GlobalScope.launch(Dispatchers.Default) {
                for (i in 0 until jsonArray.length()) {
                    val appData = jsonArray.getJSONObject(i)
                    if (appData.has("packageName")) {
                        val userId = appData.getString("userId")
                        val packageName = appData.getString("packageName")
                        val oneDay = appData.getLong("oneDay")
                        val threeDays = appData.getLong("threeDays")
                        val oneWeek = appData.getLong("oneWeek")
                        val oneMonth = appData.getLong("oneMonth")

                        val packageStatsEntity = PackageStatsEntity(
                            userId = userId ,
                            packageName = packageName,
                            oneDay = oneDay,
                            threeDays = threeDays,
                            oneWeek = oneWeek,
                            oneMonth = oneMonth
                        )
                        usersViewModel.insertPackageStats(packageStatsEntity)
                    }
                    if(appData.has("launchTracker")) {
                        val userId = appData.getString("userId")
                        val launchTracker = appData.getInt("launchTracker")
                        val screenTime = appData.getString("screenTime")
                        val screenTimeTrackerEntity = ScreenTimeTrackerEntity(
                            userId = userId,
                            launchTracker = launchTracker,
                            screenTime = screenTime
                        )
                        usersViewModel.updateTrackerAndTimeSpent(screenTimeTrackerEntity)
                    }
                }
            }
        }
        //TODO REMOVE THE COMMENT


            jsonObject.has("UPDATE_APPS_DATA") -> {
            val jsonArray = jsonObject.getJSONArray("UPDATE_APPS_DATA")
            var updateTime = ""
            val packagesList = mutableListOf<String>()
            val timeSpentList = mutableListOf<Long>()
            val userToInsert = jsonArray.getJSONObject(0).getString("userId")
            for (i in 0 until jsonArray.length()) {
                val appData = jsonArray.getJSONObject(i)
                if (appData.has("updateTime"))
                    updateTime = appData.getString("updateTime")
                else {
                    val packageName = appData.getString("packageName")
                    val timeSpent = appData.getLong("timeSpent")
                    packagesList.add(packageName)
                    timeSpentList.add(timeSpent)
                }

            }

            GlobalScope.launch(Dispatchers.Default) {
                for (i in 0 until packagesList.size) {
                    usersViewModel.updateTimeSpent(
                        userToInsert,
                        packagesList[i],
                        timeSpentList[i],
                        updateTime
                    )
                }
            }
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