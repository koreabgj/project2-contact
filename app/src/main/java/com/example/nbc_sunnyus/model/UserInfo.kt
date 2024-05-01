package com.example.nbc_sunnyus.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val name: String, // 이름
    val phoneNumber: String, // 전화번호
    val email: String, // 이메일
    val team: String, // 소속 팀
    val image: Int // 프로필 이미지
) : Parcelable