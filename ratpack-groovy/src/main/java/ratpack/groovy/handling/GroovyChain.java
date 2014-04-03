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

package ratpack.groovy.handling;

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import ratpack.handling.Chain;
import ratpack.handling.Handler;
import ratpack.func.Action;

import java.util.regex.Pattern;
/**
 * A Groovy oriented handler chain builder DSL.
 * <p>
 * The methods specific to this subclass create {@link ratpack.handling.Handler} instances from closures and
 * add them to the underlying chain.
 * <p>
 * These methods are generally shortcuts for {@link #handler(ratpack.handling.Handler)} on this underlying chain.
 */
public interface GroovyChain extends Chain {

  /**
   * Adds the given {@code Closure} as a {@code Handler} to this {@code GroovyChain}.
   *
   * @param handler the {@code Closure} to add
   * @return this {@code GroovyChain}
   */
  GroovyChain handler(@DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  /**
   * Adds a {@code Handler} to this {@code GroovyChain} that delegates to the given {@code Closure} as a {@code Handler} if the
   * relative {@code path} matches the given {@code path} exactly.
   * <p>
   * See {@link GroovyChain#handler(String, ratpack.handling.Handler)} for more details.
   *
   * @param path the relative path to match exactly on
   * @param handler the handler to delegate to
   * @return this {@code GroovyChain}
   */
  GroovyChain handler(String path, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  GroovyChain handler(Pattern path, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain handler(Handler handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain prefix(String prefix, Handler handler);

  @Override
  GroovyChain prefix(Pattern prefix, Handler handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain prefix(String prefix, Action<? super Chain> action) throws Exception;

  @Override
  GroovyChain prefix(Pattern prefix, Action<? super Chain> action) throws Exception;

  /**
   * Creates a {@code List} of {@code Handler} from the given {@code Closure} and adds a {@code Handler} to
   * this {@code GroovyChain} that delegates to the {@code Handler} list if the relative path starts with the given
   * {@code prefix}.
   * <p>
   * See {@link Chain#prefix(String, ratpack.handling.Handler)} for more details.
   *
   * @param prefix the relative path to match on
   * @param chain the definition of the chain to delegate to
   * @return this {@code GroovyChain}
   * @throws Exception any exception thrown by the given closure
   */
  GroovyChain prefix(String prefix, @DelegatesTo(value = GroovyChain.class, strategy = Closure.DELEGATE_FIRST) Closure<?> chain) throws Exception;

  GroovyChain prefix(Pattern prefix, @DelegatesTo(value = GroovyChain.class, strategy = Closure.DELEGATE_FIRST) Closure<?> chain) throws Exception;

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain handler(String path, Handler handler);

  @Override
  GroovyChain handler(Pattern path, Handler handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain get(Handler handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain get(String path, Handler handler);

  @Override
  GroovyChain get(Pattern path, Handler handler);

  /**
   * Adds a {@code Handler} to this {@code GroovyChain} that delegates to the given {@code Closure} as a {@code Handler} if the
   * relative {@code path} matches the given {@code path} and the {@code request} {@code HTTPMethod} is {@code GET}.
   * <p>
   * See {@link GroovyChain#get(String, ratpack.handling.Handler)} for more details.
   *
   * @param path the relative path to match on
   * @param handler the handler to delegate to
   * @return this {@code GroovyChain}
   */
  GroovyChain get(String path, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  GroovyChain get(Pattern path, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  /**
   * Adds a {@code Handler} to this {@code GroovyChain} that delegates to the given {@code Closure} as a {@code Handler}
   * if the {@code request} {@code HTTPMethod} is {@code GET} and the {@code path} is at the current root.
   * <p>
   * See {@link GroovyChain#get(ratpack.handling.Handler)} for more details.
   *
   * @param handler the handler to delegate to
   * @return this {@code GroovyChain}
   */
  GroovyChain get(@DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain post(String path, Handler handler);

  @Override
  GroovyChain post(Pattern path, Handler handler);

  /**
   * Adds a {@code Handler} to this {@code GroovyChain} that delegates to the given {@code Closure} as a {@code Handler} if the
   * relative {@code path} matches the given {@code path} and the {@code request} {@code HTTPMethod} is {@code POST}.
   * <p>
   * See {@link GroovyChain#post(String, ratpack.handling.Handler)} for more details.
   *
   * @param path the relative path to match on
   * @param handler the handler to delegate to
   * @return this {@code GroovyChain}
   */
  GroovyChain post(String path, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  GroovyChain post(Pattern path, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain post(Handler handler);

  /**
   * Adds a {@code Handler} to this {@code GroovyChain} that delegates to the given {@code Closure} as a {@code Handler}
   * if the {@code request} {@code HTTPMethod} is {@code POST} and the {@code path} is at the current root.
   * <p>
   * See {@link GroovyChain#post(ratpack.handling.Handler)} for more details.
   *
   * @param handler the handler to delegate to
   * @return this {@code GroovyChain}
   */
  GroovyChain post(@DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain put(Handler handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain put(String path, Handler handler);

  @Override
  GroovyChain put(Pattern path, Handler handler);

  /**
   * Adds a {@code Handler} to this {@code GroovyChain} that delegates to the given {@code Closure} as a {@code Handler} if the
   * relative {@code path} matches the given {@code path} and the {@code request} {@code HTTPMethod} is {@code PUT}.
   * <p>
   * See {@link GroovyChain#put(String, ratpack.handling.Handler)} for more details.
   *
   * @param path the relative path to match on
   * @param handler the handler to delegate to
   * @return this {@code GroovyChain}
   */
  GroovyChain put(String path, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  GroovyChain put(Pattern path, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  /**
   * Adds a {@code Handler} to this {@code GroovyChain} that delegates to the given {@code Closure} as a {@code Handler}
   * if the {@code request} {@code HTTPMethod} is {@code PUT} and the {@code path} is at the current root.
   * <p>
   * See {@link GroovyChain#put(ratpack.handling.Handler)} for more details.
   *
   * @param handler the handler to delegate to
   * @return this {@code GroovyChain}
   */
  GroovyChain put(@DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain patch(Handler handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain patch(String path, Handler handler);

  @Override
  GroovyChain patch(Pattern path, Handler handler);

  /**
   * Adds a {@code Handler} to this {@code GroovyChain} that delegates to the given {@code Closure} as a {@code Handler} if the
   * relative {@code path} matches the given {@code path} and the {@code request} {@code HTTPMethod} is {@code PATCH}.
   * <p>
   * See {@link GroovyChain#put(String, ratpack.handling.Handler)} for more details.
   *
   * @param path the relative path to match on
   * @param handler the handler to delegate to
   * @return this {@code GroovyChain}
   */
  GroovyChain patch(String path, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  GroovyChain patch(Pattern path, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  /**
   * Adds a {@code Handler} to this {@code GroovyChain} that delegates to the given {@code Closure} as a {@code Handler}
   * if the {@code request} {@code HTTPMethod} is {@code PATCH} and the {@code path} is at the current root.
   * <p>
   * See {@link GroovyChain#put(ratpack.handling.Handler)} for more details.
   *
   * @param handler the handler to delegate to
   * @return this {@code GroovyChain}
   */
  GroovyChain patch(@DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain delete(Handler handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain delete(String path, Handler handler);

  @Override
  GroovyChain delete(Pattern path, Handler handler);

  /**
   * Adds a {@code Handler} to this {@code GroovyChain} that delegates to the given {@code Closure} as a {@code Handler} if the
   * relative {@code path} matches the given {@code path} and the {@code request} {@code HTTPMethod} is {@code DELETE}.
   * <p>
   * See {@link GroovyChain#delete(String, ratpack.handling.Handler)} for more details.
   *
   * @param path the relative path to match on
   * @param handler the handler to delegate to
   * @return this {@code GroovyChain}
   */
  GroovyChain delete(String path, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  GroovyChain delete(Pattern path, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  /**
   * Adds a {@code Handler} to this {@code GroovyChain} that delegates to the given {@code Closure} as a {@code Handler}
   * if the {@code request} {@code HTTPMethod} is {@code DELETE} and the {@code path} is at the current root.
   * <p>
   * See {@link GroovyChain#delete(ratpack.handling.Handler)} for more details.
   *
   * @param handler the handler to delegate to
   * @return this {@code GroovyChain}
   */
  GroovyChain delete(@DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain register(Object object, Handler handler);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain register(Object service, Action<? super Chain> action) throws Exception;

  /**
   * Creates a {@code List} of {@code Handler} from the given {@code Closure} and adds a {@code Handler} to this {@code GroovyChain}
   * that inserts the {@code Handler} list with the given {@code service} addition.
   * <p>
   * See {@link GroovyChain#register(Object, Handler)} for more details.
   *
   * @param service the object to add to the service for the handlers
   * @param handlers the handlers to register the service with
   * @return this {@code GroovyChain}
   * @throws Exception any exception thrown by the given closure
   */
  GroovyChain register(Object service, @DelegatesTo(value = GroovyChain.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handlers) throws Exception;

  /**
   * {@inheritDoc}
   */
  @Override
  <T> GroovyChain register(Class<? super T> type, T object, Handler handler);

  @Override
  <T> GroovyChain register(Class<? super T> type, T service, Action<? super Chain> action) throws Exception;

  /**
   * Creates a {@code List} of {@code Handler} from the given {@code Closure} and adds a {@code Handler} to this {@code GroovyChain} that
   * inserts the the {@code Handler} list with the given {@code service} addition.
   * <p>
   * See {@link GroovyChain#register(Class, Object, Handler)} for more details.
   *
   * @param type the {@code Type} by which to make the service object available
   * @param service the object to add to the service for the handlers
   * @param handlers the handlers to register the service with
   * @param <T> the concrete type of the service addition
   * @return this {@code GroovyChain}
   * @throws Exception any exception thrown by the given closure
   */
  <T> GroovyChain register(Class<? super T> type, T service, @DelegatesTo(value = GroovyChain.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handlers) throws Exception;

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain assets(String path, String... indexFiles);

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain fileSystem(String path, Handler handler);

  @Override
  GroovyChain fileSystem(String path, Action<? super Chain> action) throws Exception;

  /**
   * Creates a {@code List} of {@code Handler} from the given {@code Closure} and adds a {@code Handler} to this {@code GroovyChain} that
   * changes the {@link ratpack.file.FileSystemBinding} for the {@code Handler} list.
   * <p>
   * See {@link GroovyChain#fileSystem(String, Handler)} for more details.
   *
   * @param path the relative {@code path} to the new file system binding point
   * @param handlers the definition of the handler chain
   * @return this {@code GroovyChain}
   * @throws Exception any exception thrown by the given closure
   */
  GroovyChain fileSystem(String path, @DelegatesTo(value = GroovyChain.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handlers) throws Exception;

  /**
   * {@inheritDoc}
   */
  @Override
  GroovyChain header(String headerName, String headerValue, Handler handler);

  /**
   * Adds a {@code Handler} to this {@code GroovyChain} that delegates to the given {@code Closure} as a {@code Handler} if the {@code request}
   * has a {@code HTTPHeader} with the given name and a it's value matches the given value exactly.
   * <p>
   * See {@link GroovyChain#header(String, String, ratpack.handling.Handler)} for more details.
   *
   * @param headerName the name of the HTTP Header to match on
   * @param headerValue the value of the HTTP Header to match on
   * @param handler the handler to delegate to
   * @return this {@code GroovyChain}
   */
  GroovyChain header(String headerName, String headerValue, @DelegatesTo(value = GroovyContext.class, strategy = Closure.DELEGATE_FIRST) Closure<?> handler);
}
