package estomp.esporte.lukalinat.ui.chest

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import estomp.esporte.lukalinat.R
import estomp.esporte.lukalinat.databinding.FragmentChestBinding

class ChestFragment: Fragment() {
    private var _binding: FragmentChestBinding? = null
    private val binding get() = _binding!!
    private lateinit var chestViewModel: ChestViewModel
    private var strTaps = " taps to open."
    private var numberChest = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        chestViewModel =
            ViewModelProvider(this)[ChestViewModel::class.java]
        _binding = FragmentChestBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textToOpen.text = context?.let { chestViewModel.getTaps(it.applicationContext) }
        binding.imageChest.setOnClickListener { v -> onClick(v) }
        numberChest = chestViewModel.getNumberChest(requireContext().applicationContext)
    }

    override fun onPause() {
        super.onPause()
        context?.let { chestViewModel.saveTaps(binding.textToOpen.text
            .toString().substring(0, 3).replace(" ", "").toInt(), it.applicationContext) }
        context?.let { chestViewModel.saveNumberChest(numberChest, it.applicationContext) }
    }

    private fun onClick(view: View?) {
        var taps = binding.textToOpen.text
            .toString().substring(0, 3).replace(" ", "").toInt()
        taps--
        if (taps == 0) {
            YoYo.with(Techniques.Shake)
                .duration(500)
                .repeat(1)
                .playOn(view)
            showDialog()
        } else {
            YoYo.with(Techniques.Bounce)
                .duration(200)
                .repeat(1)
                .playOn(view)
            binding.textToOpen.text = "$taps   $strTaps"
        }
    }

    private fun showDialog() {
        numberChest++
        val view = layoutInflater.inflate(R.layout.dialog, null)
        val imageView = view.findViewById<ImageView>(R.id.dialog_image)
        imageView.setImageResource(chestViewModel.getRecords())
        AlertDialog.Builder(context)
            .setTitle("You have opened the chest!" +
                    " You won the card.")
            .setPositiveButton("Ok") { dialog, _, ->
                binding.textToOpen.text = "${10 * numberChest} $strTaps"
                dialog.dismiss()
            }
            .setView(view)
            .show()
    }
}