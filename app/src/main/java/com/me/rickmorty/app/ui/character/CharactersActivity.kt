package com.me.rickmorty.app.ui.character

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.me.rickmorty.BaseActivity
import com.me.rickmorty.BaseToolbarActivity
import com.me.rickmorty.R
import com.me.rickmorty.databinding.ActivityCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : BaseToolbarActivity() {

    private val viewModel: CharacterViewModel by viewModels()

    private val binding by lazy {
        ActivityCharactersBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@CharactersActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        title = getString(R.string.characters)

        showLoading()

        viewModel.getCharacters().observe(
            this@CharactersActivity,
            getResultObjectObserver({ data ->
                data
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