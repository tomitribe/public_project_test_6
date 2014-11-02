/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tomitribe.swizzle.stream;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

import static org.tomitribe.swizzle.stream.StreamAsserts.assertFilter;

public class IncludeFilterInputStreamTest extends Assert {

    @Test
    public void large() throws Exception {
        final String slurp = StreamAsserts.getLargeHtml();

        System.out.println(slurp);
    }

    @Test
    public void defaultCaseSensitivity() throws Exception {

        assertFilter("one two three\n four fiveone two three\n four five", "", new Decorator() {
            @Override
            public InputStream decorate(InputStream inputStream) {
                return new IncludeFilterInputStream(inputStream, "ThRee", "fIvE");
            }
        });

    }

    @Test
    public void caseSensitivity() throws Exception {

        assertFilter("one two three\n four fiveone two three\n four five", "three\n four fivethree\n" +
                " four five", new Decorator() {
            @Override
            public InputStream decorate(InputStream inputStream) {
                return new IncludeFilterInputStream(inputStream, "ThRee", "fIvE", false);
            }
        });

    }

    @Test
    public void excludeDelimiters() throws Exception {

        assertFilter("one two three\n four fiveone two three\n four five", "\n four \n four ", new Decorator() {
            @Override
            public InputStream decorate(InputStream inputStream) {
                return new IncludeFilterInputStream(inputStream, "ThRee", "fIvE", false, false);
            }
        });

    }

    @Test
    public void beginNotFound() throws Exception {

        assertFilter("one two three\n four fiveone two three\n four five", "", new Decorator() {
            @Override
            public InputStream decorate(InputStream inputStream) {
                return new IncludeFilterInputStream(inputStream, "six", "seven");
            }
        });

    }


    @Test
    public void endNotFound() throws Exception {

        assertFilter("one two three\n four fiveone two three\n four five", "four fiveone two three\n four five", new Decorator() {
            @Override
            public InputStream decorate(InputStream inputStream) {
                return new IncludeFilterInputStream(inputStream, "four", "seven");
            }
        });

    }


    @Test
    public void test() throws Exception {

        assertFilter("<table>\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                      <td class=\"v-table-header-cell\" style=\"width: 65px;\">\n" +
                "                        <div class=\"v-table-resizer\"></div>\n" +
                "                        <div class=\"v-table-sort-indicator\"></div>\n" +
                "                        <div class=\"v-table-caption-container v-table-caption-container-align-left\" style=\"width: 49px;\">status</div>\n" +
                "                      </td>\n" +
                "                      <td class=\"v-table-header-cell\" style=\"width: 68px;\">\n" +
                "                        <div class=\"v-table-resizer\"></div>\n" +
                "                        <div class=\"v-table-sort-indicator\"></div>\n" +
                "                        <div class=\"v-table-caption-container v-table-caption-container-align-left\" style=\"width: 52px;\">secure</div>\n" +
                "                      </td>\n" +
                "                      <td class=\"v-table-header-cell\" style=\"width: 56px;\">\n" +
                "                        <div class=\"v-table-resizer\"></div>\n" +
                "                        <div class=\"v-table-sort-indicator\"></div>\n" +
                "                        <div class=\"v-table-caption-container v-table-caption-container-align-left\" style=\"width: 40px;\">verb</div>\n" +
                "                      </td>\n" +
                "                      <td class=\"v-table-header-cell\" style=\"width: 326px;\">\n" +
                "                        <div class=\"v-table-resizer\"></div>\n" +
                "                        <div class=\"v-table-sort-indicator\"></div>\n" +
                "                        <div class=\"v-table-caption-container v-table-caption-container-align-left\" style=\"width: 310px;\">path</div>\n" +
                "                      </td>\n" +
                "                      <td class=\"v-table-header-cell\" style=\"width: 587px;\">\n" +
                "                        <div class=\"v-table-resizer\"></div>\n" +
                "                        <div class=\"v-table-sort-indicator\"></div>\n" +
                "                        <div class=\"v-table-caption-container v-table-caption-container-align-left\" style=\"width: 571px;\">summary</div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>", "<td class=\"v-table-header-cell\" style=\"width: 65px;\">\n" +
                "                        <div class=\"v-table-resizer\"></div>\n" +
                "                        <div class=\"v-table-sort-indicator\"></div>\n" +
                "                        <div class=\"v-table-caption-container v-table-caption-container-align-left\" style=\"width: 49px;\">status</div>\n" +
                "                      </td><td class=\"v-table-header-cell\" style=\"width: 68px;\">\n" +
                "                        <div class=\"v-table-resizer\"></div>\n" +
                "                        <div class=\"v-table-sort-indicator\"></div>\n" +
                "                        <div class=\"v-table-caption-container v-table-caption-container-align-left\" style=\"width: 52px;\">secure</div>\n" +
                "                      </td><td class=\"v-table-header-cell\" style=\"width: 56px;\">\n" +
                "                        <div class=\"v-table-resizer\"></div>\n" +
                "                        <div class=\"v-table-sort-indicator\"></div>\n" +
                "                        <div class=\"v-table-caption-container v-table-caption-container-align-left\" style=\"width: 40px;\">verb</div>\n" +
                "                      </td><td class=\"v-table-header-cell\" style=\"width: 326px;\">\n" +
                "                        <div class=\"v-table-resizer\"></div>\n" +
                "                        <div class=\"v-table-sort-indicator\"></div>\n" +
                "                        <div class=\"v-table-caption-container v-table-caption-container-align-left\" style=\"width: 310px;\">path</div>\n" +
                "                      </td><td class=\"v-table-header-cell\" style=\"width: 587px;\">\n" +
                "                        <div class=\"v-table-resizer\"></div>\n" +
                "                        <div class=\"v-table-sort-indicator\"></div>\n" +
                "                        <div class=\"v-table-caption-container v-table-caption-container-align-left\" style=\"width: 571px;\">summary</div>\n" +
                "                      </td>", new Decorator() {
            @Override
            public InputStream decorate(InputStream inputStream) {
                return new IncludeFilterInputStream(inputStream, "<td", "</td>");
            }
        });

    }
}
