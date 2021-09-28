package com.d10ng.moshilib

import com.squareup.moshi.Moshi
import org.json.JSONObject
import kotlin.Exception

/** 获取Moshi构造器 */
fun getMoshiBuilder() = Moshi.Builder().build()

/**
 * 将Bean转换成Json
 * @receiver T
 * @return (kotlin.String..kotlin.String?)
 */
inline fun <reified T> T.toJson() = getMoshiBuilder().adapter(T::class.java).toJson(this)

/**
 * 将Bean转换成Json
 * @receiver T
 * @param clz Class<T>
 * @return (String..String?)
 */
fun <T> T.toJson(clz: Class<T>) = getMoshiBuilder().adapter(clz).toJson(this)

/**
 * 将object转换成实体
 * @receiver Any
 * @param clz Class<T>
 * @return T
 */
fun <T> Any.try2MoshiBean(clz: Class<T>): T {
    return try {
        val map = this as Map<String, Any>
        map.toMoshiBean(clz)
    } catch (e: Exception) {
        e.printStackTrace()
        clz.newInstance()
    }
}

/**
 * 将Map转换成实体
 * @receiver Map<String, Any>
 * @param clz Class<T>
 * @return T
 */
fun <T> Map<String, Any>.toMoshiBean(clz: Class<T>): T {
    val json = JSONObject(this)
    return json.toMoshiBean(clz)
}

/**
 * 将JSON转换成实体
 * @receiver JSONObject
 * @param clz Class<T>
 * @return T
 */
fun <T> JSONObject.toMoshiBean(clz: Class<T>): T {
    val adapter = getMoshiBuilder().adapter(clz)
    return try {
        adapter.fromJson(this.toString())?: clz.newInstance()
    } catch (e: Exception) {
        e.printStackTrace()
        clz.newInstance()
    }
}