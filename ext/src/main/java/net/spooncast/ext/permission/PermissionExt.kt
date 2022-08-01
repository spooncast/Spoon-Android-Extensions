package net.spooncast.ext.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Context.hasPermission(vararg permission: String): Boolean {
    return permission.firstOrNull { perm ->
        ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED
    } == null
}

fun Fragment.hasPermission(vararg permission: String): Boolean {
    return requireContext().hasPermission(*permission)
}

fun Activity.shouldShowRequestPermissionRationale(vararg permission: String): Boolean {
    return permission.firstOrNull { perm ->
        ActivityCompat.shouldShowRequestPermissionRationale(this, perm)
    } != null
}

fun Fragment.shouldShowRequestPermissionRationale(vararg permission: String): Boolean {
    return permission.firstOrNull { perm ->
        ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), perm)
    } != null
}
