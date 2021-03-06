/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.kafkarest.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import javax.validation.constraints.Min;

public class SchemaTopicProduceRecord extends SchemaProduceRecord
    implements TopicProduceRecord<JsonNode, JsonNode> {

  // When producing to a topic, a partition may be explicitly requested.
  @Min(0)
  protected Integer partition;

  public SchemaTopicProduceRecord(
      @JsonProperty("key") JsonNode key,
      @JsonProperty("value") JsonNode value,
      @JsonProperty("partition") Integer partition
  ) {
    super(key, value);
    this.partition = partition;
  }

  @Override
  public Integer partition() {
    return partition;
  }

  @Override
  @JsonProperty
  public Integer getPartition() {
    return partition;
  }

  @JsonProperty
  public void setPartition(Integer partition) {
    this.partition = partition;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    SchemaTopicProduceRecord that = (SchemaTopicProduceRecord) o;

    if (partition != null ? !partition.equals(that.partition) : that.partition != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (partition != null ? partition.hashCode() : 0);
    return result;
  }
}
