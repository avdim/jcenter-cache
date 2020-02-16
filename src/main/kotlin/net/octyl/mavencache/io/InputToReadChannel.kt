/*
 * Copyright (c) Octavia Togami <https://octyl.net>
 * Copyright (c) contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.octyl.mavencache.io

import io.ktor.utils.io.WriterJob
import io.ktor.utils.io.core.Input
import io.ktor.utils.io.core.readAvailable
import io.ktor.utils.io.writer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.nio.ByteBuffer

fun CoroutineScope.writerFrom(input: Input): WriterJob {
    return writer(Dispatchers.IO, autoFlush = true) {
        val buffer = ByteBuffer.allocate(8192)
        while (!input.endOfInput) {
            input.readAvailable(buffer)
            buffer.flip()
            channel.writeFully(buffer)
            buffer.clear()
        }
    }.also {
        it.invokeOnCompletion {
            input.close()
        }
    }
}