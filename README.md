# Focus - Advanced Parental Control and Mobile Device Monitoring Solution

## Overview
**Focus** is a modular parental control application designed to enhance the safety and productivity of children and teenagers in today's digital age. It comprises two separate applications: one for parents and one for children, each offering specialized features tailored to their respective users. 

The project addresses challenges such as excessive screen time, exposure to inappropriate content, and the need for real-time device monitoring and management.
![image](https://github.com/user-attachments/assets/38950296-4c98-4eb1-b316-812e5ecadf37)
![image](https://github.com/user-attachments/assets/9ba0b635-f99a-40b4-bd7a-77fb989176b6)
![image](https://github.com/user-attachments/assets/e98fa145-bb12-4c11-9eaa-a25f5ab5bbc3)
![image](https://github.com/user-attachments/assets/c837d9a6-9ad2-4b7b-8658-4d8896a56099)
![image](https://github.com/user-attachments/assets/7096dbc1-aea8-4c71-972b-5c156db3dc7e)

## Key Features
- **Application Blocking:** Restrict access to specific apps based on parental preferences.
- **Time Management:** Set limits on app usage durations.
- **Content Filtering:**
  - Block specific websites.
  - Restrict access to specific keywords or content categories.
  - Manage unproductive features like YouTube Shorts and Instagram Reels without disabling the entire app.
- **Real-Time Monitoring:** 
  - Track children's device usage and app statistics.
  - Access a real-time view of actions performed on the child's screen.
- **Location Services:** Locate the child's device with GPS and geofencing capabilities.
- **Parental Controls:**
  - Connect parent and child devices via QR code.
  - Enable rules for managing online behavior.
- **Accessibility Services:** 
  - Automatic screen lock for blocked apps.
  - Persistent server connection to ensure constant monitoring.

## Technologies Used
![image](https://github.com/user-attachments/assets/c09cb083-9ad7-4e14-858b-ec49bf251d7d)
![image](https://github.com/user-attachments/assets/590d8d7e-439d-4ec7-809d-e62cfed2e1da)
![image](https://github.com/user-attachments/assets/6d039222-86eb-4026-a27f-feb0e00d7428)
![image](https://github.com/user-attachments/assets/6594174d-80b1-4e32-9173-aca8e6c68830)
![image](https://github.com/user-attachments/assets/14884b65-82d6-4ca3-936f-4866c57f8848)
![image](https://github.com/user-attachments/assets/a07b022b-34fc-4a7c-877b-a621daec099c)
![image](https://github.com/user-attachments/assets/afa1588d-fead-4ee3-a385-6bac0c5adc67)

### Mobile Development
- **Kotlin:** For developing the Android-native applications.
- **Jetpack Compose:** For a modern, declarative UI approach.
- **SQLite & Room Database:** For local data storage.
- **Firebase Authentication:** Secure user authentication.

### Backend
- **FastAPI:** For building the backend API with high performance.
- **WebSockets:** For real-time bi-directional communication between parent and child devices.

### Deployment
- **Docker:** Containerization for backend services.
- **Google Cloud Platform (Cloud Run):** Hosting backend services with seamless integration.
