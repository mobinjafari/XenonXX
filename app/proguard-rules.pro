
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Enum
-keepclassmembers enum * { *; }
####################################################################################################
# Parcelize
-keepnames class * implements android.os.Parcelable

-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}


-keep public class org.lotka.bp.** {*;}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}


# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

 # Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
 -keep,allowobfuscation,allowshrinking interface retrofit2.Call
 -keep,allowobfuscation,allowshrinking class retrofit2.Response

 # With R8 full mode generic signatures are stripped for classes that are not
 # kept. Suspend functions are wrapped in continuations where the type argument
 # is used.
 -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

 -if class * {
   @com.google.gson.annotations.SerializedName <fields>;
 }

 -keep class <1> {
   <init>();
 }


    -keep class com.google.gson.** { *; }




# Retrofit
-keepattributes Signature
-keepattributes Exceptions

# Retrofit specific classes
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

# OkHttp specific classes
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }

# Okio specific classes
-dontwarn okio.**
-keep class okio.** { *; }
