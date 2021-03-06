/*
 *
 *  Copyright 2015-2016 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package springfox.documentation.spring.web.plugins

import com.google.common.collect.ImmutableSet
import spock.lang.Specification
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.schema.AlternateTypeProvider
import springfox.documentation.spi.schema.GenericTypeNamingStrategy
import springfox.documentation.spi.service.contexts.OperationModelContextsBuilder
import springfox.documentation.spring.web.dummy.models.Example

class OperationModelsBuilderSpec extends Specification {
  OperationModelContextsBuilder sut =
      new OperationModelContextsBuilder(
          DocumentationType.SWAGGER_12,
          Mock(AlternateTypeProvider),
          Mock(GenericTypeNamingStrategy),
          ImmutableSet.builder().build())

  def "Manages a unique set of model contexts" () {
    given:
      sut.addInputParam(Example)
    when:
      def models = sut.build()
    then:
      models.size() == 1

    and:
      sut.addInputParam(Example).build().size() == 1
      sut.addReturn(Example).build().size() == 2
  }

}
