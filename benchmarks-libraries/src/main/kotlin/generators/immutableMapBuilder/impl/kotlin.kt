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

package generators.immutableMapBuilder.impl

import generators.immutableMapBuilder.*

class MapBuilderKotlinBenchmark:
        MapBuilderGetBenchmark,
        MapBuilderIterateBenchmark,
        MapBuilderPutBenchmark,
        MapBuilderRemoveBenchmark,
        MapBuilderBenchmarkUtils
{
    override val packageName: String = "kotlin.builder"

    override fun mapBuilderType(K: String, V: String): String = "kotlinx.collections.immutable.PersistentMap.Builder<$K, $V>"

    override fun emptyOf(K: String, V: String): String = "kotlinx.collections.immutable.persistentHashMapOf<$K, $V>().builder()"
    override fun immutableOf(K: String, V: String): String = "kotlinx.collections.immutable.persistentHashMapOf<$K, $V>()"

    override val putOperation: String = "put"
    override val immutablePutOperation: String = "put"

    override val removeOperation: String = "remove"

    override val builderOperation: String = "builder"
}