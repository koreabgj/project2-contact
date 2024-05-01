package com.example.nbc_sunnyus.ui

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.example.nbc_sunnyus.R
import com.example.nbc_sunnyus.data.DummyData
import com.example.nbc_sunnyus.databinding.DialogAddBinding
import com.example.nbc_sunnyus.model.UserInfo


class DialogAdd(
    private val context: Context,
    private val layoutInflater: LayoutInflater,
    private val listener: UpdateAdapterListener?
) {

    private lateinit var binding: DialogAddBinding
    private lateinit var dialog: AlertDialog

    private fun userInfoListener() {

        // 저장 버튼 클릭 설정
        binding.btnSave.setOnClickListener {
            saveContact()
            listener?.update()
            dialog.dismiss()

        }

        // 취소 버튼 클릭 설정
        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    // 데이터 저장
    private fun saveContact() {
        val name = binding.etName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        val team = binding.etTeam.text.toString()

        DummyData.dummyItems.add(
            UserInfo(
                name, phoneNumber, email, team, R.drawable.ic_launcher_foreground
            )
        )

        // 저장 후 메시지 표시
        Toast.makeText(context, "연락처 추가가 완료되었습니다", Toast.LENGTH_SHORT).show()
    }

    fun show() {
        binding = DialogAddBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(context)
            .setView(binding.root)

        // 기본 버튼을 숨김
        builder.setPositiveButton("", null)
        builder.setNegativeButton("", null)

        dialog = builder.create()
        dialog.show()

        userInfoListener()
    }
}