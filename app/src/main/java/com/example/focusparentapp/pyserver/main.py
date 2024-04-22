from fastapi import FastAPI, WebSocket, Response
from fastapi.responses import HTMLResponse
import socket
import netifaces
import uvicorn
import firebase_admin
from firebase_admin import credentials
from firebase_admin import auth
from starlette.websockets import WebSocketDisconnect
from ConnectionManager import ConnectionManager


if not firebase_admin._apps:
    cred = credentials.Certificate("focus-e2dcf-firebase-adminsdk-wi8v1-010e1513f8.json")
    firebase_admin.initialize_app(cred)

def getAllUsersId():
    return auth.list_users()

def get_all_user_uid():
    return [user.uid for user in auth.list_users().iterate_all()]



def get_wlan_ip():
    interfaces = netifaces.interfaces()
    for interface in interfaces:
        if interface.startswith('{B6190110-015C-4548-BDEC-ED5517EA9EAA'):
            print(interface)
            addrs = netifaces.ifaddresses(interface)

            if netifaces.AF_INET in addrs:
                ip_info = addrs[netifaces.AF_INET][0]
                return ip_info['addr']
    return None

ip = get_wlan_ip()
if ip:
    print("WLAN IP Address:", ip)
else:
    print("WLAN IP Address not found.")
app = FastAPI()

html = f"""
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WebSocket Example</title>
    <script>

        var socket = new WebSocket("ws://{ip}:8200/ws/%s");
        """ + """
        socket.onopen = function(event) {
            console.log("WebSocket connection established.");
        };

        socket.onmessage = function(event) {
            console.log("Message received: " + event.data);
        };

        socket.onclose = function(event) {
            console.log("WebSocket connection closed.");
        };

        function sendMessage() {
            var message = document.getElementById("message").value; 
            socket.send(message);
            console.log("Message sent: " + message);
        }
    </script>
</head>
<body>
    <h1>WebSocket</h1>
    <input type="text" id="message" placeholder="Enter message">
    <button onclick="sendMessage()">Send</button>
</body>
</html>
"""

websocket_connections = set()
manager = ConnectionManager()

@app.get("/{user_uid}")
async def get(user_uid: str):
    ip = get_wlan_ip()
    formatted_html = html % f"{user_uid}"
    return HTMLResponse(content=formatted_html, status_code=200)


user_websockets = {}
all_user_uid = get_all_user_uid()
print(all_user_uid)
# WebSocket endpoint
@app.websocket("/ws/{user_uid}")
async def websocket_endpoint(websocket: WebSocket, user_uid: str):
    print(f"USER UID IS {user_uid}")
    if(user_uid in all_user_uid):
        await websocket.accept()
        # Associate the WebSocket connection with the user UID
        user_websockets.setdefault(user_uid, set()).add(websocket)

        print("Current user_websockets dictionary:", user_websockets)
        try:
            while True:
                data = await websocket.receive_text()
                # Broadcasting the message to all WebSocket connections for the user UID
                for ws in user_websockets.get(user_uid, []):
                    if ws != websocket:
                        await ws.send_text(data)
 
        finally:
            # Remove the WebSocket connection when closed
            user_websockets.get(user_uid, set()).remove(websocket)



"""@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket):
    await websocket.accept()
    websocket_connections.add(websocket)
    try:
        while True:
            data = await websocket.receive_text()
            # Broadcast the received message to all connected clients
            for connection in websocket_connections:
                if connection != websocket:
                    await connection.send_text(data)
    except WebSocketDisconnect as e:
        print(f"WebSocket disconnected: {e}")
    finally:
        # Remove the WebSocket connection from the set when the connection is closed
        websocket_connections.remove(websocket)
"""


if __name__ == "__main__":
    """all_users = getAllUsersId()
    for user in all_users.users:
        print(f'User with email: {user.email} and uid: {user.uid}')"""
    host = ip
    port = 8200
    reload = True 
    uvicorn.run("main:app", host=host, port=port, reload=reload)