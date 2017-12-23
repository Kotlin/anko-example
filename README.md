Anko Example Project
===========

Code in this repo shows how to set up [Anko](https://github.com/JetBrains/anko) library in the Android Gradle project. The application is very simple yet working and shows some important Anko concepts. Please take into account that to work with this code in Android Studio you will need to install Kotlin plugin from [JetBrains Plugins](https://plugins.jetbrains.com/plugin/6954?pr=androidstudio).

The repo can be also used as a template, so it may be a nice starting point for your new app. Come get some! :thumbsup:

## Importing

Project can be easily imported into IntelliJ IDEA 15 or Android Studio.
Select **File** | **Import project…** and open the ``build.gradle`` file.

Make sure you have synced a version of a builtin Kotlin compiler and project properties to be able to properly download all dependencies for the project.

* Select **File** | **Settings** | **Plugins** and write "kotlin" in the search bar. 
* Read version of the Kotlin plugin, for instance `Version: 1.2.10-release-Studio3.0-1`.
* Open `build.gradle` file and change value of `ext.kotlin_version` parameter, for instance as `ext.kotlin_version = '1.2.10'`.
* Refresh Gradle project.

## So what is Anko?

You can read more about Anko library [here](https://github.com/JetBrains/anko).
