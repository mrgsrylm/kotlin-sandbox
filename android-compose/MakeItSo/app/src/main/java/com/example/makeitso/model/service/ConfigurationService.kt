

package com.example.makeitso.model.service

interface ConfigurationService {
  suspend fun fetchConfiguration(): Boolean
  val isShowTaskEditButtonConfig: Boolean
}
