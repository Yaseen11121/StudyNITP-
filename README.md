## 📊 Links

- *🎞 Video Pitch*: [YouTube Pitch](https://www.youtube.com/watch?v=VIDEO_ID)
- *🖼 Presentation Slide*: [Google Slides](https://drive.google.com/file/d/1NumAn5QiRtXIYqZ3s8bAoL9ZMR-sQ0bx/view?usp=drive_link)
- *📱 App APK (Demo)*: [Download APK](https://drive.google.com/file/d/1spf0PLcUU35j2zByVARWACFOherVJAlD/view?usp=drive_link)(if link is not working , please find and download it from git repository.
---

# 📚 StudyNITP - One Stop Study App for Students
In colleges, especially government institutes, students face significant difficulty in accessing reliable and organized academic resources. From searching for the right books and practical videos to finding previous year questions and preparing for viva, the process is scattered, time-consuming, and often requires paid sources.

## 🧠 What is StudyNITP?
*StudyNITP* is an Android app built for college students to *access free and organized study materials* like subject-wise books, lab videos, viva questions, and more — all in one place.  
It solves the major problem of scattered, unreliable, or paid resources, especially in government institutions.

---

## 🖥 The Hack

Our goal was to centralize and democratize academic content using a *community-driven Android platform*, where:
- Students can *access verified content* (PDFs, videos, viva Qs)
- Seniors can *upload their content/experience* to help juniors
- A built-in *query system* helps students with doubts and scholarships
- Firebase is used to ensure smooth backend operations

---
## 🚀 Features

- 📘 Subject-wise Books & Notes (Free PDFs)
- 🎥 Practical/Lab Videos for better understanding
- ❓ Real Viva Questions submitted by students
- 🧠 Previous Year & Practice Questions
- 💬 Query Submission System (Academic/Scholarship doubts)
- ✅ Content Upload & Verification System

---
## 📱 Tech Stack

- Frontend: Android (Java/Kotlin), XML UI, RecyclerView, Material Design
- Backend: Firebase Firestore, Firebase Storage, Firebase Authentication
- Extras: GitHub (Collaboration), Figma/Canva (UI Design), Google Forms (Viva Survey)

---
## 🔍 Use Cases

- Students downloading free, verified books & notes for each subject
- Watching lab experiment videos before practical exams
- Preparing for viva using real questions contributed by seniors
- Posting academic doubts and getting help from peer volunteers

---
## Dependencies
dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(platform(libs.firebase.bom))
    implementation(libs.lottie)

}
---
## 🛠️Setup Instructions
1. Install Android Studio->
-Make sure you have Android Studio installed.

2. Clone the Repository->
Open Command Terminal and paste the below code and run:
git clone  https://github.com/Yaseen11121/StudyNITP-.git

3. Open the Project in Android Studio->
-Launch Android Studio
-Click File > Open > Go to Clone File
-Navigate to the cloned folder and open it

4. Run the App
-Connect a physical device (USB Debugging ON) or open an emulator
-Press the green Run button (▶️) or Shift + F10
---


### Note:
This repository was originally created from my first GitHub account. However, due to local Git config, the commits have been made from my second account. All the work is done solely by me.





