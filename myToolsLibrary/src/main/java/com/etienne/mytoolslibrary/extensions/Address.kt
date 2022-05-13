@file:JvmName("AddressUtil")

package com.etienne.mytoolslibrary.extensions

import android.location.Address


fun Address.fullStreet() =
        if (subThoroughfare != null && thoroughfare != null) {
            "$subThoroughfare $thoroughfare"
        } else if (thoroughfare != null) {
            thoroughfare
        } else if (featureName != null) {
            featureName
        } else {
            null
        }