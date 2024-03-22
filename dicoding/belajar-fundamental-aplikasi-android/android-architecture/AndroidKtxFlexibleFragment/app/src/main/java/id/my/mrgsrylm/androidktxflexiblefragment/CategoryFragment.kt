package id.my.mrgsrylm.androidktxflexiblefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class CategoryFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnDetailCategory: Button = view.findViewById(R.id.btn_detail_category)
        btnDetailCategory.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_detail_category) {
            val detailCategoryFragment = DetailCategoryFragment()

            val mBundle = bundleOf(
                DetailCategoryFragment.EXTRA_NAME to "Lifestyle"
            )
            val description = "Kategori ini akan berisi produk-produk lifestyle"

            detailCategoryFragment.arguments = mBundle
            detailCategoryFragment.description = description

            /*
            Method addToBackStack akan menambahkan fragment ke backstack
            Behaviour dari back button akan cek fragment dari backstack,
            jika ada fragment di dalam backstack maka fragment yang akan di close / remove
            jika sudah tidak ada fragment di dalam backstack maka activity yang akan di close / finish
             */
            val fragmentManager = parentFragmentManager
            fragmentManager.commit {
                addToBackStack(null)
                replace(
                    R.id.frame_container,
                    detailCategoryFragment,
                    DetailCategoryFragment::class.java.simpleName
                )
            }
        }
    }
}