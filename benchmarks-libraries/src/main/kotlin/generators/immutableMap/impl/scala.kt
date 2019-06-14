/*
 * Copyright 2016-2019 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package generators.immutableMap.impl

import generators.immutableMap.*

object ScalaMapImplementation: MapImplementation {
    override val packageName: String
            = "scala"

    override fun type(): String
            = "scala.collection.immutable.HashMap<$mapKeyType, $mapValueType>"
    override fun empty(): String
            = "scala.collection.immutable.`HashMap\$`.`MODULE\$`.empty<$mapKeyType, $mapValueType>()"

    override fun keysOperation(map: String): String
            = "$map.keys()"
    override fun valuesOperation(map: String): String
            = "$map.values()"

    override fun getOperation(map: String, key: String): String
            = "$map.get($key)"
    override fun putOperation(map: String, key: String, value: String): String
            = "$map.updated($key, $value)"
    override fun removeOperation(map: String, key: String): String
            = "$map.removed($key)"
}