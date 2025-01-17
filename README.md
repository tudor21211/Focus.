# Focus - Advanced Parental Control and Mobile Device Monitoring Solution

## Overview
**Focus** is a modular parental control application designed to enhance the safety and productivity of children and teenagers in today's digital age. It comprises two separate applications: one for parents and one for children, each offering specialized features tailored to their respective users. 

The project addresses challenges such as excessive screen time, exposure to inappropriate content, and the need for real-time device monitoring and management.

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
