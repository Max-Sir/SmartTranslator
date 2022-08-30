package com.symaxd.qrcode.aquier.utils

import timber.log.Timber
import javax.inject.Inject

class TimberLoggerServiceImpl @Inject constructor(var tree: Timber.Tree) : LoggerService {


    override fun i(message: String) {
        Timber.plant(tree)
        Timber.i(message)
    }

    override fun e(message: String) {
        Timber.plant(tree)
        Timber.e(message)
    }

    override fun e(message: Throwable?) {
        Timber.plant(tree)
        Timber.e(message)
    }
}