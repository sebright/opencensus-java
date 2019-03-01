/*
 * Copyright 2018, OpenCensus Authors
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

package io.opencensus.contrib.exemplar.util;

import io.opencensus.stats.AggregationData.DistributionData.Exemplar;
import io.opencensus.stats.MeasureMap;
import io.opencensus.trace.SpanContext;
import javax.annotation.Nullable;

/**
 * Utils for recording {@link Exemplar}s for OpenCensus stats.
 *
 * @since 0.16
 */
public final class ExemplarUtils {

  /**
   * Key for {@link SpanContext} in the contextual information of an {@link Exemplar}.
   *
   * @since 0.20
   */
  public static final String ATTACHMENT_KEY_SPAN_CONTEXT = "SpanContext";

  /**
   * Puts a {@link SpanContext} into the attachments of the given {@link MeasureMap}.
   *
   * @param measureMap the {@code MeasureMap}.
   * @param spanContext the {@code SpanContext} to be put as attachments.
   * @since 0.16
   */
  public static void putSpanContextAttachments(MeasureMap measureMap, SpanContext spanContext) {
    checkNotNull(measureMap, "measureMap");
    checkNotNull(spanContext, "spanContext");
    measureMap.putAttachment(
        ATTACHMENT_KEY_SPAN_CONTEXT, AttachmentValueSpanContext.create(spanContext));
  }

  // TODO: reuse this method from shared artifact.
  private static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
    if (reference == null) {
      throw new NullPointerException(String.valueOf(errorMessage));
    }
    return reference;
  }

  private ExemplarUtils() {}
}
