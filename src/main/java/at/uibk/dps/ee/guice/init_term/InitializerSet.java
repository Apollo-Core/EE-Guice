package at.uibk.dps.ee.guice.init_term;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.inject.Inject;
import at.uibk.dps.ee.core.Initializer;
import at.uibk.dps.ee.guice.modules.EeModule;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;

/**
 * The {@link InitializerSet} bundles the initializers used in an enactment to
 * offer a single interface for the initialization process.
 * 
 * @author Fedor Smirnov
 *
 */
public class InitializerSet implements Initializer {

  protected final Set<Initializer> initializers;

  /**
   * Injection constructor used with guice's set multibinder, see
   * {@link EeModule}.
   * 
   * @param initializers the set of initializers configured by guice modules
   */
  @Inject
  public InitializerSet(final Set<Initializer> initializers) {
    this.initializers = initializers;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Future<String> initialize() {
    final Promise<String> resultPromise = Promise.promise();
    final List<Future> initFutures = initializers.stream(). //
        map(Initializer::initialize). //
        collect(Collectors.toList());
    CompositeFuture.join(initFutures).onComplete(asyncRes -> {
      if (asyncRes.succeeded()) {
        resultPromise.complete();
      } else {
        resultPromise.fail(new IllegalStateException("Initialization Error"));
      }
    });
    return resultPromise.future();
  }
}
