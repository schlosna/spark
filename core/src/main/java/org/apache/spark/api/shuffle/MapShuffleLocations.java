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
package org.apache.spark.api.shuffle;

import org.apache.spark.annotation.Experimental;
import org.apache.spark.api.java.Optional;

import java.io.Serializable;
import java.util.List;

/**
 * Represents metadata about where shuffle blocks were written in a single map task.
 * <p>
 * This is optionally returned by shuffle writers. The inner shuffle locations may
 * be accessed by shuffle readers. Shuffle locations are only necessary when the
 * location of shuffle blocks needs to be managed by the driver; shuffle plugins
 * may choose to use an external database or other metadata management systems to
 * track the locations of shuffle blocks instead.
 */
@Experimental
public interface MapShuffleLocations extends Serializable {

  /**
   * Get the location for a given shuffle block written by this map task.
   */
  List<ShuffleLocation> getLocationsForBlock(int reduceId);

  /**
   * Mark a location for a block in this map output as unreachable, and thus partitions can no
   * longer be fetched from that location.
   * <p>
   * This is called by the scheduler when it detects that a block could not be fetched from the
   * file server located at this host and port.
   * <p>
   * This should return true if there exists a data loss from the removal of this shuffle
   * location. Otherwise, if all partitions can still be fetched from alternative locations,
   * this should return false.
   */
  boolean removeShuffleLocation(String host, Optional<Integer> port);
}