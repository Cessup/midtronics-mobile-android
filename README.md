# Midtronics Android Application
[![License](https://img.shields.io/github/license/Cessup/midtronics-mobile-android
)](https://opensource.org/licenses/Apache-2.0)
[![Workflow](https://img.shields.io/github/actions/workflow/status/Cessup/midtronics-mobile-android/midtronics-ci.yml
)](https://github.com/Cessup/midtronics-mobile-android/actions)
[![Commit](https://img.shields.io/github/last-commit/Cessup/midtronics-mobile-android
)](https://github.com/Cessup/midtronics-mobile-android)

![Logo Image App](images/midtronics_logo.png)

This is a mobile application to test at midtronics company.

> [!NOTE]
> This is a excersies to show my skills on Android.


## Building & Running

To build or run the project, use one of the following tasks:

| Task                          | Description                                                          |
|-------------------------------|----------------------------------------------------------------------|
| `./gradlew test`              | Run the tests                                                        |
| `./gradlew build`             | Build everything                                                     |
| `buildFatJar`                 | Build an executable JAR of the server with all dependencies included |
| `buildImage`                  | Build the docker image to use with the fat JAR                       |
| `publishImageToLocalRegistry` | Publish the docker image locally                                     |
| `run`                         | Run the server                                                       |
| `runDocker`                   | Run using the local docker image                                     |

If the application starts successfully, you'll see the following output:

```
Executing tasks: [:app:assembleDebug] in project /Users/user/StudioProjects/cacaomobileandroid

Starting Gradle Daemon...
Gradle Daemon started in 1 s 218 ms
> Task :app:preBuild UP-TO-DATE
> Task :app:preDebugBuild UP-TO-DATE
> Task :app:mergeDebugNativeDebugMetadata NO-SOURCE
> Task :app:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :app:generateDebugResValues
> Task :app:checkDebugAarMetadata
> Task :app:mapDebugSourceSetPaths
> Task :app:generateDebugResources
> Task :app:packageDebugResources
> Task :app:createDebugCompatibleScreenManifests
> Task :app:extractDeepLinksDebug
> Task :app:mergeDebugResources
> Task :app:parseDebugLocalResources
> Task :app:processDebugMainManifest
> Task :app:processDebugManifest
> Task :app:mergeDebugShaders
> Task :app:compileDebugShaders NO-SOURCE
> Task :app:generateDebugAssets UP-TO-DATE
> Task :app:javaPreCompileDebug
> Task :app:mergeDebugAssets
> Task :app:compressDebugAssets
> Task :app:processDebugManifestForPackage
> Task :app:desugarDebugFileDependencies
> Task :app:mergeDebugJniLibFolders
> Task :app:checkDebugDuplicateClasses
> Task :app:mergeDebugNativeLibs
> Task :app:processDebugResources
> Task :app:mergeExtDexDebug
> Task :app:mergeLibDexDebug
> Task :app:validateSigningDebug
> Task :app:writeDebugAppMetadata
> Task :app:writeDebugSigningConfigVersions
> Task :app:stripDebugDebugSymbols
> Task :app:kspDebugKotlin
> Task :app:compileDebugKotlin
> Task :app:compileDebugJavaWithJavac
> Task :app:processDebugJavaRes
> Task :app:dexBuilderDebug
> Task :app:mergeProjectDexDebug
> Task :app:mergeDebugJavaResource
> Task :app:packageDebug
> Task :app:createDebugApkListingFileRedirect
> Task :app:assembleDebug

BUILD SUCCESSFUL in 1m 16s
36 actionable tasks: 36 executed

Build Analyzer results available
```

To run the code you need a physical device or an emulation with a minimum API of 29.

If you need more information about it you should visit next site.

- [Run apps on the Android Emulator](https://developer.android.com/studio/run/emulator)
