/*
 * Copyright 2013-2017 (c) MuleSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.raml.emitter.plugins;

import org.raml.api.RamlEntity;
import org.raml.jaxrs.handlers.PojoToRamlExtensionFactory;
import org.raml.jaxrs.plugins.TypeHandler;
import org.raml.jaxrs.handlers.PojoToRamlClassParserFactory;
import org.raml.jaxrs.types.RamlType;
import org.raml.jaxrs.types.TypeRegistry;
import org.raml.pojotoraml.PojoToRaml;
import org.raml.pojotoraml.PojoToRamlBuilder;
import org.raml.pojotoraml.RamlAdjuster;
import org.raml.utilities.types.Cast;

/**
 * Created. There, you have it.
 */
public class RamlToPojoTypeHandler implements TypeHandler {

  @Override
  public String writeType(final TypeRegistry registry, RamlEntity type) {

    final Class cls = Cast.toClass(type.getType());

    RamlAdjuster adjuster = PojoToRamlExtensionFactory.createAdjusters(cls);

    final PojoToRaml pojoToRaml = PojoToRamlBuilder.create(new PojoToRamlClassParserFactory(), adjuster);

    String name = pojoToRaml.name(cls);
    registry.registerType(name, type);
    return name;
  }

}