package com.pentatrespassers.neodoollae.view.login.main

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentMyPageBinding
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.view.login.main.mypage.RoomBookInfoActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.RoomVisitTraceActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.UserBookInfoActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.UserVisitTraceActivity
import splitties.fragments.start
import splitties.permissions.requestPermission
import splitties.toast.toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MyPageFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentMyPageBinding

    /***/
    val CAMERA = Manifest.permission.CAMERA
//    val STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
    val CAMERA_CODE = 98
    val STORAGE_CODE = 99
    var currentPhotoPath = ""


    val REQUEST_IMAGE_CAPTURE = 1
    val REQUEST_GALLERY_TAKE = 2

    //갤러리 열기
//    private fun openGalleryForImage() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, REQUEST_GALLERY_TAKE)
//    }


    // 카메라 권한 요청
    private fun requestPermission() {
        ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(READ_EXTERNAL_STORAGE, CAMERA),
            REQUEST_IMAGE_CAPTURE)
    }

    // 카메라 권한 체크
    private fun checkPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(this.requireContext(), android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this.requireContext(),
            android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
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

    // 카메라 열기
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            if (takePictureIntent.resolveActivity(this.requireContext().packageManager) != null) {
                // 찍은 사진을 그림파일로 만들기
                val photoFile: File? =
                    try {
                        createImageFile()
                    } catch (ex: IOException) {
                        Log.d("TAG", "그림파일 만드는도중 에러생김")
                        null
                    }

                if (Build.VERSION.SDK_INT < 24) {
                    if(photoFile != null){
                        val photoURI = Uri.fromFile(photoFile)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)	//제거해주세요
                    }
                }
                else {
                    // 그림파일을 성공적으로 만들었다면 onActivityForResult로 보내기
                    photoFile?.also {
                        val photoURI: Uri = FileProvider.getUriForFile(
                            requireContext(),
                            "com.pentatrespassers.neodoollae.view.login.main.fileprovider",
                            it
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
                }


                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"

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
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
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

        when (requestCode){
            1 -> {
                if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){

                    data?.data?.let { uri ->
                        bind.myProfileImageView.setImageURI(uri)
                    }

                    // 카메라로부터 받은 데이터가 있을경우에만
                    val file = File(currentPhotoPath)
                    val selectedUri = Uri.fromFile(file)
                    try {
                        selectedUri?.let {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media
                                    .getBitmap(requireContext().contentResolver,
                                        selectedUri)  //Deprecated
                                bind.myProfileImageView.setImageBitmap(bitmap)
                            } else {
                                val decode = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    selectedUri)
                                val bitmap = ImageDecoder.decodeBitmap(decode)
                                bind.myProfileImageView.setImageBitmap(bitmap)
                            }
                        }
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        when (requestCode){
            2 -> {
                if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_GALLERY_TAKE){
                    bind.myProfileImageView.setImageURI(data?.data) // handle chosen image
                }
            }
        }
    }

//    if (Build.VERSION.SDK_INT < 24) {
//        if(photoFile != null){
//            val photoURI = Uri.fromFile(photoFile)
//            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//        }
//    }
//    else{
//        photoFile?.also {
//            val photoURI: Uri = FileProvider.getUriForFile(
//                this.requireContext(), "com.example.cameraonly.fileprovider", it
//            )
//            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMyPageBinding.inflate(inflater, container, false)
        with(bind) {


            myProfileImageView.setOnClickListener {
                // TODO: 프로필 사진 변경

                /** 카메라 */
//                openGalleryForImage()

                /** 사진 */

            }

            tempGalary.setOnClickListener {
//                GetAlbum()
                if (checkPermission()) {
                    dispatchTakePictureIntent()
                }
            }

            // TODO: 리뷰관리 & 방문 기록 activity 따로 만들어서 intent 전환하는 거 넣어야 함
            /*
            myRoomHistoryButton.setOnClickListener {
                start<RoomVisitTraceActivity>()
            }
            myHistoryButton.setOnClickListener {
                start<UserVisitTraceActivity>()
            }
            myRoomReservationButton.setOnClickListener {
                start<RoomBookInfoActivity>()
            }
            myReservationButton.setOnClickListener {
                start<UserBookInfoActivity>()
            } */


            return root
        }
    }

    companion object {
        fun newInstance() = MyPageFragment()
    }
//
//    override fun onHiddenChanged(hidden: Boolean) {
//        super.onHiddenChanged(hidden)
//        if (hidden) {
//            Authentication.refreshUser()
//        } else {
//            reloadInformation()
//        }
//    }


}