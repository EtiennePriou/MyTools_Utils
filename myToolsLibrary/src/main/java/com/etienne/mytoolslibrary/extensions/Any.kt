package com.etienne.mytoolslibrary.extensions

// Used to force when statements on sealed classes and enums to be exhaustive
val <T> T.exhaustive: T get() = this

