/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.sql.sources.v2;

import org.apache.spark.annotation.Experimental;

/**
 * Capabilities that can be provided by a {@link Table} implementation.
 * <p>
 * Tables use {@link Table#capabilities()} to return a set of capabilities. Each capability signals
 * to Spark that the table supports a feature identified by the capability. For example, returning
 * {@code BATCH_READ} allows Spark to read from the table using a batch scan.
 */
@Experimental
public enum TableCapability {
  /**
   * Signals that the table supports reads in batch execution mode.
   */
  BATCH_READ,

  /**
   * Signals that the table supports append writes in batch execution mode.
   * <p>
   * Tables that return this capability must support appending data and may also support additional
   * write modes, like {@link #TRUNCATE}, {@link #OVERWRITE_BY_FILTER}, and
   * {@link #OVERWRITE_DYNAMIC}.
   */
  BATCH_WRITE,

  /**
   * Signals that the table can be truncated in a write operation.
   * <p>
   * Truncating a table removes all existing rows.
   * <p>
   * See {@code org.apache.spark.sql.sources.v2.writer.SupportsTruncate}.
   */
  TRUNCATE,

  /**
   * Signals that the table can replace existing data that matches a filter with appended data in
   * a write operation.
   * <p>
   * See {@code org.apache.spark.sql.sources.v2.writer.SupportsOverwrite}.
   */
  OVERWRITE_BY_FILTER,

  /**
   * Signals that the table can dynamically replace existing data partitions with appended data in
   * a write operation.
   * <p>
   * See {@code org.apache.spark.sql.sources.v2.writer.SupportsDynamicOverwrite}.
   */
  OVERWRITE_DYNAMIC
}