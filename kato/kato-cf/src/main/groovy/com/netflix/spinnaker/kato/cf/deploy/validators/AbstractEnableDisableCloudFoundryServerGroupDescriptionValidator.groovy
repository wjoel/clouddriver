/*
 * Copyright 2015 Pivotal Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.kato.cf.deploy.validators

import com.netflix.spinnaker.clouddriver.security.AccountCredentialsProvider
import com.netflix.spinnaker.kato.cf.deploy.description.EnableDisableCloudFoundryServerGroupDescription
import com.netflix.spinnaker.kato.deploy.DescriptionValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.Errors

abstract class AbstractEnableDisableCloudFoundryServerGroupDescriptionValidator extends DescriptionValidator<EnableDisableCloudFoundryServerGroupDescription> {

  @Autowired
  AccountCredentialsProvider accountCredentialsProvider

  @Override
  void validate(List priorDescriptions, EnableDisableCloudFoundryServerGroupDescription description, Errors errors) {
    def helper = new StandardCfAttributeValidator("enableDisableCloudFoundryServerGroupDescription", errors)

    helper.validateCredentials(description.accountName, accountCredentialsProvider)
    helper.validateServerGroupName(description.serverGroupName)
    helper.validateZone(description.zone)
  }
}