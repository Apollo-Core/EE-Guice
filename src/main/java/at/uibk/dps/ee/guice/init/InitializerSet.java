package at.uibk.dps.ee.guice.init;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.inject.Inject;
import at.uibk.dps.ee.core.Initializer;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class InitializerSet implements Initializer {

  protected final Set<Initializer> initializers;

  @Inject
  public InitializerSet(Set<Initializer> initializers) {
    this.initializers = initializers;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Future<String> initialize() {
    Promise<String> resultPromise = Promise.promise();
    List<Future> initFutures = initializers.stream(). //
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
