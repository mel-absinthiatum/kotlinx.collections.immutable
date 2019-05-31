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

package generators.immutableMapBuilder

import generators.BenchmarkSourceGenerator
import java.io.PrintWriter

interface MapBuilderGetBenchmark {
    val packageName: String
    fun emptyOf(K: String, V: String): String
}

class MapBuilderGetBenchmarkGenerator(private val impl: MapBuilderGetBenchmark) : BenchmarkSourceGenerator() {
    override val benchmarkName: String = "Get"

    override fun getPackage(): String {
        return super.getPackage() + ".immutableMap." + impl.packageName
    }

    override val imports: Set<String> = super.imports + "org.openjdk.jmh.infra.Blackhole" + "benchmarks.*"

    override fun generateBody(out: PrintWriter) {
        out.println("""
open class Get {
    @Param("10000", "100000")
    var size: Int = 0

    @Param(ASCENDING_HASH_CODE, RANDOM_HASH_CODE, COLLISION_HASH_CODE, NON_EXISTING_HASH_CODE)
    var hashCodeType = ""

    @Param("0.0", "50.0")
    var immutablePercentage: Double = 0.0

    private var keys = listOf<IntWrapper>()
    private var builder = ${impl.emptyOf("IntWrapper", "String")}

    @Setup(Level.Trial)
    fun prepare() {
        keys = generateKeys(hashCodeType, size)
        builder = persistentMapBuilderPut(keys, immutablePercentage)

        if (hashCodeType == NON_EXISTING_HASH_CODE)
            keys = generateKeys(hashCodeType, size)
    }

    @Benchmark
    fun get(bh: Blackhole) {
        repeat(times = size) { index ->
            bh.consume(builder[keys[index]])
        }
    }
}
        """.trimIndent()
        )
    }
}