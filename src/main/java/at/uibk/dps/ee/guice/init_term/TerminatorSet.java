package at.uibk.dps.ee.guice.init_term;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.inject.Inject;
import at.uibk.dps.ee.core.Terminator;
import at.uibk.dps.ee.guice.modules.EeModule;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;

/**
 * The {@link TerminatorSet} bundles the terminators used in an enactment to
 * offer a single interface for the termination process.
 * 
 * @author Fedor Smirnov
 *
 */
public class TerminatorSet implements Terminator {

  protected final Set<Terminator> terminators;

  /**
   * Injection constructor used with guice's set multibinder, see
   * {@link EeModule}.
   * 
   * @param terminators the set of terminators configured by guice modules
   */
  @Inject
  public TerminatorSet(final Set<Terminator> terminators) {
    this.terminators = terminators;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Future<String> terminate() {
    final Promise<String> resultPromise = Promise.promise();
    final List<Future> termFutures = terminators.stream(). //
        map(Terminator::terminate). //
        collect(Collectors.toList());
    CompositeFuture.join(termFutures).onComplete(asyncRes -> {
      if (asyncRes.succeeded()) {
        resultPromise.complete();
      } else {
        resultPromise.fail(new IllegalStateException("Termination Error"));
      }
    });
    return resultPromise.future();
  }

}
