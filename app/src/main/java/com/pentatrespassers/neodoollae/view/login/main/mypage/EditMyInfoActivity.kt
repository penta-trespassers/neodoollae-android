package com.pentatrespassers.neodoollae.view.login.main.mypage

import android.Manifest
import android.R.attr
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.pentatrespassers.neodoollae.databinding.ActivityEditMyInfoBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class EditMyInfoActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityEditMyInfoBinding.inflate(layoutInflater)
    }

    val CAMERA = Manifest.permission.CAMERA
    val READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
    val WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
    var currentPhotoPath = ""
    val REQUEST_IMAGE_CAPTURE = 1
    val PIC_CROP = 2



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(bind) {

            profileEditButton.setOnClickListener {
                requestPermission()
                if (checkPermission()) {
                    dispatchTakePictureIntent()
                }
            }
            backButtonEditMyInfo.setOnClickListener {
                onBackPressed()
            }
            setContentView(root)
        }
    }

    // 카메라 권한 요청
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(READ_EXTERNAL_STORAGE, CAMERA, WRITE_EXTERNAL_STORAGE),
            REQUEST_IMAGE_CAPTURE
        )
    }

    // 카메라 권한 체크
    private fun checkPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    }

    // 권한요청 결과
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(
                "TAG",
                "Permission: " + permissions[0] + "was " + grantResults[0] + "카메라 허가 받음 예이^^"
            )
        } else {
            Log.d("TAG", "카메라 허가 못받음 ㅠ 젠장!!")
        }
    }

//    private fun performCrop(picUri: Uri) {
//        try {
//            val cropIntent = Intent("com.android.camera.action.CROP")
//            // indicate image type and Uri
//            cropIntent.setDataAndType(picUri, "image/*")
//            // set crop properties here
//            cropIntent.putExtra("crop", true)
//            // indicate aspect of desired crop
//            cropIntent.putExtra("aspectX", 1)
//            cropIntent.putExtra("aspectY", 1)
//            // indicate output X and Y
//            cropIntent.putExtra("outputX", 128)
//            cropIntent.putExtra("outputY", 128)
//            // retrieve data on return
//            cropIntent.putExtra("return-data", true)
//            // start the activity - we handle returning in onActivityResult
//            startActivityForResult(cropIntent, PIC_CROP)
//        } // respond to users whose devices do not support the crop action
//        catch (anfe: ActivityNotFoundException) {
//            // display an error message
//            val errorMessage = "Whoops - your device doesn't support the crop action!"
//            val toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT)
//            toast.show()
//        }
//    }
    // 카메라 열기
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            if (takePictureIntent.resolveActivity(this.packageManager) != null) {
                // 찍은 사진을 그림파일로 만들기
                val photoFile: File? =
                    try {
                        createImageFile()
                    } catch (ex: IOException) {
                        Log.d("TAG", "그림파일 만드는도중 에러생김")
                        null
                    }

                if (Build.VERSION.SDK_INT < 24) {
                    if (photoFile != null) {
                        val photoURI = Uri.fromFile(photoFile)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    }
                } else {
                    // 그림파일을 성공적으로 만들었다면 onActivityForResult로 보내기
                    photoFile?.also {
                        val photoURI: Uri = FileProvider.getUriForFile(
                            this,
                            "com.pentatrespassers.neodoollae.view.login.main.mypage.fileprovider",
                            it
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    }
                }

                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                intent.putExtra("crop", true)
                intent.action = Intent.ACTION_GET_CONTENT

                val chooserIntent = Intent.createChooser(intent, "전환할 앱을 선택해주세요.")
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(takePictureIntent))
                startActivityForResult(chooserIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }


    // 카메라로 촬영한 이미지를 파일로 저장해준다
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            1 -> {
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

                    // 갤러리에서 저장
//                    data?.data?.let { uri ->
//                        performCrop(uri)
//                    }
//                    var photoUri = data?.data
//                    bind.imageView.setImageURI(photoUri)

//                     크롭없이 그냥 저장하는 버전
                    data?.data?.let {
                            uri -> bind.imageView.setImageURI(uri)
                    }
                }

                // 카메라로부터 받은 데이터가 있을경우에만
                val file = File(currentPhotoPath)
                val selectedUri = Uri.fromFile(file)
                try {
                    selectedUri?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            val bitmap = MediaStore.Images.Media
                                .getBitmap(contentResolver, selectedUri)  //Deprecated
                            bitmap?.apply {
                                bind.imageView.setImageBitmap(bitmap)
                            }
                        } else {
                            val decode = ImageDecoder.createSource(contentResolver, selectedUri)
                            val bitmap = ImageDecoder.decodeBitmap(decode)
                            bitmap?.apply {
                                bind.imageView.setImageBitmap(bitmap)
                            }
                        }
                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }

//            2-> {
//                // get the returned data
//                val extras: Bundle = data?.extras!!
//                // get the cropped bitmap
//                val selectedBitmap = extras.getParcelable<Bitmap>("data")
//                bind.imageView.setImageBitmap(selectedBitmap)
//            }
        }
    }
}