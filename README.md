# as1
CMPUT 301 Assignment 1
Keith Mills - kgmills

Name: Kgmills-HabitTracker
License: GPL 3.0

Video Link:
https://archive.org/details/KgmillsHabitTracker

NOTE: 
Video was taken on a Saturday, so the Meditate habit was changed slightly so it would be visable and completable. 
Video was captured on a 4k monitor, hence why the assignment screen text will appear small. 

Compiler Information:
	compileSdkVerion 23
	buildToolsVersion 22.0.1
	minSdkVersion 18
	targetSdkVersion 23

Dependencies:
    compile fileTree(include: ['*.jar'], dir: 'libs')
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:23.4.0'
    testCompile 'junit:junit:4.12'
    compile 'com.google.code.gson:gson:2.7'

Backend Source Code Files:
	dateHandler.java
	droidMVC.java
	Habit.java
	HabitController.java
	HabitLog.java

JUnit Files:
	dateHandlerTests.java
	habitControllerTests.java
	habitLogTests.java
	habitUnitTests.java

Activity Files:
	deleteCompletionActivity.java
	deleteHabitActivity.java
	habitsViewActivity.java
	newHabitActivity.java

Relevant XML Files:
	activity_delete_completions.xml
	activity_delete_habit.xml
	activity_habits_view.xml
	activity_new_habit.xml
	delete_item.xml
	list_item.xml
	strings.xml
	AndroidManifest.xml