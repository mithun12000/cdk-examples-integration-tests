/**
 * Copyright 2013 Cloudera Inc.
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
package com.cloudera.cdk.examples.data;

import org.junit.Before;
import org.junit.Test;

import static com.cloudera.cdk.examples.common.TestUtil.run;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ITDataset {

  @Before
  public void setUp() throws Exception {
    // delete datasets in case they already exist
    run(any(Integer.class), any(String.class), new DeleteProductDataset());
    run(any(Integer.class), any(String.class), new DeleteUserDataset());
  }

  @Test
  public void testProductDatasetPojo() throws Exception {
    run(new CreateProductDatasetPojo());
    run(containsString("Product{name=toaster, id=0}"), new ReadProductDatasetPojo());
    run(new DeleteProductDataset());
  }

  @Test
  public void testUserDatasetGeneric() throws Exception {
    run(new CreateUserDatasetGeneric());
    run(containsString("\"username\": \"user-0\""), new ReadUserDatasetGeneric());
    run(new DeleteUserDataset());
  }

  @Test
  public void testUserDatasetGenericPartitioned() throws Exception {
    run(new CreateUserDatasetGenericPartitioned());
    run(containsString("\"username\": \"user-0\""), new ReadUserDatasetGeneric());
    run(containsString("\"username\": \"user-8\""),
        new ReadUserDatasetGenericOnePartition());
    run(new DeleteUserDataset());
  }

  @Test
  public void testUserDatasetGenericParquet() throws Exception {
    run(new CreateUserDatasetGenericParquet());
    run(containsString("\"username\": \"user-0\""), new ReadUserDatasetGeneric());
    run(new DeleteUserDataset());
  }

  @Test
  public void testHCatalogUserDatasetGeneric() throws Exception {
    run(new CreateHCatalogUserDatasetGeneric());
    run(containsString("\"username\": \"user-0\""), new ReadHCatalogUserDatasetGeneric());
    run(new DeleteHCatalogUserDataset());
  }

}
