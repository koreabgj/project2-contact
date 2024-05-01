package com.example.nbc_sunnyus.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nbc_sunnyus.R
import com.example.nbc_sunnyus.databinding.ActivityContactDetailBinding
import com.example.nbc_sunnyus.model.UserInfo
import com.example.nbc_sunnyus.util.Constants

class ContactDetailActivity : AppCompatActivity() {

    private val binding: ActivityContactDetailBinding by lazy {
        ActivityContactDetailBinding.inflate(layoutInflater)
    }

    private var userInfo: UserInfo? = null

    //콜백 인스턴스 생성
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contact_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpData()

        setUpListener()

        // 뒤로 가기 콜백
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setUpData() {
        // ContactListFragment로부터 받은 데이터에 접근
        userInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Constants.KEY_USER, UserInfo::class.java)
        } else {
            intent?.getParcelableExtra(Constants.KEY_USER)
        }

        // 받은 데이터를 이미지 뷰 및 텍스트 뷰에 설정
        userInfo?.let {
            binding.ivImage.setImageResource(it.image)
            binding.tvName.text = it.name
            binding.tvPhoneNumberData.text = it.phoneNumber
            binding.tvEmailData.text = it.email
            binding.tvTeamData.text = it.team
        }
    }

    private fun setUpListener() {

        // 플로팅 버튼 클릭하여 다이얼로그 띄우기
        binding.btnFloating.setOnClickListener {
            DialogAdd(this, this.layoutInflater,null).show()
        }

        // 뒤로 가기 버튼(화면)
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}