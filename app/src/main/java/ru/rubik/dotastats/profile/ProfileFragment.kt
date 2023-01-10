package ru.rubik.dotastats.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentProfileBinding
import ru.rubik.dotastats.login.domain.entities.User
import ru.rubik.dotastats.shared.serializable

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = requireNotNull(requireArguments().serializable<User>(USER_KEY))

        fillViewsByPreviousContent(user)
    }

    private fun fillViewsByPreviousContent(user: User) {
        binding.loginText.text = user.login
        binding.nameText.text = user.name
    }

    companion object {

        private const val USER_KEY = "USER_KEY"

        @JvmStatic
        fun newInstance(user: User) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER_KEY, user)
                }
            }
    }
}