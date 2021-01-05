package xyz.teamgravity.runningtracker.fragment.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import xyz.teamgravity.runningtracker.R
import xyz.teamgravity.runningtracker.databinding.FragmentSettingsBinding
import xyz.teamgravity.runningtracker.helper.constants.Preferences
import xyz.teamgravity.runningtracker.helper.util.Helper
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var shp: SharedPreferences

    @Inject
    lateinit var name: String

    @set:Inject
    var weight = 0F


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            updateUI()
            button(it)
        }
    }

    private fun updateUI() {
        binding.apply {
            nameField.editText?.setText(name)
            weightField.editText?.setText(weight.toString())
        }
    }

    private fun button(activity: FragmentActivity) {
        onSave(activity)
    }

    // save button
    private fun onSave(activity: FragmentActivity) {
        binding.apply {
            saveB.setOnClickListener {
                nameField.error = null
                weightField.error = null

                nameField.clearFocus()
                weightField.clearFocus()

                val name = nameField.editText?.text.toString().trim()
                val weight = weightField.editText?.text.toString().trim()

                if (name.isEmpty()) {
                    nameField.error = resources.getString(R.string.error_field)
                    nameField.requestFocus()
                    return@setOnClickListener
                }

                if (weight.isEmpty()) {
                    weightField.error = resources.getString(R.string.error_field)
                    weightField.requestFocus()
                    return@setOnClickListener
                }

                shp.edit()
                    .putString(Preferences.USER_NAME, name)
                    .putFloat(Preferences.USER_WEIGHT, weight.toFloat())
                    .apply()

                activity.findViewById<MaterialTextView>(R.id.toolbar_t).text =
                    Helper.addTwoString(resources.getString(R.string.lets_go), name)
                Snackbar.make(parentLayout, R.string.successfully_saved, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}