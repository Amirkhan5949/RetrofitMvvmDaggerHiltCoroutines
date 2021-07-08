package com.codeinger.postapp.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.codeinger.postapp.databinding.ActivityImgUplodaBinding
import com.myhexaville.smartimagepicker.ImagePicker


class ImgUplodaActivity : AppCompatActivity() {
    private var  PERMISSION_ALL : Int =1234
    private lateinit var imagePicker : ImagePicker
    private lateinit var binding: ActivityImgUplodaBinding

    companion object {
        private val REQUEST_SELECT_IMAGE_IN_ALBUM = 1

    }
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding= ActivityImgUplodaBinding.inflate(layoutInflater)
        setContentView(binding.root)


         binding.img.setOnClickListener {
             getimg()

         }




//         val PERMISSIONS = arrayOf(
//             Manifest.permission.READ_EXTERNAL_STORAGE,
//             Manifest.permission.WRITE_EXTERNAL_STORAGE,
//              Manifest.permission.CAMERA
//         )


//         imagePicker =  ImagePicker (this,null,{
//                 imageUri -> {
//             binding.img.setImageURI(imageUri)
//         }
//         })


//         binding.img.setOnClickListener {
//
//             if(!hasPermissions(this, *PERMISSIONS)){
//                 ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL)
//
//             }else{
//                 imagePicker.choosePicture(true /*show camera intents*/)
//             }
//
//         }

    }

    private fun getimg() {

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(this?.packageManager!!) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM) {
            var imageUri = data?.data
             binding.img.setImageURI(imageUri)
        }

    }



//    fun hasPermissions(context: Context?, vararg permissions: String?): Boolean {
//        if (context != null && permissions != null) {
//            for (permission in permissions) {
//                if (ActivityCompat.checkSelfPermission(
//                        context,
//                        permission!!
//                    ) != PackageManager.PERMISSION_GRANTED
//                ) {
//                    return false
//                }
//            }
//        }
//        return true
//    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        imagePicker.handleActivityResult(resultCode, requestCode, data)
//
//
//    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissionsList: Array<String?>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissionsList, grantResults)
//        imagePicker.handlePermission(requestCode, grantResults)
//        when (requestCode) {
//            PERMISSION_ALL -> {
//                if (grantResults.size > 0) {
//                    var flag = true
//                    var i = 0
//                    while (i < permissionsList.size) {
//                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
//                            flag = false
//                        }
//                        i++
//                    }
//                    if (flag == true) {
//                        imagePicker.choosePicture(true /*show camera intents*/)
//                    }
//                    // Show permissionsDenied
//                }
//                return
//            }
//        }
//    }

}