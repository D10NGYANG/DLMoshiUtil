# DLMoshiUtil
Moshi的kotlin拓展转换工具；
[![](https://jitpack.io/v/D10NGYANG/DLMoshiUtil.svg)](https://jitpack.io/#D10NGYANG/DLMoshiUtil)

## 使用
1 Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
2 Add the dependency
```gradle
dependencies {
    // moshi
    implementation('com.squareup.moshi:moshi-kotlin:1.12.0')
    kapt('com.squareup.moshi:moshi-kotlin-codegen:1.12.0')
    kaptTest 'com.squareup.moshi:moshi-kotlin-codegen:1.12.0'
    // moshi拓展工具
    implementation 'com.github.D10NGYANG:DLMoshiUtil:1.2'
}
```
3 混淆
```pro
# Moshiss
-dontwarn javax.annotation.**
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}
-keep @com.squareup.moshi.JsonQualifier interface *
-keepclassmembers @com.squareup.moshi.JsonClass class * extends java.lang.Enum {
    <fields>;
    **[] values();
}
-keepclassmembers class com.squareup.moshi.internal.Util {
    private static java.lang.String getKotlinMetadataClassName();
}

# moshi拓展工具
-keep class com.d10ng.moshilib.** {*;}
-dontwarn com.d10ng.moshilib.**
```
