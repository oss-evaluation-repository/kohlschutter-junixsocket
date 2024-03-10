/*
 * junixsocket
 *
 * Copyright 2009-2024 Christian Kohlschütter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.newsclub.net.mysql;

/**
 * Code sharable between code in junixsocket-mysql.
 *
 * @author Christian Kohlschütter
 */
final class MysqlHelper {
  private MysqlHelper() {
  }

  /**
   * Returns the shorter timeout of two given timeouts (assuming 0 means "unlimited").
   *
   * @param a The first timeout.
   * @param b The second timeout.
   * @return The shorter timeout.
   */
  static int shorterTimeout(int a, int b) {
    if (a == 0 || (b != 0 && b < a)) {
      return b;
    } else {
      return a;
    }
  }
}
