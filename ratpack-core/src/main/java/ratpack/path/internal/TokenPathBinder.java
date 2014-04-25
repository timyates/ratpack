/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ratpack.path.internal;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import ratpack.path.PathBinding;
import ratpack.path.PathBinder;
import ratpack.path.PathBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenPathBinder implements PathBinder {

  private final ImmutableList<String> tokenNames;
  private final Pattern regex;

  protected TokenPathBinder(ImmutableList<String> tokenNames, Pattern regex) {
    this.tokenNames = tokenNames;
    this.regex = regex;
  }

  public static PathBinder build(String path, boolean exact) {
    PathBuilder pathBuilder = new DefaultPathBuilder();

    Pattern placeholderPattern = Pattern.compile("((?:^|/):(\\w+)\\??:([^/])+)|((?:^|/)::([^/])+)|((?:^|/):(\\w+)\\??)");

    Pattern optTokPattern = Pattern.compile("/?:(\\w*)\\?:(.+)");
    Pattern tokPattern    = Pattern.compile("/?:(\\w*):(.+)");
    Pattern tok           = Pattern.compile("/?:(\\w*)");
    Pattern optTok        = Pattern.compile("/?:(\\w*)\\?");
    Pattern litPattern    = Pattern.compile("/?::(.+)");

    Matcher matchResult = placeholderPattern.matcher(path);

    boolean tokenized = false;
    int lastIndex = 0;

    while (matchResult.find()) {
      tokenized = true;
      int thisIndex = matchResult.start();
      if (thisIndex != lastIndex) {
        pathBuilder.literal(path.substring(lastIndex, thisIndex));
      }
      lastIndex = matchResult.end();
      String component = matchResult.group(0);
      Matcher m = optTokPattern.matcher(component);
      if (m.matches()) {
        pathBuilder.optionalTokenWithPattern(m.group(1), m.group(2));
        continue;
      }
      m = litPattern.matcher(component);
      if (m.matches()) {
        pathBuilder.literalPattern(m.group(1));
        continue;
      }
      m = tokPattern.matcher(component);
      if (m.matches()) {
        pathBuilder.tokenWithPattern(m.group(1), m.group(2));
        continue;
      }
      m = tok.matcher(component);
      if (m.matches()) {
        pathBuilder.token(m.group(1));
        continue;
      }
      m = optTok.matcher(component);
      if (m.matches()) {
        pathBuilder.optionalToken(m.group(1));
        continue;
      }
      throw new IllegalArgumentException(String.format("Cannot match path %s (%s)", path, component));
    }
    if (lastIndex < path.length()) {
      pathBuilder.literal(path.substring(lastIndex));
    }
    return pathBuilder.build(exact);
  }

  public PathBinding bind(String path, PathBinding parentBinding) {
    if (parentBinding != null) {
      path = parentBinding.getPastBinding();
    }
    Matcher matcher = regex.matcher(path);
    if (matcher.matches()) {
      MatchResult matchResult = matcher.toMatchResult();
      String boundPath = matchResult.group(1);
      ImmutableMap.Builder<String, String> paramsBuilder = ImmutableMap.builder();
      int i = 2;
      for (String name : tokenNames) {
        String value = matchResult.group(i++);
        if (value != null) {
          paramsBuilder.put(name, decodeURIComponent(value));
        }
      }

      return new DefaultPathBinding(path, boundPath, paramsBuilder.build(), parentBinding);
    } else {
      return null;
    }
  }

  private String decodeURIComponent(String s) {
    String str = null;
    try {
      str = URLDecoder.decode(s.replaceAll("\\+", "%2B"), "UTF-8");
    } catch (UnsupportedEncodingException ignored) {
      throw new IllegalStateException("UTF-8 decoder should always be available");
    }
    return str;
  }
}
