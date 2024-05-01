package com.example.nbc_sunnyus.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_SETTLING
import com.example.nbc_sunnyus.R
import com.example.nbc_sunnyus.data.DummyData
import com.example.nbc_sunnyus.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(),UpdateAdapterListener {

    private lateinit var contactListFragment: ContactListFragment
    private lateinit var myPageFragment: MyPageFragment

    //tab 목록 리스트로 묶기
    private val tabTitleArray = arrayOf("연락처", "마이페이지")

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewPagerAdapter = ViewPager2Adapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViewPager()
        setupListener()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupViewPager() {

        contactListFragment = ContactListFragment(DummyData.dummyItems)
        myPageFragment = MyPageFragment()
        viewPagerAdapter.addFragment(contactListFragment)
        viewPagerAdapter.addFragment(myPageFragment)

        //Adapter 연결
        binding.apply {
            viewpager2Main.adapter = viewPagerAdapter
            TabLayoutMediator(tablayoutMain, viewpager2Main) { tab, position ->
                when (position) {
                    0 -> tab.icon =
                        ContextCompat.getDrawable(this@MainActivity, R.drawable.tab_contactcall)

                    1 -> tab.icon =
                        ContextCompat.getDrawable(this@MainActivity, R.drawable.tab_mypage)
                }
                tab.text = tabTitleArray[position]
                tab.view.setOnClickListener {
                    contactListFragment.contactListAdapter.notifyDataSetChanged()
                }
            }.attach()
        }
    }

    private fun setupListener() {

        binding.fabMain.setOnClickListener {
            DialogAdd(this, this.layoutInflater,this).show()
        }

        // ViewPage 스와이프 fragment adapter 갱신
        binding.viewpager2Main.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if(state == SCROLL_STATE_SETTLING){
                    contactListFragment.contactListAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    //리스타트 일때 갱신
    @SuppressLint("NotifyDataSetChanged")
    override fun onRestart() {
        super.onRestart()
        contactListFragment.contactListAdapter.notifyDataSetChanged()
    }

    //메인의 ContactListFragment일 때 Dialog 추가 후 갱신
    @SuppressLint("NotifyDataSetChanged")
    override fun update() {
        contactListFragment.contactListAdapter.notifyDataSetChanged()
    }
}
