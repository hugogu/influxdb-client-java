/*
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.influxdb.query.dsl.functions;

import java.util.ArrayList;
import java.util.List;

import com.influxdb.query.dsl.Flux;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.Suite;

/**
 * @author Jakub Bednar (10/10/2018 07:45)
 */
@Suite
class CumulativeSumFluxTest {

    @Test
    void cumulativeSum() {

        Flux flux = Flux
                .from("telegraf")
                .cumulativeSum()
                    .withColumns(new String[]{"_value"});

        Assertions.assertThat(flux.toString())
                .isEqualToIgnoringWhitespace("from(bucket:\"telegraf\") |> cumulativeSum(columns: [\"_value\"])");
    }

    @Test
    void cumulativeSumArray() {

        Flux flux = Flux
                .from("telegraf")
                .cumulativeSum(new String[]{"_value", "_value2"});

        Assertions.assertThat(flux.toString())
                .isEqualToIgnoringWhitespace("from(bucket:\"telegraf\") |> cumulativeSum(columns: [\"_value\", \"_value2\"])");
    }

    @Test
    void cumulativeSumCollection() {

        List<String> columns = new ArrayList<>();
        columns.add("_value");
        columns.add("_host");

        Flux flux = Flux
                .from("telegraf")
                .cumulativeSum(columns);

        Assertions.assertThat(flux.toString())
                .isEqualToIgnoringWhitespace("from(bucket:\"telegraf\") |> cumulativeSum(columns: [\"_value\", \"_host\"])");
    }
}
