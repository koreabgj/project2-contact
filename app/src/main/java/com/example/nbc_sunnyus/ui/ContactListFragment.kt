package com.example.nbc_sunnyus.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbc_sunnyus.databinding.FragmentContactListBinding
import com.example.nbc_sunnyus.model.UserInfo
import com.example.nbc_sunnyus.util.Constants

class ContactListFragment(private val userItems: MutableList<UserInfo>) : Fragment() {

    private lateinit var binding: FragmentContactListBinding

    lateinit var contactListAdapter: ContactListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        setUpListener() // 리스너를 모아두는곳 (클릭리스너 등)

    }

    private fun setUpListener() {
        contactListAdapter.setItemClickListener(object : ContactListAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val item = contactListAdapter.getItem(position)
                val intent = Intent(context, ContactDetailActivity::class.java)
                intent.putExtra(Constants.KEY_USER, item)
                startActivity(intent)
            }
        })
    }

    private fun setRecyclerView() {
        contactListAdapter = ContactListAdapter(userItems)
        binding.rvMain.adapter = contactListAdapter
        binding.rvMain.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}