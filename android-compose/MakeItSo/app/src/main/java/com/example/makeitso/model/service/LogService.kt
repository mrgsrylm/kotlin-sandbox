

package com.example.makeitso.model.service

interface LogService {
  fun logNonFatalCrash(throwable: Throwable)
}
