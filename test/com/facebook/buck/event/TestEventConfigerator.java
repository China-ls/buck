/*
 * Copyright 2013-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.facebook.buck.event;

import java.util.concurrent.TimeUnit;

public class TestEventConfigerator {
  private TestEventConfigerator() {}

  public static <T extends AbstractBuckEvent> T configureTestEvent(T event) {
    return configureTestEvent(event, 0L, 1L, 2L);
  }

  public static <T extends AbstractBuckEvent> T configureTestEvent(T event, BuckEventBus eventBus) {
    return configureTestEvent(event, eventBus.getClock().currentTimeMillis(),
        eventBus.getClock().nanoTime(),
        eventBus.getThreadIdSupplier().get());
  }

  public static <T extends AbstractBuckEvent> T configureTestEvent(T event,
      long timestamp,
      long nanotime,
      long threadid) {
    event.configure(timestamp, nanotime, threadid, BuckEventBusFactory.BUILD_ID_FOR_TEST);
    return event;
  }

  public static <T extends AbstractBuckEvent> T configureTestEventAtTime(T event,
      long time,
      TimeUnit timeUnit,
      long threadid) {
    event.configure(timeUnit.toMillis(time),
        timeUnit.toNanos(time),
        threadid,
        BuckEventBusFactory.BUILD_ID_FOR_TEST);
    return event;
  }
}
