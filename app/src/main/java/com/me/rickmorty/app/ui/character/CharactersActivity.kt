package com.me.rickmorty.app.ui.character

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.me.rickmorty.util.BaseToolbarActivity
import com.me.rickmorty.R
import com.me.rickmorty.adapter.AutoAdapter
import com.me.rickmorty.databinding.ActivityCharactersBinding
import com.me.rickmorty.domain.model.CharacterModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : BaseToolbarActivity() {

    private val viewModel: CharacterViewModel by viewModels()

    private val binding by lazy {
        ActivityCharactersBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@CharactersActivity
        }
    }

    private val adapter = AutoAdapter<CharacterModel> {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.rvCharacters.adapter = adapter
        binding.rvCharacters.layoutManager = LinearLayoutManager(this)

        title = getString(R.string.characters)

        showLoading()

        viewModel.getCharacters().observe(
            this@CharactersActivity,
            getResultObjectObserver({ data ->
                adapter.update(data)
                hideLoading()
            })
        )
    }

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, CharactersActivity::class.java)
        }
    }
}