#TODO: Add Proguard rules for your project here.
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#-keepclassmembers class  com.zappstudio.data.model.*{
#   public *;
#}

#-keepnames class androidx.lifecycle.ViewModel
#-keepclassmembers class * extends androidx.lifecycle.ViewModel { <init>(...); }
#-keepclassmembers class * implements androidx.lifecycle.LifecycleObserver { <init>(...); }
#-keepclassmembers class * implements androidx.lifecycle.LifecycleOwner { <init>(...); }
#-keepclassmembers class androidx.lifecycle.Lifecycle$State { *; }
#-keepclassmembers class androidx.lifecycle.Lifecycle$Event { *; }
#-keep class * implements androidx.lifecycle.LifecycleOwner { public <init>(...); }
#-keep class * implements androidx.lifecycle.LifecycleObserver { public <init>(...); }
#-keepclassmembers class * { public <init>(...); }
#
#-keepclassmembers class com.zappstudio.beatbest.di.*{
#   public *;
#}
#
#-keepclassmembers class com.zappstudio.beatbest.register.di.*{
#   public *;
#}
#
#-keepclassmembers class com.zappstudio.splash.di.*{
#   public *;
#}
#
#-keepclassmembers class com.zappstudio.core.app.di.*{
#   public *;
#}
#
#-keepclassmembers class com.zappstudio.data.di.*{
#   public *;
#}
#
#
#-keepnames class android.arch.lifecycle.ViewModel
#-keepclassmembers public class * extends android.arch.lifecycle.ViewModel { public <init>(...); }
#-keepclassmembers class * { public <init>(...); }
#
#-keepclassmembers,allowobfuscation class * {
#  @com.google.gson.annotations.SerializedName <fields>;
#}
#
## Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
#-keep,allowobfuscation,allowshrinking interface retrofit2.Call
#-keep,allowobfuscation,allowshrinking class retrofit2.Response
#
## With R8 full mode generic signatures are stripped for classes that are not
## kept. Suspend functions are wrapped in continuations where the type argument
## is used.
#-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
#
#
#-keep class  com.bottleapp.app.data.entity.**{ *; }