package com.example.searchmovie

import android.content.Context
import org.json.JSONArray
import android.content.Context.MODE_PRIVATE

class SharedPreference {
    fun setRecentListPref(context: Context, key: String, searchString: String) {
        val prefs = context.getSharedPreferences(key, MODE_PRIVATE)
        var searchList = getRecentListPref(context, key)

        searchList.limitRecentSearchList(searchString)

        val editor = prefs.edit()
        val jsonArray = JSONArray()
        for (i in 0 until searchList.size) {
            jsonArray.put(searchList[i])
        }
        if (searchList.isNotEmpty()) {
            editor.putString(key, jsonArray.toString())
        } else {
            editor.putString(key, null)
        }
        editor.apply()
    }

    private fun ArrayList<String>.limitRecentSearchList(searchString: String) {//최근검색어 리스트의 제약사항 적용하는 확장함수 1. 최대10개 2. 중복금지 3.최근 추가항목을 가장 뒤로 순서 변경
        if (this.contains(searchString)) {
            for (i in this.indices) {
                if (this[i] == searchString) {
                    this.removeAt(i)
                    this.add(searchString)
                }
            }
        } else {
            if (this.size < 10) {
                this.add(searchString)
            } else {
                this.removeAt(0)
                this.add(searchString)
            }
        }
    }

    fun getRecentListPref(context: Context, key: String): ArrayList<String> {
        val prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        val jsonString = prefs.getString(key, null)
        val list = ArrayList<String>()
        if (jsonString != null) {
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                //키값없으면 optString은 "" 리턴
                list.add(jsonArray.optString(i))
            }
        }
        return list
    }

//기능 테스트를 위해 저장소 초기화하는 함수선언
//    fun clearRecentListPref(context: Context, key: String) {
//        val pref: SharedPreferences = context.getSharedPreferences(key, MODE_PRIVATE)
//        val editor = pref.edit()
//        editor.clear()
//        editor.commit()//commit 호출시 동기 처리 apply 호출시 비동기 처리
//    }

}